package com.aepl.sam.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.reports.ExtentManager;
import com.aepl.sam.reports.ExtentTestManager;
import com.aepl.sam.utils.CommonMethods;
import com.aventstack.extentreports.Status;

public class TestListener extends TestBase implements ITestListener {
	CommonMethods commonMethod;

	@Override
	public void onTestStart(ITestResult result) {
	    String testName = result.getMethod().getMethodName();
	    int retryCount = result.getMethod().getCurrentInvocationCount();
	    ExtentTestManager.startTest(testName + " (Retry: " + retryCount + ")");
	    ExtentTestManager.getTest().log(Status.INFO, "Test Started: " + testName + " | Retry Attempt: " + retryCount);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		ExtentTestManager.getTest().log(Status.PASS, "Test Passed: " + testName);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		Throwable throwable = result.getThrowable();

		ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + testName);
		ExtentTestManager.getTest().log(Status.FAIL,
				"Cause: " + (throwable != null ? throwable.getMessage() : "Unknown"));

		try {
			if (driver != null) {
				commonMethod.captureScreenshot(testName);
				ExtentTestManager.getTest().log(Status.FAIL, "Screenshot captured for failure");
			} else {
				ExtentTestManager.getTest().log(Status.WARNING, "Cannot capture screenshot: WebDriver is null");
			}
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.WARNING, "Failed to capture screenshot: " + e.getMessage());
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped: " + testName);
	}

	@Override
	public void onStart(ITestContext context) {
		if (context.getName().isEmpty()) {
			ExtentTestManager.getTest().log(Status.WARNING, "Test context name is empty.");
		}

		ExtentManager.createInstance();

		if (driver == null || wait == null) {
			throw new IllegalStateException("Driver or Wait not initialized.");
		}

		if (this.commonMethod == null) {
			this.commonMethod = new CommonMethods(driver, wait);
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		ExtentTestManager.getTest().log(Status.INFO, "Test Suite Finished: " + context.getName());
		ExtentManager.flush();
	}
}
