package org.project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WebDriverFactory {

    protected static WebDriver driver;

    public  WebDriver getCorrectBrowser(String browser) {

        switch (browser.toLowerCase()) {
            case "chrome": return new ChromeDriver(chromeOptions());
            case "firefox": return new FirefoxDriver();
            case "edge": return new EdgeDriver();

            default:
                throw new RuntimeException("Incorrect browser: "+browser);
        }
    }

    public ChromeOptions chromeOptions() {
        ChromeOptions options =new ChromeOptions();
        Map savePopUp = browserMap();
        savePopUp.put("credentials_enable_service", false);
        savePopUp.put("profile.password_manager_enabled", false);
        savePopUp.put("autofill.profile_enabled", false);
        options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
        options.setExperimentalOption("prefs", savePopUp);
        return options;
    }

    private Map browserMap() {
        return new HashMap<>();
    }

}
