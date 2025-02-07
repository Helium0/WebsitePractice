package org.project.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.project.BasePage;
import org.project.helper.ProductComponent;
import org.project.helper.ProjectHelper;
import org.project.pages.ProductPage;
import org.project.pages.SearchBarPage;
import org.project.pages.ShoppingCartSummary;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ProductTests extends BasePage {

    private SearchBarPage searchBarPage;
    private SearchBarTests searchBarTests;
    private ProductPage productPage;
    private ProjectHelper projectHelper;
    private ShoppingCartSummary shoppingCartSummary;

    private final String PRODUCT_SIZE = "M";
    private final String PRODUCT_COLOR = "Orange";
    private final String PRODUCT_NAME_ATTRIBUTE = "name";



    @Test
    public void addProductToTheCart() throws InterruptedException {
        productPage = new ProductPage(driver);
        searchBarPage = new SearchBarPage(driver);
        shoppingCartSummary = new ShoppingCartSummary(driver);
        searchBarTests = new SearchBarTests();
        projectHelper = new ProjectHelper();
        productPage.waitForElementsToAppear(searchBarPage.setSearchBarInput(), searchBarTests.getPRINTED_DRESS(),
                searchBarPage.searchBarClick(),projectHelper.webDriverWait(),
                searchBarPage.products(),driver);
        ProductComponent product = searchBarPage.getDisplayedProduct(a -> a.getProductName()
                .equals(searchBarTests.getPRINTED_DRESS()));
        product.getProduct();
        projectHelper.streamMethod(productPage.productSizeElements()).stream()
                .filter(a -> a.getText().equals(PRODUCT_SIZE)).forEach(a -> a.click());
        productPage.productColorsElements().stream()
                .filter(a -> a.getDomAttribute(PRODUCT_NAME_ATTRIBUTE).equals(PRODUCT_COLOR))
                .forEach(a -> a.click());
        productPage.clickOnAddProductToCartButton().click();
        productPage.clickOnProceedToCheckoutButton();


        WebElement el = driver.findElement(By.xpath("//td[@class='cart_total']"));
        Assert.assertEquals(el.getText(), "$29");

        shoppingCartSummary.deleteProductButton.click();

    }

    @Test
    public void changeProductQuantityAndAddToTheCart() throws InterruptedException {
        productPage = new ProductPage(driver);
        searchBarPage = new SearchBarPage(driver);
        shoppingCartSummary = new ShoppingCartSummary(driver);
        searchBarTests = new SearchBarTests();
        projectHelper = new ProjectHelper();
        productPage.waitForElementsToAppear(searchBarPage.setSearchBarInput(), searchBarTests.getPRINTED_DRESS(),
                searchBarPage.searchBarClick(),projectHelper.webDriverWait(),
                searchBarPage.products(),driver);
        ProductComponent product = searchBarPage.getDisplayedProduct(a -> a.getProductName()
                .equals(searchBarTests.getPRINTED_DRESS()));
        product.getProduct();
        projectHelper.webDriverWait()
                .until(ExpectedConditions.invisibilityOf(productPage.clickOnAddProductToCartButton()));
        projectHelper.streamMethod(productPage.productSizeElements()).stream()
                .filter(a -> a.getText().equals(PRODUCT_SIZE)).forEach(a -> a.click());
        productPage.productColorsElements().stream();
        productPage.clickToAddMoreQuantity("2");
        productPage.clickOnAddProductToCartButton().click();
        productPage.clickOnProceedToCheckoutButton();

        WebElement el = driver.findElement(By.xpath("//td[@class='cart_total']"));
        Assert.assertEquals(el.getText(), "$58");

        shoppingCartSummary.deleteProductButton.click();

    }

    @Test
    public void changeProductQuantityByButtonAndAddToTheCart() throws InterruptedException {
        productPage = new ProductPage(driver);
        searchBarPage = new SearchBarPage(driver);
        shoppingCartSummary = new ShoppingCartSummary(driver);
        searchBarTests = new SearchBarTests();
        projectHelper = new ProjectHelper();
        productPage.waitForElementsToAppear(searchBarPage.setSearchBarInput(), searchBarTests.getPRINTED_DRESS(),
                searchBarPage.searchBarClick(),projectHelper.webDriverWait(),
                searchBarPage.products(),driver);
        ProductComponent product = searchBarPage.getDisplayedProduct(a -> a.getProductName()
                .equals(searchBarTests.getPRINTED_DRESS()));
        product.getProduct();
        projectHelper.waitForElement(productPage.clickOnAddProductToCartButton());
        projectHelper.streamMethod(productPage.productSizeElements()).stream()
                .filter(a -> a.getText().equals(PRODUCT_SIZE)).forEach(a -> a.click());
        productPage.productColorsElements().stream();
        productPage.quantityWheel(productPage.getUpQuantity(), "value", 5);
        productPage.clickOnAddProductToCartButton().click();
        productPage.clickOnProceedToCheckoutButton();

        WebElement el = driver.findElement(By.xpath("//td[@class='cart_total']"));
        Assert.assertEquals(el.getText(), "$145");

        shoppingCartSummary.deleteProductButton.click();

        }

    }


