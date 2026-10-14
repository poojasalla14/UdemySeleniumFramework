package testComponent;



import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends BaseTest implements ITestListener,ISuiteListener{

	ExtentTest test;
	ExtentReports report;
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	@Override
	public void onStart(ITestContext context) {
		Date date = new Date();
		String NewDate = date.toString().replace(" ","_").replace(":", "_");
		ExtentSparkReporter reporter=new ExtentSparkReporter(System.getProperty("user.dir")+"//reports//index.html");
		reporter.config().setDocumentTitle("WebAutomationReport");
		reporter.config().setReportName("Test Result");
		reporter.config().setTheme(Theme.STANDARD);
		report=new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("Tester", "Pushpanjali");
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		String testname=result.getMethod().getMethodName();
		test=report.createTest(testname);
		extentTest.set(test);
		extentTest.get().log(Status.INFO, testname+" Execution Started");		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testname=result.getMethod().getMethodName();
		extentTest.get().log(Status.PASS, testname+" Execution Passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());//
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		String filePath = null;
		try {
			
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testname=result.getMethod().getMethodName();
		extentTest.get().log(Status.SKIP, testname+" Execution Skipped");
	}

	

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
		
	}
	
	

	
}
