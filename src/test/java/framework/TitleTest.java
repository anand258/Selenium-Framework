package framework;

import PageObjects.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.IOException;

/**
 * Developed by Anand Singh on 04/Jul/2021, 6:07 PM.
 * Copyright (c) 2021. All rights reserved.
 */
public class TitleTest extends Base {

    WebDriver driver;

    HomePage hp;

    public static Logger Log = LogManager.getLogger(Base.class.getName());

    @BeforeTest
    public void setup() throws IOException {
        driver = initialiseDriver();
        Log.info("driver is initialised");
        driver.get(prop.getProperty("url"));
        Log.info("navigated to the " + prop.getProperty("url"));
    }

    @Test(priority = 1)
    public void titleValidation() {
        hp = new HomePage(driver);
        String title = hp.getFeaturedLecture().getText();
        Log.info("got title as " + title);
        Assert.assertEquals(title, "FEATURED COURSES123");
        Log.info("assertion done for title");
    }

    @Test(priority = 2)
    public void headerTitleValidation(){
        String headerTitle = hp.getHeaderText().getText();
        System.out.println(hp.getHeaderText().isDisplayed());
        Log.info("got header title as " + headerTitle);
        Assert.assertEquals(headerTitle, "AN ACADEMY TO LEARN EVERYTHING ABOUT TESTING");
        Log.info("assertion done for header title");
    }

    @AfterTest
    public void tearDown(){
        driver.close();
        Log.info("browser closed");
    }
}
