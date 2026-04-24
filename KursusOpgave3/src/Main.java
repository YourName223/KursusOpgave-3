import javafx.application.Application;
import model.*;

public class Main
{
  public static void main(String[] args)
  {
    TreasureRoom treasureRoom = new TreasureRoom(1,1);
    Guardsman guardsman = new Guardsman(treasureRoom);
    BlockingQueue blockingQueue = new BlockingQueue();
    Accountant accountant = new Accountant(guardsman);
    King king = new King(guardsman);
    ValuableTransporter valuableTransporter = new ValuableTransporter(guardsman,blockingQueue);
    ValuablesMiner valuablesMiner = new ValuablesMiner(blockingQueue);
    Thread accountantT = new Thread(accountant);
    Thread kingT = new Thread(king);
    Thread valuableTransportT = new Thread(valuableTransporter);
    Thread valuableMinerT = new Thread(valuablesMiner);

    accountantT.start();
    kingT.start();
    valuableTransportT.start();
    valuableMinerT.start();

  }
}
