package com.pages;

import com.base.BaseTest;
import com.engine.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.Set;

import static com.base.BaseTest.timeToWaitElementLoad;

public class Chatpdf_HomePage extends BaseTest {

    public static By browseMyComputer_lnk = By.xpath("//a[text()='Browse my Computer']");
    public static By fileUpload_txt = By.xpath("//input[@type='file']");
    public static By signIn = By.xpath("//a[text()='Sign in']");
    public static By email_txt = By.id("identifierId");
    public static By next_btn = By.xpath("//span[text()='Next']");
    public static By password_txt=By.name("Passwd");

    public void clicksOnBrowseMyComputer() {
        Utility.waitForElementAndClick(browseMyComputer_lnk, timeToWaitElementLoad);
    }

    public void signInAndLogin(String userName,String password)
    {

        // Get the current window handle
        String mainWindowHandle = driver.getWindowHandle();
        Utility.waitForElementAndClick(signIn,timeToWaitElementLoad);
        Utility.Pause(5);
        // Get all window handles
        Set<String> allWindowHandles = driver.getWindowHandles();

        // Iterate over all handles
        for (String handle : allWindowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                // Switch to the new window/tab
                driver.switchTo().window(handle);
                break;
            }
        }

        Utility.waitForElementAndType(email_txt,userName,timeToWaitElementLoad);
        Utility.waitForElementAndClick(next_btn,timeToWaitElementLoad);
        Utility.waitForElementAndType(password_txt,password,timeToWaitElementLoad);
        Utility.waitForElementAndClick(next_btn,timeToWaitElementLoad);
        Utility.Pause(10);
    }

    public void refreshURL(String url)
    {
        driver.get(url);
    }

    public void fileUpload(String filePath) {
        // Execute JavaScript to set the file path in the file input element
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "var input = document.querySelector('input[type=\"file\"]');" +
                "input.style.display = 'block';" +
                "input.style.visibility = 'visible';" +
                "input.style.opacity = 1;";
        js.executeScript(script);
        driver.findElement(fileUpload_txt).sendKeys(filePath);
    }


}
