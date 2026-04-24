package model;

import java.util.ArrayList;

public class Mine
{
  private ArrayList<Valuable> valuableList;

  public Mine()
  {
    valuableList = new ArrayList<Valuable>();

    for (int i = 0; i < 1000; i++) {
      valuableList.add(Valuable.getInstance(String.valueOf(i)));
    }
  }

  public Valuable mineValuable()
  {
    if (valuableList.isEmpty()) {
      throw new IllegalStateException("Mine is empty!");
    }
    return valuableList.remove(valuableList.size() - 1);
  }
}
