package model;

public class ValuablesMiner implements Runnable
{
  private Valuable valuable;
  private Deposit deposit;
  private Mine mine;

  public ValuablesMiner(Deposit deposit)
  {
    this.deposit = deposit;

    mine = new Mine();
  }

  @Override public void run()
  {
    while (true)
    {
      mineValuable();
      depositValuable();

      try
      {
        Thread.sleep((long)(Math.random() * 500 + 100));
      }
      catch (InterruptedException e)
      {
        Thread.currentThread().interrupt();
        break;
      }
    }
  }

  private void mineValuable()
  {
    valuable = mine.mineValuable();
  }

  private void depositValuable()
  {
    deposit.put(valuable);
    valuable = null;
  }
}
