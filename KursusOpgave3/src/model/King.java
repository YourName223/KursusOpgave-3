package model;

import java.util.ArrayList;

public class King implements Runnable
{
  private ArrayList<Valuable> valuableList;
  private Door lock;

  public King(Door lock)
  {
    valuableList = new ArrayList<>();
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
    int random = (int)(Math.random() * 100 + 50);

    collectFromTreasureRoom(random);
  }

  private void collectFromTreasureRoom(int random)
  {
    ReadWriteList readWriteList = lock.acquireWrite();

    ArrayList<Valuable> treasureRoomList = readWriteList.read();

    int totalValue = 0;

    while (totalValue < random)
    {
      if (treasureRoomList == null)
      {
        if (!valuableList.isEmpty())
        {
          for (Valuable valuable1 : valuableList)
          {
            readWriteList.add(valuable1);
            valuableList.clear();
          }
        }

        lock.releaseWrite();
        break;
      }

      Valuable valuable = treasureRoomList.getFirst();

      valuableList.add(valuable);
      treasureRoomList.remove(valuable);
      totalValue += valuable.getValue();
    }
    valuableList.clear();
    lock.releaseWrite();
  }
}
