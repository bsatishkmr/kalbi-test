package com.kalbi.test.regression;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.kalbi.lib.ui.HomePage;
import com.kalbi.lib.ui.LoginPage;
import com.kalbi.lib.util.DataHandlers;
import com.kalbi.test.config.CreateDriver;

public class TestLoginS13266
{
	WebDriver driver;
	LoginPage login;
	HomePage home;
	
	@BeforeMethod
	public void preCOndition()
	{
		driver = CreateDriver.getDriverInstance();
		login = new LoginPage(driver);
		home = new HomePage(driver);
	}
	@AfterMethod
	public void postCondition()
	{
		driver.close();
	}
	@Test
	public void testValidLoginTC135467()
	{
		String un = DataHandlers.
				getDataFromExcel("data", "TC135467", 1, 0);
		String pwd = DataHandlers.
				getDataFromExcel("data", "TC135467", 1, 1);
		// Go to Login page 
		login.waitForLoginPageToLoad();
		// Enter valid UN 
		login.getUsernameTextbox().sendKeys(un);
		// Enter valid Password
		login.getPasswordTextbox().sendKeys(pwd);
		// Click on Login Button
		login.getLoginButton().click();
		// Verify Home Page
		home.waitForHomePageToLoad();
		// Verify logout button
		boolean actualStatus = home.getLogoutButton().isDisplayed();
		Assert.assertEquals(actualStatus, true);
		// Click on Logout button
		home.getLogoutButton().click();
		login.waitForLoginPageToLoad();
	}
}
