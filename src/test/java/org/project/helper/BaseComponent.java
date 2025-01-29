package org.project.helper;

import org.openqa.selenium.WebElement;

public class BaseComponent {

    protected WebElement root;


    public BaseComponent(WebElement root) {
        this.root = root;
    }

}
