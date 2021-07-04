package framework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * Developed by Anand Singh on 05/Jul/2021, 12:54 AM.
 * Copyright (c) 2021. All rights reserved.
 */
public class ExtentReportNG {

    static ExtentReports extent;

    public static ExtentReports getReportObject(){
        String path = System.getProperty("user.dir")+"/reports/index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Anand Singh");
        return extent;
    }
}
