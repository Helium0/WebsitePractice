package org.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationBarPage {

    @FindBy(css = ".logout")
    private WebElement navigationBarSignOutButton;
    @FindBy(id = "#contact-link")
    private WebElement navigationBarContactUsButton;
    @FindBy(css = ".login")
    private WebElement navigationBarSignIn;


    public NavigationBarPage(WebDriver driver) {
        PageFactory.initElements(driver,this);

    }

    public void navigationBarUserSignIn() {
        navigationBarSignIn.click();
    }

    public void signOutFromNavigationBar() {
        navigationBarSignOutButton.click();
    }

    public void clickContactUsFromNavigationBar() {
        navigationBarContactUsButton.click();
    }


}
