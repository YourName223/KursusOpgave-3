package model;

public interface Deposit
{
  public void put(Valuable element);

  public Valuable take();

  public int size();
}
