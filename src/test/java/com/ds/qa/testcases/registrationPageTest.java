package com.ds.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ds.qa.base.TestBase;
import com.ds.qa.pages.HomePage;
import com.ds.qa.pages.RegistrationPage;
import com.ds.qa.pages.getStartPage;
import com.ds.qa.util.Testutil;
import com.ds.qa.util.XLUtility;

public class registrationPageTest extends TestBase {

	HomePage homePage;
	getStartPage startPage;
	RegistrationPage regPage;
	String sheetname = "Register";
	static String title;
	public Testutil testutil;

	public registrationPageTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		initialization();
		startPage = new getStartPage();
		homePage = new HomePage();
		regPage = new RegistrationPage();
		testutil = new Testutil(driver);
		homePage = startPage.getStartClick();
		homePage.regclick();
		log.info("Registration Test - Initializing browser for registration testcases");

	}

	@DataProvider
	public String[][] getRegTestData() throws IOException {
		String path = "C:/Users/cinth/eclipse-workspace/DS-Algo/src/test/java/com/ds/qa/testdata/registrationdata.xlsx";
		XLUtility xlutil = new XLUtility(path);

		int totalrows = xlutil.getRowCount(sheetname);
		int totalcols = xlutil.getCellCount(sheetname, 1);

		String regData[][] = new String[totalrows][totalcols];
		for (int i = 1; i <= totalrows; i++) // 1
		{
			for (int j = 0; j < totalcols; j++) // 0
			{
				regData[i - 1][j] = xlutil.getCellData(sheetname, i, j);
			}
		}
		return regData;
	}

	@Test(dataProvider = "getRegTestData")
	public void validateRegCredential(String username, String password, String repassword, String exp) {

		regPage.regNewUser(username, password, repassword);
		String exp_title = regPage.alertmsg();
		String act_title = testutil.getPageTitle();
		if (exp.equals("valid")) {
			if (act_title.contains("NumpyNinja")) {
				log.info("Registration Test - The Registration page successfully registered userdetails");
				log.info("Registration Test - user is on Homepage");
				Assert.assertTrue(true);
			} else {
				log.info("Registration Test - NOT registered Successfully");
				Assert.assertTrue(false);
			}
		} else if (exp.equals("invalid")) {
			if (exp_title.contains("password_mismatch:")) {
				log.info("Registration Test - The user details NOT registered");
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		}
	}

	@Test
	public void verfiySinginClick() {
		regPage.clickSignIn();
		title = testutil.getPageTitle();
		Assert.assertEquals(title, "Login");
		log.info("Registration Test - User currently on SigninPage");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		log.info("Registration Test - closing browser for registration testcases");

	}

}
