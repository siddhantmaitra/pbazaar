package tests;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;

import base.BaseTest;
import pages.CarInsurancePage;
import utils.ScreenshotUtil;

public class CarInsuranceTests extends BaseTest {

	private CarInsurancePage carInsurancePage;

	@BeforeMethod
	private void setPage(Method method) {
		carInsurancePage = new CarInsurancePage(driver);
		createTestWithMethodName(method, browserName);

	}

	@AfterMethod
	private void takeScreenShots(ITestResult result) {
			ScreenshotUtil sc = new ScreenshotUtil();
			String loc = sc.getScreenshot(driver, result.getName());
			test.info("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(loc).build());
		
	}

	@Test(priority = 1)
	public void testVisitPolicyBazaar() {
		try {
			carInsurancePage.visitPolicyBazaar();
			test.pass("visited website sucessfully");
		} catch (Exception e) {
			test.fail(e);
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 2, dependsOnMethods = "testVisitPolicyBazaar")
	public void testClickCarInsuranceLink() {
		try {
			carInsurancePage.clickCarInsuranceLink();
			test.pass("Car Insurance clicked");
		} catch (Exception e) {
			test.fail(e);
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 3, dependsOnMethods = "testClickCarInsuranceLink")
	public void testProceedWithoutCarNo() {
		try {
			carInsurancePage.proceedWithoutCarNumber();
			test.pass("Clicked on 'Proceed without car number'");
		} catch (Exception e) {
			test.fail(e);
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 4, dependsOnMethods = "testProceedWithoutCarNo")
	public void testSelectCityName() {
		try {
			carInsurancePage.selectCity();
			test.pass("Selected City");
		} catch (Exception e) {
			test.fail(e);
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 5, dependsOnMethods = "testSelectCityName")
	public void testSelectDL1No() {
		try {
			carInsurancePage.selectDL1();
			test.pass("Selected DL1");
		} catch (Exception e) {
			test.fail(e);
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 6, dependsOnMethods = "testSelectDL1No")
	public void testSelectCarBrand() {
		try {
			carInsurancePage.selectCarBrand();
			test.pass("Selected Car Brand");
		} catch (Exception e) {
			test.info("Unable to select car brand");
			test.fail(e);
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 7, dependsOnMethods = "testSelectCarBrand")
	public void testSelectCarModel() {
		try {
			carInsurancePage.selectCarModel();
			test.pass("Selected Car Model");
		} catch (Exception e) {
			test.info("Unable to select car model");
			test.fail(e);
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 8, dependsOnMethods = "testSelectCarModel")
	public void testSelectFuelType() {
		try {
			carInsurancePage.selectFuelType();
			test.pass("Selected Car Fuel Type");
		} catch (Exception e) {
			test.info("Unable to select car fuel type");
			test.fail(e);
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 9, dependsOnMethods = "testSelectFuelType")
	public void testSelectVariantType() throws InterruptedException {
		try {
			carInsurancePage.selectVariant();
			test.pass("Selected Car Variant");
		} catch (Exception e) {
			test.info("Unable to select car variant");
			test.fail(e);
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 10, dependsOnMethods = "testSelectVariantType")
	public void testSelectRegistrationYear() {
		try {
			carInsurancePage.selectRegistrationYear();
			test.pass("Selected Car Registration Year option");
		} catch (Exception e) {
			test.info("Unable to select car brand");
			test.fail(e);
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 11, dependsOnMethods = "testSelectRegistrationYear")
	public void testFillPersonalInfo() {
		try {
			carInsurancePage.inputName();
			test.info("Entered name successfully");

			carInsurancePage.inputEmail();
			test.info("Entered email successfully");

			carInsurancePage.inputPhoneNo();
			test.info("Entered phone number successfully");

			test.pass("Filled form completely");
		} catch (Exception e) {
			test.info("Unable to fill contact form");
			test.fail(e);
			Assert.fail(e.getMessage());
		}
	}

}