package utils;

import org.testng.ISuite;
import org.testng.ITestContext;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
	public static ExtentReports extent;

	public static ExtentReports getInstance(ITestContext ctx) {
		if (extent == null) {
			ISuite suite = ctx.getSuite();
			String suiteName;
			if (suite.getXmlSuite().getParentSuite() != null) {
				suiteName = suite.getXmlSuite().getParentSuite().getName();
			} else {
				suiteName = suite.getName();
			}
			
//			System.out.println(suiteName);
			String reportName = "testOutput/ExtentReport_" + suiteName + ".html";

			ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportName);
			htmlReporter.config().setDocumentTitle(suiteName + " Test Report");
			htmlReporter.config().setReportName(suiteName + " Execution Report");
			htmlReporter.config().setTheme(Theme.DARK);

			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
		}
		return extent;
	}

}
