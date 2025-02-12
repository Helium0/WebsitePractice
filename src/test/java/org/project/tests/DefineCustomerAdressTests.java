package org.project.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.project.BasePage;
import org.project.helper.CustomerHelper;
import org.project.helper.ProjectHelper;
import org.project.pages.DefineCustomerAdressPage;
import org.project.pages.DefineCustomerDetailsPage;
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
    private CustomerHelper customerHelper;
    private DefineCustomerDetailsPage defineCustomerDetailsPage;

    private final String CUSTOMER_FUTURE_REFERENCE_ADDRESS = "HONOLULU";
    private final String NO_CUSTOMER_ADDRESSES = "No addresses are available. Add a new address";
    private final String CUSTOMER_STATE = "Kansas";  // can be change for any US states
    private final String CITY_REQUIRED = "city is required.";
    private final String POSTAL_CODE_REQUIRED = "The Zip/Postal code you've entered is invalid. It must follow this format: 00000";
    private final String PHONE_NUMBER_REQUIRED = "You must register at least one phone number.";
    private final String ADDRESS_REQUIRED = "address1 is required.";
    private final String STATE_REQUIRED = "This country requires you to chose a State.";


    @Test(groups = "functional")
    public void fillCustomerFirstAddress() throws IOException {
        loginPage = new LoginPage(driver);
        defineCustomerAdressPage = new DefineCustomerAdressPage(driver);
        projectHelper = new ProjectHelper();
        readProperties = new ReadProperties();
        customerHelper = new CustomerHelper();
        navigationBarPage = new NavigationBarPage(driver);
        projectHelper.waitCoupleTimesForElement(navigationBarPage.getUSER_LOGIN(), projectHelper.jsExecutor());
        loginPage.loginUser(readProperties.readValue("validEmail"),readProperties.readValue("validPassword"));
        loginPage.clickSubmitLoginButton();
        defineCustomerAdressPage.clickOnAddCustomerFirstAddress();
        defineCustomerAdressPage.fillCustomerFullAddress(customerHelper.customerObject());
        defineCustomerAdressPage.assignCustomerAddressInput(CUSTOMER_FUTURE_REFERENCE_ADDRESS);
        projectHelper.select(defineCustomerAdressPage.customerState()).getOptions().stream().filter(a -> a.getText().equals(CUSTOMER_STATE)).forEach(a -> a.click());
        defineCustomerAdressPage.clickOnSaveCustomerAddressButton();

        int addressNumbers = driver.findElements(By.xpath("//div[@class='bloc_adresses row']//div")).size();
        if (addressNumbers >= 1) {
            Assert.assertEquals(addressNumbers, 1);
        } else {
            Assert.fail("*** Assertion failed ***");
        }

        navigationBarPage.signOutFromNavigationBar();

    }

    @Test(dependsOnMethods = "fillCustomerFirstAddress",groups = "functional")
    public void deleteFilledCustomerAddress() throws IOException {
        loginPage = new LoginPage(driver);
        defineCustomerAdressPage = new DefineCustomerAdressPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        readProperties = new ReadProperties();
        navigationBarPage.navigationBarUserSignIn();
        loginPage.loginUser(readProperties.readValue("validEmail"),readProperties.readValue("validPassword"));
        loginPage.clickSubmitLoginButton();
        defineCustomerAdressPage.clickOnCustomerAddressesButton();
        defineCustomerAdressPage.clickDeleteCustomerAddressesButton();
        driver.switchTo().alert().accept();

        WebElement el = driver.findElement(By.xpath("//p[@class='alert alert-warning']"));
        if (el.getText().contains("No addresses")) {
            Assert.assertEquals(el.getText().trim(), NO_CUSTOMER_ADDRESSES);
        } else {
            Assert.fail("*** Assertion failed ***");
        }
        navigationBarPage.signOutFromNavigationBar();

    }

    @Test(priority = 1, groups = "regression")
    public void fillCustomerFirstAddressWithoutCity() throws IOException {
        loginPage = new LoginPage(driver);
        defineCustomerAdressPage = new DefineCustomerAdressPage(driver);
        projectHelper = new ProjectHelper();
        readProperties = new ReadProperties();
        customerHelper = new CustomerHelper();
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        projectHelper.waitCoupleTimesForElement(navigationBarPage.getUSER_LOGIN(), projectHelper.jsExecutor());
        loginPage.loginUser(readProperties.readValue("validEmail"),readProperties.readValue("validPassword"));
        loginPage.clickSubmitLoginButton();
        defineCustomerAdressPage.clickOnAddCustomerFirstAddress();
        defineCustomerAdressPage.fillCustomerAddressWithoutCity(customerHelper.customerObject());
        defineCustomerAdressPage.assignCustomerAddressInput(CUSTOMER_FUTURE_REFERENCE_ADDRESS);
        projectHelper.select(defineCustomerAdressPage.customerState()).getOptions().stream().filter(a -> a.getText().equals(CUSTOMER_STATE)).forEach(a -> a.click());
        defineCustomerAdressPage.clickOnSaveCustomerAddressButton();

        if (defineCustomerDetailsPage.webElementsError().get(0).contains("city")) {
            Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0), CITY_REQUIRED);
        } else {
            Assert.fail("*** Assertion failed ***");
        }
        navigationBarPage.signOutFromNavigationBar();

    }

    @Test(priority = 1, groups = "regression")
    public void fillCustomerFirstAddressWithoutPostalCode() throws IOException {
        loginPage = new LoginPage(driver);
        defineCustomerAdressPage = new DefineCustomerAdressPage(driver);
        projectHelper = new ProjectHelper();
        readProperties = new ReadProperties();
        customerHelper = new CustomerHelper();
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        projectHelper.waitCoupleTimesForElement(navigationBarPage.getUSER_LOGIN(), projectHelper.jsExecutor());
        loginPage.loginUser(readProperties.readValue("validEmail"),readProperties.readValue("validPassword"));
        loginPage.clickSubmitLoginButton();
        defineCustomerAdressPage.clickOnAddCustomerFirstAddress();
        defineCustomerAdressPage.fillCustomerAddressWithoutPostalCode(customerHelper.customerObject());
        defineCustomerAdressPage.assignCustomerAddressInput(CUSTOMER_FUTURE_REFERENCE_ADDRESS);
        projectHelper.select(defineCustomerAdressPage.customerState()).getOptions().stream().filter(a -> a.getText().equals(CUSTOMER_STATE)).forEach(a -> a.click());
        defineCustomerAdressPage.clickOnSaveCustomerAddressButton();

        if (defineCustomerDetailsPage.webElementsError().get(0).contains("The Zip/Postal")) {
            Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0), POSTAL_CODE_REQUIRED);
        } else {
            Assert.fail("*** Assertion failed ***");
        }
        navigationBarPage.signOutFromNavigationBar();

    }

    @Test(priority = 1, groups = "regression")
    public void fillCustomerFirstAddressWithoutMobilePhone() throws IOException {
        loginPage = new LoginPage(driver);
        defineCustomerAdressPage = new DefineCustomerAdressPage(driver);
        projectHelper = new ProjectHelper();
        readProperties = new ReadProperties();
        customerHelper = new CustomerHelper();
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        projectHelper.waitCoupleTimesForElement(navigationBarPage.getUSER_LOGIN(), projectHelper.jsExecutor());
        loginPage.loginUser(readProperties.readValue("validEmail"),readProperties.readValue("validPassword"));
        loginPage.clickSubmitLoginButton();
        defineCustomerAdressPage.clickOnAddCustomerFirstAddress();
        defineCustomerAdressPage.fillCustomerAddressWithoutMobilePhone(customerHelper.customerObject());
        defineCustomerAdressPage.assignCustomerAddressInput(CUSTOMER_FUTURE_REFERENCE_ADDRESS);
        projectHelper.select(defineCustomerAdressPage.customerState()).getOptions().stream().filter(a -> a.getText().equals(CUSTOMER_STATE)).forEach(a -> a.click());
        defineCustomerAdressPage.clickOnSaveCustomerAddressButton();

        if (defineCustomerDetailsPage.webElementsError().get(0).contains("You must register")) {
            Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0), PHONE_NUMBER_REQUIRED);
        } else {
            Assert.fail("*** Assertion failed ***");
        }
        navigationBarPage.signOutFromNavigationBar();
    }

    @Test(priority = 1, groups = "regression")
    public void fillCustomerFirstAddressWithoutCountryState() throws IOException {
        loginPage = new LoginPage(driver);
        defineCustomerAdressPage = new DefineCustomerAdressPage(driver);
        projectHelper = new ProjectHelper();
        readProperties = new ReadProperties();
        customerHelper = new CustomerHelper();
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        projectHelper.waitCoupleTimesForElement(navigationBarPage.getUSER_LOGIN(), projectHelper.jsExecutor());
        loginPage.loginUser(readProperties.readValue("validEmail"),readProperties.readValue("validPassword"));
        loginPage.clickSubmitLoginButton();
        defineCustomerAdressPage.clickOnAddCustomerFirstAddress();
        defineCustomerAdressPage.fillCustomerFullAddress(customerHelper.customerObject());
        defineCustomerAdressPage.assignCustomerAddressInput(CUSTOMER_FUTURE_REFERENCE_ADDRESS);
        defineCustomerAdressPage.clickOnSaveCustomerAddressButton();

        if (defineCustomerDetailsPage.webElementsError().get(0).contains("chose a State")) {
            Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0), STATE_REQUIRED);
        } else {
            Assert.fail("*** Assertion failed ***");
        }
        navigationBarPage.signOutFromNavigationBar();
    }

    @Test(priority = 1, groups = "regression")
    public void fillCustomerFirstAddressWithoutAddress() throws IOException {
        loginPage = new LoginPage(driver);
        defineCustomerAdressPage = new DefineCustomerAdressPage(driver);
        projectHelper = new ProjectHelper();
        readProperties = new ReadProperties();
        customerHelper = new CustomerHelper();
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        projectHelper.waitCoupleTimesForElement(navigationBarPage.getUSER_LOGIN(), projectHelper.jsExecutor());
        loginPage.loginUser(readProperties.readValue("validEmail"),readProperties.readValue("validPassword"));
        loginPage.clickSubmitLoginButton();
        defineCustomerAdressPage.clickOnAddCustomerFirstAddress();
        defineCustomerAdressPage.fillCustomerWithoutAddress(customerHelper.customerObject());
        defineCustomerAdressPage.assignCustomerAddressInput(CUSTOMER_FUTURE_REFERENCE_ADDRESS);
        defineCustomerAdressPage.clickOnSaveCustomerAddressButton();

        if (defineCustomerDetailsPage.webElementsError().get(0).contains("address")) {
            Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0), ADDRESS_REQUIRED);
        } else {
            Assert.fail("*** Assertion failed ***");
        }
        navigationBarPage.signOutFromNavigationBar();
    }

    @Test(priority = 1, groups = "regression")
    public void fillCustomerFirstAddressWithoutRequiredFields() throws IOException {
        loginPage = new LoginPage(driver);
        defineCustomerAdressPage = new DefineCustomerAdressPage(driver);
        projectHelper = new ProjectHelper();
        readProperties = new ReadProperties();
        customerHelper = new CustomerHelper();
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        projectHelper.waitCoupleTimesForElement(navigationBarPage.getUSER_LOGIN(), projectHelper.jsExecutor());
        loginPage.loginUser(readProperties.readValue("validEmail"),readProperties.readValue("validPassword"));
        loginPage.clickSubmitLoginButton();
        defineCustomerAdressPage.clickOnAddCustomerFirstAddress();
        defineCustomerAdressPage.assignCustomerAddressInput(CUSTOMER_FUTURE_REFERENCE_ADDRESS);
        defineCustomerAdressPage.clickOnSaveCustomerAddressButton();

        Assert.assertEquals(defineCustomerDetailsPage.webElementsError().size(), 5);

        navigationBarPage.signOutFromNavigationBar();

    }
}