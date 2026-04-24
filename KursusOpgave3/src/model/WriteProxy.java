package model;

import java.util.ArrayList;

public class WriteProxy implements ReadWriteList
{
  private TreasureRoom list;
  private Guardsman access;

  public WriteProxy(TreasureRoom list, Guardsman access)
  {
    this.list = list;
    this.access = access;
  }

  @Override public ArrayList<Valuable> read()
  {
    if(!access.hasWriteAccess(Thread.currentThread()))
    {
      throw new SecurityException("No read access");
    }
    return list.read();
  }

  @Override public void remove(Valuable valuable)
  {
    if(!access.hasWriteAccess(Thread.currentThread()))
    {
      throw new SecurityException("No read access");
    }
    list.remove(valuable);
  }

  @Override public void add(Valuable valuable)
  {
    if(!access.hasWriteAccess(Thread.currentThread()))
    {
      throw new SecurityException("No read access");
    }
    list.add(valuable);
  }
}