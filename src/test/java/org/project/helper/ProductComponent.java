package org.project.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductComponent extends BaseComponent {


    private final By PRODUCT = By.xpath(".//a[@class='product-name']");
    private final By PRODUCT_PRICE = By.xpath(".//div[@class='right-block']//span[@class='price product-price']");


    public ProductComponent(WebElement root) {
        super(root);
    }

    public String getProductName() {
        return root.findElement(PRODUCT).getText();
    }

    public String getProductPrice() {
        return root.findElement(PRODUCT_PRICE).getText();
    }

    public void getProduct() {
         root.findElement(PRODUCT).click();
    }




}
