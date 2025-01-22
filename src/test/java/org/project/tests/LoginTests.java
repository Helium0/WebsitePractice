package org.project.tests;


import org.project.BasePage;
import org.project.helper.DataProv;
import org.project.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTests extends BasePage {



    /* I used provider class for learning purpose. With this class I can read the data from JSON file and pass
    throught it. This allow me to reduce another tests however I leave it like that.
     */

    @Test(dataProvider = "dataProvider", dataProviderClass = DataProv.class)
    public void loginsWithDataProvider(String data) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickSignInButtonAtTheBeginning();
        String [] users = data.split(",");
        loginPage.provideEmail(users[0]);
        loginPage.providePassword(users[1]);
        loginPage.clickSubmitLoginButton();
    }

    @Test
    public void loginToAccountWithInvalidData() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.provideEmail("patryk.tester666@gmail.com");
        loginPage.providePassword("666tester");
        loginPage.clickSubmitLoginButton();
    }

    @Test
    public void loginToAccountWithoutData() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.clickSubmitLoginButton();
    }

    @Test
    public void loginToAccountWithoutPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.provideEmail("patryk.tester666@gmail.com");
        loginPage.clickSubmitLoginButton();
    }

    @Test
    public void loginToAccountWithoutEmail() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickSignInButtonAtTheBeginning();
        loginPage.providePassword("666tester");
        loginPage.clickSubmitLoginButton();
    }

//    @Test
//    public void loginToAccountWithCorrectData() throws InterruptedException {
//        driver.findElement(By.xpath("//a[normalize-space()='Sign in']")).click();
//        driver.findElement(By.id("passwd")).sendKeys("666tester");
//        driver.findElement(By.id("SubmitLogin")).click();
//        Thread.sleep(5000);
//    }

}
