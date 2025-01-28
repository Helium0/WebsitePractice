package org.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {


    @FindBy(xpath = "//a[normalize-space()='Sign in']")
    private WebElement signInButton;
    @FindBy(xpath = "//a[normalize-space()='Sign out']")
    private WebElement signOutButton;

    @FindBy(id = "email")
    private WebElement emailField;
    @FindBy(id = "email_create")
    private WebElement emailCreate;

    @FindBy(id = "passwd")
    private WebElement passwordField;

    @FindBy(id = "SubmitLogin")
    private WebElement submitLoginButton;

    @FindBy(xpath = "//a[text()='Forgot your password?']")
    private WebElement forgotPasswordButton;

    @FindBy(id = "SubmitCreate")
    private WebElement createAnAccountButton;


    public LoginPage (WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public void clickSignInButtonAtTheBeginning() {
        signInButton.click();
    }
    public void clickSignOutButton() {
        signOutButton.click();
    }

    public void provideEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void providePassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickSubmitLoginButton() {
        submitLoginButton.click();
    }
    public void clickForgotPasswordButton() {
        forgotPasswordButton.click();
    }

    public void clickCreateAnAccountButton() {
        createAnAccountButton.click();
    }
    public void sendEmailAdressField(String email) {
        emailCreate.sendKeys(email);
    }
}
