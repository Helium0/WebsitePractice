package org.project.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.project.BasePage;
import org.project.helper.ProductComponent;
import org.project.helper.ProjectHelper;
import org.project.pages.CartPage;
import org.project.pages.SearchBarPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class CartTests extends BasePage {

    private SearchBarPage searchBarPage;
    private SearchBarTests searchBarTests;
    private CartPage cartPage;
    private ProjectHelper projectHelper;

    private final String PRODUCT_SIZE = "M";
    private final String PRODUCT_COLOR = "Orange";
    private final String PRODUCT_NAME_ATTRIBUTE = "name";



    @Test
    public void addProductToTheCart() {
        cartPage = new CartPage(driver);
        searchBarPage = new SearchBarPage(driver);
        searchBarTests = new SearchBarTests();
        projectHelper = new ProjectHelper();
        searchBarPage.setSearchBarInput(searchBarTests.getPRINTED_DRESS());
        searchBarPage.searchBarClick();
        ProductComponent product = searchBarPage.getDisplayedProduct(a -> a.getProductName()
                .equals(searchBarTests.getPRINTED_DRESS()));
        product.getProduct();
        projectHelper.streamMethod(cartPage.productSizeElements()).stream()
                .filter(a -> a.getText().equals(PRODUCT_SIZE)).forEach(a -> a.click());
        cartPage.productColorsElements().stream()
                .filter(a -> a.getDomAttribute(PRODUCT_NAME_ATTRIBUTE).equals(PRODUCT_COLOR))
                .forEach(a -> a.click());
        cartPage.clickOnAddProductToCartButton().click();
        cartPage.clickOnProceedToCheckoutButton();

        WebElement el = driver.findElement(By.xpath("//td[text()='$29']"));
        Assert.assertEquals(el.getText(), "$29");

    }

    @Test
    public void changeProductQuantityAndAddToTheCart() {
        cartPage = new CartPage(driver);
        searchBarPage = new SearchBarPage(driver);
        searchBarTests = new SearchBarTests();
        projectHelper = new ProjectHelper();
        searchBarPage.setSearchBarInput(searchBarTests.getPRINTED_DRESS());
        searchBarPage.searchBarClick();
        ProductComponent product = searchBarPage.getDisplayedProduct(a -> a.getProductName()
                .equals(searchBarTests.getPRINTED_DRESS()));
        product.getProduct();
        projectHelper.waitForElement(cartPage.clickOnAddProductToCartButton());
        projectHelper.streamMethod(cartPage.productSizeElements()).stream()
                .filter(a -> a.getText().equals(PRODUCT_SIZE)).forEach(a -> a.click());
        cartPage.productColorsElements().stream();
        cartPage.clickToAddMoreQuantityButton("2");
        cartPage.clickOnAddProductToCartButton().click();
        cartPage.clickOnProceedToCheckoutButton();

        WebElement el = driver.findElement(By.xpath("//td[text()='$58']"));
        Assert.assertEquals(el.getText(), "$58");

    }

    @Test
    public void changeProductQuantityByButtonAndAddToTheCart() {
        cartPage = new CartPage(driver);
        searchBarPage = new SearchBarPage(driver);
        searchBarTests = new SearchBarTests();
        projectHelper = new ProjectHelper();
        searchBarPage.setSearchBarInput(searchBarTests.getPRINTED_DRESS());
        searchBarPage.searchBarClick();
        ProductComponent product = searchBarPage.getDisplayedProduct(a -> a.getProductName()
                .equals(searchBarTests.getPRINTED_DRESS()));
        product.getProduct();
        projectHelper.waitForElement(cartPage.clickOnAddProductToCartButton());
        projectHelper.streamMethod(cartPage.productSizeElements()).stream()
                .filter(a -> a.getText().equals(PRODUCT_SIZE)).forEach(a -> a.click());
        cartPage.productColorsElements().stream();
        WebElement y = driver.findElement(By.id("quantity_wanted"));
        int o = Integer.parseInt(y.getDomAttribute("value"));


        while ( o < 5) {

                driver.findElement(By.className("icon-plus")).click();
                o++;

        }

    }

}
