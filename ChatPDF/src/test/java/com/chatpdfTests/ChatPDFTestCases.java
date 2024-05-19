package com.chatpdfTests;

import com.base.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ChatPDFTestCases extends BaseTest {


    @Test
    public void testFileUpload() {
        String filePath = "/Users/venkata.pulaparthi/Downloads/Question1.pdf";
        driver.findElement(By.xpath("//a[text()='Browse my Computer']")).sendKeys(filePath);
    }
}
