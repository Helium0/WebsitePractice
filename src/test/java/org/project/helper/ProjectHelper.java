package org.project.helper;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.project.BasePage;
import org.testng.asserts.SoftAssert;
import java.time.Duration;


public class ProjectHelper extends BasePage {




    private WebDriverWait webDriverWait() {
        return new WebDriverWait(driver,Duration.ofSeconds(10));
    }
    public void waitForElement(WebElement element) {
        webDriverWait().until(ExpectedConditions.elementToBeClickable(element));
    }
    public SoftAssert softAssert() {
        return new SoftAssert();
    }
    public Select select(WebElement element) {
        return new Select(element);
    }


}
