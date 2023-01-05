package com.ds.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ds.qa.base.TestBase;

public class RegistrationPage extends TestBase {

	@FindBy(id = "id_username")
	WebElement usr;

	@FindBy(id = "id_password1")
	WebElement paswd;

	@FindBy(id = "id_password2")
	WebElement repaswd;

	@FindBy(xpath = "//input[@value='Register']")
	WebElement regbutton;

	@FindBy(xpath = "//div[@class='alert alert-primary']")
	WebElement err;

	@FindBy(xpath = "//ul//a[3][contains(text(),'Sign in')]")
	WebElement sign;

	public RegistrationPage() {
		PageFactory.initElements(driver, this);
	}

	public void regNewUser(String uname, String pwd, String repwd) {
		usr.clear();
		usr.sendKeys(uname);
		paswd.clear();
		paswd.sendKeys(pwd);
		repaswd.clear();
		repaswd.sendKeys(repwd);
		regbutton.click();
	}

	public String alertmsg() {
		return err.getText();
	}

	public void clickSignIn() {
		sign.click();
	}
}
