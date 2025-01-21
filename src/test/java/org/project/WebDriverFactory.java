package org.project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

    protected WebDriver driver;
    public  WebDriver getCorrectBrowser(String browser) {

        switch (browser) {
            case "Chrome": return new ChromeDriver();
            case "Firefox": return new FirefoxDriver();
            case "Edge": return new EdgeDriver();

            default:
                throw new RuntimeException("Incorrect browser: "+browser);
        }

    }

}
