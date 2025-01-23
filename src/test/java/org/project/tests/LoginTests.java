package org.project.tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.project.BasePage;
import org.project.helper.DataProv;
import org.project.pages.LoginPage;
import org.project.utilities.ReadProperties;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;


public class LoginTests extends BasePage {


    private ReadProperties readProperties = new ReadProperties(); // class for reading private credentials from separated file

    // Error texts from login page. Used for assertions.
    private final String EMAIL_REQUIRED = "An email address required.";
    private final String PASSWORD_REQUIRED = "Password is required.";
    private final String INVALID_DATA = "Authentication failed.";

    /* I used provider class for learning purpose. With this class I can read the data from JSON file and pass
    throught it. This allow me to reduce another tests however I leave it like that.
     */

    @Test(dataProvider = "dataProvider", dataProviderClass = DataProv.class,dependsOnMethods = "loginToAccountWithoutEmail")
    public void loginsWithDataProvider(String data) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickSignInButtonAtTheBeginning();
        String [] users = data.split(",");
        loginPage.provideEmail(users[0]);
        loginPage.providePassword(users[1]);
        loginPage.clickSubmitLoginButton();

    }

    @Test
    public void loginToAccountWithInvalidData() throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.provideEmail(readProperties.readValue("wrongEmail"));
        loginPage.providePassword(readProperties.readValue("wrongPassword"));
        loginPage.clickSubmitLoginButton();
        WebElement element = driver.findElement(By.xpath("//li[text()='Authentication failed.']"));

        Assert.assertEquals(element.getText(),INVALID_DATA);

    }

    @Test(dependsOnMethods = "loginToAccountWithInvalidData")
    public void loginToAccountWithoutData() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.clickSubmitLoginButton();
        WebElement element = driver.findElement(By.xpath("//li[text()='An email address required.']"));

        Assert.assertEquals(element.getText(),EMAIL_REQUIRED);

    }

    @Test(dependsOnMethods = "loginToAccountWithoutData")
    public void loginToAccountWithoutPassword() throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.provideEmail(readProperties.readValue("wrongEmail"));
        loginPage.clickSubmitLoginButton();
        WebElement element = driver.findElement(By.xpath("//li[text()='Password is required.']"));

        Assert.assertEquals(element.getText(),PASSWORD_REQUIRED);
    }

    @Test(dependsOnMethods = "loginToAccountWithoutPassword")
    public void loginToAccountWithoutEmail() throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.providePassword(readProperties.readValue("wrongPassword"));
        loginPage.clickSubmitLoginButton();
        WebElement element = driver.findElement(By.xpath("//li[text()='An email address required.']"));

        Assert.assertEquals(element.getText(),EMAIL_REQUIRED);
    }

//    @Test
//    public void loginToAccountWithCorrectData() throws InterruptedException {
//        driver.findElement(By.xpath("//a[normalize-space()='Sign in']")).click();
//        driver.findElement(By.id("passwd")).sendKeys("666tester");
//        driver.findElement(By.id("SubmitLogin")).click();
//        Thread.sleep(5000);
//    }

}
