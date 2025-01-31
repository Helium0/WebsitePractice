package org.project.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.project.BasePage;
import org.project.helper.ProjectHelper;
import org.project.pages.DefineCustomerDetailsPage;
import org.project.pages.LoginPage;
import org.project.pages.NavigationBarPage;
import org.project.pages.UpdateCustomerDetailsPage;
import org.project.utilities.ReadProperties;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdateCustomerDetailsTests extends BasePage {

    private LoginPage loginPage;
    private LoginTests loginTests;
    private ReadProperties readProperties;
    private ProjectHelper projectHelper;
    private NavigationBarPage navigationBarPage;
    private UpdateCustomerDetailsPage updateCustomerDetailsPage;
    private DefineCustomerDetailsPage defineCustomerDetailsPage;
    private DefineCustomerDetailsTests defineCustomerDetailsTests;
    private final String NEW_USER_NAME = "Updated";
    private final String SUCCESFULLY_DATA_CHANGE = "Your personal information has been successfully updated.";
    private final String INCORRECT_PASSWORD = "The password you entered is incorrect.";


    @Test
    public void updateUserFirstName() throws IOException {
        loginPage = new LoginPage(driver);
        updateCustomerDetailsPage = new UpdateCustomerDetailsPage(driver);
        readProperties = new ReadProperties();
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.provideEmail(readProperties.readValue("validEmail"));
        loginPage.providePassword(readProperties.readValue("validPassword"));
        loginPage.clickSubmitLoginButton();
        updateCustomerDetailsPage.clickOnCustomerPersonalInformation();
        updateCustomerDetailsPage.provideCustomerFirstName(NEW_USER_NAME);
        updateCustomerDetailsPage.provideCustomerOldPassword(readProperties.readValue("validPassword"));
        updateCustomerDetailsPage.clickOnSaveButton();


        Assert.assertEquals(driver.findElement(By.xpath("//p[@class='alert alert-success']")).getText(), SUCCESFULLY_DATA_CHANGE);
        Assert.assertEquals(driver.findElement(By.xpath("//a[@class='account']")).getText(), NEW_USER_NAME+" "+readProperties.readValue("validLastName"));

        loginPage.clickSignOutButton();
    }


    @Test(dependsOnMethods = "updateUserFirstName")
    public void revertCustomerSurname() throws IOException {
        loginPage = new LoginPage(driver);
        updateCustomerDetailsPage = new UpdateCustomerDetailsPage(driver);
        readProperties = new ReadProperties();
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.provideEmail(readProperties.readValue("validEmail"));
        loginPage.providePassword(readProperties.readValue("validPassword"));
        loginPage.clickSubmitLoginButton();
        updateCustomerDetailsPage.clickOnCustomerPersonalInformation();
        updateCustomerDetailsPage.provideCustomerFirstName(readProperties.readValue("validFirstName"));
        updateCustomerDetailsPage.provideCustomerOldPassword(readProperties.readValue("validPassword"));
        updateCustomerDetailsPage.clickOnSaveButton();

        loginPage.clickSignOutButton();
    }

    @Test
    public void updateCustomerDetailsWithoutPassword() throws IOException {
        loginPage = new LoginPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        updateCustomerDetailsPage = new UpdateCustomerDetailsPage(driver);
        readProperties = new ReadProperties();
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.provideEmail(readProperties.readValue("validEmail"));
        loginPage.providePassword(readProperties.readValue("validPassword"));
        loginPage.clickSubmitLoginButton();
        updateCustomerDetailsPage.clickOnCustomerPersonalInformation();
        updateCustomerDetailsPage.clickOnSaveButton();

        Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0), INCORRECT_PASSWORD);
        navigationBarPage.signOutFromNavigationBar();

    }

    @Test
    public void updateCustomerDetailsWhenClearedFirstName() throws IOException {
        loginPage = new LoginPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        updateCustomerDetailsPage = new UpdateCustomerDetailsPage(driver);
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        defineCustomerDetailsTests = new DefineCustomerDetailsTests();
        readProperties = new ReadProperties();
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.provideEmail(readProperties.readValue("validEmail"));
        loginPage.providePassword(readProperties.readValue("validPassword"));
        loginPage.clickSubmitLoginButton();
        updateCustomerDetailsPage.clickOnCustomerPersonalInformation();
        updateCustomerDetailsPage.clearCustomerFirstName();
        updateCustomerDetailsPage.provideCustomerOldPassword(readProperties.readValue("validPassword"));
        updateCustomerDetailsPage.clickOnSaveButton();

        Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0), defineCustomerDetailsTests.getFIRST_NAME());
        navigationBarPage.signOutFromNavigationBar();
    }

    @Test
    public void updateCustomerDetailsWhenClearedLastName() throws IOException {
        loginPage = new LoginPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        updateCustomerDetailsPage = new UpdateCustomerDetailsPage(driver);
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        defineCustomerDetailsTests = new DefineCustomerDetailsTests();
        readProperties = new ReadProperties();
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.provideEmail(readProperties.readValue("validEmail"));
        loginPage.providePassword(readProperties.readValue("validPassword"));
        loginPage.clickSubmitLoginButton();
        updateCustomerDetailsPage.clickOnCustomerPersonalInformation();
        updateCustomerDetailsPage.clearCustomerLastName();
        updateCustomerDetailsPage.provideCustomerOldPassword(readProperties.readValue("validPassword"));
        updateCustomerDetailsPage.clickOnSaveButton();

        Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0), defineCustomerDetailsTests.getLAST_NAME());
        navigationBarPage.signOutFromNavigationBar();
    }

    @Test
    public void updateCustomerDetailsWhenClearedFirstNameAndLastName() throws IOException {
        loginPage = new LoginPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        updateCustomerDetailsPage = new UpdateCustomerDetailsPage(driver);
        projectHelper = new ProjectHelper();
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        defineCustomerDetailsTests = new DefineCustomerDetailsTests();
        readProperties = new ReadProperties();
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.provideEmail(readProperties.readValue("validEmail"));
        loginPage.providePassword(readProperties.readValue("validPassword"));
        loginPage.clickSubmitLoginButton();
        updateCustomerDetailsPage.clickOnCustomerPersonalInformation();
        updateCustomerDetailsPage.clearCustomerFirstName();
        updateCustomerDetailsPage.clearCustomerLastName();
        updateCustomerDetailsPage.provideCustomerOldPassword(readProperties.readValue("validPassword"));
        updateCustomerDetailsPage.clickOnSaveButton();

        projectHelper.softAssert().assertEquals(defineCustomerDetailsPage.webElementsError().get(0),defineCustomerDetailsTests.getFIRST_NAME());
        projectHelper.softAssert().assertEquals(defineCustomerDetailsPage.webElementsError().get(1),defineCustomerDetailsTests.getLAST_NAME());
        projectHelper.softAssert().assertAll();
        navigationBarPage.signOutFromNavigationBar();

    }

    @Test
    public void updateCustomerDetailsWithWrongPassword() throws IOException {
        loginPage = new LoginPage(driver);
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        updateCustomerDetailsPage = new UpdateCustomerDetailsPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        readProperties = new ReadProperties();
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.provideEmail(readProperties.readValue("validEmail"));
        loginPage.providePassword(readProperties.readValue("validPassword"));
        loginPage.clickSubmitLoginButton();
        updateCustomerDetailsPage.clickOnCustomerPersonalInformation();
        updateCustomerDetailsPage.provideCustomerOldPassword(readProperties.readValue("wrongPassword"));
        updateCustomerDetailsPage.clickOnSaveButton();

        Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0), INCORRECT_PASSWORD);
        navigationBarPage.signOutFromNavigationBar();
    }

    @Test
    public void backToHomePage() throws IOException {
        loginPage = new LoginPage(driver);
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        updateCustomerDetailsPage = new UpdateCustomerDetailsPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        readProperties = new ReadProperties();
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.provideEmail(readProperties.readValue("validEmail"));
        loginPage.providePassword(readProperties.readValue("validPassword"));
        loginPage.clickSubmitLoginButton();
        updateCustomerDetailsPage.clickOnCustomerPersonalInformation();
        driver.findElement(By.xpath("//span[normalize-space()='Home']")).click();
        boolean homePage = driver.findElement(By.id("home-page-tabs")).isDisplayed();

        Assert.assertTrue(homePage);
        navigationBarPage.signOutFromNavigationBar();

    }

    @Test
    public void backToCustomerAccountDetails() throws IOException {
        loginTests = new LoginTests();
        loginPage = new LoginPage(driver);
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        updateCustomerDetailsPage = new UpdateCustomerDetailsPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        readProperties = new ReadProperties();
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.provideEmail(readProperties.readValue("validEmail"));
        loginPage.providePassword(readProperties.readValue("validPassword"));
        loginPage.clickSubmitLoginButton();
        updateCustomerDetailsPage.clickOnCustomerPersonalInformation();
        driver.findElement(By.xpath("//span[normalize-space()='Back to your account']")).click();
        WebElement el = driver.findElement(By.xpath("//p[text()='Welcome to your account. Here you can manage all of your personal information and orders.']"));

        Assert.assertEquals(el.getText(),loginTests.getSUCCESSFULLY_LOGGED());
        navigationBarPage.signOutFromNavigationBar();

    }

}
