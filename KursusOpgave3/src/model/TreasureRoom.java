package model;

import java.util.ArrayList;

public class TreasureRoom implements ReadWriteList
{
  private ArrayList<Valuable> list;
  private int secondsToRead;
  private int secondsToWrite;

  public TreasureRoom(int secondsToRead, int secondsToWrite)
  {
    list = new ArrayList<>();
    this.secondsToRead = secondsToRead;
    this.secondsToWrite = secondsToWrite;
  }

  public ArrayList<Valuable> read()
  {
    try { Thread.sleep(secondsToRead * 1000L); }
    catch (InterruptedException e) { e.printStackTrace(); }

    return list;
  }

  public void add(Valuable valuable)
  {
    try { Thread.sleep(secondsToWrite * 1000L); }
    catch (InterruptedException e) { e.printStackTrace(); }

    list.add(valuable);
    if (list.size() > 10000)
    {
      list.remove(0);
    }
  }

  public void remove(Valuable valuable)
  {
    try { Thread.sleep(secondsToWrite * 1000L); }
    catch (InterruptedException e) { e.printStackTrace(); }

    list.add(valuable);
    if (list.size() > 10000)
    {
      list.remove(0);
    }
  }
}