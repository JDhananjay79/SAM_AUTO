package com.aepl.sam.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.pages.DeviceDashboardPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class DeviceDashboardPageTest extends TestBase {
	private ExcelUtility excelUtility;
	private DeviceDashboardPage devicedashboardPage;
	private CommonMethods commonMethods;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.devicedashboardPage = new DeviceDashboardPage(driver,wait,action);
		this.commonMethods = new CommonMethods(driver,wait);
		this.excelUtility = new ExcelUtility();
		excelUtility.initializeExcel("Device_Dashboard_Test");
	}

	@Test(priority = 1)
	public void PageLogo() throws InterruptedException {
		String testCaseName = "Test Company Logo visible on web page";
		String expectedURL = Constants.DASH_URL;
		String actualURL = "";
		String result = "FAIL"; // Default failure status

		logger.info("Executing the Page Logo test case: " + testCaseName);
		try {
			logger.info("Attempting to verify the webpage logo...");
			commonMethods.verifyWebpageLogo(); // Verify logo visibility
			actualURL = driver.getCurrentUrl();
			// Using Soft Assertion
			softAssert.assertEquals(actualURL, expectedURL, "URL Mismatch: Navigation failed!");
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			logger.info("Test Result: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while verifying the webpage logo.", e);
//			actualURL = "Test Failed"; // Indicate failure
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expectedURL, actualURL, result);
			logger.info("Test case execution completed for: " + testCaseName);
			System.out.println("Company logo successfully visible on Homepage");

			// Ensure soft assertions are checked at the end
			softAssert.assertAll();
		}
	}

	@Test(priority = 2)
	public void PageTitle() throws InterruptedException {
		String testCaseName = "Test Page Title is visible";
		String expectedTitle = driver.getTitle();
		String actualTitle = "";
		String result = "FAIL"; // Default failure status

		logger.info("Executing the Page Title visible test case: " + testCaseName);
		try {
			logger.info("Attempting to visible Page Title on webpage...");
			commonMethods.verifyPageTitle();
			actualTitle = driver.getTitle();
			softAssert.assertEquals(actualTitle, expectedTitle, "URL Mismatch: Navigation failed!");
			result = expectedTitle.equalsIgnoreCase(actualTitle) ? "PASS" : "FAIL";
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while page title is not visible on webpage.", e);
//			actualTitle = driver.getTitle();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expectedTitle, actualTitle, result);
			logger.info("Test case execution completed for: " + testCaseName);
			System.out.println("Page Title Successfully visible on Dashboard Tab");

			softAssert.assertAll();
		}
	}

	@Test(priority = 3)
	public void ClickRefreshBtn() throws InterruptedException {
		String testCaseName = "Test Click on refresh button";
		String expectedURL = Constants.DASH_URL;
		String actualURL = "";
		String result = "FAIL"; // Default failure status

		logger.info("Executing the test Click Refresh button for test case: " + testCaseName);
		try {
			logger.info("Attempting to click on the refresh button...");
			commonMethods.clickRefreshButton();
			actualURL = driver.getCurrentUrl();
			softAssert.assertEquals(actualURL, expectedURL, "URL Mismatch: Navigation failed!");
			result = expectedURL.equalsIgnoreCase(actualURL) ? "PASS" : "FAIL";
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clicking on the refresh button.", e);
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expectedURL, actualURL, result);
			logger.info("Test case execution completed for: " + testCaseName);
			System.out.println("Successfully clicked on Refresh button");
			softAssert.assertAll();
		}
	}

	@Test(priority = 4)
	public void PageName() throws InterruptedException {
		String testCaseName = "Test Verify Navigate to Dashboard Tab";
		String expectedPageName = "Device Dashboard";
		String actualPageName = "";
		String result = "FAIL"; // Default failure status

		logger.info("Executing the test Visible Page Name for test case: " + testCaseName);
		try {
			logger.info("Attempting to Visible Page Name ...");
			commonMethods.clickNavBar();
			actualPageName = "Device Dashboard";
			softAssert.assertEquals(actualPageName, expectedPageName, "URL Mismatch: Navigation failed!");
			result = expectedPageName.equalsIgnoreCase(actualPageName) ? "PASS" : "FAIL";
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while Page name not visible.", e);
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expectedPageName, actualPageName, result);
			logger.info("Test case execution completed for: " + testCaseName);
			System.out.println("Successfully Navigated to Dashboard Tab");
			softAssert.assertAll();
		}
	}
	
	@Test(priority = 5)
	public void PageNameDevUtilt() throws InterruptedException {
		String testCaseName = "Test Verify Navigate to Device Utility Tab";
		String expectedPageName = "Device Utility";
		String actualPageName = "";
		String result = "FAIL"; // Default failure status

		logger.info("Executing the test Visible Page Name for test case: " + testCaseName);
		try {
			logger.info("Attempting to Visible Page Name ...");
			commonMethods.clickNavBarDeviceUtil();
			actualPageName = "Device Utility";
			softAssert.assertEquals(actualPageName, expectedPageName, "URL Mismatch: Navigation failed!");
			result = expectedPageName.equalsIgnoreCase(actualPageName) ? "PASS" : "FAIL";
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while Page name not visible.", e);
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expectedPageName, actualPageName, result);
			logger.info("Test case execution completed for: " + testCaseName);
			System.out.println("\"Successfully Navigated to Device Utility Tab");
			softAssert.assertAll();
		}
	}
	
	@Test(priority = 6)
	public void PageNameUser() throws InterruptedException {
		String testCaseName = "Test Verify Navigate to User Tab";
		String expectedPageName = "User";
		String actualPageName = "";
		String result = "FAIL"; // Default failure status

		logger.info("Executing the test Visible Page Name for test case: " + testCaseName);
		try {
			logger.info("Attempting to Visible Page Name ...");
			commonMethods.clickNavBarUser();
			actualPageName = "User";
			softAssert.assertEquals(actualPageName, expectedPageName, "URL Mismatch: Navigation failed!");
			result = expectedPageName.equalsIgnoreCase(actualPageName) ? "PASS" : "FAIL";
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while Page name not visible.", e);
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expectedPageName, actualPageName, result);
			logger.info("Test case execution completed for: " + testCaseName);
			System.out.println("Successfully Navigated to User Tab");
			softAssert.assertAll();
		}
	}
	
	@Test(priority = 7)
	public void ClickUserProfile() throws InterruptedException {
		String testCaseName = "Test Verify Navigate to User Profile Tab";
		String expectedPageName = "Hi, Super Adm";
		String actualPageName = "";
		String result = "FAIL"; // Default failure status

		logger.info("Executing the test Visible Page Name for test case: " + testCaseName);
		try {
			logger.info("Attempting to Visible Page Name ...");
			commonMethods.clickNavBarUser();
			actualPageName = "Hi, Super Adm";
			softAssert.assertEquals(actualPageName, expectedPageName, "URL Mismatch: Navigation failed!");
			result = expectedPageName.equalsIgnoreCase(actualPageName) ? "PASS" : "FAIL";
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while Page name not visible.", e);
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expectedPageName, actualPageName, result);
			logger.info("Test case execution completed for: " + testCaseName);
			System.out.println("Successfully Navigated to User Profile Tab");
			softAssert.assertAll();
		}
	}
	
	@Test(priority = 8)
	public void ReadPageName() throws InterruptedException {
		String testCaseName = "Test Verify Page name is visible";
		String expectedKPIName = "Device Dashboard";
		String actualKPIName = devicedashboardPage.verifyDashPageTitle();
		String result = "FAIL"; // Default failure status

		logger.info("Executing the test Visible Page Name for test case: " + testCaseName);
		try {
			logger.info("Attempting to Visible of Page Name ...");
//			devicedashboardPage.verifyAndClickKPITotalProDev();
			softAssert.assertEquals(actualKPIName, expectedKPIName, "URL Mismatch: Navigation failed!");
			result = expectedKPIName.equalsIgnoreCase(actualKPIName) ? "PASS" : "FAIL";
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while Visible of Page Name", e);
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expectedKPIName, actualKPIName, result);
			logger.info("Test case execution completed for: " + testCaseName);
			System.out.println("Successfully Visible Page Name is Device Dashboard");
			softAssert.assertAll();
		}
	}

	@Test(priority = 9)
	public void ClickNameandReadKPI1Count() throws InterruptedException {
		String testCaseName = "Test Verify click and Read KPI Count of Total Production Devices ";
		String expectedKPICount = "";
		String actualKPICount = devicedashboardPage.verifyAndClickKPITotalProDevWithCount();
		String result = "FAIL"; // Default failure status

		logger.info("Executing the test Visible Page KPI for test case: " + testCaseName);
		try {
			logger.info("Attempting to clickable KPI count ...");
//			devicedashboardPage.verifyCountKPITotalProDev();
			expectedKPICount = actualKPICount;
			softAssert.assertEquals(actualKPICount, expectedKPICount, "URL Mismatch: Navigation failed!");		
			result = expectedKPICount.equalsIgnoreCase(actualKPICount) ? "PASS" : "FAIL";
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clickable and read KPI count", e);
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expectedKPICount, actualKPICount, result);
			logger.info("Test case execution completed for: " + testCaseName);
			System.out.println("Successfully Navigated Click,Read KPI Count and open table of Total Production Devices");
			softAssert.assertAll();
		}
	}
	
	@Test(priority = 10)
	public void ClickNameandReadKPI2Count() throws InterruptedException {
		String testCaseName = "Test Verify click and Read KPI Count of Total Dispatched Devices ";
		String expectedKPICount = "";
		String actualKPICount = devicedashboardPage.verifyAndClickKPITotalDisDevWithCount();
		String result = "FAIL"; // Default failure status

		logger.info("Executing the test Visible Page KPI for test case: " + testCaseName);
		try {
			logger.info("Attempting to clickable KPI count ...");
//			devicedashboardPage.verifyCountKPITotalProDev();
			expectedKPICount = actualKPICount;
			softAssert.assertEquals(actualKPICount, expectedKPICount, "URL Mismatch: Navigation failed!");		
			result = expectedKPICount.equalsIgnoreCase(actualKPICount) ? "PASS" : "FAIL";
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clickable and read KPI count", e);
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expectedKPICount, actualKPICount, result);
			logger.info("Test case execution completed for: " + testCaseName);
			System.out.println("Successfully Navigated Click,Read KPI Count and open table of Total Dispatched Devices");
			softAssert.assertAll();
		}
	}
	
	@Test(priority = 11)
	public void ClickNameandReadKPI3Count() throws InterruptedException {
		String testCaseName = "Test Verify click and Read KPI Count of Total Installed Devices ";
		String expectedKPICount = "";
		String actualKPICount = devicedashboardPage.verifyAndClickKPITotalInsDevWithCount();
		String result = "FAIL"; // Default failure status

		logger.info("Executing the test Visible Page KPI for test case: " + testCaseName);
		try {
			logger.info("Attempting to clickable KPI count ...");
//			devicedashboardPage.verifyCountKPITotalProDev();
			expectedKPICount = actualKPICount;
			softAssert.assertEquals(actualKPICount, expectedKPICount, "URL Mismatch: Navigation failed!");		
			result = expectedKPICount.equalsIgnoreCase(actualKPICount) ? "PASS" : "FAIL";
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clickable and read KPI count", e);
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expectedKPICount, actualKPICount, result);
			logger.info("Test case execution completed for: " + testCaseName);
			System.out.println("Successfully Navigated Click,Read KPI Count and open table of Total Installed Devices");
			softAssert.assertAll();
		}
	}	
	
	@Test(priority = 12)
	public void ClickNameandReadKPI4Count() throws InterruptedException {
		String testCaseName = "Test Verify click and Read KPI Count of Total Discarded Devices ";
		String expectedKPICount = "";
		String actualKPICount = devicedashboardPage.verifyAndClickKPITotalDiscardDevWithCount();
		String result = "FAIL"; // Default failure status

		logger.info("Executing the test Visible Page KPI for test case: " + testCaseName);
		try {
			logger.info("Attempting to clickable KPI count ...");
//			devicedashboardPage.verifyCountKPITotalProDev();
			expectedKPICount = actualKPICount;
			softAssert.assertEquals(actualKPICount, expectedKPICount, "URL Mismatch: Navigation failed!");		
			result = expectedKPICount.equalsIgnoreCase(actualKPICount) ? "PASS" : "FAIL";
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while clickable and read KPI count", e);
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expectedKPICount, actualKPICount, result);
			logger.info("Test case execution completed for: " + testCaseName);
			System.out.println("Successfully Navigated Click and Read KPI Count and open table of Total Discarded Devices");
			softAssert.assertAll();
		}
	}
	
	@Test(priority = 13)
	public void SearchBox() throws InterruptedException {
		String testCaseName = "Test Verify Searchbox of Total Production Devices";
		String expectedIMEI = "867409079963166" + "| 89916431144821180029" + "| ACON4IA202200096315";
		String actualIMEI = devicedashboardPage.clickAndEnterTextInSearchBox();		
		String result = "FAIL"; // Default failure status

		logger.info("Executing the test click in serch box of Device Dashboard Page: " + testCaseName);
		try {
			logger.info("Attempting to click in searchbox ...");
//			actualIMEI = "867409079963166" + "| 89916431144821180029" + "| ACON4IA202200096315";
//			devicedashboardPage.clickAndEnterTextInSearchBox();			
			softAssert.assertEquals(actualIMEI, expectedIMEI, "URL Mismatch: Navigation failed!");
			result = expectedIMEI.equalsIgnoreCase(actualIMEI) ? "PASS" : "FAIL";
			logger.info("Result is: " + result);
		} catch (Exception e) {
			logger.error("An error occurred while click in the search box.", e);
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expectedIMEI, actualIMEI, result);
			logger.info("Test case execution completed for: " + testCaseName);
			System.out.println("Successfully Navigated click and enter input in the search box of 'Total Production Devices'");
		}
	}
	
//	@Test(priority = 14)
//	public void EnterInputSearchBox() throws InterruptedException {
//		String testCaseName = "Test Verify Searchbox on Device Dashboard page";
//		String expectedKPIName = "TOTAL PRODUCTION DEVICES";
//		String actualKPIName = "";
//		String result = "FAIL"; // Default failure status
//
//		logger.info("Executing the test input enter in serch box of Device Dashboard Page: " + testCaseName);
//		try {
//			logger.info("Attempting to input enter in searchbox ...");
//			devicedashboardPage.clickAndEnterTextInSearchBox();
//			actualKPIName = "TOTAL PRODUCTION DEVICES";
//			softAssert.assertEquals(actualKPIName, expectedKPIName, "URL Mismatch: Navigation failed!");
//			result = expectedKPIName.equalsIgnoreCase(actualKPIName) ? "PASS" : "FAIL";
//			logger.info("Result is: " + result);
//		} catch (Exception e) {
//			logger.error("An error occurred while input enter in the search box.", e);
//			e.printStackTrace();
//		} finally {
//			excelUtility.writeTestDataToExcel(testCaseName, expectedKPIName, actualKPIName, result);
//			logger.info("Test case execution completed for: " + testCaseName);
//			System.out.println("Successfully click and input enter in the search box of Device Dashboard Page");
//			softAssert.assertAll();
//		}
//	}
	
}
