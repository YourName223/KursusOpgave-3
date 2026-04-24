package model;

import java.util.ArrayList;

public class ValuableTransporter implements Runnable
{
  private ArrayList<Valuable> valuableList;
  private Door lock;
  private Deposit deposit;

  public ValuableTransporter(Door lock, Deposit deposit)
  {
    this.lock = lock;
    this.deposit = deposit;
  }

  @Override public void run()
  {
    valuableList = new ArrayList<>();

    while (true)
    {
      collectFromDeposit();
      deliverToTreasureRoom();

      valuableList.clear();

      try
      {
        Thread.sleep((long) (Math.random() * 1000 + 500));
      }
      catch (InterruptedException e)
      {
        Thread.currentThread().interrupt();
        break;
      }
    }
  }

  private void collectFromDeposit()
  {
    int random = (int)(Math.random()*150+50);
    int valuableListValue = 0;
    while (valuableListValue < random)
    {
      if(!deposit.isEmpty())
      {
        valuableList.add(deposit.take());
        valuableListValue += valuableList.getLast().getValue();
      }
      else
      {
        try {
          Thread.sleep(50); // avoid busy spin
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          return;
        }
      }
    }
  }

  private void deliverToTreasureRoom()
  {
    ReadWriteList readWriteList = lock.acquireWrite();

    for(Valuable valuable : valuableList)
    {
      Log.getInstance().addLog("Added : " + valuable + " in treasure room");
      readWriteList.add(valuable);
    }

    lock.releaseWrite();
  }
}
