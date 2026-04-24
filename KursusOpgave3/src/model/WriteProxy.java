package model;

import java.util.ArrayList;

public class WriteProxy implements ReadWriteList
{
  private TreasureRoom list;

  public WriteProxy(TreasureRoom list, Guardsman access)
  {
    this.list = list;
  }

  @Override public void write(Valuable valuable)
  {
    list.write(valuable);
  }

  @Override public ArrayList<Valuable> read()
  {
    return list.read();
  }
}