package com.ds.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ds.qa.base.TestBase;
import com.ds.qa.pages.GetStartPage;
import com.ds.qa.pages.HomePage;

public class GetStartPageTest extends TestBase {
	public GetStartPage startPage;
	public HomePage homePage;
	public String title;

	public GetStartPageTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		initialization();
		log.info("GetStartedPage Test- initializing the browser for GetStartPage Testcases");
		startPage = new GetStartPage();

	}

	@Test
	public void pageTitle() {
		title = startPage.validateDsPageTitle();
		Assert.assertEquals(title, "Numpy Ninja");
		log.info("GetStartedPage Test-GetStarted Page Title");

	}

	@Test
	public void landHomePageTest() {
		homePage = startPage.getStartClick();
		log.info("GetStartedPage Test-Click on Get StartPage button and lands on Home Page");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}
}