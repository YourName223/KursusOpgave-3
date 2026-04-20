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

  }

  private void countTreasureRoomValue()
  {
    ReadList readList = lock.acquireWrite();
    Log log = Log.getInstance();
    log.addLog(readList.toString());
  }
}
