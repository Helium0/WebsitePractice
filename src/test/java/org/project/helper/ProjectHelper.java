package org.project.helper;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.project.BasePage;
import org.testng.asserts.SoftAssert;
import java.time.Duration;
import java.util.List;



public class ProjectHelper extends BasePage {


    public WebDriverWait webDriverWait() {
        return new WebDriverWait(driver,Duration.ofSeconds(20));
    }

    public void waitForElement(WebElement element) {
        webDriverWait().until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitCoupleTimesForElement (By locator, JavascriptExecutor js) {
        int attempt = 1;
        int sumAttempts = 9;
        while (attempt < sumAttempts) {
            try {
                attempt ++;
                js.executeScript("location.reload();");
                WebElement el = webDriverWait().until(ExpectedConditions.presenceOfElementLocated(locator));
                js.executeScript("arguments[0].click();",el);
                break;
            } catch ( TimeoutException  |  NoSuchElementException e){
                if (attempt == sumAttempts) {
                    throw e;
                }
            }
        }
    }

    public Actions actions(WebDriver driver) {
        return new Actions(driver);
    }

    public SoftAssert softAssert() {
        return new SoftAssert();
    }

    public Select select(WebElement element) {
        return new Select(element);
    }

    public List<WebElement> streamMethod(WebElement element) {
        return select(element).getOptions();
    }


    public JavascriptExecutor jsExecutor() {
        return (JavascriptExecutor) driver;
    }

}
