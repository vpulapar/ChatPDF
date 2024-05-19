package com.base;

import org.openqa.selenium.WebDriver;

public class PageObjects {
    public static WebDriver driver;
    public PageObjects()
    {
        driver= DriverFactory.getDriver();

    }

}