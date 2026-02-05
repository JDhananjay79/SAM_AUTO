package com.aepl.sam.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.utils.Constants;
import com.aepl.sam.pages.CommonMethods;
import com.aepl.sam.pages.FotaPage;
import com.aepl.sam.utils.ExcelUtility;

public class FotaPageTest extends TestBase {
	private static final String FOTA_EXCEL_SHEET = "FOTA_Test";

	// TC
	private static final String TC_PAGE_LOGO = "Verify Company Logo on Webpage";
	private static final String TC_PAGE_TITLE = "Verify Page Title on Webpage";
	private static final String TC_REFRESH_BTN = "Verify Refresh Button Functionality";
	private static final String TC_DEVICE_UTILITY = "Verify Device Utility Functionality";
	private static final String TC_FOTA = "Verify FOTA Functionality";
	private static final String TC_VALIDATE_COMPONENTS = "Verify All Components on Webpage";
	private static final String TC_VALIDATE_BUTTONS = "Verify All Buttons on Webpage";
	private static final String TC_CREATE_MANUAL_BATCH = "Create FOTA Batch";
	private static final String TC_CREATE_BULK_BATCH = "Create Bulk FOTA Batch";
	private static final String TC_PAGINATION = "Pagination Check";
	private static final String TC_BATCH_LIST = "Get FOTA Batch List";
	private static final String TC_VERSION = "Verify Version Functionality";
	private static final String TC_COPYRIGHT = "Verify Copyright Functionality";

	// Expected Results
	private static final String EXP_LOGO_DISPLAYED = "Logo Displayed";
	private static final String EXP_PAGE_TITLE = "AEPL Sampark Diagnostic Cloud";
	private static final String EXP_REFRESH_BTN = "Clicked on the refreshed button";
	private static final String EXP_DEVICE_UTILITY = "Clicked on Device Utility";
	private static final String EXP_FOTA = "Clicked on FOTA";
	private static final String EXP_VALIDATE_COMPONENTS = "All components are displayed and validated successfully.";
	private static final String EXP_VALIDATE_BUTTONS = "All buttons are displayed and enabled successfully.";
	private static final String EXP_CREATE_MANUAL_BATCH = "FOTA batch created successfully.";
	private static final String EXP_CREATE_BULK_BATCH = "Bulk FOTA batch created successfully.";
	private static final String EXP_PAGINATION = "Pagination verified successfully!";
	private static final String EXP_BATCH_LIST = "Batch seted successfully!";

	private FotaPage fota;
	private ExcelUtility excelUtility;
	private CommonMethods comm;
	private SoftAssert softAssert;
	private Executor executor;

	@Override
	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.fota = new FotaPage(driver, wait, comm);
		this.excelUtility = new ExcelUtility();
		this.softAssert = new SoftAssert();
		this.executor = new Executor(excelUtility, softAssert);
		excelUtility.initializeExcel(FOTA_EXCEL_SHEET);
		logger.info("Setup completed for FotaPageTest");
	}

	@Test(priority = 1)
	public void testCompanyLogo() {
		executor.executeTest(TC_PAGE_LOGO, EXP_LOGO_DISPLAYED,
				() -> comm.verifyWebpageLogo() ? EXP_LOGO_DISPLAYED : "Logo Not Displayed");
	}

	@Test(priority = 2)
	public void testPageTitle() {
		executor.executeTest(TC_PAGE_TITLE, EXP_PAGE_TITLE, comm::verifyPageTitle);
	}

	@Test(priority = 3)
	public void testRefreshButton() {
		executor.executeTest(TC_REFRESH_BTN, EXP_REFRESH_BTN, () -> {
			comm.clickRefreshButton();
			return EXP_REFRESH_BTN;
		});
	}

	@Test(priority = 4)
	public void testDeviceUtility() {
		executor.executeTest(TC_DEVICE_UTILITY, EXP_DEVICE_UTILITY, () -> {
			fota.clickDeviceUtility();
			return EXP_DEVICE_UTILITY;
		});
	}

	@Test(priority = 5)
	public void testFota() {
		executor.executeTest(TC_FOTA, EXP_FOTA, () -> {
			fota.clickFota();
			return EXP_FOTA;
		});
	}

	@Test(priority = 6)
	public void testAllComponents() {
		executor.executeTest(TC_VALIDATE_COMPONENTS, EXP_VALIDATE_COMPONENTS, comm::validateComponents);
	}

	@Test(priority = 7)
	public void testAllButtons() {
		executor.executeTest(TC_VALIDATE_BUTTONS, EXP_VALIDATE_BUTTONS, comm::validateButtons);
	}

	@Test(priority = 8)
	public void testCreateManualFotaBatch() {
		executor.executeTest(TC_CREATE_MANUAL_BATCH, EXP_CREATE_MANUAL_BATCH, () -> {
			fota.selectFOTATypeButton("manual");
			fota.createManualFotaBatch(Constants.IMEI);
			return EXP_CREATE_MANUAL_BATCH;
		});
	}

	@Test(priority = 9)
	public void testCreateBulkFotaBatch() {
		executor.executeTest(TC_CREATE_BULK_BATCH, EXP_CREATE_BULK_BATCH, () -> {
			fota.selectFOTATypeButton("bulk");
			comm.clickSampleFileButton();
			fota.createBulkFotaBatch();
			return EXP_CREATE_BULK_BATCH;
		});
	}

	@Test(priority = 10)
	public void testPagination() {
		executor.executeTest(TC_PAGINATION, EXP_PAGINATION, () -> {
			comm.checkPagination();
			return EXP_PAGINATION;
		});
	}

	@Test(priority = 11)
	public void testGetFotaBatchList() {
		executor.executeTest(TC_BATCH_LIST, EXP_BATCH_LIST, fota::getFotaBatchList);
	}

	@Test(priority = 12)
	public void testFotaBatchButtons() {
		executor.executeTest(TC_VALIDATE_BUTTONS, EXP_VALIDATE_BUTTONS, comm::validateButtons);
	}

	@Test(priority = 13)
	public void testVersion() {
		executor.executeTest(TC_VERSION, Constants.EXP_VERSION_TEXT, comm::checkVersion);
	}

	@Test(priority = 14)
	public void testCopyright() {
		executor.executeTest(TC_COPYRIGHT, Constants.EXP_COPYRIGHT_TEXT, comm::checkCopyright);
	}

	@AfterClass
	public void tearDownAssertions() {
		softAssert.assertAll();
	}
}
