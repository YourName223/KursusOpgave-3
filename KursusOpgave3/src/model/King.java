package model;

import java.util.ArrayList;

public class King implements Runnable
{
  private ArrayList<Valuable> valuableList;
  private Door lock;

  public King(Door lock)
  {
    this.lock = lock;
  }

  @Override public void run()
  {
    while (true)
    {
      planFest();

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

  private void planFest()
  {
    int random = (int)(Math.random() * 300 + 50);

    collectFromTreasureRoom(random);
  }

  private void collectFromTreasureRoom(int value)
  {
    valuableList = new ArrayList<>();

    ReadWriteList readWriteList = lock.acquireWrite();

    ArrayList<Valuable> treasureRoomList = readWriteList.read();

    int totalValue = 0;

    while (totalValue < value && !treasureRoomList.isEmpty())
    {
      Valuable valuable = treasureRoomList.removeFirst();

      valuableList.add(valuable);
      totalValue += valuable.getValue();
    }

    if(totalValue >= value)
    {
      Log.getInstance().addLog("LETS GO PARTY!");
    }
    else
    {
      for (Valuable v : valuableList)
      {
        readWriteList.add(v);
      }
    }

    valuableList.clear();
    lock.releaseWrite();
  }
}
