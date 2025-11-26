package org.test.Listener;


import org.testng.ITestContext;		
import org.testng.ITestListener;		
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;

import org.test.ExtentReportHelper.ExtentManager;
import org.test.ExtentReportHelper.ExtentTestManager;

public class ListenerTest implements ITestListener {
	
	public void onTestStart(ITestResult result) {
		System.out.println(result.getName()+" test case started");
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("The name of the testcase passed is :"+result.getName());
		ExtentTestManager.getTest().log(Status.PASS, "Test passed");
		
	}

	public void onTestFailure(ITestResult result) {
		System.out.println(("The name of the testcase Failed is :"+result.getName()));
		ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("The name of the testcase Skipped is :"+result.getName());
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		
	}

	public void onFinish(ITestContext context) {
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
		
	}
}

