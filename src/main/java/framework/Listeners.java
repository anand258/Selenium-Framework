package framework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

/**
 * Developed by Anand Singh on 05/Jul/2021, 12:30 AM.
 * Copyright (c) 2021. All rights reserved.
 */
public class Listeners extends Base implements ITestListener {

    ExtentReports extent = ExtentReportNG.getReportObject();
    ExtentTest test;
    ThreadLocal<ExtentTest> extentPool = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        extentPool.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentPool.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentPool.get().fail(result.getThrowable());
        WebDriver driver = null;
        String testCaseName = result.getMethod().getMethodName();
        try {
            driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            extentPool.get().addScreenCaptureFromPath(getScreenShot(driver,testCaseName), testCaseName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
