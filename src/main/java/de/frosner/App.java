package de.frosner;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.ReloadingFileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.reloading.PeriodicReloadingTrigger;

public class App
{
    public static void main(String[] args) throws InterruptedException
    {
        Parameters params = new Parameters();
        File propertiesFile = new File("/config/config.properties");
        ReloadingFileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new ReloadingFileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.fileBased()
                                .setFile(propertiesFile));
        PeriodicReloadingTrigger trigger = new PeriodicReloadingTrigger(builder.getReloadingController(),
                null, 1, TimeUnit.SECONDS);
        trigger.start();

        try
        {
            while (true)
            {
                Configuration config = builder.getConfiguration();
                String name = config.getString("name");
                int sleepInterval = config.getInt("sleepIntervalMillis");
                System.out.println(String.format("Hello %s, it is %s", name, new Date()));
                Thread.sleep(sleepInterval);
            }
        }
        catch (ConfigurationException cex)
        {
            System.err.println("Failed to load configuration: " + cex);
        }
    }
}
