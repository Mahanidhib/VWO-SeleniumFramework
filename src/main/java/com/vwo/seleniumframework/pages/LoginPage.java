package com.vwo.seleniumframework.pages;

import org.openqa.selenium.By;

import com.vwo.seleniumframework.base.BasePage;
import com.vwo.seleniumframework.driver.DriverManagerTL;
import com.vwo.seleniumframework.utils.PropertyReader;



public class LoginPage extends BasePage{
	
	 public LoginPage() {
	        super();
	    }

	    // Page Locators
	    private By username = By.id("login-username");
	    private By password = By.id("login-password");
	    private By signButton = By.id("js-login-btn");

	    By error_message = By.id("js-notification-box-msg");

	    // Page Actions

	    public LoginPage loginToVWO(boolean invalid) throws Exception {

	        if (!invalid) {
	            enterInput(username, PropertyReader.readKey("invalid_username"));
	        } else {
	            enterInput(username, PropertyReader.readKey("username"));
	        }

	        enterInput(password, PropertyReader.readKey("password"));
	        clickElement(signButton);
	        return this;
	    }

	    public String error_message_text() throws InterruptedException {
	        visibilityOfElementLocated(error_message);
	        return DriverManagerTL.getDriver().findElement(error_message).getText();
	    }

	    // Another - Remaing

	    public DashboardPage afterLogin() {
	        return new DashboardPage();
	    }
	}
