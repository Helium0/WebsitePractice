package org.project;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.time.Duration;

public class BasePage extends WebDriverFactory {


    @BeforeTest
    public void getWebPage() {
        WebDriverFactory webDriverFactory = new WebDriverFactory();
        driver = webDriverFactory.getCorrectBrowser("Chrome");
        driver.get("http://www.automationpractice.pl/index.php");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
