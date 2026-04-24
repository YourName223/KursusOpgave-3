package model;
import utility.collection.ArrayList;
import utility.collection.ListADT;

import java.util.Random;

public class BlockingQueue implements Deposit
{
  private ListADT<Valuable> list;

  public BlockingQueue()
  {
    list = new ArrayList<>();
  }

  @Override public String toString()
  {
    return super.toString();
  }

  @Override public void put(Valuable element)
  {
    try
    {
      Thread.sleep((long)(Math.random() * 500 + 1000));
    }
    catch (InterruptedException e)
    {
      Thread.currentThread().interrupt();
    }
    list.add(element);
  }

  @Override public Valuable take()
  {
    try
    {
      Thread.sleep((long)(Math.random() * 500 + 1000));
    }
    catch (InterruptedException e)
    {
      Thread.currentThread().interrupt();
    }

    Random rand = new Random();
    int randomValuable = rand.nextInt(list.size());

    return list.get(randomValuable);
  }

  @Override public int size()
  {
    return list.size();
  }
}
