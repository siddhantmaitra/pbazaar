package pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.BaseClass;
import utils.ConfigReader;
import utils.ExcelUtil;
import utils.JsonReader;

public class HealthInsurancePage extends BaseClass {
	final String healthSheet = ConfigReader.prop.getProperty("healthSheet");
	final String expectedLandingTitle = JsonReader.data.get("landingTitle");

	public HealthInsurancePage(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//li[@class=\"ruby-menu-mega shade mr\"]/child::a")
	private WebElement insuranceDropDown;

	@FindBy(xpath = "//a[@class='headlink' and contains(text(),'Health Insurance')]/parent::h3/following-sibling::ul/li/a/span")
	private List<WebElement> healthInsuranceList;

	public void visitPolicyBazaar() {
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedLandingTitle);
	}

	public void getHealthInsuranceList() {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(insuranceDropDown).build().perform();

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ExcelUtil.selectSheetName(healthSheet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < healthInsuranceList.size(); i++) {
			String msg = healthInsuranceList.get(i).getText();
			System.out.println(msg);
			ExcelUtil.createRow(i);
			ExcelUtil.setExcelCell(msg);

		}
		try {
			ExcelUtil.writeExcel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
