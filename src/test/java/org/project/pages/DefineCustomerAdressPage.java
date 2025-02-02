package org.project.pages;

import com.github.javafaker.Faker;
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
    private WebElement customerPostalCode;
    @FindBy(id = "phone_mobile")
    private WebElement customerMobilePhone;
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

    private Faker faker;
    private Customer customer;


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

    public void clickOnSaveAddressButton() {
        saveAddressButton.click();
    }

    public void fillCustomerAddress() {
         customerAddressInput.sendKeys(setCustomerAddress().getCustomerAddress());
         customerCityInput.sendKeys(setCustomerAddress().getCustomerCity());
         customerPostalCode.sendKeys(setCustomerAddress().getCustomerPostalCode());
         customerMobilePhone.sendKeys(setCustomerAddress().getCustomerMobilePhone());
    }

    private Customer setCustomerAddress() {
        customer = new Customer();
        faker = new Faker();
        customer.setCustomerAddress(faker.address().streetAddress());
        customer.setCustomerCity(faker.address().cityName());
        customer.setCustomerPostalCode(customerZipCode());
        customer.setCustomerMobilePhone(faker.phoneNumber().cellPhone());
        return customer;
    }

    private String customerZipCode() {
        faker = new Faker();
        String zip = faker.address().zipCode();
        if(faker.address().zipCode().length() >= 5) {
             zip = faker.address().zipCode().substring(0,5);
        }
        return  zip;
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


}
