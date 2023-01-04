package com.ds.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ds.qa.base.TestBase;
import com.ds.qa.pages.HomePage;
import com.ds.qa.pages.getStartPage;

public class getStartPageTest extends TestBase {
	public getStartPage startPage;
	public HomePage homePage;
	public String title;

	public getStartPageTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		initialization();
		startPage = new getStartPage();
	}

	@Test
	public void pageTitle() {
		title = startPage.validateDsPageTitle();
		Assert.assertEquals(title, "Numpy Ninja");
	}

	@Test
	public void landHomePageTest() {
		homePage = startPage.getStartClick();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
