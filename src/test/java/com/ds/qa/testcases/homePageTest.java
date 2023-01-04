package com.ds.qa.testcases;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ds.qa.base.TestBase;
import com.ds.qa.pages.HomePage;
import com.ds.qa.pages.getStartPage;
import com.ds.qa.util.Testutil;

public class homePageTest extends TestBase {
	public getStartPage startPage;
	public HomePage homePage;
	public Testutil testutil;
	public static String title;
	public static String message;

	public homePageTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		initialization();
		homePage = new HomePage();
		startPage = new getStartPage();
		homePage = startPage.getStartClick();
		testutil = new Testutil(driver);
	}

	@Test
	public void verifyPageTitle() {
		String title = testutil.getPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, "NumpyNinja");
	}

	@Test
	public void sectionCountDisplay() {
		int c = homePage.sectionCount();
		System.out.println("Total number of columns present in homepage is: " + c);
		Assert.assertEquals(c, 7);
	}

	@Test
	public void homeColDisplayTest() {
		List<String> exp = Arrays.asList("Arrays", "Linked List", "Stack", "Queue", "Tree", "Graph");

		List<String> actual = homePage.actValue();
		Assert.assertEquals(actual, exp, "Error in compare dropdown value");
	}

	@Test
	public void verifyLoginTest() {
		message = homePage.VerifyLogin();
		Assert.assertEquals(message, "You are not logged in");
	}

	@Test
	public void verfiyclickDs() {
		message = homePage.verifyError();
		Assert.assertEquals(message, "You are not logged in");
	}

	@Test
	public void verifyRegTest() {
		homePage.regclick();
		title = testutil.getPageTitle();
		Assert.assertEquals(title, "Registration");
	}

	@Test
	public void verifySigninTest() {
		homePage.signClick();
		title = testutil.getPageTitle();
		Assert.assertEquals(title, "Login");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
