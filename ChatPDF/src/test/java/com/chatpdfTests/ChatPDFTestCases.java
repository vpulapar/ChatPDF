package com.chatpdfTests;

import com.aventstack.extentreports.Status;
import com.base.BaseTest;
import com.engine.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class ChatPDFTestCases extends BaseTest {


    @Test(priority = 1,description = "Upload a pdf question file")
    public void testFileUpload() {

        test =extent.createTest("Upload a pdf question file");
        test.log(Status.PASS,"Upload a pdf question file");
        String filePath = "/Users/venkata.pulaparthi/Question1.pdf";
        objectsEngine.chatpdfHomePage.clicksOnBrowseMyComputer();
        objectsEngine.chatpdfHomePage.fileUpload(filePath);
        Utility.ecsClick();
        objectsEngine.chatpdfHomePage.signInAndLogin("vb.krishna465@gmail.com","xxxx");
        System.out.println();

    }

    @Test(priority = 2,description = "chat feature: ask question, u get answer")
    public void chatQuestionAndAnswers(){

        test =extent.createTest("chat feature: ask question, u get answer");
        test.log(Status.PASS,"chat feature: ask question, u get answer");
        String filePath = "/Users/venkata.pulaparthi/Question1.pdf";
        objectsEngine.chatpdfHomePage.clicksOnBrowseMyComputer();
        objectsEngine.chatpdfHomePage.fileUpload(filePath);
        Utility.ecsClick();
        objectsEngine.chatpdfHomePage.signInAndLogin("vb.krishna465@gmail.com","xxxx");
        System.out.println();

    }

    @Test(priority = 3,description = "Login Chat PDF")
    public void loginCharPDF(){

        test =extent.createTest("Login Chat PDF");
        test.log(Status.PASS,"Login Chat PDF");
        String filePath = "/Users/venkata.pulaparthi/Question1.pdf";
        objectsEngine.chatpdfHomePage.clicksOnBrowseMyComputer();
        objectsEngine.chatpdfHomePage.fileUpload(filePath);
        Utility.ecsClick();
        objectsEngine.chatpdfHomePage.signInAndLogin("vb.krishna465@gmail.com","xxxx");
        System.out.println();

    }

}
