package org.project.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.project.BasePage;
import org.project.helper.CustomerHelper;
import org.project.pages.DefineCustomerDetailsPage;
import org.project.pages.LoginPage;
import org.project.pages.NavigationBarPage;
import org.project.utilities.ReadProperties;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;



public class DefineCustomerDetailsTests extends BasePage {

    private LoginPage loginPage;
    private ReadProperties readProperties;
    private DefineCustomerDetailsPage defineCustomerDetailsPage;
    private NavigationBarPage navigationBarPage;
    private CustomerHelper customerHelper;
    private final String FIRST_NAME = "firstname is required.";
    private final String LAST_NAME = "lastname is required.";
    private final String PASSWORD = "passwd is required.";
    private final String ACCOUNT_CREATED = "Your account has been created.";
    private final String ACCOUNT_ON_THIS_EMAIL_ALREADY_REGISTERED = "An account using this email address has already been registered.";



    public String getFIRST_NAME() {  // returning class fields for use it in another tests
        return FIRST_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }


    @Test
    public void createAccountFillPersonalInformation() {
        loginPage = new LoginPage(driver);
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        customerHelper = new CustomerHelper();
        navigationBarPage.navigationBarUserSignIn();
        loginPage.sendEmailAdressField(customerHelper.customerObject().getCustomerEmail());
        loginPage.clickCreateAnAccountButton();
        defineCustomerDetailsPage.clickMaleRadioButton();
        defineCustomerDetailsPage.setCustomerFirstName(customerHelper.customerObject().getCustomerName());
        defineCustomerDetailsPage.setCustomerLastName(customerHelper.customerObject().getCustomerLastName());
        loginPage.providePassword(customerHelper.customerObject().getCustomerPassword());
        defineCustomerDetailsPage.selectCustomerBirth(defineCustomerDetailsPage.dayOfBirth(), customerHelper.customerObject().getCustomerBirthday()[2]);
        defineCustomerDetailsPage.selectCustomerBirth(defineCustomerDetailsPage.monthOfBirth(), customerHelper.customerObject().getCustomerBirthday()[1]);
        defineCustomerDetailsPage.selectCustomerBirth(defineCustomerDetailsPage.yearOfBirth(), customerHelper.customerObject().getCustomerBirthday()[5]);
        defineCustomerDetailsPage.clickRegisterAccountButton();
        WebElement el = driver.findElement(By.xpath("//p[normalize-space()='Your account has been created.']"));

        Assert.assertEquals(el.getText(), ACCOUNT_CREATED);

        loginPage.clickSignOutButton();

    }

    @Test
    public void createAccountFillPersonalInformationWithoutDateBirth() {
        loginPage = new LoginPage(driver);
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        customerHelper = new CustomerHelper();
        navigationBarPage.navigationBarUserSignIn();
        loginPage.sendEmailAdressField(customerHelper.customerObject().getCustomerEmail());
        loginPage.clickCreateAnAccountButton();
        defineCustomerDetailsPage.clickMaleRadioButton();
        defineCustomerDetailsPage.setCustomerFirstName(customerHelper.customerObject().getCustomerName());
        defineCustomerDetailsPage.setCustomerLastName(customerHelper.customerObject().getCustomerLastName());
        loginPage.providePassword(customerHelper.customerObject().getCustomerPassword());
        defineCustomerDetailsPage.clickRegisterAccountButton();
        WebElement el = driver.findElement(By.xpath("//p[normalize-space()='Your account has been created.']"));

        Assert.assertEquals(el.getText(),ACCOUNT_CREATED);

        loginPage.clickSignOutButton();
    }

    @Test
    public void createAccountWithoutAnyPersonalInformation() {
        loginPage = new LoginPage(driver);
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        customerHelper = new CustomerHelper();
        navigationBarPage.navigationBarUserSignIn();
        loginPage.sendEmailAdressField(customerHelper.customerObject().getCustomerEmail());
        loginPage.clickCreateAnAccountButton();
        defineCustomerDetailsPage.clickRegisterAccountButton();

        Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0),LAST_NAME);
        Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(1),FIRST_NAME);
        Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(2),PASSWORD);

    }

    @Test
    public void createAccountWithoutPassword() {
        loginPage = new LoginPage(driver);
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        customerHelper = new CustomerHelper();
        navigationBarPage.navigationBarUserSignIn();
        loginPage.sendEmailAdressField(customerHelper.customerObject().getCustomerEmail());
        loginPage.clickCreateAnAccountButton();
        defineCustomerDetailsPage.clickMaleRadioButton();
        defineCustomerDetailsPage.setCustomerFirstName(customerHelper.customerObject().getCustomerName());
        defineCustomerDetailsPage.setCustomerLastName(customerHelper.customerObject().getCustomerLastName());
        defineCustomerDetailsPage.clickRegisterAccountButton();

        Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0),PASSWORD);

    }

    @Test
    public void createAccountWithoutFirstName() {
        loginPage = new LoginPage(driver);
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        customerHelper = new CustomerHelper();
        navigationBarPage.navigationBarUserSignIn();
        loginPage.sendEmailAdressField(customerHelper.customerObject().getCustomerEmail());
        loginPage.clickCreateAnAccountButton();
        defineCustomerDetailsPage.clickMaleRadioButton();
        defineCustomerDetailsPage.setCustomerLastName(customerHelper.customerObject().getCustomerLastName());
        loginPage.providePassword(customerHelper.customerObject().getCustomerPassword());
        defineCustomerDetailsPage.clickRegisterAccountButton();

        Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0),FIRST_NAME);
    }

    @Test
    public void createAccountWithoutLastName() {
        loginPage = new LoginPage(driver);
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        customerHelper = new CustomerHelper();
        navigationBarPage.navigationBarUserSignIn();
        loginPage.sendEmailAdressField(customerHelper.customerObject().getCustomerEmail());
        loginPage.clickCreateAnAccountButton();
        defineCustomerDetailsPage.clickMaleRadioButton();
        defineCustomerDetailsPage.setCustomerFirstName(customerHelper.customerObject().getCustomerName());
        loginPage.providePassword(customerHelper.customerObject().getCustomerPassword());
        defineCustomerDetailsPage.clickRegisterAccountButton();

        Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0),LAST_NAME);
    }

    @Test
    public void createAccountWithoutFirstNameAndLastName() {
        loginPage = new LoginPage(driver);
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        customerHelper = new CustomerHelper();
        navigationBarPage.navigationBarUserSignIn();
        loginPage.sendEmailAdressField(customerHelper.customerObject().getCustomerEmail());
        loginPage.clickCreateAnAccountButton();
        defineCustomerDetailsPage.clickMaleRadioButton();
        loginPage.providePassword(customerHelper.customerObject().getCustomerPassword());
        defineCustomerDetailsPage.clickRegisterAccountButton();

        Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0),LAST_NAME);
        Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(1),FIRST_NAME);

    }

    @Test
    public void createAccountWithoutFirstNameAndPassword() {
        loginPage = new LoginPage(driver);
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        customerHelper = new CustomerHelper();
        navigationBarPage.navigationBarUserSignIn();
        loginPage.sendEmailAdressField(customerHelper.customerObject().getCustomerEmail());
        loginPage.clickCreateAnAccountButton();
        defineCustomerDetailsPage.clickMaleRadioButton();
        defineCustomerDetailsPage.setCustomerLastName(customerHelper.customerObject().getCustomerEmail());
        defineCustomerDetailsPage.clickRegisterAccountButton();

        Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0),FIRST_NAME);
        Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(1),PASSWORD);

    }

    @Test
    public void createAccountWithExistedEmail() throws IOException {
        loginPage = new LoginPage(driver);
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        navigationBarPage = new NavigationBarPage(driver);
        customerHelper = new CustomerHelper();
        readProperties = new ReadProperties();
        navigationBarPage.navigationBarUserSignIn();
        loginPage.sendEmailAdressField(customerHelper.customerObject().getCustomerEmail());
        loginPage.clickCreateAnAccountButton();
        defineCustomerDetailsPage.clickMaleRadioButton();
        defineCustomerDetailsPage.setCustomerFirstName(customerHelper.customerObject().getCustomerName());
        defineCustomerDetailsPage.setCustomerLastName(customerHelper.customerObject().getCustomerLastName());
        loginPage.provideEmail(readProperties.readValue("validEmail"));
        loginPage.providePassword(customerHelper.customerObject().getCustomerPassword());
        defineCustomerDetailsPage.clickRegisterAccountButton();

        Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0),ACCOUNT_ON_THIS_EMAIL_ALREADY_REGISTERED);

    }
}
