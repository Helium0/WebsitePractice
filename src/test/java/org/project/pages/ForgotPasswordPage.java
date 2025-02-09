package org.project.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage {

    @FindBy(xpath = "//span[text()='Retrieve Password']")
    private WebElement retrievePasswordButton;

    @FindBy(xpath = "//a[@title='Back to Login']")
    private WebElement backToLoginButton;

    @FindBy(xpath = "//p[@class='alert alert-success']")
    private WebElement successMessage;

    @FindBy(xpath = "//div[@class='alert alert-danger']//li")
    private WebElement alertMessage;

    public ForgotPasswordPage(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }

    public WebElement getConfirmationMessage() {
        return successMessage;
    }

    public WebElement getErrorMessage() {
        return alertMessage;
    }


    public void clickOnRetrievePasswordButton() {
        retrievePasswordButton.click();
    }

    public void clickOnBackToLoginButton() {
        backToLoginButton.click();
    }

    public boolean systemValidationMessage(WebElement el, WebElement el2) {
        boolean asercja = false;
        try {
            if (el.isDisplayed()) {
                asercja = true;
            }
        } catch (NoSuchElementException e) {
            el = el2;
            asercja = el2.isDisplayed();
        }

        return asercja;
        }

    }



