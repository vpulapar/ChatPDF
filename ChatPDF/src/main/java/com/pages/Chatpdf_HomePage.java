package com.pages;

import com.base.BaseTest;
import com.engine.Utility;
import org.openqa.selenium.By;
import static com.base.BaseTest.timeToWaitElementLoad;

public class Chatpdf_HomePage {

    public static By browseMyComputer_lnk=By.xpath("//a[text()='Browse my Computer']");


    public void clicksOnBrowseMyComputer()
    {
        Utility.waitForElementAndClick(browseMyComputer_lnk, timeToWaitElementLoad);
    }


}
