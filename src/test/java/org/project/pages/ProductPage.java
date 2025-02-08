package org.project.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.project.BasePage;

import java.util.List;

public class ProductPage {

    @FindBy(id = "group_1")
    private WebElement productSize;

    @FindBy(xpath = "//ul[@id='color_to_pick_list']//a")
    private List<WebElement> productColors;

    @FindBy(xpath = "//span[text()='Add to cart']")
    private WebElement addProductToCartButton;

    @FindBy(xpath = "//span[normalize-space()='Proceed to checkout']")
    private WebElement proceedToCheckoutButton;

    @FindBy(id = "quantity_wanted")
    private WebElement upQuantityInput;

    @FindBy(className = "icon-plus")
    private WebElement plusQuantityButton;




    public ProductPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement productSizeElements() {
        return productSize;
    }

    public List<WebElement> productColorsElements() {
        return productColors;
    }

    public WebElement clickOnAddProductToCartButton() {
        return addProductToCartButton;
    }

    public void clickOnProceedToCheckoutButton() {
        proceedToCheckoutButton.click();
    }

    public void clickToAddMoreQuantity(String quantity) {
        upQuantityInput.clear();
        upQuantityInput.sendKeys(quantity);
    }

    public WebElement getUpQuantity() {
        return upQuantityInput;
    }

    public void clickPlusQuantityButton() {
        plusQuantityButton.click();
    }

    public String returnDomAttribute(WebElement element, String name) {
        return element.getDomAttribute(name);
    }

    public void quantityWheel(WebElement element, String number, int numberTwo) {
        int numberr = Integer.parseInt(returnDomAttribute(element, number));
        while(numberr < numberTwo) {
            clickPlusQuantityButton();
            numberr++;
        }
    }



    /* Below method is little complicated howeaver I left it like it is. I could separate it in to smaller parts
        or use Action class outside single method but for practice I have done like this :) */

    public void waitForElementsToAppear(WebElement element, String text, WebElement elementTwo, WebDriverWait wait, List<WebElement> elementsList, WebDriver driver) {
        int att = 0;
        int sumAtt = 5;
        while (att < sumAtt) {
            try {
                att++;
                Actions ac = new Actions(driver);
                ac.click(element).perform();
                element.sendKeys(text);
                elementTwo.click();
                wait.until(ExpectedConditions.visibilityOfAllElements(elementsList));
                break;
            } catch (StaleElementReferenceException | NoSuchElementException |
                     NullPointerException | InvalidElementStateException ek) {
                elementTwo.clear();
                System.out.println("After: " + att);
                if (att == sumAtt) {
                    System.out.println("Couldn`t find the element after: "+sumAtt);
                    throw ek;
                }
            }
        }
    }
}
