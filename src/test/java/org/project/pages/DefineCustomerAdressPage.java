package org.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.project.helper.Customer;


public class DefineCustomerAdressPage {

    @FindBy(id = "address1")
    private WebElement customerAddressInput;
    @FindBy(id = "city")
    private WebElement customerCityInput;
    @FindBy(id = "postcode")
    private WebElement customerPostalCodeInput;
    @FindBy(id = "phone_mobile")
    private WebElement customerMobilePhoneInput;
    @FindBy(name = "alias")
    private WebElement assignCustomerAddressInput;
    @FindBy(xpath = "//span[text()='Add my first address']")
    private WebElement customerFirstAdressButton;
    @FindBy(name = "alias")
    private WebElement customerAssignAddress;
    @FindBy(id = "submitAddress")
    private WebElement saveAddressButton;
    @FindBy(id = "id_state")
    private WebElement selectCustomerState;
    @FindBy(id = "submitAddress")
    private WebElement saveCustomerAddressButton;
    @FindBy(xpath = "//span[text()='My addresses']")
    private WebElement customerAddressesButton;
    @FindBy(xpath = "//span[text()='Delete']")
    private WebElement deleteCustomerAddressButton;



    public DefineCustomerAdressPage(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }


    public void clickOnAddCustomerFirstAddress() {
        customerFirstAdressButton.click();
    }

    public void assignCustomerAddressInput(String address) {
        customerAssignAddress.clear();
        customerAssignAddress.sendKeys(address);
    }

    public WebElement customerState() {
        return selectCustomerState;
    }

    public void clickOnSaveCustomerAddressButton() {
        saveAddressButton.click();
    }

    public void clickOnCustomerAddressesButton() {
        customerAddressesButton.click();
    }

    public void clickDeleteCustomerAddressesButton() {
        deleteCustomerAddressButton.click();
    }

    public void fillCustomerFullAddress(Customer.CustomerBuilder customerBuilder) {
         customerAddressInput.sendKeys(customerBuilder.getCustomerAddress());
         customerCityInput.sendKeys(customerBuilder.getCustomerCity());
         customerPostalCodeInput.sendKeys(customerBuilder.getCustomerPostalCode());
         customerMobilePhoneInput.sendKeys(customerBuilder.getCustomerMobilePhone());
    }

    public void fillCustomerWithoutAddress(Customer.CustomerBuilder customerBuilder) {
        customerCityInput.sendKeys(customerBuilder.getCustomerCity());
        customerPostalCodeInput.sendKeys(customerBuilder.getCustomerPostalCode());
        customerMobilePhoneInput.sendKeys(customerBuilder.getCustomerMobilePhone());
    }

    public void fillCustomerAddressWithoutCity(Customer.CustomerBuilder customerBuilder) {
        customerAddressInput.sendKeys(customerBuilder.getCustomerAddress());
        customerPostalCodeInput.sendKeys(customerBuilder.getCustomerPostalCode());
        customerMobilePhoneInput.sendKeys(customerBuilder.getCustomerMobilePhone());
    }

    public void fillCustomerAddressWithoutPostalCode(Customer.CustomerBuilder customerBuilder) {
        customerAddressInput.sendKeys(customerBuilder.getCustomerAddress());
        customerCityInput.sendKeys(customerBuilder.getCustomerCity());
        customerMobilePhoneInput.sendKeys(customerBuilder.getCustomerMobilePhone());
    }

    public void fillCustomerAddressWithoutMobilePhone(Customer.CustomerBuilder customerBuilder) {
        customerAddressInput.sendKeys(customerBuilder.getCustomerAddress());
        customerCityInput.sendKeys(customerBuilder.getCustomerCity());
        customerPostalCodeInput.sendKeys(customerBuilder.getCustomerPostalCode());
    }


}
