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

  @Override public void write(Valuable valuable)
  {
    if(!access.hasWriteAccess(Thread.currentThread()))
    {
      throw new SecurityException("No read access");
    }
    list.write(valuable);
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