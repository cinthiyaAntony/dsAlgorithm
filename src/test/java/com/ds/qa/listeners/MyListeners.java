package com.ds.qa.listeners;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ds.qa.util.ExtentReporter;

public class MyListeners implements ITestListener {

	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName;
	public WebDriver driver;

	@Override
	public void onStart(ITestContext context) {
		extentReport = ExtentReporter.generateExtentReporter();
	}

	@Override
	public void onTestStart(ITestResult result) {
		testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName + "Started Executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		extentTest.log(Status.PASS, testName + "got successfully executed");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getName();

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result);
		} catch (IllegalAccessException | NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir" + "\\Screenshots\\" + testName + ".png");
		try {
			FileHandler.copy(Screenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName + "got failed ");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// String testName = result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName + "got failed ");
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
	}

}
