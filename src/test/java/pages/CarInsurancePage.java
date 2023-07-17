package pages;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BaseClass;
import utils.ConfigReader;
import utils.ExcelUtil;
import utils.JsonReader;

public class CarInsurancePage extends BaseClass {

	final String mobileNo = JsonReader.data.get("mobileNo");
	final String invalidEmail = JsonReader.data.get("invalidEmail");
	final String buyerName = JsonReader.data.get("buyerName");
	final String carSheet = ConfigReader.prop.getProperty("carSheet");
	final String expectedLandingTitle = JsonReader.data.get("landingTitle");
	final String expectedTitle = JsonReader.data.get("carInsurancePageTitle");
	final String expectedErrorText = JsonReader.data.get("errorMsg");

	public CarInsurancePage(WebDriver driver) {

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='headlink' and contains(text(),'Car Insurance')]")
	private WebElement carInsuranceLink;

	@FindBy(xpath = "//li[@class=\"ruby-menu-mega shade mr\"]/child::a")
	private WebElement insuranceDropDown;

	@FindBy(xpath = "//div[@class=\"icon no-number\"]/following-sibling::button")
	private WebElement proceedWithoutCarNumber;

	@FindBy(xpath = "//div[text()='Delhi ']")
	private WebElement selectCity;

	@FindBy(xpath = "//span[text()='DL1']")
	private WebElement selectDL1;

	@FindBy(xpath = "//span[text()='Maruti']")
	private WebElement carBrand;

	@FindBy(xpath = "//span[text()='SWIFT']")
	private WebElement carModel;

	@FindBy(xpath = "//span[text()='Petrol']")
	private WebElement fuelType;

	@FindBy(xpath = "//span[text()='VXI NEW']")
	private WebElement variant;

	@FindBy(xpath = "//div[@class='brand-new-car-button']")
	private WebElement registrationYear;

	@FindBy(xpath = "//input[@id='txtName']")
	private WebElement nameInput;

	@FindBy(xpath = "//input[@id='txtEmail']")
	private WebElement emailInput;

	@FindBy(xpath = "//input[@id='mobNumber']")
	private WebElement phoneInput;

	@FindBy(xpath = "(//input[@id='txtEmail']/following::div[@class='err'])[1]")
	private WebElement errorMsg;

	public void visitPolicyBazaar() {
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedLandingTitle);
	}

	public void clickCarInsuranceLink() {

		Actions action = new Actions(driver);
		action.moveToElement(insuranceDropDown).build().perform();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(carInsuranceLink)).click();
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);

	}

	public void proceedWithoutCarNumber() {
		proceedWithoutCarNumber.click();

	}

	/**
	 * Selects city from form
	 */
	public void selectCity() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		cm.pauseForSecs(2);
		wait.until(ExpectedConditions.elementToBeClickable(selectCity)).click();

	}

	/**
	 * Selects DL choice
	 */
	public void selectDL1() {
		selectDL1.click();

	}

	/**
	 * Selects car brand
	 */
	public void selectCarBrand() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(carBrand)).click();

	}

	/**
	 * Selects car model
	 */
	public void selectCarModel() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(carModel)).click();

	}

	/**
	 * Selects fuel type for the car
	 */
	public void selectFuelType() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(fuelType)).click();

	}

	/**
	 * Selects car variant
	 */
	public void selectVariant() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(variant)).click();

		cm.pauseForSecs(2);

	}

	/**
	 * Selects registration year of the car
	 */
	public void selectRegistrationYear() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.elementToBeClickable(registrationYear)).click();

	}

	/**
	 * Inputs name in contact form
	 */
	public void inputName() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(nameInput)).sendKeys(buyerName);

	}

	/**
	 * Inputs email in contact form, writes error message to excel.
	 */
	public void inputEmail() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(emailInput)).sendKeys(invalidEmail);
		emailInput.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOf(errorMsg));

		String msg = errorMsg.getAttribute("innerHTML");
		Assert.assertEquals(msg, expectedErrorText);
		System.out.println(msg);

		// write the error message in excel file
		try {
			ExcelUtil.selectSheetName(carSheet);
			ExcelUtil.createRow(0);
			ExcelUtil.setExcelCell("Error Message for any invalid field");
			ExcelUtil.createRow(1);
			ExcelUtil.setExcelCell(msg);
		} catch (Exception e) {

			e.printStackTrace();
		}
		try {
			ExcelUtil.writeExcel();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Inputs phone number in contact form
	 */
	public void inputPhoneNo() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(phoneInput)).sendKeys(mobileNo);

	}

}
