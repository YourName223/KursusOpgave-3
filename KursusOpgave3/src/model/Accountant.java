package model;

public class Accountant implements Runnable
{
  private Door lock;

  public Accountant(Door lock)
  {
    this.lock = lock;
  }

  @Override public void run()
  {
    while (true)
    {
      countTreasureRoomValue();

      try
      {
        Thread.sleep((long)(Math.random() * 1000 + 500));
      }
      catch (InterruptedException e)
      {
        Thread.currentThread().interrupt();
        break;
      }
    }
  }

  private void countTreasureRoomValue()
  {
    ReadList readList = lock.acquireRead();

    int totalValue = 0;

    for (Valuable valuable : readList.read())
    {
      totalValue += valuable.getValue();
    }

    Log.getInstance().addLog("Total value in treasure room: " + totalValue);

    lock.releaseRead();
  }
}