package com.vwo.seleniumframework.pages;

import org.openqa.selenium.By;

import com.vwo.seleniumframework.base.BasePage;

public class DashboardPage extends BasePage {
	 DashboardPage() {
	    }

	    By userNameOnDashboard = By.xpath("//span[@data-qa=\"lufexuloga\"]");


	    // Page Actions
	    public String loggedInUserName() {
	        try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            throw new RuntimeException(e);
	        }
	        //presenceOfElement(userNameOnDashboard);
	        return getElement(userNameOnDashboard).getText();
	    }
	}


