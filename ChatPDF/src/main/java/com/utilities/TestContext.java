package com.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class TestContext {

    public static Properties properties;
    public static Properties testProperties;


    static {

        properties = new Properties();
        InputStream input = null;
        InputStream inputMobile = null;

        try {
            System.out.println("TextContext Class Load...");
            input = TestContext.class.getClassLoader().getResourceAsStream("config/environment.properties");
            inputMobile = TestContext.class.getClassLoader().getResourceAsStream("config/mobile.properties");
            properties.load(input);
            properties.load(inputMobile);



        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getBrowser() {
        return properties.getProperty("BROWSER");
    }

    public static String getLogin() {
        return properties.getProperty("login");
    }

    public static String getAppUrl() {
        String Environment = System.getProperty("Environment");
        System.out.println("Environment From Jenkins is :" + Environment);
        if (Environment == null || Environment.equals(null)) {
            Environment = properties.getProperty("PORTALURL");
        }
        return Environment;
    }

    public static String getEnvironment() {
        Properties testData = null;
        String url = System.getProperty("Environment");
        try {
            System.out.println("TextContext ENv prop..");
            InputStream fileTESTDATA = TestContext.class.getClassLoader().getResourceAsStream("config/testInfo.properties");
            System.out.println("TextContext ENv prop Obj.."+fileTESTDATA);
            testData = new Properties();
            testData.load(fileTESTDATA);
        }
        catch (Exception e)
        {
            System.out.println(e+" en prop");
        }
        System.out.println(url + " Before url");
        if (url == null) {
            url = testData.getProperty("LocalEnvironment");
        }

        return url;
    }

    public static String getTimeToWaitElementLoad() {
        return properties.getProperty("TimeToWaitElementLoad");
    }


}
