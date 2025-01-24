package org.project.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.project.BasePage;
import org.project.helper.DataProv;
import org.project.pages.ForgotPasswordPage;
import org.project.pages.LoginPage;
import org.project.utilities.ReadProperties;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ForgotPasswordTests extends BasePage {

    private LoginPage loginPage;
    private ForgotPasswordPage forgotPasswordPage;
    private ReadProperties readProperties;
    private final String AUTHENTICATION_TEXT = "AUTHENTICATION";


    @Test
    public void forgotPasswordWithEmptyEmailField() {
        loginPage = new LoginPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.clickForgotPasswordButton();
        forgotPasswordPage.clickOnRetrievePasswordButton();

    }

    @Test
    public void forgotPasswordWithInvalidEmail() throws IOException {
        loginPage = new LoginPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        readProperties = new ReadProperties();
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.clickForgotPasswordButton();
        loginPage.provideEmail(readProperties.readValue("wrongEmail"));
        forgotPasswordPage.clickOnRetrievePasswordButton();

    }

    @Test
    public void forgotPasswordWithValidEmail() {

    }

    @Test
    public void forgotPasswordBackToLoginPage() {
        loginPage = new LoginPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.clickForgotPasswordButton();
        forgotPasswordPage.clickOnRetrievePasswordButton();
        forgotPasswordPage.clickOnBackToLoginButton();
        WebElement element = driver.findElement(By.xpath("//h1[text()='Authentication']"));

        Assert.assertEquals(element.getText(),AUTHENTICATION_TEXT);
    }

    @Test(dataProvider = "dataProvider", dataProviderClass = DataProv.class)    // Data Provider also used here.
    public void differentEmailsFromDataProvider(String data) {
        loginPage = new LoginPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.clickForgotPasswordButton();
        String [] email = data.split(",");
        loginPage.provideEmail(email[0]);
        forgotPasswordPage.clickOnRetrievePasswordButton();
    }

}
