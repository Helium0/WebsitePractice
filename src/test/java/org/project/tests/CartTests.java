package org.project.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.project.BasePage;
import org.project.helper.ProductComponent;
import org.project.pages.SearchBarPage;
import org.testng.annotations.Test;

import java.util.List;

public class CartTests extends BasePage {

    private SearchBarPage searchBarPage;
    private SearchBarTests searchBarTests;



    @Test
    public void ttt() throws InterruptedException {
        searchBarPage = new SearchBarPage(driver);
        searchBarTests = new SearchBarTests();
        searchBarPage.setSearchBarInput(searchBarTests.getPRINTED_DRESS());
        searchBarPage.searchBarClick();
        ProductComponent product = searchBarPage.getDisplayedProduct(a -> a.getProductName().equals(searchBarTests.getPRINTED_DRESS()));
        product.getProduct();
        WebElement element = driver.findElement(By.id("group_1"));
        Select select = new Select(element);
        select.getOptions().stream().filter(a -> a.getText().equals("M")).forEach(a -> a.click());
        List<WebElement> colors = driver.findElements(By.xpath("//ul[@id='color_to_pick_list']//li"));
        colors.stream().filter(a -> a.getDomProperty("Orange").contains("Orange")).forEach(a -> a.click());
        System.out.println(colors.size());
        Thread.sleep(4000);
    }

}
