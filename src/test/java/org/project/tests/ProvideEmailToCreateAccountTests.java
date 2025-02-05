package org.project.tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.project.BasePage;
import org.project.helper.DataProv;
import org.project.pages.DefineCustomerDetailsPage;
import org.project.pages.LoginPage;
import org.project.utilities.ReadProperties;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class ProvideEmailToCreateAccountTests extends BasePage {


    private LoginPage loginPage;
    private ReadProperties readProperties;
    private LoginTests loginTests;
    private DefineCustomerDetailsPage defineCustomerDetailsPage;
    private final String ALREADY_REGISTERED_EMAIL = "An account using this email address has already been registered. Please enter a valid password or request a new one.";
    private final String PERSONAL_INFORMATION = "YOUR PERSONAL INFORMATION";


    @Test
    public void emptyEmailAdress() {
        loginPage = new LoginPage(driver);
        loginTests = new LoginTests();
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.clickCreateAnAccountButton();

        Assert.assertEquals(loginTests.getINVALID_EMAIL(),"Invalid email address.");
    }

    @Test
    public void numberAsEmailAdress() throws IOException {
        readProperties = new ReadProperties();
        loginPage = new LoginPage(driver);
        loginTests = new LoginTests();
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.sendEmailAdressField(readProperties.readValue("numberEmail"));
        loginPage.clickCreateAnAccountButton();

        Assert.assertEquals(loginTests.getINVALID_EMAIL(), "Invalid email address.");
    }

    @Test
    public void registerOnExistingEmail() throws IOException {
        readProperties = new ReadProperties();
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        loginPage = new LoginPage(driver);
        loginTests = new LoginTests();
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.sendEmailAdressField(readProperties.readValue("registeredEmail"));
        loginPage.clickCreateAnAccountButton();

        Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0), ALREADY_REGISTERED_EMAIL);
    }

    @Test
    public void validEmailAdress() throws IOException {
        readProperties = new ReadProperties();
        loginPage = new LoginPage(driver);
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.sendEmailAdressField(readProperties.readValue("unregisteredEmail"));
        loginPage.clickCreateAnAccountButton();
        WebElement el = driver.findElement(By.xpath("//div[@class='account_creation']//h3"));

        Assert.assertEquals(el.getText(), PERSONAL_INFORMATION);
    }

    @Test(dataProvider = "dataProvider", dataProviderClass = DataProv.class)    // DataProv class used also in this case :)
    public void differentEmailVariations(String email) throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.clickSignInButtonAtTheBeginning();
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        loginTests = new LoginTests();
        String[] userEmail = email.split(",");
        loginPage.sendEmailAdressField(userEmail[0]);
        loginPage.clickCreateAnAccountButton();


        if(defineCustomerDetailsPage.webElementsError().isEmpty()) {
                Assert.assertEquals(driver.findElement(By.xpath("//div[@class='account_creation']//h3")).getText(), PERSONAL_INFORMATION);
        } else if (defineCustomerDetailsPage.webElementsError().get(0).contains("already been registered")) {
            Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0), ALREADY_REGISTERED_EMAIL);
        } else if (defineCustomerDetailsPage.webElementsError().get(0).contains("Invalid email")) {
            Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0), loginTests.getINVALID_EMAIL());
        } else {
            Assert.fail("Assertion failed");
        }
    }

}
