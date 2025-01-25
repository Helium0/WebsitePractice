package org.project.tests;


import org.project.BasePage;
import org.project.helper.DataProv;
import org.project.pages.LoginPage;
import org.project.utilities.ReadProperties;
import org.testng.annotations.Test;

import java.io.IOException;


public class ProvideEmailToCreateAccountTests extends BasePage {


    private LoginPage loginPage;
    private ReadProperties readProperties;


    @Test
    public void emptyEmailAdress() {
        loginPage = new LoginPage(driver);
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.clickCreateAnAccountButton();

    }

    @Test
    public void numberAsEmailAdress() throws IOException {
        readProperties = new ReadProperties();
        loginPage = new LoginPage(driver);
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.sendEmailAdressField(readProperties.readValue("numberEmail"));
        loginPage.clickCreateAnAccountButton();
    }

    @Test
    public void notExistingEmailAdress() throws IOException {
        readProperties = new ReadProperties();
        loginPage = new LoginPage(driver);
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.sendEmailAdressField(readProperties.readValue("wrongEmail"));
        loginPage.clickCreateAnAccountButton();
    }

    @Test
    public void validEmailAdress() throws IOException {
        readProperties = new ReadProperties();
        loginPage = new LoginPage(driver);
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.sendEmailAdressField(readProperties.readValue("validEmail"));
        loginPage.clickCreateAnAccountButton();
    }

    @Test(dataProvider = "dataProvider", dataProviderClass = DataProv.class)    // DataProv class used also in this case :)
    public void differentEmailVariations(String email) {
        loginPage = new LoginPage(driver);
        loginPage.clickSignInButtonAtTheBeginning();
        String[] userEmail = email.split(",");
        loginPage.sendEmailAdressField(userEmail[0]);
        loginPage.clickCreateAnAccountButton();

    }

}
