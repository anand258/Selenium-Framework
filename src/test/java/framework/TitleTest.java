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

    public static Logger Log = LogManager.getLogger(Base.class.getName());

    @BeforeMethod
    public void setup() throws IOException {
        driver = initialiseDriver();
        Log.info("driver is initialised");
        driver.get(prop.getProperty("url"));
        Log.info("navigated to the " + prop.getProperty("url"));
    }

    @Test
    public void titleValidation() {
        HomePage hp = new HomePage(driver);
        String title = hp.getFeaturedLecture().getText();
        Log.info("got title as " + title);
        Assert.assertEquals(title, "FEATURED COURSES123");
        Log.info("assertion done for title");
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
        Log.info("browser closed");
    }
}
