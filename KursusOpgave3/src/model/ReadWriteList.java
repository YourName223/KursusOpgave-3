package model;

public interface ReadWriteList extends ReadList
{
  void remove(Valuable valuable);
  void add(Valuable valuable);
}
