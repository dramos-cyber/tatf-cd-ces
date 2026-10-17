package com.tatf.core.driver.manager;

import com.tatf.core.util.ConfigReader;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriver extends DriverManager {
    public FirefoxDriver() {
        ConfigReader config = new ConfigReader("config.properties");
        FirefoxOptions options = new FirefoxOptions();
        if (config.asBoolean("firefox.headless")) {
            options.addArguments("-headless");
        }
        this.driver = new org.openqa.selenium.firefox.FirefoxDriver(options);
        setDefaultConfig();
        if (config.asBoolean("firefox.maximized")) {
            driver.manage().window().maximize();
        }
    }
}
