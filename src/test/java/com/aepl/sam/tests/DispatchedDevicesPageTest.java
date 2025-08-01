package com.aepl.sam.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.constants.DispatchDeviceConstants;
import com.aepl.sam.enums.Result;
import com.aepl.sam.pages.DispatchedDevicesPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;
import com.google.common.base.Supplier;

public class DispatchedDevicesPageTest extends TestBase implements DispatchDeviceConstants {
	private ExcelUtility excelUtility;
	private DispatchedDevicesPage dispatchedDevicePage;
	private CommonMethods comm;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.dispatchedDevicePage = new DispatchedDevicesPage(driver, wait, comm);
		this.excelUtility = new ExcelUtility();
		excelUtility.initializeExcel("Dispached_Devices_Test");
	}

	public void executeTest(String testCaseName, String expected, Supplier<String> actualSupplier) {
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing test case: { " + testCaseName + " }");
		try {
			actual = actualSupplier.get();
			Assert.assertEquals(actual, expected, testCaseName + " failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
		} catch (Exception e) {
			logger.error("Exception during execution of: " + testCaseName, e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
		}
	}

	@Test(priority = -1)
	public void testCompanyLogo() {
		executeTest(TC_LOGO, LOGO_DISPLAYED, () -> comm.verifyWebpageLogo() ? LOGO_DISPLAYED : LOGO_NOT_DISPLAYED);
	}

	@Test(priority = 0)
	public void testPageTitle() {
		executeTest(TC_PAGE_TITLE, EXP_PAGE_TITLE, comm::verifyPageTitle);
	}

	@Test(priority = 1)
	public void navBarLinkTest() {
		executeTest(TC_NAV_BAR_LINK, Constants.DEVICE_LINK, dispatchedDevicePage::navBarLink);
	}

	@Test(priority = 2)
	public void ClickAddDisDeviceTest() {
		executeTest(TC_ADD_DISPATCH_DEVICE, EXP_ADD_DISPATCH_DEVICE_PAGE, dispatchedDevicePage::ClickAddDisDevice);

	}

	@Test(priority = 3)
	public void TestAddDisDevice() throws InterruptedException {

		executeTest(TC_NEW_INPUT_FIELDS, EXP_NEW_INPUT_FIELDS, () -> {
			try {
				return dispatchedDevicePage.NewInputFields("add");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "Not able to add device";
		});

//		String testCaseName = "Test input fields by entering values";
//		String expected = "Dispatched Devices";
//		String actual = "";
//		String result = Result.FAIL.getValue();
//
//		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
//		try {
//			actual = dispatchedDevicePage.NewInputFields("add");
//			Assert.assertEquals(actual, expected, "Model addition failed!");
//			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
//		} catch (Exception e) {
//			logger.error("An error occurred while adding the device.", e);
//			result = Result.ERROR.getValue();
//			e.printStackTrace();
//		} finally {
//			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
//			// Assert.assertAll();
//		}
	}

	@Test(priority = 4)
	public void TestSearchDeviceTest() {
		String testCaseName = "Test search functionality for device models";
		String expected = "Dispatched Devices";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			actual = dispatchedDevicePage.SearchDevice();
			Assert.assertEquals(actual, expected, "Search operation failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
		} catch (Exception e) {
			logger.error("An error occurred while searching for the model.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			// Assert.assertAll();
		}
	}

	@Test(priority = 5)
	public void TestViewDevice() {
		String testCaseName = "Test View Device Model";
		String expected = "Update Dispatched Device";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			actual = dispatchedDevicePage.viewDevice();
			Assert.assertEquals(actual, expected, "Device viewing failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
		} catch (Exception e) {
			logger.error("An error occurred while viewing the device.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			// Assert.assertAll();
		}
	}

	@Test(priority = 6)
	public void TestUpdateDevice() throws InterruptedException {
		String testCaseName = "Test input fields by updating values";
		String expected = "Update Dispatched Device";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			actual = dispatchedDevicePage.NewInputFields("update");
			Assert.assertEquals(actual, expected, "Model update failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
		} catch (Exception e) {
			logger.error("An error occurred while updating the model.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			// Assert.assertAll();
		}
	}

	@Test(priority = 7)
	public void TestSearchDeviceTest1() {
		String testCaseName = "Test search functionality for device models";
		String expected = "Dispatched Devices";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			actual = dispatchedDevicePage.SearchDevice();
			Assert.assertEquals(actual, expected, "Search operation failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
		} catch (Exception e) {
			logger.error("An error occurred while searching for the model.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			// Assert.assertAll();
		}
	}

	@Test(priority = 8)
	public void DeleteDeviceTest() {
		String testCaseName = "Test View Device Model";
		String expected = "Dispatched Devices";
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			actual = dispatchedDevicePage.DeleteDevice();
			Assert.assertEquals(actual, expected, "Model deleting failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
		} catch (Exception e) {
			logger.error("An error occurred while deleting the model.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			// Assert.assertAll();
		}
	}

	@Test(priority = 9)
	public void testAllButtons() {
		String testCaseName = "Test All Buttons";
		String expected = "All buttons are displayed and enabled successfully.";
		String actual = "";
		String result = Result.FAIL.getValue();
		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");
		try {
			actual = comm.validateButtons();
			Assert.assertEquals(actual, expected, "Model deleting failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
		} catch (Exception e) {
			logger.error("An error occurred while deleting the model.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			// Assert.assertAll();
		}
	}

	@Test(priority = 10)
	public void testAllComponents() {
		String testCaseName = "Test All Components";
		String expected = "All components are displayed and validated successfully.";
		String actual = "";
		String result = Result.FAIL.getValue();
		logger.info("Executing the test Visible Page Name for test case: { " + testCaseName + " }");

		try {
			actual = comm.validateComponents();
			Assert.assertEquals(actual, expected, "Model deleting failed!");
			result = expected.equalsIgnoreCase(actual) ? "PASS" : "FAIL";
		} catch (Exception e) {
			logger.error("An error occurred while deleting the model.", e);
			result = Result.ERROR.getValue();
			e.printStackTrace();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			// Assert.assertAll();
		}
	}

	/* must have to implement the bulk add of dispatched devices */

}
