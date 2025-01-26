package org.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateAccountPage {


    @FindBy(id = "id_gender1")
    private WebElement maleRadioButton;
    @FindBy(id = "customer_firstname")
    private WebElement customerFirstName;
    @FindBy(id = "customer_lastname")
    private WebElement customerLastName;
    @FindBy(id = "days")
    private WebElement birthDay;
    @FindBy(id = "months")
    private WebElement birthMonth;
    @FindBy(id = "years")
    private WebElement birthYear;
    @FindBy(id = "newsletter")
    private WebElement newsletterCheckbox;
    @FindBy(id = "submitAccount")
    private WebElement registerAccountButton;


    public CreateAccountPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public void clickMaleRadioButton() {
        maleRadioButton.click();
    }

    public void setCustomerFirstName(String firstName) {
        customerFirstName.sendKeys(firstName);
    }

    public void setCustomerLastName(String lastName) {
        customerLastName.sendKeys(lastName);
    }
    public void clickRegisterAccountButton() {
        registerAccountButton.click();
    }
    public WebElement dayOfBirth() {
        return birthDay;
    }
    public WebElement monthOfBirth() {
        return birthMonth;
    }
    public WebElement yearOfBirth() {
        return birthYear;
    }
    private Select select(WebElement element) {
        return new Select(element);
    }

    public void selectCustomerBirth(WebElement element, String value) {
        select(element).getOptions().stream().filter(a->a.getText().trim().contains(value)).forEach(a->a.click());
    }

}
