package org.project.tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.project.BasePage;
import org.project.helper.DataProv;
import org.project.pages.DefineCustomerDetailsPage;
import org.project.pages.LoginPage;
import org.project.pages.NavigationBarPage;
import org.project.utilities.ReadProperties;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;


public class LoginTests extends BasePage {


    private ReadProperties readProperties; // class for reading private credentials from separated file
    private LoginPage loginPage;
    private DefineCustomerDetailsPage defineCustomerDetailsPage;
    private NavigationBarPage navigationBarPage;

    // Error texts from login page. Used for assertions.
    private final String EMAIL_REQUIRED = "An email address required.";
    private final String PASSWORD_REQUIRED = "Password is required.";
    private final String INVALID_DATA = "Authentication failed.";
    private final String INVALID_EMAIL = "Invalid email address.";
    private final String INVALID_PASSWORD = "Invalid password.";
    private final String SUCCESSFULLY_LOGGED = "Welcome to your account. Here you can manage all of your personal information and orders.";

    /* I used provider class for learning purpose. With this class I can read the data from JSON file and pass
    throught it. This allow me to reduce another tests however I leave it like that.
     */


    public String getSUCCESSFULLY_LOGGED() {    // returning class field for use it in another tests
        return SUCCESSFULLY_LOGGED;
    }
    public String getINVALID_EMAIL() {
        return INVALID_EMAIL;
    }

    @Test(dataProvider = "dataProvider", dataProviderClass = DataProv.class, groups = "smoke", priority = 1)
    public void loginsWithDataProvider(String data) {
        loginPage = new LoginPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        navigationBarPage.navigationBarUserSignIn();
        String [] users = data.split(",");
        loginPage.loginUser(users[0],users[1]);
        loginPage.clickSubmitLoginButton();

        if(defineCustomerDetailsPage.webElementsError().get(0).contains("address required")) {
            Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0), EMAIL_REQUIRED);
        } else if (defineCustomerDetailsPage.webElementsError().get(0).contains("Password is")) {
            Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0), PASSWORD_REQUIRED);
        } else if (defineCustomerDetailsPage.webElementsError().get(0).contains("Authentication")) {
            Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0), INVALID_DATA);
        } else if (defineCustomerDetailsPage.webElementsError().get(0).contains("Invalid email")) {
            Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0), INVALID_EMAIL);
        } else if (defineCustomerDetailsPage.webElementsError().get(0).contains("Invalid password")) {
            Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0), INVALID_PASSWORD);
        } else {
            Assert.fail("Assertion failed");
        }

    }

    @Test(groups = "regression")
    public void loginToAccountWithInvalidData() throws IOException {
        loginPage = new LoginPage(driver);
        readProperties = new ReadProperties();
        navigationBarPage = new NavigationBarPage(driver);
        navigationBarPage.navigationBarUserSignIn();
        loginPage.loginUser(readProperties.readValue("wrongEmail"),readProperties.readValue("wrongPassword"));
        loginPage.clickSubmitLoginButton();
        WebElement element = driver.findElement(By.xpath("//li[text()='Authentication failed.']"));

        Assert.assertEquals(element.getText(),INVALID_DATA);

    }

    @Test(dependsOnMethods = "loginToAccountWithInvalidData",groups = "regression")
    public void loginToAccountWithoutData() {
        loginPage = new LoginPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        navigationBarPage.navigationBarUserSignIn();
        loginPage.clickSubmitLoginButton();
        WebElement element = driver.findElement(By.xpath("//li[text()='An email address required.']"));

        Assert.assertEquals(element.getText(),EMAIL_REQUIRED);

    }

    @Test(dependsOnMethods = "loginToAccountWithoutData",groups = "regression")
    public void loginToAccountWithoutPassword() throws IOException {
        loginPage = new LoginPage(driver);
        readProperties = new ReadProperties();
        navigationBarPage = new NavigationBarPage(driver);
        navigationBarPage.navigationBarUserSignIn();
        loginPage.loginUser(readProperties.readValue("wrongEmail"),"");
        loginPage.clickSubmitLoginButton();
        WebElement element = driver.findElement(By.xpath("//li[text()='Password is required.']"));

        Assert.assertEquals(element.getText(),PASSWORD_REQUIRED);
    }

    @Test(dependsOnMethods = "loginToAccountWithoutPassword",groups = "regression")
    public void loginToAccountWithoutEmail() throws IOException {
        loginPage = new LoginPage(driver);
        readProperties = new ReadProperties();
        navigationBarPage = new NavigationBarPage(driver);
        navigationBarPage.navigationBarUserSignIn();
        loginPage.loginUser("",readProperties.readValue("wrongPassword"));
        loginPage.clickSubmitLoginButton();
        WebElement element = driver.findElement(By.xpath("//li[text()='An email address required.']"));

        Assert.assertEquals(element.getText(),EMAIL_REQUIRED);
    }

    @Test(groups = "functional")
    public void loginToAccountWithCorrectData() throws IOException {
        loginPage = new LoginPage(driver);
        readProperties = new ReadProperties();
        navigationBarPage = new NavigationBarPage(driver);
        navigationBarPage.navigationBarUserSignIn();
        loginPage.loginUser(readProperties.readValue("validEmail"),readProperties.readValue("validPassword"));
        loginPage.clickSubmitLoginButton();
        WebElement el = driver.findElement(By.xpath("//p[text()='Welcome to your account. Here you can manage all of your personal information and orders.']"));

        Assert.assertEquals(el.getText(),SUCCESSFULLY_LOGGED);
        loginPage.clickSignOutButton();

    }

}
