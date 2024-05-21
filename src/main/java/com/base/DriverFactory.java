package com.base;

import com.utilities.TestContext;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class DriverFactory {

    //private static ChromeDriver driver;
    private static WebDriver driver;

    public static String browserType="";

    //Do nothing
    private DriverFactory()
    {

    }
    public static WebDriver getDriver() {

        String jenkinsBrowser = System.getenv("Jenkins_Browser");
        if (jenkinsBrowser == null) {
            browserType= TestContext.getBrowser();
            System.out.println("Select browser is :"+browserType);
        }
        else{
            browserType=jenkinsBrowser;
            System.out.println("Select browser is :"+browserType);
        }
        if (null == driver) {
            try {
                if (browserType.equalsIgnoreCase("edge"))
                {
                    //ChromeDriverManager.getInstance().browserVersion("89.0.4389.23");
                    EdgeOptions options = new EdgeOptions();
                    HashMap<String, Integer> contentSettings=new HashMap<String, Integer>();
                    HashMap<String, Object> profile=new HashMap<String, Object>();
                    HashMap<String, Object> prefs=new HashMap<String, Object>();
                    contentSettings.put("media_stream",1);
                    profile.put("managed_default_content_settings",contentSettings);
                    prefs.put("profile",profile);
                    options.setExperimentalOption("prefs",prefs);
                    WebDriverManager.chromedriver().setup();
                    prefs.put("download.default_directory",System.getProperty("user.dir") + File.separator + "externalFiles" + File.separator + "downloadFiles");
                    options.setExperimentalOption("prefs", prefs);
                    options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                    options.addArguments("window-size=1920,1080");
                    //options.addArguments("headless");
                    options.addArguments("disable-gpu");
                    options.addArguments("--remote-allow-origins=*");
                    driver = new EdgeDriver(options);
                    System.out.println("Edge browser started..");

                    return driver;
                }
                else if (browserType.equalsIgnoreCase("chrome"))
                {
                    //ChromeDriverManager.getInstance().browserVersion("89.0.4389.23");
                    ChromeOptions options = new ChromeOptions();
                    HashMap<String, Integer> contentSettings=new HashMap<String, Integer>();
                    HashMap<String, Object> profile=new HashMap<String, Object>();
                    HashMap<String, Object> prefs=new HashMap<String, Object>();
                    contentSettings.put("media_stream",1);
                    profile.put("managed_default_content_settings",contentSettings);
                    prefs.put("profile",profile);
                    options.setExperimentalOption("prefs",prefs);
                    WebDriverManager.chromedriver().clearDriverCache().setup();
                    //prefs.put("download.default_directory","C:\\devops-artifacts");
                    prefs.put("download.default_directory",System.getProperty("user.dir") + File.separator + "externalFiles" + File.separator + "downloadFiles");
                    //options.addArguments("--start-fullscreen");
                    options.setExperimentalOption("prefs", prefs);
                    options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                    options.addArguments("--remote-allow-origins=*");
                    options.addArguments("disable-infobars");
                    options.addArguments("--lang=en");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--start-maximized");
                    options.addArguments("--disable-popup-blocking");
                    driver = new ChromeDriver(options);
                    System.out.println("Chrome browser started..");

                    return driver;
                }
                else if (browserType.equalsIgnoreCase("ChromeHeadless")) {
                    WebDriverManager.chromedriver().setup();
                    Map<String, Object> prefs = new HashMap<String, Object>();
                    prefs.put("download.default_directory","C:\\devops-artifacts");
                    //prefs.put("download.default_directory",System.getProperty("user.dir") + File.separator + "externalFiles" + File.separator + "downloadFiles");
                    ChromeOptions options = new ChromeOptions();
                    options.setExperimentalOption("prefs", prefs);
                    options.addArguments("--headless");
                    driver = new ChromeDriver(options);
                    System.out.println("Chrome browser started..");
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("document.body.style.zoom='80%'");
                    return driver;
                }
            } catch (Exception e) {

                StackTraceElement[] elements = e.getStackTrace();
                System.out.println("Exception triggered ...");
                for (int iterator=1; iterator<=elements.length; iterator++)
                    System.out.println("Class Name:"+elements[iterator-1].getClassName()+" Method Name:"+elements[iterator-1].getMethodName()+" Line Number:"+elements[iterator-1].getLineNumber());

                System.out.println(e);
            }


        }
        return driver;
    }

    public static void closeWebBrowser(){
        driver.close();
    }

    public static void quitWebBrowser(){
        if (null != driver){
            driver.quit();
        }
        driver = null;
    }
}
