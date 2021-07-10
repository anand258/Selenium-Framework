package framework;

import PageObjects.ForgotPasswordPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Developed by Anand Singh on 04/Jul/2021, 4:51 PM.
 * Copyright (c) 2021. All rights reserved.
 */
public class HomePageTestUsingDb extends Base{

    WebDriver driver;

    public static Logger Log = LogManager.getLogger(Base.class.getName());

    @BeforeTest
    public void setup() throws IOException {
        driver = initialiseDriver();
        Log.info("driver is initialised");
    }

    @DataProvider
    public Object[][] getData() throws SQLException, IOException {
        JdbcConnection obj  = new JdbcConnection();

        ResultSet res = obj.connect();
        int size = 0, counter = 0;
        if (res.last()) {
            size = res.getRow();
            res.beforeFirst();
        }
        Object[][] data = new Object[size][2];
        while (res.next()) {
            if(counter<size){
                data[counter][0] = res.getString("username");
                data[counter][1] = res.getString("password");
            }
            counter++;
        }
        return data;
    }

    @Test(dataProvider="getData")
    public void basePageNavigation(String email, String password) {
        driver.get(prop.getProperty("url"));
        Log.info("navigated to the " + prop.getProperty("url"));
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

    @AfterTest
    public void tearDown(){
        driver.close();
        Log.info("browser closed");
    }
}
