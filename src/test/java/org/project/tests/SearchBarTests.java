package org.project.tests;

import org.project.BasePage;
import org.project.helper.ProductComponent;
import org.project.pages.SearchBarPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class SearchBarTests extends BasePage {


    private SearchBarPage searchBarPage;
    private final String FADED_DRESS = "Faded Short Sleeve T-shirts";
    private final String PRINTED_DRESS = "Printed Summer Dress";


    @Test
    public void searchBarExactMatch() {
        searchBarPage = new SearchBarPage(driver);
        searchBarPage.setSearchBarInput(FADED_DRESS);
        searchBarPage.searchBarClick();
        ProductComponent foundedDress = searchBarPage.getDisplayedProduct(a -> a.getProductName().equals(FADED_DRESS));

        Assert.assertEquals(foundedDress.getProductName(), FADED_DRESS);
        Assert.assertEquals(foundedDress.getProductPrice(), "$17");

    }

    @Test
    public void searchBarPartialMatch() {
        searchBarPage = new SearchBarPage(driver);
        searchBarPage.setSearchBarInput("dress");
        searchBarPage.searchBarClick();
        ProductComponent dress = searchBarPage.getDisplayedProduct(a -> a.getProductName().equals(PRINTED_DRESS));

        Assert.assertEquals(dress.getProductName(), PRINTED_DRESS);
        Assert.assertEquals(dress.getProductPrice(), "$29");

    }

    @Test
    public void invalidSearchNoResults() {
        searchBarPage = new SearchBarPage(driver);
        searchBarPage.setSearchBarInput("boots");
        searchBarPage.searchBarClick();
        List<ProductComponent> boots = searchBarPage.getAllDisplayedProducts();

        Assert.assertEquals(boots.size(),0);
    }

}
