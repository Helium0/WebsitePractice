package org.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartSummary {

    @FindBy(className = "cart_quantity_delete")
    public WebElement deleteProductButton;



    public ShoppingCartSummary(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    private void clickDeleteProductButton() {
        deleteProductButton.click();
    }

}
