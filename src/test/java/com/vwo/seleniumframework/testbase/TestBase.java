package com.vwo.seleniumframework.testbase;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.vwo.seleniumframework.driver.DriverManagerTL;

import io.qameta.allure.Allure;

public class TestBase {


    ExtentReports extent = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter("target/report.html");
    ExtentTest test;



    // Call to Driver, TakeScreenshot,
    @BeforeSuite
    protected void setUp() throws Exception {
        extent.attachReporter(spark);
        DriverManagerTL.init();
    }

    public ExtentTest createTest(String name) {
        return extent.createTest(name).assignCategory("Regression Test").assignDevice("MacOsx");
    }

    @BeforeTest
    public void setConfig() {
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("VWO Testcases");
    }

    @AfterSuite
    protected void tearDown() {
        DriverManagerTL.down();
        extent.flush();
    }

    protected void takeScreenShot(String name, WebDriver driver) {
        Allure.addAttachment(name, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    public static String captureScreenshot(WebDriver driver) throws IOException {
        File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destination_filepath = new File(System.getProperty("user.dir") + "images/screenshot" + System.currentTimeMillis() + ".png");
        FileUtils.copyFile(srcfile, destination_filepath);
        return destination_filepath.toString();
    }
}
