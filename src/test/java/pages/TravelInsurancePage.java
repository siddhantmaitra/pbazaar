package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.BaseClass;
import utils.ConfigReader;
import utils.ExcelUtil;

public class TravelInsurancePage extends BaseClass {

	public TravelInsurancePage(WebDriver driver) {

		PageFactory.initElements(driver, this);

	}
	
	final String destination = ConfigReader.prop.getProperty("destination");
	final String baseURL = ConfigReader.prop.getProperty("baseURL");
	final String traveller01Age = ConfigReader.prop.getProperty("travellerAge01");
	final String traveller02Age = ConfigReader.prop.getProperty("travellerAge02");
	final String mobileNo = ConfigReader.prop.getProperty("mobileNo");
	
	private JavascriptExecutor js;

	@FindBy(xpath = "(//a[@class='headlink' and contains(text(),'Other Insurance')]/parent::h3/following-sibling::ul/li/a/span)[1]")
	private WebElement travelInsuranceLink;

	@FindBy(xpath = "//li[@class=\"ruby-menu-mega shade mr\"]/child::a")
	private WebElement insuranceDropDown;

	@FindBy(xpath = "//a[contains(text(), 'Student Travel Insurance')]")
	private WebElement studentTravelInsuranceLink;

	@FindBy(xpath = "(//a[contains(text(), 'Senior Citizen Travel Insurance')]/parent::h3/following-sibling::p)[2]")
	private WebElement scrollElement;

	@FindBy(id = "exit-intent-popup-close")
	private WebElement mainDivClose;

	@FindBy(className = "strip_close")
	private WebElement bottomDivClose;

	@FindBy(id = "destination-autocomplete")
	private WebElement destinationInput;
//	@FindBy(id = dst)
//	private WebElement destinationInput = driver.findElement(By.id(dst));

	@FindBy(className = "process-button")
	private WebElement continueBtn;

//	@FindBy(xpath = "//div[text()='20']")
//	private WebElement startDay;
//
//	@FindBy(xpath = "//div[text()='26']")
//	private WebElement endDay;
	
	@FindBy(xpath = "(//div[text()='1'])[3]")
    private WebElement startDay;

 

    @FindBy(xpath = "(//div[text()='10'])[1]")
    private WebElement endDay;

	@FindBy(id = "startdate")
	private WebElement datePicker;

	@FindBy(id = "tcl-item-2")
	private WebElement noOfTravellers;

	@FindBy(id = "travellerAge1")
	private WebElement travellerAgeSelector01;

	@FindBy(id = "travellerAge2")
	private WebElement travellerAgeSelector02;
	
	@FindBy(id = "isPed_n")
	private WebElement healthStatusInput;
	
	@FindBy(id = "travelmobile")
	private WebElement contactNoInputBox;
	
	@FindBy(xpath="//input[@class=\"whatsapp\"]//following-sibling::span")
	private WebElement getWhatsAppUpdates;
	
	@FindBy(linkText = "Sort by")
	private WebElement sortMenu;
	
	@FindBy(id = "17")
	private WebElement ascendingPriceSort;
	
	@FindBy(xpath = "//button[text()='Apply']")
	private WebElement applySort;
	
	@FindBy(xpath = "//div[@class='quotesCard__planName hideSmall']/p[@class='quotesCard--insurerName']")
	private List<WebElement> insuranceVendors;
	
	@FindBy(xpath = "//p[@class=\"wrap-space \"]/child::span")
	private List<WebElement> insurancePrices;
	
	@FindBy(xpath = "(//div[@class='grid'])[1]")
	private WebElement banner;

	
	public void visitPolicyBazaar() throws InterruptedException {

		driver.get(baseURL);

	}

	public void clickTravelInsuranceLink() {
		Actions action = new Actions(driver);
		action.moveToElement(insuranceDropDown).build().perform();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(travelInsuranceLink)).click();

	}



	public void clickOnStudentTravelInsurance() throws InterruptedException {
		js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		js.executeScript("arguments[0].scrollIntoView(true);", scrollElement);
		
		// necessary or scroll gets interrupted
		Thread.sleep(4000);
	
		
		wait.until(ExpectedConditions.elementToBeClickable(bottomDivClose)).click();
		// rescroll to adjust
		js.executeScript("arguments[0].scrollIntoView(true);", scrollElement);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(studentTravelInsuranceLink));
		
		studentTravelInsuranceLink.click();
	}

	public void enterDestination() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		destinationInput.clear();
		

		destinationInput.sendKeys(destination);
		destinationInput.sendKeys(Keys.ENTER);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
//		js.executeScript("arguments[0].scrollIntoView(true);", banner);
		
		wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
//		continueBtn.click();
	}

	public void chooseDate() throws InterruptedException {
		datePicker.click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(startDay)).click();
		wait.until(ExpectedConditions.visibilityOf(endDay)).click();
		wait.until(ExpectedConditions.visibilityOf(continueBtn)).click();
	}

	public void fillTravellerInfo() {
		noOfTravellers.click();
		selectOption(driver, travellerAgeSelector01, traveller01Age);
		selectOption(driver, travellerAgeSelector02, traveller02Age);
		continueBtn.click();

	}

	public static void selectOption(WebDriver driver, WebElement dropdownElement, String optionValue) {

		Select dropdown = new Select(dropdownElement);
		dropdown.selectByValue(optionValue);
	}
	
	public void chooseHealthInfo() {
		healthStatusInput.click();
//		continueBtn.click();
	}
	
	public void fillContactInfo() {
		contactNoInputBox.sendKeys(mobileNo);
//		contactNoInputBox.sendKeys(ConfigReader.prop.getProperty("mobileNo"));
		getWhatsAppUpdates.click();
		continueBtn.click();
	}
	
	public void sortPrices() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(sortMenu)).click();
//		sortMenu.click();
		ascendingPriceSort.click();
		applySort.click();
		
	}
	
	public void getLowestPrices() throws Exception {

		ExcelUtil.selectSheetName("TravelInsurance");
		
		for(int i = 0; i<3 && i<insuranceVendors.size(); i++) {
			ExcelUtil.createRow(i);
			ExcelUtil.setValue(insuranceVendors.get(i).getText(), insurancePrices.get(i).getText());
			System.out.println(insuranceVendors.get(i).getText());
			System.out.println(insurancePrices.get(i).getText());
		}
		ExcelUtil.writeExcel();
	}

	

}
