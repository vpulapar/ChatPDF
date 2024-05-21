package com.pages;

import com.base.BaseTest;
import com.engine.Utility;
import com.engine.UtilityMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Chat_Page extends BaseTest {

    public static By askQuestion_txt=By.xpath("//textarea[@placeholder='Ask any question…']");
    public static By send_btn=By.xpath("//button/span[@aria-label='send']");
    public static By answerForQuestion=By.xpath("(//p[text()='%s']/../../../../..//following-sibling::ol)[1]");
    public static By answerFooter=By.xpath("(//p[text()='%s']/../../../../..//following-sibling::p)[1]");
    public static By newChart_btn=By.xpath("//div[normalize-space()='New Chat']/span");
    public static By allListAnswers=By.xpath("//p[text()='%s']/ancestor::div[@class='chat-message-row human']//following-sibling::div//p");



    public void enterQuestion(String question)
    {
        Utility.waitForElementAndType(askQuestion_txt,question,timeToWaitElementLoad);

    }
    public void ClickOnSend()
    {
        Utility.waitForElementAndClick(send_btn);
    }

    public List<String> getAnswerList(String question)
    {
        List<String> answersValues=new ArrayList<>();
        Utility.waitFor(UtilityMethods.getNewLocator(allListAnswers,question),timeToWaitElementLoad);
        List<WebElement> getAllAnsList=driver.findElements(UtilityMethods.getNewLocator(allListAnswers,question));
        for (WebElement ele:getAllAnsList
             ) {
            answersValues.add(ele.getText());
        }
        return answersValues;
    }

}
