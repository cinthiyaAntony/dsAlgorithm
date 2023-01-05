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
		log.info("HomePage - Initializing browser for HomePage Testcases");

	}

	@Test
	public void verifyPageTitle() {
		String title = testutil.getPageTitle();
		log.info("HomePage -verfiying HomePage title");
		Assert.assertEquals(title, "NumpyNinja");
	}

	@Test
	public void sectionCountDisplay() {
		int c = homePage.sectionCount();
		log.info("HomePage -Total number of columns present in homepage is: " + c);
		Assert.assertEquals(c, 7);
	}

	@Test
	public void homeColDisplayTest() {
		List<String> exp = Arrays.asList("Arrays", "Linked List", "Stack", "Queue", "Tree", "Graph");

		List<String> actual = homePage.actValue();
		Assert.assertEquals(actual, exp, "Error in compare dropdown value");
		log.info("HomePage -expected DataStructure list and actual DataStructure list are same");

	}

	@Test
	public void verifyLoginTest() {
		message = homePage.VerifyLogin();
		log.info("HomePage -Verfiy sginin link in home page ");
		Assert.assertEquals(message, "You are not logged in");
	}

	@Test
	public void verfiyclickDs() {
		message = homePage.verifyError();
		log.info("HomePage -verfiying can access datastructures in home page without signin");
		Assert.assertEquals(message, "You are not logged in");
	}

	@Test
	public void verifyRegTest() {
		homePage.regclick();
		title = testutil.getPageTitle();
		Assert.assertEquals(title, "Registration");
		log.info("The current page title is Registration page");

	}

	@Test
	public void verifySigninTest() {
		homePage.signClick();
		title = testutil.getPageTitle();
		Assert.assertEquals(title, "Login");
		log.info("HomePage -The current page title is Signin Page");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		log.info("HomePage -closing browser for homepage testcases");

	}
}
