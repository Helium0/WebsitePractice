package org.project;

import org.testng.annotations.*;

import java.time.Duration;

public class BasePage extends WebDriverFactory {




    @BeforeGroups({"functional", "regression", "smoke"})
    public void getWebPage() {
        WebDriverFactory webDriverFactory = new WebDriverFactory();
        webDriverFactory.chromeOptions();
        if (driver == null) {
            driver = webDriverFactory.getCorrectBrowser("Chrome");
            driver.get("http://www.automationpractice.pl/index.php");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();

        }
    }


    @AfterGroups({"functional", "regression", "smoke"})
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
