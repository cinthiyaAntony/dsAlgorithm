package com.ds.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ds.qa.base.TestBase;
import com.ds.qa.util.Testutil;

public class HomePage extends TestBase {
	Testutil testutil = new Testutil(driver);

	@FindBy(css = "div.row-cols-1 h5")
	List<WebElement> col;

	@FindBy(xpath = "//a[@class='nav-link dropdown-toggle']")
	WebElement dropDown;

	@FindBy(xpath = "//a[@href='data-structures-introduction']")
	WebElement clickAny;

	@FindBy(xpath = "//div[@class='alert alert-primary']")
	WebElement noLogin;

	@FindBy(xpath = "//a[@class='nav-link dropdown-toggle']/following-sibling::div[@class='dropdown-menu show']//a[4][contains(text(), 'Queue')]")
	WebElement clickDs;

	@FindBy(xpath = "//ul//a[2][contains(text(),'Register')]")
	WebElement reg;

	@FindBy(xpath = "//ul//a[3][contains(text(),'Sign in')]")
	WebElement sign;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public int sectionCount() {
		return col.size();
	}

	public List<String> actValue() {
		dropDown.click();
		List<WebElement> dValue = driver.findElements(By.xpath(
				"//a[@class='nav-link dropdown-toggle']/ following-sibling::div[@class='dropdown-menu show']//a"));
		List<String> actualvalues = new ArrayList<String>();
		for (WebElement we : dValue) {
			actualvalues.add(we.getText());
		}
		return actualvalues;
	}

	public String VerifyLogin() {
		clickAny.click();
		return noLogin.getText();
	}

	public String verifyError() {
		dropDown.click();
		testutil.actionmethod(driver, clickDs);
		return noLogin.getText();
	}

	public RegistrationPage regclick() {
		reg.click();
		return new RegistrationPage();
	}

	public SigninPage signClick() {
		sign.click();
		return new SigninPage();
	}

}
