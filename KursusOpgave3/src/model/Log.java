package model;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Log
{

  private static Log instance;
  private String logFile = "app.log";

  private Log() {}

  public static Log getInstance() {
    if (instance == null) {
      instance = new Log();
    }
    return instance;
  }

  public void addLog(String action) {
    String time = LocalDateTime.now().toString();
    String fullMessage = time + " | " + action;

    System.out.println(fullMessage);

    try (FileWriter writer = new FileWriter(logFile, true)) {
      writer.write(fullMessage + "\n");
    } catch (IOException e) {
      System.err.println("Could not write to log file: " + logFile);
    }
  }
}
