package org.project.helper;


import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.project.BasePage;
import org.testng.asserts.SoftAssert;
import java.time.Duration;
import java.util.List;



public class ProjectHelper extends BasePage {


    public WebDriverWait webDriverWait() {
        return new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    public void waitForElement(WebElement element) {
        webDriverWait().until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitCoupleTimesForElement(WebElement element) {
        int attempt = 1;
        int sumAttempts = 5;
        while (attempt < sumAttempts) {
            try {
                attempt ++;
                webDriverWait().until(ExpectedConditions.visibilityOf(element));
                webDriverWait().until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                break;
            } catch (NullPointerException | NoSuchElementException | InvalidElementStateException e){
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

}
