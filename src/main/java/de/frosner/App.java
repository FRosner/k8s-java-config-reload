package de.frosner;

import java.util.Date;
import org.apache.commons.configuration2.Configuration;

public class App {

  public static void main(String[] args) throws InterruptedException {
    try (ConfigReloader configReloader = new ConfigReloader("/config/config.properties")) {
      while (true) {
        Configuration config = configReloader.getConfig();
        String name = config.getString("name");
        int sleepInterval = config.getInt("sleepIntervalMillis");
        System.out.println(String.format("Hello %s, it is %s", name, new Date()));
        Thread.sleep(sleepInterval);
      }
    }
  }
}
