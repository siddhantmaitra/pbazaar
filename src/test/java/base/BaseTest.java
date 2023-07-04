package base;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import utils.ExtentReportManager;
import utils.ScreenshotUtil;

public class BaseTest extends BaseClass {

	protected void createTestWithMethodName(Method method, String browserName) {
		String methodName = method.getName();
		test = ExtentReportManager.extent.createTest(methodName + ": " + browserName);
	}

	protected String getTestMethodName() {
		Method method = getTestMethod();
		if (method != null) {
			return method.getName();
		}
		return "Unknown";
	}

	private Method getTestMethod() {
		Method enclosingMethod = new Object() {
		}.getClass().getEnclosingMethod();
		if (enclosingMethod != null) {
			Test annotation = enclosingMethod.getAnnotation(Test.class);
			if (annotation != null) {
				return enclosingMethod;
			}
		}
		return null;
	}
	
	@AfterMethod
	protected void takeFailedScreenshots(ITestResult result, Method method) {
		if(result.getStatus() == ITestResult.FAILURE) {
			ScreenshotUtil sc = new ScreenshotUtil();
			sc.getScreenshot(driver, result.getName());
		}
	}

}
