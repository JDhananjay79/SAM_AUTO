package com.aepl.sam.tests;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.utils.Constants;
import com.aepl.sam.pages.CommonMethods;
import com.aepl.sam.pages.SimBatchDataDetailsPage;
import com.aepl.sam.utils.ExcelUtility;

public class SimBatchDataDetailsPageTest extends TestBase {
	// Excel Sheet Name
	private static final String SHEET_NAME = "Sim_Batch_Data_Details_Test";

	// ---- Test Case Names ----
	private static final String TC_COMPANY_LOGO = "Test Company logo";
	private static final String TC_NAV_BAR_LINK = "Test Nav Bar Link for {Sim Batch Data Details}";
	private static final String TC_PAGE_TITLE = "Test page title for {Sim Batch Data Details}";
	private static final String TC_COMPONENT_TITLE = "Test Page Component Title";
	private static final String TC_VALIDATE_BUTTONS = "Test all button on page {Sim Batch Data Details}";
	private static final String TC_VALIDATE_COMPONENTS = "Test All Components on the page {Sim Batch Data Details}";
	private static final String TC_DOWNLOAD_SAMPLE_FILE = "Test the sample file on page {Sim Batch Data Details}";
	private static final String TC_UPLOAD_BOX_CORRECT = "Test the input box is correct";
	private static final String TC_ERROR_MSG_INPUT_BOX = "Test error message for blank input";
	private static final String TC_UPLOAD_BUTTON_ENABLED = "Test the upload button is enabled";
	private static final String TC_UPLOAD_FILE = "Test upload file";
	private static final String TC_SUBMIT_BUTTON = "Test Submit button";
	private static final String TC_UPLOAD_TABLE_HEADERS = "Test Upload Sim Data Details Components Table Headers";
	private static final String TC_DUPLICATE_ICCID_HEADERS = "Test Duplicate ICCID's In Uploaded File Components Table Headers";
	private static final String TC_NOT_PRESENT_ICCID_HEADERS = "Test ICCID Not Present In Sensorise Database Components Table Headers";
	private static final String TC_EXPORT_BUTTON_NOT_PRESENT = "Test Export button of ICCID Not present";
	private static final String TC_EXPORT_BUTTON_DUPLICATE = "Test Export button of Duplicate ICCID Uploaded";
	private static final String TC_EXPORT_BUTTON_UPLOAD = "Test Export button of Sim Data Details";
	private static final String TC_MANUAL_UPLOAD_VISIBLE = "Test manual upload button is visible";
	private static final String TC_MANUAL_UPLOAD_CLICKABLE = "Test manual upload button is clickable";
	private static final String TC_MANUAL_UPLOAD_CLICKED_OPENED = "Test manual upload button is clicked and opened";
	private static final String TC_INPUT_BOX_ENABLED = "Test input box enabled";
	private static final String TC_EMPTY_INPUT_VALIDATION = "Empty input validation";
	private static final String TC_SHORT_INPUT_VALIDATION = "Short input validation";
	private static final String TC_LONG_INPUT_VALIDATION = "Long input validation";
	private static final String TC_SPECIAL_CHAR_VALIDATION = "Special char validation";
	private static final String TC_SUBMIT_BUTTON_ENABLED = "Test submit button enabled";
	private static final String TC_CLICK_SUBMIT_BUTTON = "Test Clicked the submit button";
	private static final String TC_PAGINATION = "Test pagination of the whole {Sim Data Details Page}";
	private static final String TC_VERSION = "Verify Application Version Display";
	private static final String TC_COPYRIGHT = "Verify Copyright Text";

	// ---- Expected Results ----
	private static final boolean EXP_LOGO_DISPLAYED = true;
	private static final boolean EXP_NAV_BAR_LINK = true;
	private static final String EXP_PAGE_TITLE = "Sensorise SIM Data Details";
	private static final String EXP_COMPONENT_TITLE = "SIM Data Details";
	private static final String EXP_VALIDATE_BUTTONS = "All buttons are displayed and enabled successfully.";
	private static final String EXP_VALIDATE_COMPONENTS = "All components are displayed and validated successfully.";
	private static final String EXP_DOWNLOAD_SAMPLE_FILE = "File downloaded successfully.";
	private static final String EXP_UPLOAD_BOX_CORRECT = "Upload ICCID's to get SIM Data Details";
	private static final String EXP_ERROR_MSG_INPUT_BOX = "This field is mandatory.";
	private static final boolean EXP_UPLOAD_BUTTON_ENABLED = true;
	private static final boolean EXP_UPLOAD_FILE = true;
	private static final boolean EXP_SUBMIT_BUTTON = true;

	private static final List<String> EXP_UPLOAD_TABLE_HEADERS = List.of("ICCID", "CARD STATE", "CARD STATUS",
			"PRIMARY TSP", "FALLBACK TSP",
			"PRIMARY STATUS", "PRIMARY MSISDN", "FALLBACK STATUS", "FALLBACK MSISDN", "ACTIVE PROFILES",
			"CARD EXPIRY DATE", "PRODUCT NAME", "IS RSU REQUIRED", "IS IMSI REQUIRED", "ACTIVE SR NUMBER");

	private static final List<String> EXP_DUPLICATE_ICCID_HEADERS = List.of("ICCID", "MESSAGE");

	private static final List<String> EXP_NOT_PRESENT_ICCID_HEADERS = List.of("ICCID", "MESSAGE");

	private static final boolean EXP_EXPORT_BUTTON = true;
	private static final boolean EXP_MANUAL_UPLOAD_VISIBLE = true;
	private static final boolean EXP_MANUAL_UPLOAD_CLICKABLE = true;

	private static final List<String> EXP_MANUAL_UPLOAD_CLICKED_OPENED = List.of(Constants.SIM_MANUAL_UPLOAD,
			"SIM Data Details");

	private static final boolean EXP_INPUT_BOX_ENABLED = true;
	private static final String EXP_EMPTY_INPUT_VALIDATION = "This field is required and can't be only spaces.";
	private static final String EXP_SHORT_INPUT_VALIDATION = "Value must be exactly 20 characters long.";
	private static final String EXP_LONG_INPUT_VALIDATION = "Value must be exactly 20 characters long.";
	private static final String EXP_SPECIAL_CHAR_VALIDATION = "Special characters are not allowed.";
	private static final boolean EXP_SUBMIT_BUTTON_ENABLED = true;
	private static final boolean EXP_CLICK_SUBMIT_BUTTON = true;
	private static final boolean EXP_PAGINATION = true;
	private static final String EXP_VERSION = "Version: 1.5.0";
	private static final String EXP_COPYRIGHT = "Accolade Electronics Pvt. Ltd.";

	private ExcelUtility excelUtility;
	private SimBatchDataDetailsPage simBatch;
	private CommonMethods comm;
	private SoftAssert softAssert;
	private Executor executor;

	@Override
	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.simBatch = new SimBatchDataDetailsPage(driver, wait, comm);
		this.excelUtility = new ExcelUtility();
		this.softAssert = new SoftAssert();
		excelUtility.initializeExcel(SHEET_NAME);
		this.executor = new Executor(excelUtility, softAssert);
	}

	@Test(priority = 1)
	public void testCompanyLogo() {
		executor.executeTest(TC_COMPANY_LOGO, EXP_LOGO_DISPLAYED, comm::verifyWebpageLogo);
	}

	@Test(priority = 2)
	public void testNavBarLink() {
		executor.executeTest(TC_NAV_BAR_LINK, EXP_NAV_BAR_LINK, simBatch::navBarLink);
	}

	@Test(priority = 3)
	public void testPageTitle() {
		executor.executeTest(TC_PAGE_TITLE, EXP_PAGE_TITLE, simBatch::verifyPageTitle);
	}

	@Test(priority = 4)
	public void testComponentTitle() {
		executor.executeTest(TC_COMPONENT_TITLE, EXP_COMPONENT_TITLE, comm::validateComponentTitle);
	}

	@Test(priority = 5)
	public void testButtons() {
		executor.executeTest(TC_VALIDATE_BUTTONS, EXP_VALIDATE_BUTTONS, comm::validateButtons);
	}

	@Test(priority = 6)
	public void testComponents() {
		executor.executeTest(TC_VALIDATE_COMPONENTS, EXP_VALIDATE_COMPONENTS, comm::validateComponents);
	}

	@Test(priority = 7)
	public void testDownloadSampleFile() {
		executor.executeTest(TC_DOWNLOAD_SAMPLE_FILE, EXP_DOWNLOAD_SAMPLE_FILE, comm::clickSampleFileButton);
	}

	@Test(priority = 8)
	public void testUploadBoxIsCorrect() {
		executor.executeTest(TC_UPLOAD_BOX_CORRECT, EXP_UPLOAD_BOX_CORRECT, simBatch::validateCorrectBox);
	}

	@Test(priority = 9)
	public void testErrorMessageOfTheInputBox() {
		executor.executeTest(TC_ERROR_MSG_INPUT_BOX, EXP_ERROR_MSG_INPUT_BOX,
				() -> simBatch.isInputBoxHaveProperValidations("error-msg"));
	}

	@Test(priority = 10)
	public void testUploadButtonIsEnabled() {
		executor.executeTest(TC_UPLOAD_BUTTON_ENABLED, EXP_UPLOAD_BUTTON_ENABLED, simBatch::isUploadButtonIsEnabled);
	}

	@Test(priority = 11)
	public void testUploadFile() {
		executor.executeTest(TC_UPLOAD_FILE, EXP_UPLOAD_FILE, simBatch::validateUpload);
	}

	@Test(priority = 12)
	public void testSubmitButton() {
		executor.executeTest(TC_SUBMIT_BUTTON, EXP_SUBMIT_BUTTON, simBatch::validateSubmitButton);
	}

	@Test(priority = 16)
	public void testUploadSimDataDetailsComponentsTableHeaders() {
		executor.executeTest(TC_UPLOAD_TABLE_HEADERS, EXP_UPLOAD_TABLE_HEADERS,
				() -> simBatch.validateTableHeaders("upload"));
	}

	@Test(priority = 17)
	public void testDuplicateICCIDInUploadedExcelSheetTableHeaders() {
		executor.executeTest(TC_DUPLICATE_ICCID_HEADERS, EXP_DUPLICATE_ICCID_HEADERS,
				() -> simBatch.validateTableHeaders("duplicate"));
	}

	@Test(priority = 18)
	public void testICCIDNotPresentInSensoriseDatabaseTableHeaders() {
		executor.executeTest(TC_NOT_PRESENT_ICCID_HEADERS, EXP_NOT_PRESENT_ICCID_HEADERS,
				() -> simBatch.validateTableHeaders("not present"));
	}

	@Test(priority = 19)
	public void testICCIDNotPresentInSensoriseDatabaseExportButton() {
		executor.executeTest(TC_EXPORT_BUTTON_NOT_PRESENT, EXP_EXPORT_BUTTON, simBatch::validateExportButton);
	}

	@Test(priority = 20)
	public void testDuplicateICCIDInUploadedExcelSheetExportButton() {
		executor.executeTest(TC_EXPORT_BUTTON_DUPLICATE, EXP_EXPORT_BUTTON, simBatch::validateExportButton);
	}

	@Test(priority = 21)
	public void testUploadSimDataDetailsComponentsExportButton() {
		executor.executeTest(TC_EXPORT_BUTTON_UPLOAD, EXP_EXPORT_BUTTON, simBatch::validateExportButton);
	}

	@Test(priority = 22)
	public void testManualUploadButtonIsVisible() {
		executor.executeTest(TC_MANUAL_UPLOAD_VISIBLE, EXP_MANUAL_UPLOAD_VISIBLE,
				simBatch::isManualUploadButtonsVisible);
	}

	@Test(priority = 23)
	public void testManualUploadButtonIsClickable() {
		executor.executeTest(TC_MANUAL_UPLOAD_CLICKABLE, EXP_MANUAL_UPLOAD_CLICKABLE,
				simBatch::isManualUploadButtonsClickable);
	}

	@Test(priority = 24)
	public void testManualUploadClickAndOpen() {
		executor.executeTest(TC_MANUAL_UPLOAD_CLICKED_OPENED, EXP_MANUAL_UPLOAD_CLICKED_OPENED,
				simBatch::manualUploadButtonClickedAndOpened);
	}

	@Test(priority = 27)
	public void testInputBoxEnabled() {
		executor.executeTest(TC_INPUT_BOX_ENABLED, EXP_INPUT_BOX_ENABLED, simBatch::isInputBoxEnabled);
	}

	@Test(priority = 28)
	public void testEmptyInputValidation() {
		executor.executeTest(TC_EMPTY_INPUT_VALIDATION, EXP_EMPTY_INPUT_VALIDATION,
				() -> simBatch.isInputBoxHaveProperValidations(" "));
	}

	@Test(priority = 29)
	public void testShortInputValidation() {
		executor.executeTest(TC_SHORT_INPUT_VALIDATION, EXP_SHORT_INPUT_VALIDATION,
				() -> simBatch.isInputBoxHaveProperValidations("shortText"));
	}

	@Test(priority = 30)
	public void testLongInputValidation() {
		executor.executeTest(TC_LONG_INPUT_VALIDATION, EXP_LONG_INPUT_VALIDATION,
				() -> simBatch.isInputBoxHaveProperValidations("thisIsMoreThan20CharactersInput"));
	}

	@Test(priority = 31)
	public void testSpecialCharValidation() {
		executor.executeTest(TC_SPECIAL_CHAR_VALIDATION, EXP_SPECIAL_CHAR_VALIDATION,
				() -> simBatch.isInputBoxHaveProperValidations("Invalid@#%CharsInput"));
	}

	@Test(priority = 32)
	public void testSubmitButtonEnabled() {
		executor.executeTest(TC_SUBMIT_BUTTON_ENABLED, EXP_SUBMIT_BUTTON_ENABLED, simBatch::isSubmitButtonEnabled);
	}

	@Test(priority = 33)
	public void testClickSubmitButton() {
		executor.executeTest(TC_CLICK_SUBMIT_BUTTON, EXP_CLICK_SUBMIT_BUTTON, simBatch::clickSubmitButton);
	}

	@Test(priority = 35)
	public void testPaginationofSimDataDetailsWholePage2() {
		executor.executeTest(TC_PAGINATION, EXP_PAGINATION, () -> {
			try {
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
				Thread.sleep(500);
				comm.checkPagination();
				return true;
			} catch (Exception e) {
				return false;
			}
		});
	}

	@Test(priority = 36)
	public void testVersion() {
		executor.executeTest(TC_VERSION, EXP_VERSION, comm::checkVersion);
	}

	@Test(priority = 37)
	public void testCopyright() {
		executor.executeTest(TC_COPYRIGHT, EXP_COPYRIGHT, comm::checkCopyright);
	}

	@AfterClass
	public void tearDownAssertions() {
		softAssert.assertAll();
	}
}
