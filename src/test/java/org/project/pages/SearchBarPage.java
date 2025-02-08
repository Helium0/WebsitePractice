package org.project.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.project.BasePage;
import org.project.helper.ProductComponent;
import java.util.List;
import java.util.function.Predicate;



public class SearchBarPage extends BasePage {


    @FindBy(id = "search_query_top")
    private WebElement searchBarInput;

    @FindBy(name = "submit_search")
    private WebElement searchBarSubmit;

    @FindBy(xpath = "//div[@class='product-container']")
    private List<WebElement> productsList;


    public SearchBarPage(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }

    public WebElement getSearchBarInput() {
        return searchBarInput;
    }

    public WebElement setSearchBarInput() {
        return searchBarInput;
    }

    public WebElement searchBarClick() {
      return  searchBarSubmit;
    }

    public List<WebElement> products() {
        return productsList;
    }

    public List<ProductComponent> getAllDisplayedProducts() {
        return productsList.stream().map(a-> new ProductComponent(a))
                .toList();
    }

    public ProductComponent getDisplayedProduct(Predicate<ProductComponent> condition) {
        return getAllDisplayedProducts().stream()
                .filter(condition).findFirst().orElseThrow();
    }






}
