package org.project.tests;

import org.openqa.selenium.Keys;
import org.project.BasePage;
import org.project.helper.ProductComponent;
import org.project.helper.ProjectHelper;
import org.project.pages.SearchBarPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class SearchBarTests extends BasePage {


    private SearchBarPage searchBarPage;
    private ProjectHelper projectHelper;
    private final String FADED_DRESS = "Faded Short Sleeve T-shirts";
    private final String PRINTED_DRESS = "Printed Summer Dress";


    public String getPRINTED_DRESS() {
        return PRINTED_DRESS;
    }




    @Test(groups = "functional")
    public void searchBarExactMatch() {
        searchBarPage = new SearchBarPage(driver);
        projectHelper = new ProjectHelper();
        projectHelper.actions(driver).click(searchBarPage.setSearchBarInput())
                .sendKeys(FADED_DRESS).click(searchBarPage.searchBarClick()).perform();
        ProductComponent foundedDress = searchBarPage.getDisplayedProduct(a -> a.getProductName().equals(FADED_DRESS));

        Assert.assertEquals(foundedDress.getProductName(), FADED_DRESS);
        Assert.assertEquals(foundedDress.getProductPrice(), "$17");

    }

    @Test(groups = "functional", priority = 1)
    public void searchBarPartialMatch() {
        searchBarPage = new SearchBarPage(driver);
        projectHelper = new ProjectHelper();
        projectHelper.actions(driver).click(searchBarPage.setSearchBarInput())
                .keyDown(Keys.CONTROL).sendKeys("A")
                .keyUp(Keys.CONTROL)
                .keyDown(Keys.BACK_SPACE)
                .keyUp(Keys.BACK_SPACE)
                .sendKeys(PRINTED_DRESS).click(searchBarPage.searchBarClick()).build().perform();

        ProductComponent dress = searchBarPage.getDisplayedProduct(a -> a.getProductName().equals(PRINTED_DRESS));

        Assert.assertEquals(dress.getProductName(), PRINTED_DRESS);
        Assert.assertEquals(dress.getProductPrice(), "$29");

    }

    @Test(groups = "functional", priority = 2)
    public void invalidSearchNoResults() throws InterruptedException {
        searchBarPage = new SearchBarPage(driver);
        projectHelper = new ProjectHelper();
        projectHelper.actions(driver).click(searchBarPage.setSearchBarInput())
                .keyDown(Keys.CONTROL).sendKeys("A")
                .keyUp(Keys.CONTROL)
                .keyDown(Keys.BACK_SPACE)
                .keyUp(Keys.BACK_SPACE)
                .sendKeys("boots").click(searchBarPage.searchBarClick()).perform();
        List<ProductComponent> boots = searchBarPage.getAllDisplayedProducts();

        Assert.assertEquals(boots.size(),0);
        projectHelper.actions(driver).click(searchBarPage.setSearchBarInput())
                .keyDown(Keys.CONTROL).sendKeys("A")
                .keyUp(Keys.CONTROL)
                .keyDown(Keys.BACK_SPACE)
                .keyUp(Keys.BACK_SPACE)
                .perform();

    }

}
