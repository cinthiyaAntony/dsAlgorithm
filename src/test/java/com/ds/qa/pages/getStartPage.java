package com.ds.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ds.qa.base.TestBase;

public class getStartPage extends TestBase {
	// 1. pagefactory

	@FindBy(xpath = "//button[contains(text(), 'Get Started')]")
	WebElement st;

	public getStartPage() {
		PageFactory.initElements(driver, this);
	}

	public String validateDsPageTitle() {
		return driver.getTitle();
	}

	public HomePage getStartClick() {
		st.click();
		return new HomePage();
	}

}
