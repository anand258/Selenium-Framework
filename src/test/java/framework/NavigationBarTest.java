package framework;

import PageObjects.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

/**
 * Developed by Anand Singh on 04/Jul/2021, 6:21 PM.
 * Copyright (c) 2021. All rights reserved.
 */
public class NavigationBarTest extends Base{

    WebDriver driver;

    public static Logger Log = LogManager.getLogger(Base.class.getName());

    @BeforeTest
    public void setup() throws IOException {
        driver = initialiseDriver();
        Log.info("driver is initialised");
        driver.get(prop.getProperty("url"));
        Log.info("navigated to the " + prop.getProperty("url"));
    }

    @Test
    public void navigationBarValidation() {
        HomePage hp = new HomePage(driver);
        Assert.assertTrue(hp.getNavigationBar().isDisplayed());
        Log.info("Assertion done for navigation bar");
    }

    @AfterTest
    public void tearDown(){
        driver.close();
        Log.info("browser closed");
    }
}
