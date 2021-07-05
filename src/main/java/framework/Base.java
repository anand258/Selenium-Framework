package framework;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Developed by Anand Singh on 04/Jul/2021, 4:40 PM.
 * Copyright (c) 2021. All rights reserved.
 */
public class Base {
    public WebDriver driver;
    public Properties prop;
    public WebDriver initialiseDriver() throws IOException {
        prop = new Properties();
        FileInputStream fin = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/data.properties");
        prop.load(fin);
        String browserName = prop.getProperty("browser"); // to read browser name from property file
        //String browserName = System.getProperty("browser"); // to read browser name from mvn cmd for jenkins
        if(browserName.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/resources/chromedriver");
            driver = new ChromeDriver();
        } else if(browserName.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/src/main/resources/geckodriver");
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }

    public String getScreenShot(WebDriver driver, String testCaseName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir")+"/reports/"+testCaseName+".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
    }
}
