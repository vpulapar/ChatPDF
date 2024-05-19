package com.engine;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.base.BaseTest;
import com.utilities.TestContext;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


public class Utility extends BaseTest {

   

    public static int randomNumber(int min, int max) {
        Random rand = new Random();
// nextInt as provided by Random is exclusive of the top value so you need to add 1
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    /**
     * waitFor method to wait up to a designated period before
     * throwing exception (static locator)
     */
    public static void waitFor(WebElement element,
                               int timer) {

       
        // wait for the static element to appear
        log.info("WAIT FOR Element...");
        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.visibilityOf(element)));
    }

    /**
     * overloaded waitFor method to wait up to a designated period before
     * throwing exception (dynamic locator)
     */
    public static void waitFor(By by, int timer) {

        // wait for the dynamic element to appear
        
        log.info("wait for Element..Start " + by);
        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.visibilityOfElementLocated(by)));
        //log.info("wait for Element..End " + by);
        test.log(Status.PASS, "Element found on UI ");


    }
    public static void waitForNoLog(By by, int timer) {

        // wait for the dynamic element to appear
        
        log.info("wait for Element..Start " + by);
        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.visibilityOfElementLocated(by)));
        //log.info("wait for Element..End " + by);


    }

    public static void waitFor(By by, int timer, WebDriver driver) {

        // wait for the dynamic element to appear


        // examples: By.id(id),By.name(name),By.xpath(locator),
        // By.cssSelector(css)
        try {
            log.info("wait for Element..Start " + by);
            WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(timer));
            waits.until(ExpectedConditions.refreshed(
                    ExpectedConditions.visibilityOfElementLocated(by)));
            log.info("wait for Element..End " + by);
        } catch (Exception e) {
            test.info("Wait for element error " + e);
        }
    }

    /**
     * waitFor method to wait up to a designated period before
     * throwing exception if Title is not found
     */
    public static void waitFor(String title,
                               int timer)
            throws Exception {

        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.titleContains(title)));
    }

    public static String getCurrentTimeStamp() {
        String stamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
        return stamp;
    }

    public static String getCurrentDateStamp() {
        String stamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return stamp;
    }

    public static String getCurrentDate() {
        String date = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        return date;
    }
    public static String getCurrentDateMonthnameandTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat simpleformat = new SimpleDateFormat("E, dd MMM yyyy");
       // simpleformat.setTimeZone(TimeZone.getTimeZone("US/Mountain"));
        return simpleformat.format(cal.getTime());
    }

    public static String getCurrentDateAndTime() {
        String date = new SimpleDateFormat("MM/dd/yyyy HH:mm").format(new Date());
        return date;
    }
    public static String getCurrentDateRR() {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return date;
    }
    public static String getCurrentDate1() {
        String date = new SimpleDateFormat("MM-dd-yyyy").format(new Date());
        return date;
    }

    public static String getCurrentDateAndTime1() {
        String date = new SimpleDateFormat("MM-dd-yyyy HH:ss").format(new Date());
        return date;
    }
    public static void waitForJavaScriptToLoad() {

        wait.until(new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver webDriver) {
                return ((JavascriptExecutor) driver).
                        executeScript("return document.readyState").equals("complete");
            }
        });
        log.info("wait for javascript to Load..");
    }

    public static void waitForJavaScriptToLoad(WebDriver drivers) {

        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return ((JavascriptExecutor) driver).
                        executeScript("return document.readyState").equals("complete");
            }
        });
        log.info("wait for javascript to Load..");
    }


    public static void waitForspining() {
        try {
            Thread.sleep(3000);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@src='../images/loader.gif']")));
            log.info("wait for Loader Icon Element.. ");
        } catch (Exception e) {
            log.info("Exception on Icon Element.. " + e);
            System.out.println(e);
        }
    }
   

    public static void getScreenShot() throws Throwable {
        test.log(Status.INFO, "Screenshot from test step: Click on base64-img", MediaEntityBuilder.createScreenCaptureFromBase64String(CaptureScreen(driver)).build());

    }



    public static void getScreenShot(String info) throws Throwable {
        test.log(Status.INFO, info + ": Click on base64-img", MediaEntityBuilder.createScreenCaptureFromBase64String(CaptureScreen(driver)).build());

    }

    public static void waitForUntillInvisible(By locator) {
        try {
            Thread.sleep(3000);
            waitFor(locator, timeToWaitElementLoad);
            log.info("wait on Invisible Element.. ");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            log.info("Exception on Invisible Element.. " + e);
            System.out.println(e);
        }
    }

    /**
     * overloaded waitFor method to wait Invisibility up to a designated period before
     * throwing exception (dynamic locator)
     */
    public static void waitForInvisibility(By by, int timer) {

        // wait for the dynamic element to appear

        // examples: By.id(id),By.name(name),By.xpath(locator),
        // By.cssSelector(css)
        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.invisibilityOfElementLocated(by)));
    }

    public static void waitForVisibility(By by, int timer) {

        try {
            log.info("Wait on visible Element.. ");
            wait.until(ExpectedConditions.refreshed(
                    ExpectedConditions.visibilityOfElementLocated(by)));
        } catch (Exception e) {
            log.info("Exception on visible Element.. " + e);
            System.out.println("TimeOut for waiting...");
        }
    }

    /**
     * overloaded waitFor method to wait Clickable up to a designated period before
     * throwing exception (dynamic locator)
     */
    public static void waitForClickable(By by, int timer) {
        // wait for the dynamic element to appear
        // examples: By.id(id),By.name(name),By.xpath(locator),
        // By.cssSelector(css)
        try {
            log.info("WaitForClick on Element.. ");
            wait.until(ExpectedConditions.refreshed(
                    ExpectedConditions.elementToBeClickable(by)));
            log.info("Waited for Clickable " + by);
            test.log(Status.PASS, "Element is clickable on UI " + by);
        } catch (Exception e) {
            test.log(Status.FAIL, "Element is not clickable on UI " + by);
            log.info("Exception for Clickable... " + by);
        }
    }

    public static void scrollToElement(By element) {
        waitFor(element, timeToWaitElementLoad);
        WebElement ele = driver.findElement(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            log.info("Scroll To Element.. " + e);
        }


    }

    public static void scrollToElement(By element, WebDriver driver) {
        waitFor(element, timeToWaitElementLoad);
        WebElement ele = driver.findElement(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            log.info("Scroll To Element.. " + e);
        }


    }

    public static void waitForAjaxFinished() {
        log.info("Wait for Ajax to finish start.. ");
        ExpectedCondition<Boolean> expectationAjax = new
                ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver webDriver) {
                        return ((Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0"));
                    }
                };
        try {
            Thread.sleep(250);

            wait.until(expectationAjax);
        } catch (Throwable error) {
            log.info("Timeout waiting for Ajax Finished to complete.." + error);
            //Assert.fail("Timeout waiting for Ajax Finished to complete.");
        }
        log.info("Wait for Ajax to finish Completed.. ");
    }

    public static void waitForAjaxFinished(WebDriver driver) {
        log.info("Wait for Ajax to finish start.. ");
        ExpectedCondition<Boolean> expectationAjax = new
                ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver webDriver) {
                        return ((Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0"));
                    }
                };
        try {
            Thread.sleep(250);

            wait.until(expectationAjax);
        } catch (Throwable error) {
            log.info("Timeout waiting for Ajax Finished to complete.." + error);
            //Assert.fail("Timeout waiting for Ajax Finished to complete.");
        }
        log.info("Wait for Ajax to finish Completed.. ");
    }

    /**
     * This method check element visible or not if not it will return false
     */
    public static boolean isElementVisible(By by) throws InterruptedException {
        boolean flag = false;
        log.info("Wait Element to Visible Start.. ");
        try {
            Thread.sleep(5000);
            int n = driver.findElements(by).size();
            if (n > 0) {
                flag = true;
                return flag;
            } else
                return flag;
        } catch (StaleElementReferenceException e) {
            Thread.sleep(5000);
            int n = driver.findElements(by).size();
            if (n > 0) {
                flag = true;
                return flag;
            } else
                return flag;
        } catch (NoSuchElementException | InterruptedException e) {
            return false;
        }

    }

    /**
     * This method Get Text from element
     */
    public static String getTextFromElement(By by) throws InterruptedException {
        String text = "";
        try {

            waitFor(by, timeToWaitElementLoad);
            text = driver.findElement(by).getText();
            System.out.println("Text is : " + text);
        } catch (StaleElementReferenceException e) {
            Thread.sleep(5000);
            text = driver.findElement(by).getText();
        } catch (NoSuchElementException e) {
            return text;
        }
        log.info("Get text from Element is " + text);
        return text;
    }

    /**
     * This method Get attribute from element
     */
    public static String getAttributeFromElement(By by, String attribute) throws InterruptedException {
        String text = "";
        try {

            waitFor(by, timeToWaitElementLoad);
            text = driver.findElement(by).getAttribute(attribute);
            System.out.println("Text is : " + text);
        } catch (StaleElementReferenceException e) {
            Thread.sleep(5000);
            text = driver.findElement(by).getAttribute(attribute);
        } catch (NoSuchElementException e) {
            return text;
        }
        log.info("Get Attribute from Element is " + text);
        return text;
    }

    /**
     * This method check element visible or not if not it will return false
     */
    public static Boolean isElementVisibleSize(By by) {
        waitForElementNoFail(by);
        try {
            Thread.sleep(3000);
            return driver.findElements(by).size() > 0;
        } catch (NoSuchElementException | InterruptedException e) {
            return false;
        }

    }

    /**
     * This method check element visible or not if not it will return false
     */
    public static Boolean isElementEnable(By by) {

        try {
            Thread.sleep(1000);
            return driver.findElement(by).isEnabled();
        } catch (NoSuchElementException | InterruptedException e) {
            return false;
        }

    }

    public static Boolean isElementPresent(By by) {

        try {
            Thread.sleep(10000);
            int i = driver.findElements(by).size();
            if (i == 1)
                return true;
            else
                return false;
        } catch (NoSuchElementException | InterruptedException e) {
            return false;
        }

    }

    /**
     * This method check element visible or not if not it will return false
     */
    public static boolean isElementVisiableOnUI(By by) {
        try {
            driver.findElement(by).isDisplayed();
            driver.findElement(by).click();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public static void waitForElementNoFail(By locator) {
        try {
            waitFor(locator, timeToWaitElementLoad);
        } catch (Exception e) {

        }
    }
    public static void fluentwait(By ele){
        Wait<WebDriver> wait2= new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(100)).pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
        WebElement th=wait2.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver){
                return driver.findElement(ele);
            }
        });
    }

    public static void waitForElementNoFail(By locator, int time) {
        try {
            waitFor(locator, time);
        } catch (Exception e) {

        }
    }

    /**
     * This method check element visible or not if not it will return false
     */
    public static boolean isElementDisplay(By by) {
        try {
            Thread.sleep(1000);
            log.info(by.toString());
            return driver.findElement(by).isDisplayed();

        } catch (Exception e) {
            return false;
        }

    }

    /**
     * This method will give future date using current day + given day
     * 06/04/2020 + 1 day = 06/05/2020
     */
    public static String getFutureByDate(int day) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date currentDate = new Date();
        System.out.println(dateFormat.format(currentDate));
        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, day);
        // convert calendar to date
        Date currentDatePlusOne = c.getTime();
        String changedDate = new SimpleDateFormat("MM/dd/yyyy").format(currentDatePlusOne);
        return changedDate;

    }

    /**
     * This method will give future date using current day + given day
     * 06/04/2020 + 1 day = 06/05/2020
     */
    public static String getByDate(int day) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        System.out.println(dateFormat.format(currentDate));
        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, day);
        // convert calendar to date
        Date currentDatePlusOne = c.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(currentDatePlusOne);

    }

    public static String getByDateAndTime(int day) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY HH:mm");
        Date currentDate = new Date();
        System.out.println(dateFormat.format(currentDate));
        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, day);
        // convert calendar to date
        Date currentDatePlusOne = c.getTime();
        return new SimpleDateFormat("MM/dd/YYYY HH:mm").format(currentDatePlusOne);

    }

    public static String getPastDate(int days) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = new Date(System.currentTimeMillis());
        System.out.println("result is " + dateFormat.format(myDate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.DATE, -days);
        System.out.println(dateFormat.format(cal.getTime()));
        return dateFormat.format(cal.getTime());
    }

    public static String getPastDate1(int days) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
        Date myDate = new Date(System.currentTimeMillis());
        System.out.println("result is " + dateFormat.format(myDate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.DATE, -days);
        System.out.println(dateFormat.format(cal.getTime()));
        return dateFormat.format(cal.getTime());
    }

    public static String getPastDate2(int days) {
        DateFormat dateFormat = new SimpleDateFormat("MMMM dd");
        Date myDate = new Date(System.currentTimeMillis());
        System.out.println("result is " + dateFormat.format(myDate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.DATE, -days);
        System.out.println(dateFormat.format(cal.getTime()));
        return dateFormat.format(cal.getTime());
    }

    public static String getFutureDate(int day) {

        //Instant after = Instant.now(Clock.system(ZoneId.of("US/Mountain"))).truncatedTo(ChronoUnit.SECONDS).plus(Duration.ofDays(0));
        Instant after = Instant.now(Clock.system(ZoneId.systemDefault())).truncatedTo(ChronoUnit.SECONDS).plus(Duration.ofDays(day));
        Date dateAfter = Date.from(after);
        String changedDate = new SimpleDateFormat("MM/dd/yyyy").format(dateAfter);
        System.out.println(changedDate);
        return changedDate;

    }

    public static String getFutureDate1(int day) {

        //Instant after = Instant.now(Clock.system(ZoneId.of("US/Mountain"))).truncatedTo(ChronoUnit.SECONDS).plus(Duration.ofDays(0));
        Instant after = Instant.now(Clock.system(ZoneId.systemDefault())).truncatedTo(ChronoUnit.SECONDS).plus(Duration.ofDays(day));
        Date dateAfter = Date.from(after);
        String changedDate = new SimpleDateFormat("MMMM dd").format(dateAfter);
        System.out.println(changedDate);
        return changedDate;

    }

    /**
     * Get the date java.util.Date object for days after current date
     *
     * @param days
     * @return
     */
    public static String getDateAfterDays(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days); // +days
        String changedDate = new SimpleDateFormat("MM/dd/yyyy").format(cal.getTime());
        return changedDate;
    }

    public static String getCurrentServerDate() {
        log.info("Starting of get Current Date");
        //capturing today's date
        Date today = new Date();
        //displaying this date on PST timezone
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        df.setTimeZone(TimeZone.getTimeZone("US/Mountain"));
        String PST = df.format(today);
        System.out.println("Date in US/Mountain Timezone : " + PST);
        log.info("End of get Current Date " + PST);
        return PST;

    }

    public static String getCurrentServerDateFormat() {
        log.info("Starting of get Current Date");
        //capturing today's date
        Date today = new Date();
        //displaying this date on PST timezone
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setTimeZone(TimeZone.getTimeZone("US/Mountain"));
        String PST = df.format(today);
        System.out.println("Date in US/Mountain Timezone : " + PST);
        log.info("End of get Current Date " + PST);
        return PST;

    }

    public static String getForCurrentServerDate() {

        //capturing today's date
        Date today = new Date();
        //displaying this date on PST timezone
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setTimeZone(TimeZone.getTimeZone("US/Mountain"));
        String PST = df.format(today);
        System.out.println("Date in US/Mountain Timezone : " + PST);
        return PST;

    }

    public static String getForCurrentServerDate1() {

        //capturing today's date
        Date today = new Date();
        //displaying this date on PST timezone
        DateFormat df = new SimpleDateFormat("MMMM dd");
        df.setTimeZone(TimeZone.getTimeZone("US/Mountain"));
        String PST = df.format(today);
        System.out.println("Date in US/Mountain Timezone : " + PST);
        return PST;

    }

    public static String getFormatCurrentServerDate() {
        log.info("Get Date method Start..");
        //capturing today's date
        Date today = new Date();
        //displaying this date on PST timezone
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        df.setTimeZone(TimeZone.getTimeZone("US/Mountain"));
        String PST = df.format(today);
        System.out.println("Date in US/Mountain Timezone : " + PST);
        log.info("Get Date method End .." + PST);
        return PST;

    }

    public static void tabCLick() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
        } catch (Exception e) {

        }
    }

    /**
     * This method Will wait for element and click
     */
    public static void waitForElementAndClick(By locator, int time, String... message) {
        waitFor(locator, time);
        log.info("Waiting for Clickable..");
        waitForClickable(locator, time);
        try {
            Thread.sleep(1000);
            driver.findElement(locator).click();
            //test.log(Status.PASS, "Element is Clicked " + locator);
            log.info("Clicked on " + locator);
            test.log(Status.PASS, "Element is Clicked " + Arrays.toString(message));
        } catch (ElementNotInteractableException e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement ele = driver.findElement(locator);
            js.executeScript("arguments[0].click();", ele);
            log.info("JavaScript Clicked on " + locator);
        } catch (StaleElementReferenceException e) {
            log.info("Error clicking on StaleElementReferenceException " + locator);
            driver.findElement(locator).click();
            test.log(Status.PASS, "Element is Clicked " + locator);
        } catch (InterruptedException | NoSuchElementException e) {
            test.log(Status.FAIL, "No Such a Element on UI " + locator);
            e.printStackTrace();
            test.fail(e);
            log.info("Error clicking on  " + e);
        }

    }

    public static void waitForElementAndClickLogin(By locator, int time, String... message) {
        waitFor(locator, time);
        log.info("Waiting for Clickable..");
        waitForClickable(locator, time);
        try {
            Thread.sleep(1000);
            driver.findElement(locator).click();
            test.log(Status.PASS, "Element is Clicked " + locator);
            log.info("Clicked on " + locator);
        } catch (ElementNotInteractableException e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement ele = driver.findElement(locator);
            js.executeScript("arguments[0].click();", ele);
            log.info("JavaScript Clicked on " + locator);
            test.log(Status.PASS, "Element is Clicked " + locator);
        } catch (StaleElementReferenceException e) {
            log.info("Error clicking on StaleElementReferenceException " + locator);
            driver.findElement(locator).click();
            test.log(Status.PASS, "Element is Clicked " + locator);
        } catch (InterruptedException | NoSuchElementException e) {
            log.info("Error clicking on  " + e);
        }

    }

    public static void loginElementAndClickLogin(By locator, int time, String... message) {
        log.info("Waiting for Clickable..");
        try {
            Thread.sleep(1000);
            driver.findElement(locator).click();
            log.info("Clicked on " + locator);
        } catch (ElementNotInteractableException e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement ele = driver.findElement(locator);
            js.executeScript("arguments[0].click();", ele);
            log.info("JavaScript Clicked on " + locator);
        } catch (StaleElementReferenceException e) {
            log.info("Error clicking on StaleElementReferenceException " + locator);
            driver.findElement(locator).click();
        } catch (InterruptedException | NoSuchElementException e) {
            log.info("Error clicking on  " + e);
        }

    }

    public static void waitElementAndClick(By locator, int time, String... message) throws InterruptedException, NoSuchElementException {
        waitFor(locator, time);
        log.info("Waiting for Clickable..");
        waitForClickable(locator, time);
        try {
            Thread.sleep(1000);
            driver.findElement(locator).click();
            test.log(Status.PASS, "Element is Clicked " + locator);
            log.info("Clicked on " + locator);
            //test.log(Status.PASS, "Element is Clicked " + Arrays.toString(message));
        } catch (ElementNotInteractableException e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement ele = driver.findElement(locator);
            js.executeScript("arguments[0].click();", ele);
            log.info("JavaScript Clicked on " + locator);
        } catch (StaleElementReferenceException e) {
            log.info("Error clicking on StaleElementReferenceException " + locator);
            driver.findElement(locator).click();
            test.log(Status.PASS, "Element is Clicked " + locator);
        }

    }

    /**
     * This method Will wait for element and click
     */
    public static String getText(By locator, int time, String... message) {
        waitFor(locator, time);

        try {
            Thread.sleep(1000);
            test.log(Status.PASS, "locator " + locator);
            String value = driver.findElement(locator).getText().trim();
            test.log(Status.PASS, "Available text " + value);
            return value;
        } catch (InterruptedException | NoSuchElementException ignored) {

        }
        return "";
    }

    /**
     * This method Will wait for element and click
     */
    public static String getTextNoWait(By locator, int time, String... message) {

        String value = "";
        try {
            Thread.sleep(1000);
            test.log(Status.PASS, "locator " + locator);
            value = driver.findElement(locator).getText();
            test.log(Status.PASS, "Available text " + value);
        } catch (InterruptedException | NoSuchElementException ignored) {
            System.out.println("Exception: ");
        }
        return value;
    }

    /**
     * This method Will wait for element and click
     */
    public static String getTextByWebEleNoWait(WebElement locator, int time, String... message) {

        String value = "";
        try {
            Thread.sleep(1000);
            test.log(Status.PASS, "locator " + locator);
            value = locator.getText();
            test.log(Status.PASS, "Available text " + value);
        } catch (InterruptedException | NoSuchElementException ignored) {
            System.out.println("Exception: ");
        }
        return value;
    }

    /**
     * This method Will wait for element and click
     */
    public static String getValue(By locator, int time, String... message) {
        waitFor(locator, time);

        try {
            Thread.sleep(1000);
            String value = driver.findElement(locator).getAttribute("value").trim();
            test.log(Status.PASS, "Available text " + value);
            return value;
        } catch (InterruptedException | NoSuchElementException ignored) {

        }
        return "";
    }

    public static String getSelectedText(By locator, int time, String... message) {
        waitFor(locator, time);

        try {
            Thread.sleep(1000);
            Select select = new Select(driver.findElement(locator));
            String value = select.getFirstSelectedOption().getText();
            test.log(Status.PASS, "Available text " + value);
            return value;
        } catch (InterruptedException | NoSuchElementException ignored) {

        }
        return "";
    }

    public static void softClick(By locator, int i) {
        try {
            Utility.waitForElementAndClick(locator, i);
        } catch (Exception e) {
            System.out.println(e + " Exception ");
        }
    }

    /**
     * This method Will wait for element and click
     */
    public static void waitForElementAndClick(WebElement locator) {

        try {
            Thread.sleep(1000);
            locator.click();
            test.log(Status.PASS, "Element is Clicked " + locator);
            //test.log(Status.PASS, "Element is Clicked " + Arrays.toString(message));
        } catch (ElementNotInteractableException e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", locator);
        } catch (InterruptedException ignored) {

        }

    }

    public static void waitForElementAndClick(By locator) {

        try {
            Thread.sleep(1000);
            driver.findElement(locator).click();
            test.log(Status.PASS, "Element is Clicked " + locator);
            log.info("Element  Clicked on " + locator);
            //test.log(Status.PASS, "Element is Clicked " + Arrays.toString(message));
        } catch (ElementNotInteractableException | StaleElementReferenceException e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", locator);
            log.info("Element  JAVASCRIPT Clicked on " + locator);
        } catch (InterruptedException E) {
            log.info("Element  Clicked EXCEPTION on " + E);
        }

    }

    /**
     * This method Will wait for element and click
     */
    public static void elementClick(By locator, String... message) {

        waitForClickable(locator, 1);
        try {
            Thread.sleep(1000);
            driver.findElement(locator).click();
            test.log(Status.PASS, "Element is Clicked " + locator);
            //test.log(Status.PASS, "Element is Clicked " + Arrays.toString(message));
        } catch (ElementNotInteractableException e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement ele = driver.findElement(locator);
            js.executeScript("arguments[0].click();", ele);
        } catch (StaleElementReferenceException e) {
            driver.findElement(locator).click();
            test.log(Status.PASS, "Element is Clicked " + locator);
        } catch (InterruptedException e) {

        }

    }

    public static void elementEnterClick(By locator, String... message) {

        waitForClickable(locator, 1);
        try {
            Thread.sleep(1000);
            driver.findElement(locator).sendKeys(Keys.ENTER);
            test.log(Status.PASS, "Element is Clicked " + locator);
            log.info("Element  ENTER Clicked on " + locator);
            //test.log(Status.PASS, "Element is Clicked " + Arrays.toString(message));
        } catch (ElementNotInteractableException e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement ele = driver.findElement(locator);
            js.executeScript("arguments[0].click();", ele);
        } catch (StaleElementReferenceException e) {
            driver.findElement(locator).sendKeys(Keys.ENTER);
            test.log(Status.PASS, "Element is Clicked " + locator);
        } catch (InterruptedException e) {

        }

    }

    /**
     * This method Will wait for element and click
     */
    public static void elementAndClick(By locator, int time) {

        try {
            Thread.sleep(1000);
            driver.findElement(locator).click();
            //test.log(Status.PASS, "Element is Clicked " + locator);
            log.info("Element  Clicked on " + locator);
        } catch (ElementNotInteractableException e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement ele = driver.findElement(locator);
            js.executeScript("arguments[0].click();", ele);
            log.info("Element  javascript Clicked on " + locator);
        } catch (StaleElementReferenceException e) {
            driver.findElement(locator).click();
            //test.log(Status.PASS, "Element is Clicked " + locator);
        } catch (InterruptedException e) {
            log.info("Element not Clicked exception on " + e);
        }

    }

    /**
     * This method Will wait for element and click
     */
    public static void ElementClickORJsClick(By locator, int time) {
        try {
            driver.findElements(locator).get(0).click();
            test.log(Status.PASS, "Element is Clicked " + locator);
        } catch (ElementClickInterceptedException e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", driver.findElement(locator));
        }
        log.info("Javascript Clicked on " + locator);

    }

    /**
     * This method Will wait for element and Type
     */
    public static void waitForElementAndType(By locator, String data, int time) {
        waitFor(locator, time);
        log.info("wait and Type start " + locator + " as text " + data);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(data);
        test.log(Status.PASS, "Element is typed with " + data + " on" + locator);
        log.info("wait and Type end " + locator + " as text " + data);
    }

    public static void sendHumanKeys(WebElement element, String text) {
        Random r = new Random();
        for(int i = 0; i < text.length(); i++) {
            try {
                Thread.sleep((int)(r.nextGaussian() * 15 + 100));
            } catch(InterruptedException e) {}
            String s = new StringBuilder().append(text.charAt(i)).toString();
            element.sendKeys(s);
        }
    }

    /**
     * This method Will wait for element and Type
     */
    public static void waitForElementAndClearType(By locator, String data, int time) {
        waitFor(locator, time);
        log.info("wait and Clear Type start " + locator + " as text " + data);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(data);
        test.log(Status.PASS, "Element is cleared and typed with " + data + " on " + locator);
        driver.findElement(locator).sendKeys(Keys.TAB);
        log.info("wait and Clear Type end " + locator + " as text " + data);

    }

    /**
     * This method Will wait for element and Type
     */
    public static void loginElementAndClearType(By locator, String data) {
        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.visibilityOfElementLocated(locator)));
        log.info("wait and Clear Type start " + locator + " as text " + data);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(data);
        driver.findElement(locator).sendKeys(Keys.TAB);
        log.info("wait and Clear Type end " + locator + " as text " + data);

    }

    public static void highLighterMethod(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: Yellow; border: 2px solid Red;');", element);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        js.executeScript("arguments[0].setAttribute('style','border: solid 2px white'); ", element);
    }

    public static void RemainHighted(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid Red;');", element);
    }

    public static WebDriver LaunchChrome(WebDriver driver) {
        String OsName = System.getProperty("os.name");
        if (OsName.startsWith("Mac")) {
            System.setProperty("webdriver.chrome.driver", "../drivers/chromedriver");
        } else if (OsName.startsWith("Windows")) {
            System.setProperty("webdriver.chrome.driver", "../drivers/chromedriver.exe");
        }
        driver = new ChromeDriver();
        ////driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }

    public static void selectByvisualText(By locator, String text) {
        waitFor(locator, timeToWaitElementLoad);
        Select selectByVisualText = new Select(driver.findElement(locator));
        selectByVisualText.selectByVisibleText(text);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }

    }

    public static void selectByValue(By locator, String text) {
        waitFor(locator, timeToWaitElementLoad);
        Select selectByVisualText = new Select(driver.findElement(locator));
        selectByVisualText.selectByValue(text);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }

    }

    public static void selectByIndex(By locator, int text) {
        try {

            waitFor(locator, timeToWaitElementLoad);
            Select selectByVisualText = new Select(driver.findElement(locator));
            selectByVisualText.selectByIndex(text);

            Thread.sleep(1000);
        } catch (Exception e) {

        }

    }

    public static String selectByIndexAndGetValue(By locator, int text) {
        String value = "";
        try {

            waitFor(locator, timeToWaitElementLoad);
            Select selectByVisualText = new Select(driver.findElement(locator));
            selectByVisualText.selectByIndex(text);
            Thread.sleep(1000);
            value = selectByVisualText.getFirstSelectedOption().getText();
        } catch (Exception e) {

        }
        return value;
    }

    public static boolean selectByIndexCheck(By locator, int text) {
        boolean flag = false;
        try {

            waitFor(locator, timeToWaitElementLoad);
            Select selectByVisualText = new Select(driver.findElement(locator));
            selectByVisualText.selectByIndex(text);
            flag = true;
            Thread.sleep(1000);
        } catch (Exception e) {
            flag = false;

        }
        return flag;
    }

    public static void selectByIndex(By locator, int text, WebDriver driver) {
        waitFor(locator, timeToWaitElementLoad);
        Select selectByVisualText = new Select(driver.findElement(locator));
        selectByVisualText.selectByIndex(text);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }

    }

    public static String getSelectedValue(By locator) {
        waitFor(locator, timeToWaitElementLoad);
        Select selectByVisualText = new Select(driver.findElement(locator));

        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }
        return selectByVisualText.getFirstSelectedOption().getText();
    }

    public static void moveToElementAndClick(By locator,int time,String text) {
        waitFor(locator, timeToWaitElementLoad);
        WebElement ele = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(ele).click().perform();
        test.log(Status.PASS,"clicked on "+text);
    }



    public static void moveToElement(WebElement locator) {
        waitFor(locator, timeToWaitElementLoad);
        Actions actions = new Actions(driver);
        actions.moveToElement(locator).build().perform();
    }

    public static void ecsClick()
    {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE);
    }
    public static void moveToElement(By locator) {
        try {
            Actions actions = new Actions(driver);
            WebElement ele = driver.findElement(locator);
            actions.moveToElement(ele).build().perform();
            test.log(Status.PASS,"Moved to Specified Element "+locator);
        } catch (Exception e) {
            System.out.printf("Exception while move to Element");
        }
    }

    public static void scrollIntoView(By locator) {
        WebElement ele = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'center', inline: 'center'});", ele);
    }


    public static String CaptutreScreenShot(WebDriver driver, String ModuleName, String screenShotName, String testName) {
        String fileSeperator = System.getProperty("file.separator");
        try {
            File file = new File("Screenshots");
            if (!file.exists()) {
                file.mkdir();
            }

            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File targetFile = new File("Screenshots" + fileSeperator + ModuleName + fileSeperator + testName, screenShotName);
            FileUtils.copyFile(screenshotFile, targetFile);

            return screenShotName;
        } catch (Exception e) {
            System.out.println("An exception occured while taking screenshot " + e.getCause());
            return null;
        }
    }

    public static boolean CloseAllOtherWindows(WebDriver driver) {
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String CurrentWindowHandle : allWindowHandles) {
            if (!CurrentWindowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(CurrentWindowHandle);
                driver.close();
            }
        }
        driver.switchTo().window(mainWindowHandle);
        if (driver.getWindowHandles().size() == 1) {
            return true;
        } else {
            return false;
        }

    }

    public static void DragAndDrop(WebDriver driver, WebElement srcelement, WebElement dstelement) {
        Actions action = new Actions(driver);
        action.dragAndDrop(srcelement, dstelement).perform();
    }

    public static void moveTODragAndDrop(WebDriver driver, WebElement fromElement, WebElement toElement) {
        Actions builder = new Actions(driver);

        Action dragAndDrop = builder.clickAndHold(fromElement).moveToElement(toElement).release(toElement).build();

        dragAndDrop.perform();
    }

    public static void DragAndDropJS(WebDriver driver, WebElement source, WebElement destination) throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n" + "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n" + "data: {},\n" + "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n" + "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n" + "return event;\n" + "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n" + "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n" + "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n" + "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n" + "}\n" + "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n" + "var dragStartEvent =createEvent('dragstart');\n" + "dispatchEvent(element, dragStartEvent);\n" + "var dropEvent = createEvent('drop');\n" + "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n" + "var dragEndEvent = createEvent('dragend');\n" + "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n" + "var source = arguments[0];\n" + "var destination = arguments[1];\n" + "simulateHTML5DragAndDrop(source,destination);", source, destination);
        Thread.sleep(1500);

    }

    public static void alertWindow(WebDriver driver) {
        Actions action = new Actions(driver);

    }

    public static void ImplicitWait(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static String getPropertiesString(String PropertiesPath, String elementName) {
        String elementValue = null;
        try {
            Properties prop = new Properties();
            FileInputStream file = new FileInputStream(System.getProperty("user.dir") + PropertiesPath);
            prop.load(file);
            elementValue = prop.getProperty(elementName);
            return elementValue;
        } catch (Exception e) {
            System.out.println("getPropetiesString method failed with Exception : " + e);
            return elementValue;
        }
    }

    public static WebElement getPropertiesElement(WebDriver driver, String PropertiesPath, String elementName) throws Exception {
        WebElement element = null;
        try {
            Properties prop = new Properties();
            FileInputStream file = new FileInputStream(System.getProperty("user.dir") + PropertiesPath);
            prop.load(file);
            String elementpath = prop.getProperty(elementName);
            element = driver.findElement(By.xpath("" + elementpath + ""));
            return element;
        } catch (Exception e) {
            System.out.println("getPropertiesElement method failed with Exception : " + e);
            return element;
        }
    }

    public static void SelectDateFromDatePicker(WebDriver driver, int Days) {
        try {
            Date todayDate = new Date();
            String YesDate = null;

            DateFormat calanderDate = new SimpleDateFormat("d");
            String dateValue = calanderDate.format(todayDate);

            DateFormat calanderMonth = new SimpleDateFormat("MMM");
            String monthValue = calanderMonth.format(todayDate);

            DateFormat calanderYear = new SimpleDateFormat("yyyy");
            String yearValue = calanderYear.format(todayDate);

            WebElement DOBWidget = driver.findElement(By.xpath("//input[@name='DOB']"));
            Utility.highLighterMethod(driver, DOBWidget);
            DOBWidget.click();

            WebElement Month = driver.findElement(By.xpath("//div[@class='calendar left single']//select[@class='monthselect']"));
            Utility.highLighterMethod(driver, Month);
            Select selectMonth = new Select(Month);
            selectMonth.selectByVisibleText(monthValue);

            WebElement Year = driver.findElement(By.xpath("//div[@class='calendar left single']//select[@class='yearselect']"));
            Utility.highLighterMethod(driver, Year);
            Select selectYear = new Select(Year);
            selectYear.selectByVisibleText(yearValue);

            int Date = Integer.parseInt(dateValue);
            if (Date == 1) {
                YesDate = String.valueOf(Date);
            } else {
                int YesterdayDate = Integer.parseInt(dateValue) - Days;
                YesDate = String.valueOf(YesterdayDate);
            }

            for (int tr = 1; tr <= 5; tr++) {
                for (int td = 1; td <= 7; td++) {
                    WebElement selectDate = driver.findElement(By.xpath("//div[@class='calendar left single']//tr[" + tr + "]//td[" + td + "]"));
                    String getDate = selectDate.getText();

                    if (getDate.equals(YesDate)) {
                        Utility.highLighterMethod(driver, driver.findElement(By.xpath("//div[@class='calendar left single']//tr[" + tr + "]//td[" + td + "]")));
                        driver.findElement(By.xpath("//div[@class='calendar left single']//tr[" + tr + "]//td[" + td + "]")).click();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("SelectDateFromDatePicker method failed with Exception :" + e);
        }
    }


	/* if user wants to select the current date then pass the value of Days parameter as Zero while if user wants to select any previous date then pass the value of Days variable
	as the number of shortfall days from current date example: if user wants to select Yesterday's date then pass Days value as 1 and if user wants to select day before yesterday's date
	then pass the Days value as 2 */

   

    public static void Pause(WebDriver driver, int Seconds) {
        try {
            TimeUnit.SECONDS.sleep(Seconds);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

    }

    public static void Pause(int Seconds) {
        try {
            TimeUnit.SECONDS.sleep(Seconds);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

    }

    public static void logout() {
        try {
            String url = System.getProperty("Environment");
            System.out.println(url + " Before url");
            if (url == null) {
                url = TestContext.getEnvironment();
            }
            // To Logout from Current session
            System.out.println("last " + String.valueOf(url.charAt(url.length() - 1)));
            if (String.valueOf(url.charAt(url.length() - 1)) == "/") {
                System.out.println("/ if");
                driver.navigate().to(url + "Account/Logout");
            } else {
                System.out.println(" else not /");
                driver.navigate().to(url + "/Account/Logout");
            }
        } catch (Exception e) {
            System.out.println("Exception on Logout..");
        }
    }

    public static void logout(String url) {
        try {
            System.out.println(url + " LogOut url");
            driver.navigate().to(url);
            Utility.waitForAjaxFinished();
            Utility.waitForJavaScriptToLoad();
        } catch (Exception e) {
            System.out.println("Exception on Logout..");
        }
    }

    public static void enterRequiredDate(By by, String date, int timer) {
        Utility.waitForElementAndClearType(by, date, timer);
    }

    public static WebDriver LoginAgain(WebDriver driver, String Username, String Password, String Orgname) throws Exception {
        String PropertiesPath1 = "/src/main/java/com/resources/TestDataOR/VhaChyDevXpath_ashutosh.properties";

        WebElement userName = Utility.getPropertiesElement(driver, PropertiesPath1, "elementUserName");
        wait.until(ExpectedConditions.visibilityOf(userName));
        Utility.highLighterMethod(driver, userName);
        userName.clear();
        userName.sendKeys(Username);
        WebElement passWord = Utility.getPropertiesElement(driver, PropertiesPath1, "elementPassword");
        Utility.highLighterMethod(driver, passWord);
        passWord.clear();
        passWord.sendKeys(Password);

        WebElement orga = Utility.getPropertiesElement(driver, PropertiesPath1, "elementOrganization");
        Utility.highLighterMethod(driver, orga);
        orga.clear();
        orga.sendKeys(Orgname);
        WebElement submit = Utility.getPropertiesElement(driver, PropertiesPath1, "elementSubmit");
        Utility.highLighterMethod(driver, submit);
        submit.click();
        return driver;
    }

    public static void switchToTabs(int tab) {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tab));
    }

    public static void switchToExistingTabs(int tab) {
        driver.switchTo().defaultContent();
        if(tab==1){
        Utility.waitForNoLog(By.xpath("//iframe[@id='extnBrowser']"),100);
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='extnBrowser']")));//.navigate().refresh();
        Utility.waitForAjaxFinished();
        Utility.waitForJavaScriptToLoad();
        }
        else{
        Utility.waitForNoLog(By.xpath("//iframe[contains(@src,'../app')]"),60);
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'../app')]")));
        ;}

    }

    public static void extBrowserRefresh()
    {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.getElementById('extnBrowser').location.reload();");
            waitForJavaScriptToLoad();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public static String switchToTabTitle(int tab) {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tab));
        return driver.getTitle();
    }

    /**
     * This method check element visible or not if not it will return false
     */
    public static Boolean isElementVisibleClickable(By by) {

        try {
            Thread.sleep(2000);
            driver.findElement(by).isDisplayed();
            driver.findElement(by).clear();
            return true;
        } catch (NoSuchElementException | InterruptedException | InvalidElementStateException e) {
            return false;
        }

    }
    public static void switchTonewTab(int tab) {

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tab));
    }

    public static void switchToTab(int i) {
        driver.switchTo().defaultContent();
        if(i==1){

            Utility.waitForNoLog(By.xpath("//iframe[@id='extnBrowser']"),100);
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='extnBrowser']")));
            Utility.waitForJavaScriptToLoad();
            Utility.waitForAjaxFinished();
        }

        else{
            Utility.waitForNoLog(By.xpath("//iframe[contains(@src,'../app')]"),60);
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'../app')]")));}

    }
    public static void switchTodefaultContent() {
        driver.switchTo().defaultContent();
    }
    public static void jsClick(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public static void jsType(WebElement element, String value) {
        // Use JavaScript Executor to type into the input field
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].value = arguments[1];", element, value);

    }

    public static void jsByType(By element, String value) {
        WebElement ele=driver.findElement(element);
        // Use JavaScript Executor to type into the input field
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].value = arguments[1];", ele, value);

    }

    public static void jsClick(WebElement element, WebDriver driver) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public static void jsByClick(By element) {
        WebElement ele=driver.findElement(element);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", ele);
    }

    public static void jsCalendarDateEnterByID(By locator, String date) throws Throwable {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value ='';", driver.findElement(locator));
        Thread.sleep(2000);
        String id = driver.findElement(locator).getAttribute("id");
        js.executeScript("document.getElementById('" + id + "').value = \"" + date + "\";");
        driver.findElement(By.id(id)).sendKeys(Keys.TAB);
        Thread.sleep(2000);
        test.pass("Entered the date " + date);
    }

    public static void selectDate(By locator, String date) {

        driver.findElement(locator).click();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            System.out.println(sdf2.format(sdf.parse(date)));
            LocalDate currentDate = LocalDate.parse(sdf2.format(sdf.parse(date)));

            Thread.sleep(2000);

            // Get month from date - ex 30/05/2021 - May
            Month month = currentDate.getMonth();

            //Adding Month - Select Month Name on dropdown
            WebElement monthEle = driver.findElement(By.xpath("(//select[@class='monthselect'])[1]"));
            Select selectMonth = new Select(monthEle);
            String m = month.name().toLowerCase().substring(0, 3).substring(0, 1).toUpperCase() + month.name().toLowerCase().substring(0, 3).substring(1, 3);
            selectMonth.selectByVisibleText(m);

            //Add Year - Select Year from dropdown - ex 30/05/2021 - 2021
            WebElement yearEle = driver.findElement(By.xpath("(//select[@class='yearselect'])[1]"));
            Select selectYear = new Select(yearEle);
            selectYear.selectByVisibleText(String.valueOf(currentDate.getYear()));

            //Select Date
            driver.findElement(By.xpath("(//td[contains(@class,'available')][text()='" + currentDate.getDayOfMonth() + "'])[1]")).click();
        } catch (ParseException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void certificateAllow() {

    }

    public static void smartPinEnter(String pinNumber) {
        try {
            int timeWait = 60;
           /* Screen screen = new Screen();
            String basePath = System.getProperty("user.dir") + "/Screens";
            Pattern certificateWindow = new Pattern(basePath + "/CertificateWindow.png");
            Pattern certificateSelection = new Pattern(basePath + "/CertificateSelection.png");
            Pattern certificateOK = new Pattern(basePath + "/CertificateOk.png");

            Pattern pinPopup = new Pattern(basePath + "/pinScreen.png");
            Pattern pinPopupTextBox = new Pattern(basePath + "/TextboxScreen.png");
            Pattern pinPopupOK = new Pattern(basePath + "/OkScreen.png");

            Pattern pinSmartPopup = new Pattern(basePath + "/SmartCardWindow.png");
            Pattern pinSmartPopupTextBox = new Pattern(basePath + "/SmartPinTextbox.png");
            Pattern pinSmartPopupOK = new Pattern(basePath + "/SmartPinOK.png");

            screen.wait(certificateWindow, timeWait);
            Region region = screen.find(certificateSelection);
            region.click();
            screen.click(certificateOK);
            Thread.sleep(5000);
            screen.wait(certificateWindow, timeWait);
            if (screen.exists(pinPopup, 60) != null) {
                screen.type(pinPopupTextBox, pinNumber);
                screen.click(pinPopupOK);
            }
            if (screen.exists(pinSmartPopup, 60) != null) {
                screen.type(pinSmartPopupTextBox, pinNumber);
                screen.click(pinSmartPopupOK);
            }*/


        } catch (Exception e) {
            System.out.println("Failed on Sikuli ..");
            test.info(e);
        }
    }

    public static void writePropertyFile(String path, String propFileName, String propName, String propValue) {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(path + propFileName + ".properties"));
            prop.setProperty(propName, propValue);
            prop.store(new FileOutputStream(path + propFileName + ".properties"), null);
        } catch (Exception e) {
            System.out.println(e);
            test.fail(e);
        }
    }




    public static String getToken() {
        String path = System.getProperty("user.dir") + "/src/main/resources/api/MirthConnectionString/";

        List<String> fileNames = searchForFileNameContainingSubstring("mirthtoken20");

        //Assert.assertEquals(fileNames.size(),1,"No files or Still multiple files available");
        String token = "";
        try (BufferedReader br = new BufferedReader(new FileReader(path + fileNames.get(0)))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            token = sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;
    }

    private static List<String> searchForFileNameContainingSubstring(String substring) {
        String path = System.getProperty("user.dir") + "/src/main/resources/api/MirthConnectionString";

        File file = new File(path); //Change this to the directory you want to search in.

        List<String> filesContainingSubstring = new ArrayList<String>();

        if (file.exists() && file.isDirectory()) {
            String[] files = file.list(); //get the files in String format.
            for (String fileName : files) {
                if (fileName.contains(substring))
                    filesContainingSubstring.add(fileName);
            }
        }

        for (String fileName : filesContainingSubstring) {
            System.out.println(fileName); //or do other operation
        }

        return filesContainingSubstring; //return the list of filenames containing substring.
    }

    public boolean waitForLoaderComplete(WebDriver driver) throws InterruptedException {
        boolean flag = false;
        Thread.sleep(2000);
        while (driver.findElements(By.xpath("//div[@style='display: block;']/div/img")).size() != 0) {
            try {

                if (driver.findElements(By.xpath("//div[@style='display: block;']/div/img")).size() == 0) {
                    flag = true;
                    System.out.println("Loader completed..");
                    break;
                } else {
                    flag = false;
                    System.out.println("Loader still displaying..");
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                flag = false;
            }
        }


        return flag;
    }

    public void selectDateAndTime(By locator, String date, String hours, String mins) {
        driver.findElement(locator).click();
        //Adding hours
        WebElement hoursEle = driver.findElement(By.xpath("(//select[@class='hourselect'])[1]"));
        Select selectHours = new Select(hoursEle);
        selectHours.selectByValue(hours);

        //Adding mins
        WebElement minsEle = driver.findElement(By.xpath("(//select[@class='minuteselect'])[1]"));
        Select selectMins = new Select(minsEle);
        selectMins.selectByValue(mins);

        LocalDate currentDate = LocalDate.parse(date);
        // Get month from date - 30/05/2021 - May
        Month month = currentDate.getMonth();

        //Adding Month - Select Month Name on dropdown
        WebElement monthEle = driver.findElement(By.xpath("(//select[@class='monthselect'])[1]"));
        Select selectMonth = new Select(monthEle);
        selectMonth.selectByValue(month.name());

        //Add Year - Select Year from dropdown
        WebElement yearEle = driver.findElement(By.xpath("(//select[@class='yearselect'])[1]"));
        Select selectYear = new Select(yearEle);
        selectYear.selectByValue(String.valueOf(currentDate.getYear()));

        //Select Date
        driver.findElement(By.xpath("(//td[contains(@class,'available')][text()='" + currentDate.getDayOfMonth() + "'])[1]")).click();

    }



    public static void clearCHROMEBrowserCache()
    {
        try {
            driver.manage().deleteAllCookies();
            driver.get("chrome://settings/clearBrowserData");
            Thread.sleep(2000);
            //Utility.getScreenShot("Browser Cache After");
            JavascriptExecutor js=(JavascriptExecutor)driver;
            //get clear cache button
            WebElement clearBtn = (WebElement) js.executeScript("return document.querySelector(\"body > settings-ui\").shadowRoot.querySelector(\"#main\").shadowRoot.querySelector(\"settings-basic-page\").shadowRoot.querySelector(\"#basicPage > settings-section:nth-child(9) > settings-privacy-page\").shadowRoot.querySelector(\"settings-clear-browsing-data-dialog\").shadowRoot.querySelector(\"#clearBrowsingDataConfirm\")");
            //Click
            clearBtn.click();
            Thread.sleep(1000);
            test.log(Status.PASS,"Browser Cache cleared");
            //Utility.getScreenShot("Browser Cache After");
        }
        catch (Throwable e)
        {
            System.out.println("exception cache clear ..");

        }

    }

    public static void pageEnd() throws AWTException {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    public static void clearChromeCache()
    {
        /*DevTools devTools=driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.clearBrowserCookies());
        devTools.send(Network.clearBrowserCache());*/
    }

    public static void browserZoom(String value)
    {
        ((JavascriptExecutor)driver).executeScript("document.body.style.zoom='"+value+"%'");
    }
    public static void closeNewOpenedTabs() {
        Set<String> windowHandles = driver.getWindowHandles();
        if (windowHandles.size() > 1) {
            List<String> handlesList = new ArrayList<>(windowHandles);
            for (int i = handlesList.size() - 1; i >= 1; i--) {
                String windowHandle = handlesList.get(i);
                driver.switchTo().window(windowHandle);
                driver.close();
            }
            driver.switchTo().window(handlesList.get(0));
        }
    }
    public static void ExplicitWait(By element)
    {
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitDurationInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
    public static void closeNewOpenedTab()
    {
       Set<String> s1= driver.getWindowHandles();
       if (s1.size()>1){
           for(int i=s1.size()-1; i>=1; i--){
//               Utility.switchToTab(i);
               driver.close();
           }
//           Utility.switchToTab(0);
       }

    }
    public static int getElementCount(By locator,int time){
        waitFor(locator, time);
        List<WebElement> eleCount =driver.findElements(locator);
        return eleCount.size();
    }
    public static void scrollIntoElementView(By element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        waitFor(element,timeToWaitElementLoad);
        WebElement ele=driver.findElement(element);
        js.executeScript("arguments[0].scrollIntoView();", ele);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public static int getfirsttwodigit(int n){

        String str = Integer.toString(n);
        str=str.substring(0,2);
        int i=Integer.parseInt(str);
        return i;
    }
    public static double calculateAverage(double[]average){
        double sum=0;
        for(int i=0;i<average.length;i++){
            sum=sum+average[i];
        }
        int d=average.length;
        double avg=sum/d;
        return Math.round(avg*100)/100.0;
    }
    public  static double calculateMedian(double bc[]){
        double k=0;
        double []dg=bc;

        int l= dg.length;
        if(l>1){
            double temp;
            for(int i=0; i< l; i++){

                for(int j=i+1; j<l;j++){
                    if(dg[i]>dg[j]){
                        temp=dg[i];
                        dg[i]=dg[j];
                        dg[j]=temp;
                    }
                }
            }
            if(l==2){
                double p=dg[0]+dg[1];
                k=p/2;
            } else if (l>2) {
                if(l%2==0){
                    int m=l/2;
                    k=(dg[m-1]+dg[m])/2;
                }
                else{
                    int m=(l-1)/2;
                    k=dg[m];
                }
            }}else {
            k=dg[0];
        }
        return Math.round(k*100)/100.0;
    }
    //Copyright © 2023–2024 CoreMobile, Inc.
}
