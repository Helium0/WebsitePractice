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

    public void waitCoupleTimesForElement (By locator) {
        int attempt = 1;
        int sumAttempts = 9;
        while (attempt < sumAttempts) {
            try {
                attempt ++;
                new WebDriverWait(driver, Duration.ofSeconds(15)).until(webDriver -> ((JavascriptExecutor)webDriver)
                        .executeScript("return document.readyState").equals("complete"));
                WebElement el = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfElementLocated(locator));
                JavascriptExecutor js = (JavascriptExecutor) driver;
                new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(locator));
                new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(locator));
//                actions(driver).click(el).perform();
                js.executeScript("arguments[0].click();",el);
                break;
            } catch ( TimeoutException e){
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

    public Wait fluentWait() {
        return new FluentWait<>(driver)
               .withTimeout(Duration.ofSeconds(15))
               .pollingEvery(Duration.ofMillis(500))
               .ignoring(NoSuchElementException.class);
    }

}
