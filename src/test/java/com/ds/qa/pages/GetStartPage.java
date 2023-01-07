package com.ds.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ds.qa.base.TestBase;
import com.ds.qa.util.Testutil;

public class GetStartPage extends TestBase {
	Testutil testutil = new Testutil(driver);

	String stButton = "//button[contains(text(), 'Get Started')]";

	@FindBy(xpath = "//button[contains(text(), 'Get Started')]")
	WebElement st;

	public GetStartPage() {
		PageFactory.initElements(driver, this);
	}

	public String validateDsPageTitle() {
		testutil.waitForElement(stButton);
		return driver.getTitle();
	}

	public HomePage getStartClick() {
		testutil.waitForElement(stButton);
		st.click();
		return new HomePage();
	}

}
