package model;

import java.util.ArrayList;

public class ReadProxy implements ReadList
{
  private TreasureRoom list;
  private Guardsman access;

  public ReadProxy(TreasureRoom list, Guardsman access)
  {
    this.list = list;
    this.access = access;
  }

  @Override public ArrayList<Valuable> read()
  {
    if(!access.hasReadAccess(Thread.currentThread()))
    {
      throw new SecurityException("No read access");
    }
    return list.read();
  }
}