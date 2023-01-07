package com.ds.qa.util;

import static org.testng.Assert.assertFalse;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;

import com.ds.qa.base.TestBase;

public class Testutil extends TestBase {

	WebDriver driver;
	public WebDriverWait wait;
	int timeout = 20;

	String usrnameLocator = "//input[@name='username']";
	String pwdLocator = "//input[@name='password']";

	public Testutil(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement waitForElement(String elementLocator) {
		WebElement webelement = null;
		int timeout = 20; // in seconds
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			System.out.println(elementLocator);
			webelement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementLocator)));
		} catch (WebDriverException e) {

		}
		if (webelement == null) {
			assertFalse(true, "WebElement not found within " + timeout + " seconds.Failing test!" + "of element "
					+ elementLocator + "/nCurrent page: " + driver.getCurrentUrl());
		}
		return webelement;
	}

	public String getPageTitle() {
		try {
			System.out.print(String.format("The title of the page is: %s\n\n", driver.getTitle()));
			return driver.getTitle();
		} catch (Exception e) {
			throw new TestException(String.format("Current page title is: %s", driver.getTitle()));
		}
	}

	public void actionmethod(WebDriver driver, WebElement elementLocator) {
		Actions tap = new Actions(driver);
		tap.moveToElement(elementLocator).click().build().perform();

	}

	public String[][] getTestData(String sheetname) throws IOException {
		String path = "C:/Users/cinth/eclipse-workspace/DS-Algo/src/test/java/com/ds/qa/testdata/registrationdata.xlsx";
		XLUtility xlutil = new XLUtility(path);

		int totalrows = xlutil.getRowCount(sheetname);
		int totalcols = xlutil.getCellCount(sheetname, 1);

		String Data[][] = new String[totalrows][totalcols];
		for (int i = 1; i <= totalrows; i++) // 1
		{
			for (int j = 0; j < totalcols; j++) // 0
			{
				Data[i - 1][j] = xlutil.getCellData(sheetname, i, j);
			}
		}
		return Data;
	}

	public void userDetial(String username, String password) {
		driver.findElement(By.xpath(usrnameLocator)).clear();
		driver.findElement(By.xpath(usrnameLocator)).sendKeys(username);
		driver.findElement(By.xpath(pwdLocator)).clear();
		driver.findElement(By.xpath(pwdLocator)).sendKeys(password);
	}

	public void click(String elementLocator) {
		WebElement webelement = waitForElement(elementLocator);
		try {
			webelement.click();
		} catch (Exception ex) {
		}
	}

}
