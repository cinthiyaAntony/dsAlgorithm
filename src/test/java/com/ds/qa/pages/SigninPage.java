package com.ds.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ds.qa.base.TestBase;

public class SigninPage extends TestBase {

	@FindBy(xpath = "//input[@name='username']")
	WebElement usr;

	@FindBy(xpath = "//input[@name='password']")
	WebElement pwd;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement loginBtn;

	@FindBy(xpath = "//a[contains(text(),'Register!')]")
	WebElement regLink;

	@FindBy(xpath = "//div[@class = 'alert alert-primary']")
	WebElement err;

	public SigninPage() {
		PageFactory.initElements(driver, this);
	}

	public void signClick() {
		loginBtn.click();
	}

	public void regClick() {
		regLink.click();
	}

	public void signUser(String usern, String passwd) {
		usr.clear();
		usr.sendKeys(usern);
		pwd.clear();
		pwd.sendKeys(passwd);
		loginBtn.click();
	}

	public String errBtn() {
		return err.getText();
	}
}
