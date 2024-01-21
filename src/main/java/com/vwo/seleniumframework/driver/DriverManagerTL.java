package com.vwo.seleniumframework.driver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class DriverManagerTL {

	
	static WebDriver driver;
    private static final ThreadLocal<WebDriver> dr = new ThreadLocal<>();

    public static void setDriver(WebDriver driverRef){
        dr.set(driverRef);
    }

    public static WebDriver getDriver() {
        return dr.get();
    }

    public static void unload(){
        dr.remove();
    }

    @BeforeMethod
    public static void init() throws MalformedURLException {
        if(getDriver() == null){
            // TODO #1 - Need to make it support for Firefox, Edge, Safari, and IE

            ChromeOptions options = new ChromeOptions();
            options.setCapability("selenoid:options", new HashMap<String, Object>() {{
                /*  adding test badge */
                put("name", "Test badge...");

                /* setting session timeout */
                put("sessionTimeout", "15m");

                /*  setting timezone */
                put("env", new ArrayList<String>() {{
                    add("TZ=UTC");
                }});

                /* How to add "trash" button */
                put("labels", new HashMap<String, Object>() {{
                    put("manual", "true");
                }});

                /* How to enable video recording */
                put("enableVideo", true);
            }});
            driver = new RemoteWebDriver(new URL("http://52.23.178.106:4444/wd/hub"), options);
            setDriver(driver);
        }
    }


    @AfterMethod
    public static void down(){
        if(getDriver()!= null){
            getDriver().quit();
        }

    }
}
