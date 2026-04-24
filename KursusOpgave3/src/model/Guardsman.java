package model;

import java.util.ArrayList;

public class Guardsman implements Door
{
  private int readers;
  private int writers;
  private int waitingWriters;
  private ArrayList<Thread> allowedReadAccess;
  private ArrayList<Thread> allowedWriteAccess;
  private TreasureRoom list;

  public Guardsman(TreasureRoom list)
  {
    readers = 0;
    writers = 0;
    waitingWriters = 0;
    allowedReadAccess = new ArrayList<>();
    allowedWriteAccess = new ArrayList<>();
    this.list = list;
  }

  @Override synchronized public ReadList acquireRead()
  {
    while(waitingWriters > 0 || writers > 0)
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }
    }
    readers++;
    allowedReadAccess.add(Thread.currentThread());
    return new ReadProxy(list, this);
  }

  @Override synchronized public void releaseRead()
  {
    readers--;
    allowedReadAccess.remove(Thread.currentThread());
    notifyAll();
  }

  @Override synchronized public ReadWriteList acquireWrite()
  {
    waitingWriters ++;
    while (readers > 0 || writers > 0)
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }
    }
    waitingWriters--;
    writers++;
    allowedWriteAccess.add(Thread.currentThread());
    return new WriteProxy(list,this);
  }

  @Override synchronized public void releaseWrite()
  {
    writers--;
    allowedWriteAccess.remove(Thread.currentThread());
    notifyAll();
  }

  public boolean hasReadAccess(Thread t)
  {
    for(Thread thread : allowedReadAccess)
    {
      if(thread.equals(t))
      {
        return true;
      }
    }
    return false;
  }

  public boolean hasWriteAccess(Thread t)
  {
    for(Thread thread : allowedWriteAccess)
    {
      if(thread.equals(t))
      {
        return true;
      }
    }
    return false;
  }
}
