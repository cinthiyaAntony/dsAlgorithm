package com.ds.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ds.qa.base.TestBase;
import com.ds.qa.pages.GetStartPage;
import com.ds.qa.pages.HomePage;
import com.ds.qa.pages.RegistrationPage;
import com.ds.qa.pages.SigninPage;
import com.ds.qa.util.Testutil;
import com.ds.qa.util.XLUtility;

public class SignInPageTest extends TestBase {

	HomePage homePage;
	GetStartPage startPage;
	RegistrationPage regPage;
	SigninPage signPage;

	String sheetname = "signin";
	static String title;
	public Testutil testutil;

	public SignInPageTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		initialization();
		startPage = new GetStartPage();
		homePage = new HomePage();
		regPage = new RegistrationPage();
		signPage = new SigninPage();
		testutil = new Testutil(driver);
		homePage = startPage.getStartClick();
		signPage = homePage.signClick();
		log.info("SigininPage - Initializing browser for registration testcases");
	}

	@DataProvider
	public String[][] getSignTestData() throws IOException {
		String path = "C:/Users/cinth/eclipse-workspace/DS-Algo/src/test/java/com/ds/qa/testdata/registrationdata.xlsx";
		XLUtility xlutil = new XLUtility(path);

		int totalrows = xlutil.getRowCount(sheetname);
		int totalcols = xlutil.getCellCount(sheetname, 1);

		String signData[][] = new String[totalrows][totalcols];
		for (int i = 1; i <= totalrows; i++) // 1
		{
			for (int j = 0; j < totalcols; j++) // 0
			{
				signData[i - 1][j] = xlutil.getCellData(sheetname, i, j);
			}
		}
		return signData;
	}

	@Test(dataProvider = "getSignTestData")
	public void validateSignCredential(String username, String password, String exp) {

		signPage.signUser(username, password);
		if (exp.equals("invalid")) {
			title = signPage.errBtn();
			log.info("SigninPage Test - invalid userdetails");
			Assert.assertTrue(title.contains("Invalid Username and Password"));
		}
		if (exp.equals("valid")) {
			log.info("SigninPage Test - user signin Successfully");
			title = testutil.getPageTitle();
			Assert.assertTrue(title.contains("NumpyNinja"));
		}

	}

	public void verifyRegInSignin() {
		signPage.regClick();
		title = testutil.getPageTitle();
		Assert.assertTrue(title.contains("Registration"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		log.info("SigninPage Test - closing browser for registration testcases");

	}

}
