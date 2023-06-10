package tests;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.BaseTest;
import pages.HealthInsurancePage;
import utils.ExtentReportManager;

public class HealthInsuranceTests extends BaseTest {

	private HealthInsurancePage healthInsurancePage;

	@BeforeMethod
	void setPage(Method method) {
		healthInsurancePage = new HealthInsurancePage(driver);
		createTestWithMethodName(method, browserName);

	}

	@Test(priority = 1)
	public void testVisitPolicyBazaar() throws InterruptedException {

		try {
			healthInsurancePage.visitPolicyBazaar();
			test.pass("visited website successfully.");
//			test.log(Status.PASS, "visited website successfully.");

		} catch (Exception e) {
//			test.log(Status.FAIL, e.getMessage());
			test.fail(e);
		}

	}

	@Test(priority = 2, dependsOnMethods = "testVisitPolicyBazaar")
	public void testGetHealthInsuranceList() throws Exception {
		try {
			healthInsurancePage.getHealthInsuranceList();
			test.pass( "Acquired Health Insurance menu items.");
		} catch (Exception e) {
			test.fail(e);
		}

		
		// Add assertions or further test steps as needed
	}

}
