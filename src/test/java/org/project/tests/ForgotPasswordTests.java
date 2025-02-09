package org.project.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.project.BasePage;
import org.project.helper.DataProv;
import org.project.helper.ProjectHelper;
import org.project.pages.DefineCustomerDetailsPage;
import org.project.pages.ForgotPasswordPage;
import org.project.pages.LoginPage;
import org.project.pages.NavigationBarPage;
import org.project.utilities.ReadProperties;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class ForgotPasswordTests extends BasePage {

    private LoginPage loginPage;
    private ForgotPasswordPage forgotPasswordPage;
    private DefineCustomerDetailsPage defineCustomerDetailsPage;
    private ReadProperties readProperties;
    private NavigationBarPage navigationBarPage;
    private final String AUTHENTICATION_TEXT = "AUTHENTICATION";
    private final String CONFIRMATION_EMAIL = "email has been sent";
    private final String INVALID_EMAIL = "Invalid email address.";
    private final String NO_ACCOUNT_REGISTERED_ON_THIS_EMAIL = "There is no account registered for this email address.";
    private final String EMAIL_HAS_BEEN_SENT = "A confirmation email has been sent to your address: patryk.automat999@gmail.com";

    private final By TEST = By.xpath("//div[@class='alert alert-success']");

    @Test(groups = "regression", priority = 1)
    public void forgotPasswordWithEmptyEmailField() {
        loginPage = new LoginPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        navigationBarPage.navigationBarUserSignIn();
        loginPage.clickForgotPasswordButton();
        forgotPasswordPage.clickOnRetrievePasswordButton();

        Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0),INVALID_EMAIL);

    }

    @Test(groups = "regression", priority = 1)
    public void forgotPasswordWithInvalidEmail() throws IOException {
        loginPage = new LoginPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        readProperties = new ReadProperties();
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        navigationBarPage.navigationBarUserSignIn();
        loginPage.clickForgotPasswordButton();
        loginPage.provideEmail(readProperties.readValue("wrongEmail"));
        forgotPasswordPage.clickOnRetrievePasswordButton();

        Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0),NO_ACCOUNT_REGISTERED_ON_THIS_EMAIL);
    }

    @Test(groups = "functional")
    public void forgotPasswordWithValidEmail() throws IOException {
        loginPage = new LoginPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        readProperties = new ReadProperties();
        navigationBarPage = new NavigationBarPage(driver);
        navigationBarPage.navigationBarUserSignIn();
        loginPage.clickForgotPasswordButton();
        loginPage.provideEmail(readProperties.readValue("validEmail"));
        forgotPasswordPage.clickOnRetrievePasswordButton();
        WebElement pp = driver.findElement(By.xpath("//p[contains(text(),'A confirmation email has been sent')]"));

        Assert.assertTrue(pp.getText().contains(CONFIRMATION_EMAIL));

    }

    @Test(groups = "regression", priority = 1)
    public void forgotPasswordBackToLoginPage() {
        loginPage = new LoginPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        navigationBarPage.navigationBarUserSignIn();
        loginPage.clickForgotPasswordButton();
        forgotPasswordPage.clickOnRetrievePasswordButton();
        forgotPasswordPage.clickOnBackToLoginButton();
        WebElement element = driver.findElement(By.xpath("//h1[text()='Authentication']"));

        Assert.assertEquals(element.getText(),AUTHENTICATION_TEXT);
    }

    @Test(dataProvider = "dataProvider", dataProviderClass = DataProv.class, groups = "smoke", priority = 2)    // Data Provider also used here.
    public void differentEmailsFromDataProvider(String data) {
        loginPage = new LoginPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        navigationBarPage.navigationBarUserSignIn();
        loginPage.clickForgotPasswordButton();
        String [] email = data.split(",");
        loginPage.provideEmail(email[0]);
        forgotPasswordPage.clickOnRetrievePasswordButton();


//        if(driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getDomAttribute("class").contains("danger") ||
//                driver.findElement(By.xpath("//p[@class='alert alert-success']")).getText().contains("A confirmation email")) {
//            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getDomAttribute("class").contains("danger") ||
//                    driver.findElement(By.xpath("//p[@class='alert alert-success']")).getText().contains("A confirmation email"));
//        };

//        forgotPasswordPage.testTest(forgotPasswordPage.getErrorMessage(),forgotPasswordPage.getConfirmationMessage());

    }

////        if (forgotPasswordPage.getErrorMessage().getText().contains("Invalid email")) {
//            Assert.assertEquals(forgotPasswordPage.getErrorMessage().getText(), INVALID_EMAIL);
////        } else if (forgotPasswordPage.getErrorMessage().getText().contains("There is no account")) {
//            Assert.assertEquals(forgotPasswordPage.getErrorMessage().getText(), NO_ACCOUNT_REGISTERED_ON_THIS_EMAIL);
////        } else if (!forgotPasswordPage.getConfirmationMessage().) {
//            Assert.assertEquals(forgotPasswordPage.getConfirmationMessage().getText(), EMAIL_HAS_BEEN_SENT);
//            Assert.assertEquals(TEST.);
//        } else {
//            Assert.fail("Assertion failed");
//        }
//
//    }

}
