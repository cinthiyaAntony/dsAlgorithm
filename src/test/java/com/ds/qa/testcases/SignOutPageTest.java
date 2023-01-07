package com.ds.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ds.qa.base.TestBase;
import com.ds.qa.pages.GetStartPage;
import com.ds.qa.pages.HomePage;
import com.ds.qa.pages.RegistrationPage;
import com.ds.qa.pages.SigninPage;
import com.ds.qa.pages.SignoutPage;
import com.ds.qa.util.Testutil;

public class SignOutPageTest extends TestBase {
	HomePage homePage;
	GetStartPage startPage;
	RegistrationPage regPage;
	SigninPage signPage;
	SignoutPage signoutPage;
	static String title;
	public Testutil testutil;

	public SignOutPageTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		initialization();
		startPage = new GetStartPage();
		homePage = new HomePage();
		regPage = new RegistrationPage();
		signPage = new SigninPage();
		signoutPage = new SignoutPage();

		testutil = new Testutil(driver);
		homePage = startPage.getStartClick();
		signPage = homePage.signClick();
		log.info("SignOut Page - Initializing browser for SigninOut testcases");
	}

	@Parameters({ "username", "password" })
	@Test
	public void signoutTest(String username, String password) {
		testutil.userDetial(username, password);
		homePage = signoutPage.loginBtnClick();
		String message = signoutPage.signoutclick();
		Assert.assertEquals(message, "Logged out successfully");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		log.info("Signout Page Test - closing browser for registration testcases");

	}

}
