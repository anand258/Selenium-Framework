package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Developed by Anand Singh on 04/Jul/2021, 5:20 PM.
 * Copyright (c) 2021. All rights reserved.
 */
public class LoginPage {
    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    By emailTextBox = By.id("user_email");
    By passwordTextBox = By.id("user_password");
    By loginButton = By.name("commit");
    By forgotPassword = By.cssSelector("a[href*='password/new']");

    public WebElement getEmail(){
        return driver.findElement(emailTextBox);
    }

    public WebElement getPassword(){
        return driver.findElement(passwordTextBox);
    }

    public WebElement getLogin() {
        return driver.findElement(loginButton);
    }

    public ForgotPasswordPage getForgotPassword() {
        driver.findElement(forgotPassword).click();
        return new ForgotPasswordPage(driver);
    }
}
