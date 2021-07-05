package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Developed by Anand Singh on 04/Jul/2021, 5:20 PM.
 * Copyright (c) 2021. All rights reserved.
 */
public class HomePage {
    public WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private By loginButton = By.cssSelector("a[href*='sign_in']");
    private By featuredLectureText = By.cssSelector("section[id='content'] h2");
    private By navigationBar = By.cssSelector(".nav.navbar-nav.navbar-right");

    public LoginPage getLogin() {
        driver.findElement(loginButton).click();
        return new LoginPage(driver);
    }

    public WebElement getFeaturedLecture(){
        return driver.findElement(featuredLectureText);
    }

    public WebElement getNavigationBar(){
        return driver.findElement(navigationBar);
    }

}
