package base;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.ExtentReportManager;
import utils.JsonReader;

public class BaseClass {
	protected static WebDriver driver;
//	protected String destination;
	protected String browserName;
	protected ExtentReports extent;
	protected ExtentTest test;
	protected static CommonMethods cm = new CommonMethods();
	

	
	@Parameters("browser")
    @BeforeClass
	public void initDriver(@Optional("edge") String browser) {
		browserName = browser;
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless=new","--no-sandbox","--disable-dev-shm-usage","--window-size=1920,1480");
			driver = new ChromeDriver(options);
			
//			driver = new ChromeDriver();
			
		} else if (browser.equalsIgnoreCase("edge")) {

			WebDriverManager.edgedriver().setup();
			
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--headless=new","--no-sandbox","--disable-dev-shm-usage","--window-size=1920,1480");
			driver = new EdgeDriver(options);
//			driver = new EdgeDriver();
		} else {
			throw new IllegalArgumentException("Invalid browser name provided!");
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	@BeforeClass
	public void initProperties() {
		ConfigReader.property();
		JsonReader.readData(ConfigReader.prop.getProperty("testData"));
		
		
	}
	
	@BeforeClass
	public void initExtentReport(ITestContext ctx) {
		ExtentReportManager.getInstance(ctx);
		
	}
	
	
	@AfterClass
    public void tearDown() {
		
		driver.quit();
//		if (driver != null) {
//            driver.quit();
//        }
    }
	
	@AfterSuite
	public void closeExtentReport() {
		ExtentReportManager.extent.flush();
	}
	
	
	

}
