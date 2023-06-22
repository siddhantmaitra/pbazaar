package base;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigReader;
import utils.ExtentReportManager;

public class BaseClass {
	protected static WebDriver driver;
//	protected String destination;
	protected String browserName;
	protected ExtentReports extent;
	protected ExtentTest test;
	

	
	@Parameters("browser")
    @BeforeClass
	public void initDriver(@Optional("chrome") String browser) {
		browserName = browser;
		if (browser.equalsIgnoreCase("chrome")) {

//			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\2266897\\Downloads\\chromedriver_win32\\chromedriver.exe");

//			System.setProperty("webdriver.chrome.driver", "C:\\Users\\2266897\\Documents\\chromedrivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			throw new IllegalArgumentException("Invalid browser name provided!");
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//		driver.get("https://www.policybazaar.com");
	}
	
	@BeforeClass
	public void initProperties() {
//		System.out.println(browserName);
		ConfigReader.property();
		
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
