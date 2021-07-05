package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Developed by Anand Singh on 05/Jul/2021, 1:45 AM.
 * Copyright (c) 2021. All rights reserved.
 */
public class ForgotPasswordPage {
    public WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    private By emailIdTextBox = By.id("user_email");
    private By sendMeButton = By.name("commit");

    public WebElement getEmailId(){
        return driver.findElement(emailIdTextBox);
    }

    public WebElement getSendMeInstructions(){
        return driver.findElement(sendMeButton);
    }
}
