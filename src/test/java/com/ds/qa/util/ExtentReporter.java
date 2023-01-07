package com.ds.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	public WebDriver driver;

	public static ExtentReports generateExtentReporter() {
		ExtentReports extentReport = new ExtentReports();
		File extentReportFile = new File(
				System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("DsAlgo Project Test Automation Results");
		sparkReporter.config().setDocumentTitle("TN Automation Report");
		sparkReporter.config().setTimeStampFormat("DD/MM/YYYY hh:mm:ss");

		extentReport.attachReporter(sparkReporter);

		Properties configpop = new Properties();
		File configPropfile = new File(
				System.getProperty("user.dir") + "\\src\\test\\java\\com\\ds\\qa\\config\\config.properties");
		FileInputStream fis;
		try {
			fis = new FileInputStream(configPropfile);
			configpop.load(fis);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentReport.setSystemInfo("Application URL", configpop.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", configpop.getProperty("browser"));
		return extentReport;

	}

}
