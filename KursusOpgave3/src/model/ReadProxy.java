package model;

import java.util.ArrayList;

public class ReadProxy implements ReadList
{
  private TreasureRoom list;

  public ReadProxy(TreasureRoom list, Guardsman access)
  {
    this.list = list;
  }

  @Override public ArrayList<Valuable> read()
  {
    return list.read();
  }
}