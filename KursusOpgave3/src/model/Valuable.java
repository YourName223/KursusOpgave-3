package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Valuable
{
  private int value;
  private String type;

  private static Map<String, Valuable> allInstances = new HashMap<>();
  private static final Random RANDOM = new Random();

  private static final String[] TYPES = {"Diamant", "GoldNugget", "Jewel",
      "Ruby", "WoodenCoin"};

  private Valuable(String type, int value)
  {
    this.type = type;
    this.value = value;
  }

  private static String randomType()
  {
    return TYPES[RANDOM.nextInt(TYPES.length)];
  }

  private static int randomInRange(int min, int max)
  {
    return RANDOM.nextInt((max - min) + 1) + min;
  }

  private static int valueForType(String type)
  {
    switch (type)
    {
      case "Diamant":
        return randomInRange(45, 60);
      case "GoldNugget":
        return randomInRange(15, 30);
      case "Jewel":
        return randomInRange(10, 20);
      case "Ruby":
        return randomInRange(5, 15);
      case "WoodenCoin":
        return 1;
      default:
        throw new IllegalArgumentException("Unknown valuable type: " + type);
    }
  }

  public static Valuable getInstance(String key)
  {
    if (allInstances.containsKey(key))
    {
      return allInstances.get(key);
    }

    String randomType = randomType();
    int value = valueForType(randomType);

    Valuable v = new Valuable(randomType, value);
    allInstances.put(key, v);

    return v;
  }

  public int getValue()
  {
    return value;
  }

  public String toString()
  {
    return "Type: " + type + " Value: " + value;
  }
}
