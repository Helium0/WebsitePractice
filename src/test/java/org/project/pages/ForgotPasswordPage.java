package org.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage {

    @FindBy(xpath = "//span[text()='Retrieve Password']")
    private WebElement retrievePasswordButton;

    @FindBy(xpath = "//a[@title='Back to Login']")
    private WebElement backToLoginButton;

    @FindBy(className = ".alert.alert-success")
    private WebElement confirmationMessage;


    public ForgotPasswordPage(WebDriver driver) {
        PageFactory.initElements(driver,this);

    }

    public WebElement getConfirmationMessage() {
        return confirmationMessage;
    }

    public void clickOnRetrievePasswordButton() {
        retrievePasswordButton.click();
    }

    public void clickOnBackToLoginButton() {
        backToLoginButton.click();
    }
}
