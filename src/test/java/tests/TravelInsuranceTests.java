package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.TravelInsurancePage;

public class TravelInsuranceTests extends BaseTest {

	private TravelInsurancePage travelInsurance;

	@BeforeMethod
	void setPage() {
		travelInsurance = new TravelInsurancePage(driver);

	}

	@Test(priority = 1)
	public void testVisitPolicyBazaar() throws InterruptedException {
		travelInsurance.visitPolicyBazaar();
		// Add assertions or further test steps as needed
	}

	@Test(priority = 2, dependsOnMethods = {"testVisitPolicyBazaar"})
	public void testClickTravelInsuranceLink() {
//		policyBazaarPage.visitPolicyBazaar();
		travelInsurance.clickTravelInsuranceLink();
		// Add assertions or further test steps as needed
	}

	@Test(priority = 3, dependsOnMethods = {"testClickTravelInsuranceLink"})
	public void testClickStudentTravelInsuranceLink() throws InterruptedException {
		travelInsurance.clickOnStudentTravelInsurance();
	}

	@Test(priority = 4,dependsOnMethods = "testClickStudentTravelInsuranceLink")
	public void testSelectDestination() {
		travelInsurance.enterDestination();
		
	}
	
	@Test(priority = 5,dependsOnMethods = "testSelectDestination")
	public void testChooseDates() throws InterruptedException {
		travelInsurance.chooseDate();
	}
	@Test(priority = 6)
	public void testFillTravellerInfo() {
		travelInsurance.fillTravellerInfo();
	}
	@Test(priority = 7)
	public void testChooseHealthInfo() {
		travelInsurance.chooseHealthInfo();
	}
	@Test(priority = 8)
	public void testFillContactInfo() {
		travelInsurance.fillContactInfo();
	}
	
	@Test(priority = 9)
	public void testSortPrices() {
		travelInsurance.sortPrices();
	}
	
	@Test(priority = 10)
	public void printLowestPrices() throws Exception {
		travelInsurance.getLowestPrices();
	}

}
