package org.project.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.project.BasePage;
import org.project.helper.ProjectHelper;
import org.project.pages.DefineCustomerAdressPage;
import org.project.pages.LoginPage;
import org.project.pages.NavigationBarPage;
import org.project.utilities.ReadProperties;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;


public class DefineCustomerAdressTests extends BasePage {

    private LoginPage loginPage;
    private DefineCustomerAdressPage defineCustomerAdressPage;
    private ProjectHelper projectHelper;
    private ReadProperties readProperties;
    private NavigationBarPage navigationBarPage;

    private final String CUSTOMER_FUTURE_REFERENCE_ADDRESS = "HONOLULU";
    private final String NO_CUSTOMER_ADDRESSES = "No addresses are available. Add a new address";
    private final String CUSTOMER_STATE = "Kansas";  // can be change for any US states



    @Test
    public void fillCustomerFirstAddress() throws IOException, InterruptedException {
        loginPage = new LoginPage(driver);
        defineCustomerAdressPage = new DefineCustomerAdressPage(driver);
        projectHelper = new ProjectHelper();
        readProperties = new ReadProperties();
        navigationBarPage = new NavigationBarPage(driver);
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.provideEmail(readProperties.readValue("validEmail"));
        loginPage.providePassword(readProperties.readValue("validPassword"));
        loginPage.clickSubmitLoginButton();
        defineCustomerAdressPage.clickOnAddCustomerFirstAddress();
        defineCustomerAdressPage.fillCustomerAddress();
        defineCustomerAdressPage.assignCustomerAddressInput(CUSTOMER_FUTURE_REFERENCE_ADDRESS);
        projectHelper.select(defineCustomerAdressPage.customerState()).getOptions().stream().filter(a->a.getText().equals(CUSTOMER_STATE)).forEach(a->a.click());
        defineCustomerAdressPage.clickOnSaveCustomerAddressButton();

        int addressNumbers = driver.findElements(By.xpath("//div[@class='bloc_adresses row']//div")).size();
        Assert.assertEquals(addressNumbers, 1);
        navigationBarPage.signOutFromNavigationBar();

    }

    @Test(dependsOnMethods = "fillCustomerFirstAddress")
    public void deleteFilledCustomerAddress() throws IOException, InterruptedException {
        loginPage = new LoginPage(driver);
        defineCustomerAdressPage = new DefineCustomerAdressPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        readProperties = new ReadProperties();
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.provideEmail(readProperties.readValue("validEmail"));
        loginPage.providePassword(readProperties.readValue("validPassword"));
        loginPage.clickSubmitLoginButton();
        defineCustomerAdressPage.clickOnCustomerAddressesButton();
        defineCustomerAdressPage.clickDeleteCustomerAddressesButton();
        driver.switchTo().alert().accept();

        WebElement el = driver.findElement(By.xpath("//p[@class='alert alert-warning']"));
        Assert.assertEquals(el.getText().trim(), NO_CUSTOMER_ADDRESSES);
        navigationBarPage.signOutFromNavigationBar();

    }

}
