package com.kalbi.test.regression;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.kalbi.lib.ui.LoginPage;
import com.kalbi.lib.util.DataHandlers;
import com.kalbi.test.config.CreateDriver;

public class TestLoginS13265 
{
	WebDriver driver;
	LoginPage login;
	
	@BeforeMethod
	public void preCondition()
	{
		driver = CreateDriver.getDriverInstance();
		login = new LoginPage(driver);
	}
	@AfterMethod
	public void postCondition()
	{
		driver.close();
	}
	@Test
	public void testInvalidLoginTC135462()
	{
		String un = DataHandlers.
				getDataFromExcel("data", "TC135462", 1, 0);
		String pwd = DataHandlers.
				getDataFromExcel("data", "TC135462", 1, 1);
		// Go to Login page
		login.waitForLoginPageToLoad();
		// Enter valid username
		login.getUsernameTextbox().sendKeys(un);
		// Enter Invalid password
		login.getPasswordTextbox().sendKeys(pwd);
		// Click on login button
		login.getLoginButton().click();
		// Get Error Msg
		String actualErrorMsg = login.getErrorLoginMsg().getText();
		String expectedErrorMsg = "Username or Password is "
				+ "invalid. Please try again.";
		Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
	}
}
