package org.project.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UpdateCustomerDetailsPage {


   @FindBy(xpath = ".//span[text()='My personal information']")
   private WebElement customerPersonalInformationButton;
   @FindBy(id = "firstname")
   private WebElement customerFirstNameInput;
   @FindBy(id = "lastname")
   private WebElement customerLastNameInput;
   @FindBy(id = "old_passwd")
   private WebElement customerOldPasswordInput;
   @FindBy(name = "submitIdentity")
   private WebElement saveCustomerPersonalInformationChangesButton;



   public UpdateCustomerDetailsPage(WebDriver driver) {
       PageFactory.initElements(driver, this);

   }

   public void clickOnCustomerPersonalInformation() {
       customerPersonalInformationButton.click();
   }
   public void provideCustomerFirstName(String firstName) {
       customerFirstNameInput.clear();
       customerFirstNameInput.sendKeys(firstName);
   }
   public void clearCustomerFirstName() {
       customerFirstNameInput.clear();
   }
   public void clearCustomerLastName() {
       customerLastNameInput.clear();
   }
   public void provideCustomerOldPassword(String oldPassword) {
       customerOldPasswordInput.sendKeys(oldPassword);
   }
   public void clickOnSaveButton() {
       saveCustomerPersonalInformationChangesButton.click();
   }


}
