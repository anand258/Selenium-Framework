package framework;

import PageObjects.ForgotPasswordPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;

/**
 * Developed by Anand Singh on 04/Jul/2021, 4:51 PM.
 * Copyright (c) 2021. All rights reserved.
 */
public class HomePageTest extends Base{

    WebDriver driver;

    public static Logger Log = LogManager.getLogger(Base.class.getName());

    @BeforeMethod
    public void setup() throws IOException {
        driver = initialiseDriver();
        Log.info("driver is initialised");
        driver.get(prop.getProperty("url"));
        Log.info("navigated to the " + prop.getProperty("url"));
    }

    @DataProvider
    public Object[][] getData(){
        Object[][] data = new Object[2][2];
        data[0][0]="restrictedUser@gmail.com";
        data[0][1]="thisIsPassword";
        data[1][0]="unRestrictedUser@gmail.com";
        data[1][1]="thisIsPassword";
        return data;
    }

    @Test(dataProvider="getData")
    public void basePageNavigation(String email, String password) {
        HomePage hp = new HomePage(driver);
        LoginPage lp = hp.getLogin();
        Log.info("clicked to log in");
        lp.getEmail().sendKeys(email);
        Log.info("sent "+email+" email text");
        lp.getPassword().sendKeys(password);
        Log.info("sent "+password+" password text");
        lp.getLogin().click();
        Log.info("clicked on the login button");
        ForgotPasswordPage fp = lp.getForgotPassword();
        fp.getEmailId().sendKeys("xxx");
        fp.getSendMeInstructions().click();
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
        Log.info("browser closed");
    }
}
