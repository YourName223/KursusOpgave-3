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
