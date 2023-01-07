package com.ds.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ds.qa.base.TestBase;

public class SignoutPage extends TestBase {

	@FindBy(xpath = "//input[@value='Login']")
	WebElement loginBtn;

	@FindBy(xpath = "//ul/a[2]")
	WebElement usrLocator;

	@FindBy(xpath = "//ul//a[3]")
	WebElement signoutBtn;

	@FindBy(xpath = "//div[@class='alert alert-primary']")
	WebElement logoutmsg;

	public SignoutPage() {
		PageFactory.initElements(driver, this);
	}

	public HomePage loginBtnClick() {
		loginBtn.click();
		return new HomePage();
	}

	public String usrNameCheck() {
		return usrLocator.getText();
	}

	public String signoutclick() {
		signoutBtn.click();
		return logoutmsg.getText();
	}
}
