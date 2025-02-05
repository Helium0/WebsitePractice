package org.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {

    @FindBy(id = "group_1")
    private WebElement productSize;

    @FindBy(xpath = "//ul[@id='color_to_pick_list']//a")
    private List<WebElement> productColors;

    @FindBy(xpath = "//span[text()='Add to cart']")
    private WebElement addProductToCartButton;

    @FindBy(xpath = "//span[normalize-space()='Proceed to checkout']")
    private WebElement proceedToCheckoutButton;

    @FindBy(id = "quantity_wanted")
    private WebElement upQuantityButton;



    public CartPage(WebDriver driver) {
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

    public void clickToAddMoreQuantityButton(String quantity) {
        upQuantityButton.clear();
        upQuantityButton.sendKeys(quantity);
    }


}
