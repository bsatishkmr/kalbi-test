package com.kalbi.test.smoke;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.kalbi.lib.ui.LoginPage;
import com.kalbi.test.config.CreateDriver;

public class TestSample 
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
	public void testSample()
	{
		// Wait for login page
		login.waitForLoginPageToLoad();
		// Get Title and verify
		String actualTitle = driver.getTitle();
		String expectedTitle = "actiTIME - Login";
		Assert.assertEquals(actualTitle, expectedTitle);
	}
}