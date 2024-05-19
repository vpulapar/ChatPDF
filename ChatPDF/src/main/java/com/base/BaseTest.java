package com.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.engine.ObjectsEngine;
import com.utilities.TestContext;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.time.Duration;

public class BaseTest extends PageObjects {

    public static ExtentReports extent;
    public static ExtentTest test;

    public static int timeToWaitElementLoad = Integer.parseInt(TestContext.getTimeToWaitElementLoad());
    public static WebDriverWait wait;
    public ObjectsEngine objectsEngine;

    public static org.apache.logging.log4j.Logger log = LogManager.getLogger();

    @Parameters("RunMode")
    @BeforeSuite(alwaysRun = true)
    public void setUp(@Optional("web") String runMode) {

        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/src/main/reports/ChatPDF_Report.html");
        extent.attachReporter(spark);
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeToWaitElementLoad));
        driver.manage().window().maximize();
        driver.get(TestContext.getWebAppUrl());
    }


    @Parameters({"RunMode", "CodeDownload"})
    @BeforeMethod(alwaysRun = true)
    public void beforeTest(@Optional("web") String runMode,@Optional("no") String CodeDownload) throws Throwable {
        System.out.println("===BeforeMethod Start=======");


        System.out.println("===BeforeMethod End=======");
    }

    @Parameters({"RunMode", "CodeDownload"})
    @AfterMethod(alwaysRun = true)
    public void getResult(@Optional("web") String runMode,ITestResult result,@Optional("no") String CodeDownload) throws Throwable {

        System.out.println("===AfterMethod Start=======");

        if (result.getStatus() == ITestResult.FAILURE) {

            System.out.println(result.getMethod().getMethodName() + ": Status - Failed");
            log.info("Test Case : " + result.getMethod().getMethodName());
            log.info("Test Case : Failed");
            log.error(result.getThrowable().toString());
            System.out.println(result.getThrowable().toString());
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:", ExtentColor.RED));
            test.fail(result.getThrowable());

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println(result.getMethod().getMethodName() + " : Status - Passed");
            log.info("Test Case : " + result.getMethod().getMethodName());
            log.info("Test Case : Passed");
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
        } else if (result.getStatus() == ITestResult.SKIP) {
            System.out.println(result.getMethod().getMethodName() + ": Status - Skipped");
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
            log.info("Test Case : " + result.getMethod().getMethodName());
            log.info("Test Case : Skipped");
        } else {
            log.info("Test Case : " + result.getMethod().getMethodName());
            log.info("Test Case : Skipped");
            test.log(Status.SKIP, "Error occurred..");
        }


        System.out.println("Test Case Completed======" + result.getMethod().getMethodName() + "=======");
        System.out.println("===AfterMethod End=======");
    }


    @AfterSuite(alwaysRun = true)
    @Parameters({"RunMode"})
    public void tearDown(@Optional("web")String runMode) {
        extent.flush();
        if(driver!=null)
            DriverFactory.quitWebBrowser(); //Quitting Driver
    }

    public static String CaptureScreen(WebDriver driver) {
        TakesScreenshot newScreen = (TakesScreenshot) driver;
        String scnShot = newScreen.getScreenshotAs(OutputType.BASE64);
        return "data:image/png;base64," + scnShot;
    }


}
