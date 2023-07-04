package tests;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import base.BaseTest;
import pages.TravelInsurancePage;
import utils.ScreenshotUtil;

public class TravelInsuranceTests extends BaseTest {

	private TravelInsurancePage travelInsurance;

	@BeforeMethod
	private void setPage(Method method) {
		travelInsurance = new TravelInsurancePage(driver);
		createTestWithMethodName(method, browserName);

	}

	@AfterMethod
	private void takeScreenshots(ITestResult result) {
		ScreenshotUtil sc = new ScreenshotUtil();
		String loc = sc.getScreenshot(driver, result.getName());
		test.info("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(loc).build());
	}

	@Test(priority = 1)
	public void testVisitPolicyBazaar() {
		travelInsurance.visitPolicyBazaar();
	}

	@Test(priority = 2, dependsOnMethods = { "testVisitPolicyBazaar" })
	public void testClickTravelInsuranceLink() {

		try {
			travelInsurance.clickTravelInsuranceLink();
			test.pass("Successfully clicked travel insurance link");
		} catch (Exception e) {
			e.printStackTrace();
			test.fail(e);
		}

	}

	@Test(priority = 3, dependsOnMethods = { "testClickTravelInsuranceLink" })
	public void testClickStudentTravelInsuranceLink() throws InterruptedException {
		try {
			travelInsurance.clickOnStudentTravelInsurance();
			test.pass("Successfully clicked Student Travel Insurance");
		} catch (Exception e) {
			e.printStackTrace();
			test.fail(e);
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 4, dependsOnMethods = "testClickStudentTravelInsuranceLink")
	public void testSelectDestination() {
		try {
			travelInsurance.enterDestination();
			test.pass("Successfully selected destination");
		} catch (Exception e) {
			e.printStackTrace();
			test.fail(e);
			Assert.fail(e.getMessage());
		}

	}

	@Test(priority = 5, dependsOnMethods = "testSelectDestination")
	public void testChooseDates() throws InterruptedException {
		try {
			travelInsurance.chooseDate();
			test.pass("Successfully chosen dates");
		} catch (InterruptedException e) {
			e.printStackTrace();
			test.fail(e);
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 6)
	public void testFillTravellerInfo() {
		try {
			travelInsurance.fillTravellerInfo();
			test.pass("Sucessfully filled traveller info");
		} catch (Exception e) {
			e.printStackTrace();
			test.fail(e);
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 7)
	public void testChooseHealthInfo() {
		try {
			travelInsurance.chooseHealthInfo();
			test.pass("Successfully selected health info");
		} catch (Exception e) {
			e.printStackTrace();
			test.fail(e);
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 8)
	public void testFillContactInfo() {
		try {
			travelInsurance.fillContactInfo();
			test.pass("Successfully filled contact info");
		} catch (Exception e) {
			e.printStackTrace();
			test.fail(e);
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 9)
	public void testSortPrices() {
		try {
			travelInsurance.sortPrices();
			test.pass("Sorted prices successfully");
		} catch (Exception e) {
			e.printStackTrace();
			test.fail(e);
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 10)
	public void printLowestPrices() throws Exception {
		try {
			travelInsurance.getLowestPrices();
			test.pass("Printed 3 lowest prices to excel");
		} catch (Exception e) {
			e.printStackTrace();
			test.fail(e);
			Assert.fail(e.getMessage());
		}
	}

}
