package pages;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseClass;
import utils.ConfigReader;
import utils.ExcelUtil;

public class CarInsurancePage extends BaseClass {

	final String mobileNo = ConfigReader.prop.getProperty("mobileNo");
	final String invalidEmail = ConfigReader.prop.getProperty("invalidEmail");
	final String buyerName = ConfigReader.prop.getProperty("buyerName");

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
//	@FindBy(xpath="//label[@data-id='230']")
	private WebElement carModel;

	@FindBy(xpath = "//span[text()='Petrol']")
	private WebElement fuelType;

	@FindBy(xpath = "//span[text()='VXI NEW']")
	private WebElement variant;

//	@FindBy(xpath="//span[text()='2022']")
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
		
		driver.get("https://www.policybazaar.com");
	}

	public void clickCarInsuranceLink() {

		Actions action = new Actions(driver);
		action.moveToElement(insuranceDropDown).build().perform();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(carInsuranceLink)).click();

	}

	public void proceedWithoutCarNumber() {
		proceedWithoutCarNumber.click();

	}

	public void selectCity() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(selectCity)).click();

	}

	public void selectDL1() {
		selectDL1.click();

	}

	public void selectCarBrand() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(carBrand)).click();

	}

	public void selectCarModel() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(carModel)).click();

	}

	public void selectFuelType() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(fuelType)).click();

	}

	public void selectVariant() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(variant)).click();

		Thread.sleep(2000);

	}

	public void selectRegistrationYear() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.elementToBeClickable(registrationYear)).click();

	}

	public void inputName() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(nameInput)).sendKeys(buyerName);

	}

	public void inputEmail() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(emailInput)).sendKeys(invalidEmail);
		emailInput.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOf(errorMsg));

		String msg = errorMsg.getAttribute("innerHTML");


		// write the error message in excel file
		try {
			ExcelUtil.selectSheetName("CarInsurance");
			ExcelUtil.createRow(0);
			ExcelUtil.setCarInsuranceErrorMsg("Error Message for any invalid field");
			ExcelUtil.createRow(1);
			ExcelUtil.setCarInsuranceErrorMsg(msg);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		try {
			ExcelUtil.writeExcel();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	public void inputPhoneNo() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(phoneInput)).sendKeys(mobileNo);

	}

}
