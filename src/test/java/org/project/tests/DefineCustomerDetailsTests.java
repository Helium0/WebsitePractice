package org.project.tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.project.BasePage;
import org.project.helper.Customer;
import org.project.helper.ProjectHelper;
import org.project.pages.DefineCustomerDetailsPage;
import org.project.pages.LoginPage;
import org.project.utilities.ReadProperties;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;



public class DefineCustomerDetailsTests extends BasePage {

    private LoginPage loginPage;
    private ReadProperties readProperties;
    private DefineCustomerDetailsPage defineCustomerDetailsPage;
    private Customer customer;
    private Faker faker;
    private ProjectHelper projectHelper;
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
    public void createAccountFillPersonalInformation() throws IOException, InterruptedException {
        loginPage = new LoginPage(driver);
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        customer = new Customer();
        faker = new Faker();
        loginPage.clickSignInButtonAtTheBeginning();
        customer.setCustomerEmail(faker.internet().emailAddress());
        loginPage.sendEmailAdressField(customer.getCustomerEmail());
        loginPage.clickCreateAnAccountButton();
        defineCustomerDetailsPage.clickMaleRadioButton();
        customer.setCustomerName(faker.name().name());
        defineCustomerDetailsPage.setCustomerFirstName(customer.getCustomerName());
        customer.setCustomerLastName(faker.name().lastName());
        defineCustomerDetailsPage.setCustomerLastName(customer.getCustomerLastName());
        customer.setCustomerBirthday(customer.tab());
        customer.setCustomerPassword(faker.internet().password());
        loginPage.providePassword(customer.getCustomerPassword());
        defineCustomerDetailsPage.selectCustomerBirth(defineCustomerDetailsPage.dayOfBirth(), customer.getCustomerBirthday()[2]);
        defineCustomerDetailsPage.selectCustomerBirth(defineCustomerDetailsPage.monthOfBirth(), customer.getCustomerBirthday()[1]);
        defineCustomerDetailsPage.selectCustomerBirth(defineCustomerDetailsPage.yearOfBirth(), customer.getCustomerBirthday()[5]);
        defineCustomerDetailsPage.clickRegisterAccountButton();
        WebElement el = driver.findElement(By.xpath("//p[normalize-space()='Your account has been created.']"));


        Assert.assertEquals(el.getText(),ACCOUNT_CREATED);

        loginPage.clickSignOutButton();


    }

    @Test
    public void createAccountFillPersonalInformationWithoutDateBirth() {
        loginPage = new LoginPage(driver);
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        customer = new Customer();
        faker = new Faker();
        loginPage.clickSignInButtonAtTheBeginning();
        customer.setCustomerEmail(faker.internet().emailAddress());
        loginPage.sendEmailAdressField(customer.getCustomerEmail());
        loginPage.clickCreateAnAccountButton();
        defineCustomerDetailsPage.clickMaleRadioButton();
        customer.setCustomerName(faker.name().name());
        defineCustomerDetailsPage.setCustomerFirstName(customer.getCustomerName());
        customer.setCustomerLastName(faker.name().lastName());
        defineCustomerDetailsPage.setCustomerLastName(customer.getCustomerLastName());
        customer.setCustomerBirthday(customer.tab());
        customer.setCustomerPassword(faker.internet().password());
        loginPage.providePassword(customer.getCustomerPassword());
        defineCustomerDetailsPage.clickRegisterAccountButton();
        WebElement el = driver.findElement(By.xpath("//p[normalize-space()='Your account has been created.']"));

        Assert.assertEquals(el.getText(),ACCOUNT_CREATED);

        loginPage.clickSignOutButton();
    }

    @Test
    public void createAccountWithoutAnyPersonalInformation() {
        loginPage = new LoginPage(driver);
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        customer = new Customer();
        faker = new Faker();
        projectHelper = new ProjectHelper();
        loginPage.clickSignInButtonAtTheBeginning();
        customer.setCustomerEmail(faker.internet().emailAddress());
        loginPage.sendEmailAdressField(customer.getCustomerEmail());
        loginPage.clickCreateAnAccountButton();
        defineCustomerDetailsPage.clickRegisterAccountButton();

        projectHelper.softAssert();
        projectHelper.softAssert().assertEquals(defineCustomerDetailsPage.webElementsError().get(0),LAST_NAME);
        projectHelper.softAssert().assertEquals(defineCustomerDetailsPage.webElementsError().get(1),FIRST_NAME);
        projectHelper.softAssert().assertEquals(defineCustomerDetailsPage.webElementsError().get(2),PASSWORD);
        projectHelper.softAssert().assertAll();

    }

    @Test
    public void createAccountWithoutPassword() throws InterruptedException {
        loginPage = new LoginPage(driver);
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        customer = new Customer();
        faker = new Faker();
        projectHelper = new ProjectHelper();
        loginPage.clickSignInButtonAtTheBeginning();
        customer.setCustomerEmail(faker.internet().emailAddress());
        loginPage.sendEmailAdressField(customer.getCustomerEmail());
        loginPage.clickCreateAnAccountButton();
        defineCustomerDetailsPage.clickMaleRadioButton();
        customer.setCustomerName(faker.name().name());
        defineCustomerDetailsPage.setCustomerFirstName(customer.getCustomerName());
        customer.setCustomerLastName(faker.name().lastName());
        defineCustomerDetailsPage.setCustomerLastName(customer.getCustomerLastName());
        defineCustomerDetailsPage.clickRegisterAccountButton();

        Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0),PASSWORD);

    }

    @Test
    public void createAccountWithoutFirstName() {
        loginPage = new LoginPage(driver);
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        customer = new Customer();
        faker = new Faker();
        projectHelper = new ProjectHelper();
        loginPage.clickSignInButtonAtTheBeginning();
        customer.setCustomerEmail(faker.internet().emailAddress());
        loginPage.sendEmailAdressField(customer.getCustomerEmail());
        loginPage.clickCreateAnAccountButton();
        defineCustomerDetailsPage.clickMaleRadioButton();
        customer.setCustomerLastName(faker.name().lastName());
        defineCustomerDetailsPage.setCustomerLastName(customer.getCustomerLastName());
        customer.setCustomerPassword(faker.internet().password());
        loginPage.providePassword(customer.getCustomerPassword());
        defineCustomerDetailsPage.clickRegisterAccountButton();

        Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0),FIRST_NAME);
    }

    @Test
    public void createAccountWithoutLastName() {
        loginPage = new LoginPage(driver);
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        customer = new Customer();
        faker = new Faker();
        projectHelper = new ProjectHelper();
        loginPage.clickSignInButtonAtTheBeginning();
        customer.setCustomerEmail(faker.internet().emailAddress());
        loginPage.sendEmailAdressField(customer.getCustomerEmail());
        loginPage.clickCreateAnAccountButton();
        defineCustomerDetailsPage.clickMaleRadioButton();
        customer.setCustomerName(faker.name().name());
        defineCustomerDetailsPage.setCustomerFirstName(customer.getCustomerName());
        customer.setCustomerPassword(faker.internet().password());
        loginPage.providePassword(customer.getCustomerPassword());
        defineCustomerDetailsPage.clickRegisterAccountButton();

        Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0),LAST_NAME);
    }

    @Test
    public void createAccountWithoutFirstNameAndLastName() {
        loginPage = new LoginPage(driver);
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        customer = new Customer();
        faker = new Faker();
        projectHelper = new ProjectHelper();
        loginPage.clickSignInButtonAtTheBeginning();
        customer.setCustomerEmail(faker.internet().emailAddress());
        loginPage.sendEmailAdressField(customer.getCustomerEmail());
        loginPage.clickCreateAnAccountButton();
        defineCustomerDetailsPage.clickMaleRadioButton();
        customer.setCustomerPassword(faker.internet().password());
        loginPage.providePassword(customer.getCustomerPassword());
        defineCustomerDetailsPage.clickRegisterAccountButton();

        projectHelper.softAssert().assertEquals(defineCustomerDetailsPage.webElementsError().get(0),FIRST_NAME);
        projectHelper.softAssert().assertEquals(defineCustomerDetailsPage.webElementsError().get(1),LAST_NAME);
        projectHelper.softAssert().assertAll();
    }

    @Test
    public void createAccountWithoutFirstNameAndPassword() {
        loginPage = new LoginPage(driver);
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        customer = new Customer();
        faker = new Faker();
        projectHelper = new ProjectHelper();
        loginPage.clickSignInButtonAtTheBeginning();
        customer.setCustomerEmail(faker.internet().emailAddress());
        loginPage.sendEmailAdressField(customer.getCustomerEmail());
        loginPage.clickCreateAnAccountButton();
        defineCustomerDetailsPage.clickMaleRadioButton();
        customer.setCustomerLastName(faker.name().lastName());
        defineCustomerDetailsPage.setCustomerLastName(customer.getCustomerLastName());
        defineCustomerDetailsPage.clickRegisterAccountButton();

        projectHelper.softAssert().assertEquals(defineCustomerDetailsPage.webElementsError().get(0),FIRST_NAME);
        projectHelper.softAssert().assertEquals(defineCustomerDetailsPage.webElementsError().get(1),PASSWORD);
        projectHelper.softAssert().assertAll();
    }

    @Test
    public void createAccountWithExistedEmail() throws IOException {
        loginPage = new LoginPage(driver);
        defineCustomerDetailsPage = new DefineCustomerDetailsPage(driver);
        customer = new Customer();
        faker = new Faker();
        projectHelper = new ProjectHelper();
        readProperties = new ReadProperties();
        loginPage.clickSignInButtonAtTheBeginning();
        customer.setCustomerEmail(faker.internet().emailAddress());
        loginPage.sendEmailAdressField(customer.getCustomerEmail());
        loginPage.clickCreateAnAccountButton();
        defineCustomerDetailsPage.clickMaleRadioButton();
        customer.setCustomerName(faker.name().name());
        defineCustomerDetailsPage.setCustomerFirstName(customer.getCustomerName());
        customer.setCustomerLastName(faker.name().lastName());
        defineCustomerDetailsPage.setCustomerLastName(customer.getCustomerLastName());
        loginPage.provideEmail(readProperties.readValue("validEmail"));
        customer.setCustomerPassword(faker.internet().password());
        loginPage.providePassword(customer.getCustomerPassword());
        defineCustomerDetailsPage.clickRegisterAccountButton();

        Assert.assertEquals(defineCustomerDetailsPage.webElementsError().get(0),ACCOUNT_ON_THIS_EMAIL_ALREADY_REGISTERED);

    }

}
