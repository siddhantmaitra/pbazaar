package base;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;

import utils.ExtentReportManager;
import utils.ScreenshotUtil;

public class BaseTest extends BaseClass {

	protected void createTestWithMethodName(Method method, String browserName) {
		String methodName = method.getName();
		test = ExtentReportManager.extent.createTest(methodName + ": " + browserName);
	}



}
