package com.base;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class CoreTestListener extends BaseTest implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {
        saveScreenshot(iTestResult.getName(),driver);
    }
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        saveScreenshot(iTestResult.getName(),driver);
    }


    public byte[] saveScreenshot(String name, WebDriver driver) {
        return (byte[]) ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public void onTestFailure(ITestResult iTestResult) {

        try {


            test.log( Status.FAIL,iTestResult.getThrowable());
            test.addScreenCaptureFromBase64String(CaptureScreen(driver),iTestResult.getName());
            test.log(Status.FAIL, "Screenshot from failed test step: Click on base64-img", MediaEntityBuilder.createScreenCaptureFromBase64String(CaptureScreen(driver)).build());
            saveScreenshot(iTestResult.getName(),driver);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
    
}
