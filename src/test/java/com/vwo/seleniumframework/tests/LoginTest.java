package com.vwo.seleniumframework.tests;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;

import com.aventstack.extentreports.ExtentTest;
import com.thetestingacademy.utils.ProperReader;
import com.vwo.seleniumframework.driver.DriverManagerTL;
import com.vwo.seleniumframework.pages.LoginPage;
import com.vwo.seleniumframework.testbase.TestBase;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class LoginTest extends TestBase { 
    ExtentTest test;
    private static final Logger logger = LogManager.getLogger(LoginTest.class);


    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify the Valid Credentials are working file")
//    @Test(groups = {"P0", "negative", }, priority = 1)
    @Test(groups = {"QA"})
    public void invalid_loginVWO(Method method) throws Exception {
       
        
        test = createTest(method.getName());
        DriverManagerTL.getDriver().get(ProperReader.readKey("url"));
        String expectResult = new LoginPage().loginToVWO(false).error_message_text();
        logger.info("Failed");

        if (expectResult.equalsIgnoreCase(expectResult)) {
            logger.info("Failed");
            test.fail("Failed Testcases");
            test.addScreenCaptureFromBase64String(captureScreenshot(DriverManagerTL.getDriver()));
            takeScreenShot(method.getName(),DriverManagerTL.getDriver());
        }

        Assertions.assertThat(expectResult)
                .isNotBlank().isNotNull().contains(ProperReader.readKey("expected_error"));

    }
}

//    @Severity(SeverityLevel.CRITICAL)
//    @Description("Verify the Invalid Credentials are working file")
//    @Test(groups = {"P0", "positive"},priority = 2)
//    public void testLoginPositive() throws Exception {
//        // Navigate, Login to VWO and Assert
//        // Abstraction
//        DriverManagerTL.getDriver().get(ProperReader.readKey("url"));
//        DashboardPage dashboardPage = new LoginPage().loginToVWO(true).afterLogin();
//        String expectResult = dashboardPage.loggedInUserName();
//        Assertions.assertThat(expectResult)
//                .isNotBlank().isNotNull().contains(ProperReader.readKey("expected_username"));
//
//    }
