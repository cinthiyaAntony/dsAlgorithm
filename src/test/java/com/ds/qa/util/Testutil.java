package com.ds.qa.util;

import static org.testng.Assert.assertFalse;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;

public class Testutil {

	WebDriver driver;

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

	public void actionmethod(WebElement elementLocator) {
		Actions tap = new Actions(driver);
		tap.moveToElement(elementLocator).click();

	}

	public void click(String elementLocator) {
		WebElement webelement = waitForElement(elementLocator);
		try {
			webelement.click();
		} catch (Exception ex) {
		}
	}

}
