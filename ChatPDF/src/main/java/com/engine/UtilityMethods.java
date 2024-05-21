package com.engine;

import com.aventstack.extentreports.Status;
import com.base.BaseTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Random;


public class UtilityMethods extends BaseTest {

    static ArrayList<String> tabs;

    private enum LocatorType {
        xpath, cssSelector, id, className, name, linkText, partialLinkText, tagName;
    }

    public static int getRandomNumber() {
        Random rand = new Random();
        int numNoRange = rand.nextInt();
        return numNoRange;
    }

    public static int getRandomTenNumber() {
        int aNumber = 0;
        aNumber = (int) ((Math.random() * 9000000) + 1000000);
        int a2 = 111;
        int mobileNum = Integer.valueOf(String.valueOf(a2) + String.valueOf(aNumber));
        System.out.println(mobileNum);
        return mobileNum;
    }

    public static String getRandomCharNumber() {
        int aNumber1 = 0;
        aNumber1 = (int) ((Math.random() * 9000) + 1000);
        System.out.println((aNumber1));
        Random r = new Random();
        String uppercaseletter = String.valueOf((char) (r.nextInt(26) + 'a')).toUpperCase();
        System.out.println(uppercaseletter);
        String a1 = uppercaseletter;
        String anonId = String.valueOf(String.valueOf(a1) + String.valueOf(aNumber1));
        System.out.println(anonId);
        return anonId;
    }

    /**
     * TODO To get the entire exception stack trace
     *
     * @return returns the stack trace
     */
    public static String getStackTrace() {
        String trace = "";
        int i;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (i = 2; i < stackTrace.length; i++) {
            trace = trace + "\n" + stackTrace[i];
        }
        return trace;
    }

    //Dynamic locator change
    public static By getNewLocator(By locator, String dynamicText) {
        //By.xpath("//div/a[@title='%s']");
        String locatorTypeS = locator.toString().split(": ")[0].split("\\.")[1];
        String newLocatorString = String.format(locator.toString().split(": ")[1], dynamicText);
        LocatorType locatorType = LocatorType.valueOf(locatorTypeS);
        switch (locatorType) {
            case xpath:
                locator = By.xpath(newLocatorString);
                break;
            case cssSelector:
                locator = By.cssSelector(newLocatorString);
                break;
            case id:
                locator = By.id(newLocatorString);
                break;
            case className:
                locator = By.className(newLocatorString);
                break;
            case name:
                locator = By.name(newLocatorString);
                break;
            case linkText:
                locator = By.linkText(newLocatorString);
                break;
            case partialLinkText:
                locator = By.partialLinkText(newLocatorString);
                break;
            case tagName:
                locator = By.tagName(newLocatorString);
                break;
        }
        return locator;
    }

    public static String getNewLocatorios(String locator, String dynamicText) {

        String xpath = locator.replace("%s", dynamicText);

        return xpath;
    }

    public static String getNewLocatorios(String locator, String dynamicText, String text) {
        String xpath = locator.replace("%s", dynamicText);
        xpath = xpath.replace("#", text);
        return xpath;
    }

    public static String getNewLocatorios(String locator, String dynamicText, String count, String text) {
        String xpath = locator.replace("%s", dynamicText);
        xpath = xpath.replace("%c", count);
        xpath = xpath.replace("#", text);
        return xpath;
    }

    public static By getNewLocator(By locator, String dynamicText, String dynamicText2) {
        //By.xpath("//div/a[@title='%s']");
        String locatorTypeS = locator.toString().split(": ")[0].split("\\.")[1];
        String newLocatorString = String.format(locator.toString().split(": ")[1], dynamicText);
        newLocatorString = newLocatorString.replace("#", dynamicText2);
        LocatorType locatorType = LocatorType.valueOf(locatorTypeS);
        switch (locatorType) {
            case xpath:
                locator = By.xpath(newLocatorString);
                break;
            case cssSelector:
                locator = By.cssSelector(newLocatorString);
                break;
            case id:
                locator = By.id(newLocatorString);
                break;
            case className:
                locator = By.className(newLocatorString);
                break;
            case name:
                locator = By.name(newLocatorString);
                break;
            case linkText:
                locator = By.linkText(newLocatorString);
                break;
            case partialLinkText:
                locator = By.partialLinkText(newLocatorString);
                break;
            case tagName:
                locator = By.tagName(newLocatorString);
                break;
        }
        return locator;
    }

    public static By getNewLocator(By locator, String dynamicText, String dynamicText2, String dynamicText3) {
        //By.xpath("//div/a[@title='%s']");
        String locatorTypeS = locator.toString().split(": ")[0].split("\\.")[1];
        String newLocatorString = String.format(locator.toString().split(": ")[1], dynamicText);
        newLocatorString = newLocatorString.replace("#", dynamicText2);
        newLocatorString = newLocatorString.replace("$", dynamicText3);
        LocatorType locatorType = LocatorType.valueOf(locatorTypeS);
        switch (locatorType) {
            case xpath:
                locator = By.xpath(newLocatorString);
                break;
            case cssSelector:
                locator = By.cssSelector(newLocatorString);
                break;
            case id:
                locator = By.id(newLocatorString);
                break;
            case className:
                locator = By.className(newLocatorString);
                break;
            case name:
                locator = By.name(newLocatorString);
                break;
            case linkText:
                locator = By.linkText(newLocatorString);
                break;
            case partialLinkText:
                locator = By.partialLinkText(newLocatorString);
                break;
            case tagName:
                locator = By.tagName(newLocatorString);
                break;
        }
        return locator;
    }

    public static By getLocator(By locator, String dynamicText, String dynamicText2) {
        //By.xpath("//div/a[@title='%s']");
        String locatorTypeS = locator.toString().split(": ")[0].split("\\.")[1];
        String newLocatorString = String.format(locator.toString().split(": ")[1], dynamicText);
        newLocatorString = newLocatorString.replace("$", dynamicText2);
        LocatorType locatorType = LocatorType.valueOf(locatorTypeS);
        switch (locatorType) {
            case xpath:
                locator = By.xpath(newLocatorString);
                break;
            case cssSelector:
                locator = By.cssSelector(newLocatorString);
                break;
            case id:
                locator = By.id(newLocatorString);
                break;
            case className:
                locator = By.className(newLocatorString);
                break;
            case name:
                locator = By.name(newLocatorString);
                break;
            case linkText:
                locator = By.linkText(newLocatorString);
                break;
            case partialLinkText:
                locator = By.partialLinkText(newLocatorString);
                break;
            case tagName:
                locator = By.tagName(newLocatorString);
                break;
        }
        return locator;
    }

    public static void openNewTab() {
        ((JavascriptExecutor) driver).executeScript("window.open()");
    }

    public static void switchToTab() throws InterruptedException {
        tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Thread.sleep(3000);
    }

    public static void getURL(String url) {
        driver.get(url);

    }

    public static void switchToMainTab() {
        tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }

    public void cookieSetting() {
        if (driver.findElements(By.xpath("//a[contains(text(),'Got it')]")).size() > 0) {
            driver.findElement(By.xpath("//a[contains(text(),'Got it')]")).click();
        } else {
            System.out.println("Cookie Accept Button not Available");
        }
    }

    /*  public void encryptString(String stringToEncrypt, String keyToEncrypt){
          driver.findElement(EncryptDecrypt.inputString).sendKeys(stringToEncrypt);
          driver.findElement(EncryptDecrypt.keyToEncryptOrDecrypt).sendKeys(keyToEncrypt);
          driver.findElement(EncryptDecrypt.encrypt).click();
      }
      public void decryptString(String stringToDecrypt, String keyToDecrypt){
          cookieSetting();
          waitForJavaScriptToLoad();
          driver.findElement(EncryptDecrypt.inputString).sendKeys(stringToDecrypt);
          driver.findElement(EncryptDecrypt.keyToEncryptOrDecrypt).sendKeys(keyToDecrypt);
          driver.findElement(EncryptDecrypt.decrypt).click();
          waitForJavaScriptToLoad();

      }
      public String resultAfterEncryptionOrDecryption(){
          String output = driver.findElement(EncryptDecrypt.outputString).getText();
          System.out.println("Output After Encryption/Decreyption is : " + output);
          return output;
      }*/
    public static String getCurrentTimeStamp() {
        String stamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
        return stamp;
    }

    public static String getCurrentDate1() {
        String stamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return stamp;
    }

    public static String getCurrentDate() {
        String date = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        return date;
    }

    /**
     * This method will give future date using current day + given day
     * 06/04/2020 + 1 day = 06/05/2020
     */
    public static String getFutureDates(int day) {

        Instant now = Instant.now(); //current date
        Instant after = now.plus(Duration.ofDays(day));
        Date dateAfter = Date.from(after);
        String changedDate = new SimpleDateFormat("MM/dd/yyyy").format(dateAfter);
        return changedDate;

    }

    public static String randamPatientName() {

        //Patient Name
        String patientName = "Z_" + RandomStringUtils.randomAlphabetic(3) + " " + RandomStringUtils.randomAlphabetic(3);
        System.out.println("Generated Patient Name:  " + patientName);
        return patientName;

    }

    public static String randamformName() {

        //Patient Name
        String formName = "CustomOrder" + RandomStringUtils.randomAlphabetic(3);
        System.out.println("Generated form Name:  " + formName);
        return formName;

    }

    //30/05/2021
    public void enterCalandarDate(By locator, String date) {
        // Date select
    }

    public static String randamPatientName(String l) {

        //Patient Name
        String patientName = l + "_" + RandomStringUtils.randomAlphabetic(3) + " " + RandomStringUtils.randomAlphabetic(3);
        System.out.println("Generated Patient Name:  " + patientName);
        return patientName;

    }

    public static String randamAnonID() {
        long aNumber1 = 0;
        aNumber1 = (long) ((Math.random() * 900000000) + 100000000);
        System.out.println((aNumber1));
        return Long.toString(aNumber1);

        /*Random r = new Random();
        String uppercaseletter = String.valueOf((char) (r.nextInt(26) + 'c')).toUpperCase();
        System.out.println(uppercaseletter);
        final String randomCode2 = UUID.randomUUID().toString().substring(0, 5);
        String AnonId = randomCode2.toUpperCase();
        //String.valueOf(String.valueOf(a1) + String.valueOf(aNumber1));
        System.out.println(AnonId);
        return AnonId;*/

    }

    public static String phoneNumber() {
        int aNumberA = 0;
        aNumberA = (int) ((Math.random() * 9000000) + 1000000);
        int a2 = 111;
        int mobilenumA = Integer.valueOf(String.valueOf(a2) + String.valueOf(aNumberA));
        System.out.println(mobilenumA);
        return Integer.toString(mobilenumA);

    }

    public static String generateRandomDOB() {
        Random random = new Random();
        int month = random.nextInt(12) + 1; // 1 to 12
        int date = random.nextInt(28) + 1; // 1 to 28
        int year = random.nextInt(2022 - 1930 + 1) + 1930; // 1950 to 2022

        return String.format("%02d/%02d/%04d", month, date, year);
    }


    public static void saveDataOnProperty(String name, String value) throws IOException {
        FileOutputStream out = null;
        Properties testData = null;
        try {
            testData = new Properties();
            InputStream in = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config/testData.properties");
            testData.load(in);
            out = new FileOutputStream(System.getProperty("user.dir") + "/src/main/resources/config/testData.properties");
            testData.setProperty(name, value);


        } catch (Exception e) {
            test.log(Status.INFO, e.getMessage());
            test.log(Status.FAIL, "Error on saving the data");

        } finally {
            testData.store(out, null);
            out.close();
        }
    }

    public static String readPropertyData(String key) throws IOException {

        String value = null;
        InputStream stream = null;
        if (key.equalsIgnoreCase("newPatient")) {
            String jenkinsPatient = System.getenv("Jenkins_PatientName");
            if (jenkinsPatient == null) {
                System.out.println("No value on Jenkins");
                try {
                    Properties testData = new Properties();
                    stream = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config/testData.properties");
                    testData.load(stream);
                    value = testData.getProperty(key);
                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    stream.close();
                }
            } else
                value = jenkinsPatient;

        } else {
            try {
                Properties testData = new Properties();
                stream = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config/testData.properties");
                testData.load(stream);
                value = testData.getProperty(key);
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                stream.close();
            }
        }
        return value;
    }


}
