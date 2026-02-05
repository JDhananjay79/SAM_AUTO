package com.aepl.sam.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import java.util.List;

import com.aepl.sam.pages.CommonMethods;
import com.aepl.sam.pages.DealersManagementPage;
import com.aepl.sam.utils.ExcelUtility;

public class DealersManagementPageTest extends TestBase {
	// Excel Sheet Name
	private static final String SHEET_NAME = "Dealer_Management_Test";

	// Test Case Names
	private static final String TC_COMPANY_LOGO = "Test Company logo";
	private static final String TC_NAV_BAR_LINK = "Test Nav Bar Link for {Dealer Management}";
	private static final String TC_PAGE_TITLE = "Test page title for {Sim Batch Data Details}";
	private static final String TC_COMPONENT_TITLE = "Test Page Component Title";
	private static final String TC_VALIDATE_BUTTONS = "Test all button on page {Sim Batch Data Details}";
	private static final String TC_VALIDATE_COMPONENTS = "Test All Components on the page {Sim Batch Data Details}";
	private static final String TC_SEARCH_BUTTON_ENABLED = "Test search button is enabled? ";
	private static final String TC_SEARCH_BOX_ENABLED = "Test search box is enabled? ";
	private static final String TC_SEARCH_BOX_MULTIPLE_INPUTS = "Test input box with multiple inputs";
	private static final String TC_TABLE_HEADERS = "Test Table headers of {Dealer Management Page}";
	private static final String TC_TABLE_DATA = "Validate table data of Dealer Management";
	private static final String TC_VIEW_BUTTONS = "Validate View Buttons in Dealer Management";
	private static final String TC_DELETE_BUTTONS = "Validate Delete Buttons in Dealer Management";
	private static final String TC_PAGINATION = "Test Pagination";
	private static final String TC_ADD_DEALER_BUTTON_ENABLED = "Test add dealer button enabled";
	private static final String TC_ADD_DEALER_BUTTON_VISIBLE = "Test add dealer button enabled";
	private static final String TC_CLICK_ADD_DEALER_BUTTON = "Test Add Dealer Button";
	private static final String TC_ALL_INPUT_BOXES = "Test all input boxes for adding new dealer details";
	private static final String TC_FORM_VALIDATIONS = "Test all input boxes and validations for dealer form";
	private static final String TC_SUBMIT_BUTTON_NO_DATA = "Test submit button is visible on if not data is visible";
	private static final String TC_SUBMIT_BUTTON = "Test the submit button";

	// Expected Results
	private static final boolean EXP_LOGO_DISPLAYED = true;
	private static final boolean EXP_NAV_BAR_LINK = true;
	private static final String EXP_PAGE_TITLE = "Sensorise SIM Data Details";
	private static final String EXP_COMPONENT_TITLE = "SIM Data Details";
	private static final String EXP_VALIDATE_BUTTONS = "All buttons are displayed and enabled successfully.";
	private static final String EXP_VALIDATE_COMPONENTS = "All components are displayed and validated successfully.";
	private static final boolean EXP_SEARCH_BUTTON_ENABLED = true;
	private static final boolean EXP_SEARCH_BOX_ENABLED = true;
	private static final boolean EXP_SEARCH_BOX_MULTIPLE_INPUTS = true;
	private static final List<String> EXP_TABLE_HEADERS = List.of("FULL NAME", "EMAIL", "MOBILE NO.", "CREATED BY",
			"STATUS", "ACTION");
	private static final boolean EXP_TABLE_DATA = true;
	private static final boolean EXP_VIEW_BUTTONS = true;
	private static final boolean EXP_DELETE_BUTTONS = true;
	private static final boolean EXP_PAGINATION = true;
	private static final boolean EXP_ADD_DEALER_BUTTON_ENABLED = true;
	private static final boolean EXP_ADD_DEALER_BUTTON_VISIBLE = true;
	private static final String EXP_CLICK_ADD_DEALER_BUTTON = "Save Dealers Details";
	private static final boolean EXP_ALL_INPUT_BOXES = true;
	private static final boolean EXP_FORM_VALIDATIONS = true;
	private static final boolean EXP_SUBMIT_BUTTON_NO_DATA = true;
	private static final boolean EXP_SUBMIT_BUTTON = true;

	private ExcelUtility excelUtility;
	private DealersManagementPage dealerPage;
	private CommonMethods comm;
	private SoftAssert softAssert;
	private Executor executor;

	@Override
	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.dealerPage = new DealersManagementPage(driver, wait, comm);
		this.excelUtility = new ExcelUtility();
		this.softAssert = new SoftAssert();
		excelUtility.initializeExcel(SHEET_NAME);
		this.executor = new Executor(excelUtility, softAssert);
	}

	@Test(priority = 1)
	public void testCompanyLogo() {
		executor.executeTest(TC_COMPANY_LOGO, EXP_LOGO_DISPLAYED, () -> comm.verifyWebpageLogo());
	}

	@Test(priority = 2)
	public void testNavBarLink() {
		executor.executeTest(TC_NAV_BAR_LINK, EXP_NAV_BAR_LINK, dealerPage::navBarLink);
	}

	@Test(priority = 3)
	public void testPageTitle() {
		executor.executeTest(TC_PAGE_TITLE, EXP_PAGE_TITLE, dealerPage::verifyPageTitle);
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
	public void testSearchButtonIsEnabled() {
		executor.executeTest(TC_SEARCH_BUTTON_ENABLED, EXP_SEARCH_BUTTON_ENABLED, dealerPage::isSearchButtonEnabled);
	}

	@Test(priority = 8)
	public void testSearchBoxIsEnabled() {
		executor.executeTest(TC_SEARCH_BOX_ENABLED, EXP_SEARCH_BOX_ENABLED, dealerPage::isSearchBoxEnabled);
	}

	@Test(priority = 9)
	public void testSearchBoxByMultipleInputs() {
		executor.executeTest(TC_SEARCH_BOX_MULTIPLE_INPUTS, EXP_SEARCH_BOX_MULTIPLE_INPUTS,
				dealerPage::validateSearchBoxWithMultipleInputs);
	}

	@Test(priority = 10)
	public void testTableHeadersOfDealerManagement() {
		executor.executeTest(TC_TABLE_HEADERS, EXP_TABLE_HEADERS, dealerPage::validateTableHeaders);
	}

	@Test(priority = 11)
	public void testTableDataOfDealerManagement() {
		executor.executeTest(TC_TABLE_DATA, EXP_TABLE_DATA, dealerPage::validateTableData);
	}

	@Test(priority = 12)
	public void testViewButtonsEnabled() {
		executor.executeTest(TC_VIEW_BUTTONS, EXP_VIEW_BUTTONS, dealerPage::validateViewButtons);
	}

	@Test(priority = 13)
	public void testDeleteButtonsStatusWise() {
		executor.executeTest(TC_DELETE_BUTTONS, EXP_DELETE_BUTTONS, dealerPage::validateDeleteButtons);
	}

	@Test(priority = 14)
	public void testPaginationOnDealersManagementPage() {
		executor.executeTest(TC_PAGINATION, EXP_PAGINATION, () -> {
			try {
				comm.checkPagination();
				return true;
			} catch (Exception e) {
				logger.error("Pagination test failed: {}", e.getMessage(), e);
				return false;
			}
		});
	}

	@Test(priority = 15)
	public void testAddDealersButtonEnabled() {
		executor.executeTest(TC_ADD_DEALER_BUTTON_ENABLED, EXP_ADD_DEALER_BUTTON_ENABLED,
				dealerPage::isAddDealerButtonEnabled);
	}

	@Test(priority = 16)
	public void testAddDealersButtonVisible() {
		executor.executeTest(TC_ADD_DEALER_BUTTON_VISIBLE, EXP_ADD_DEALER_BUTTON_VISIBLE,
				dealerPage::isAddDealerButtonVisible);
	}

	@Test(priority = 17)
	public void testClickOnAddDealerBtn() {
		executor.executeTest(TC_CLICK_ADD_DEALER_BUTTON, EXP_CLICK_ADD_DEALER_BUTTON, dealerPage::clickAddDealerButton);
	}

	@Test(priority = 18)
	public void testAllInputBoxesEnabled() {
		executor.executeTest(TC_ALL_INPUT_BOXES, EXP_ALL_INPUT_BOXES, dealerPage::testAllFormFields);
	}

	@Test(priority = 19)
	public void testDealerFormValidations() {
		executor.executeTest(TC_FORM_VALIDATIONS, EXP_FORM_VALIDATIONS, dealerPage::testAllFormFieldsErrors);
	}

	@Test(priority = 20)
	public void testSubmitButtonVisibleIfNoDataInputed() {
		executor.executeTest(TC_SUBMIT_BUTTON_NO_DATA, EXP_SUBMIT_BUTTON_NO_DATA,
				dealerPage::isSubmitButtonIsVisibleIfNoDataIsInputed);
	}

	@Test(priority = 21)
	public void testSubmitButton() {
		executor.executeTest(TC_SUBMIT_BUTTON, EXP_SUBMIT_BUTTON, dealerPage::isDataSubmittedSuccessfully);
	}

	@AfterClass
	public void tearDownAssertions() {
		softAssert.assertAll();
	}
}
