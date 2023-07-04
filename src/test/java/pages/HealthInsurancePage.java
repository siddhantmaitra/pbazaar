package pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;
import utils.ConfigReader;
import utils.ExcelUtil;


public class HealthInsurancePage extends BaseClass {
	final String healthSheet = ConfigReader.prop.getProperty("healthSheet");
	
	public HealthInsurancePage(WebDriver driver) {
//		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	
	@FindBy(xpath = "//li[@class=\"ruby-menu-mega shade mr\"]/child::a")
	private WebElement insuranceDropDown;
	
	@FindBy(xpath = "//a[@class='headlink' and contains(text(),'Health Insurance')]/parent::h3/following-sibling::ul/li/a/span")
	private List<WebElement> healthInsuranceList;
	
	public void visitPolicyBazaar() throws InterruptedException {
		driver.get("https://www.policybazaar.com");
//		Thread.sleep(6000);
	}

	public void getHealthInsuranceList() {
		try {
		Actions action = new Actions(driver);
		action.moveToElement(insuranceDropDown).build().perform();
		
		}catch( Exception e ) {
			e.printStackTrace();
		}
		try {
			ExcelUtil.selectSheetName(healthSheet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		for(int i = 0; i<healthInsuranceList.size(); i++) {
			String msg = healthInsuranceList.get(i).getText();
			System.out.println(msg);
			ExcelUtil.createRow(i);
			ExcelUtil.setCarInsuranceErrorMsg(msg);
			
			
		}
		try {
			ExcelUtil.writeExcel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
