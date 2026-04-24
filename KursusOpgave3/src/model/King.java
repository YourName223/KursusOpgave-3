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
    collectFromTreasureRoom();
  }

  private void collectFromTreasureRoom()
  {
    int random = (int)(Math.random() * 100 + 50);

    ReadWriteList readWriteList = lock.acquireWrite();

    int totalValue = 0;

    while (true)
    {
      Valuable valuable = readWriteList.read();

      if (valuable == null) break;

      valuableList.add(valuable);
      totalValue += valuable.getValue();

      try { Thread.sleep(100); }
      catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    if (totalValue >= random)
    {
      // Målet nået - hold fest, smid værdigenstandene væk
      valuableList.clear();
    }
    else
    {
      // Ikke nok - læg dem tilbage
      for (Valuable valuable : valuableList)
      {
        readWriteList.write(valuable);
      }
      valuableList.clear();
    }

    lock.releaseWrite();
  }
}
