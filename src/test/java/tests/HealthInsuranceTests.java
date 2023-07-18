package tests;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import base.BaseTest;
import pages.HealthInsurancePage;
import utils.ExtentReportManager;
import utils.ScreenshotUtil;

public class HealthInsuranceTests extends BaseTest {

	private HealthInsurancePage healthInsurancePage;

	@BeforeMethod
	private void setPage(Method method) {
		healthInsurancePage = new HealthInsurancePage();
		createTestWithMethodName(method, browserName);

	}

	@AfterMethod
	private void takeScreenshots(ITestResult result) {

		ScreenshotUtil sc = new ScreenshotUtil();
		String loc = sc.getScreenshot(driver, result.getName());
		test.info("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(loc).build());

	}

	@Test(priority = 1)
	public void testVisitPolicyBazaar() throws InterruptedException {

		try {
			healthInsurancePage.visitPolicyBazaar();
			test.pass("visited website successfully.");

		} catch (Exception e) {
			test.fail(e);
			Assert.fail(e.getMessage());
		}

	}

	@Test(priority = 2, dependsOnMethods = "testVisitPolicyBazaar")
	public void testGetHealthInsuranceList() throws Exception {
		try {
			healthInsurancePage.getHealthInsuranceList();
			test.pass("Acquired Health Insurance menu items.");
		} catch (Exception e) {
			test.fail(e);
			Assert.fail(e.getMessage());
		}

	}

}
