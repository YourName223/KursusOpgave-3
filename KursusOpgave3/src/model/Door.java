package model;

public interface Door
{
  public ReadList acquireRead();

  public void releaseRead();

  public ReadWriteList acquireWrite();

  public void releaseWrite();
}