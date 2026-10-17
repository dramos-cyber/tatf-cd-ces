package com.tatf.core.driver.manager;

import org.openqa.selenium.chrome.ChromeOptions;
import com.tatf.core.util.ConfigReader;

public class ChromeDriver extends DriverManager {
    /**
     * Crea el driver de Chrome con las opciones por defecto.
     */
    public ChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        ConfigReader config = new ConfigReader("config.properties");
        if (config.asBoolean("chrome.maximized")) {
            chromeOptions.addArguments("start-maximized");
        }
        if (config.asBoolean("chrome.headless")) {
            chromeOptions.addArguments("--headless=new");
        }
        chromeOptions.addArguments("--ignore-certificate-errors");

        this.driver = new org.openqa.selenium.chrome.ChromeDriver(chromeOptions);
        setDefaultConfig();
    }
}
