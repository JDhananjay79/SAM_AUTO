package com.aepl.sam.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.utils.Constants;
import com.aepl.sam.pages.CommonMethods;
import com.aepl.sam.pages.RoleManagementPage;
import com.aepl.sam.utils.ExcelUtility;

public class RoleManagementPageTest extends TestBase {
	private static final String SHEET_NAME = "User_Role_Test";

	// Test Case Names
	private static final String TC_LOGO = "Verify Company Logo on Webpage";
	private static final String TC_PAGE_TITLE = "Verify Page Title on Webpage";
	private static final String TC_NAV_BAR = "Verify NavBar Link Navigation";
	private static final String TC_BACK_BTN = "Verify Back Button Navigation";
	private static final String TC_REFRESH_BTN = "Verify Refresh Button Functionality";
	private static final String TC_ADD_USER_ROLE = "Verify 'Add User Role' Button Click";
	private static final String TC_SELECT_OPTIONS = "Verify Selecting Options in Add User Role";
	private static final String TC_SUBMIT_ROLE = "Verify Select All Permissions and Submit Role";
	private static final String TC_SEARCH_ROLE = "Verify Search User Role Functionality";
	private static final String TC_UPDATE_ROLE = "Verify Update User Role Functionality";
	private static final String TC_DELETE_ROLE = "Verify Delete User Role Functionality";
	private static final String TC_PAGINATION = "Verify Pagination Functionality";
	private static final String TC_VERSION = "Verify Version Functionality";
	private static final String TC_COPYRIGHT = "Verify Copyright Functionality";

	// Expected Results
	private static final String EXP_LOGO_DISPLAYED = "Logo Displayed";
	private static final String EXP_PAGE_TITLE = "AEPL Sampark Diagnostic Cloud";
	private static final String EXP_NAV_BAR = "Role Management";
	private static final String EXP_BACK_NAVIGATION = "Back button navigated successfully.";
	private static final String EXP_REFRESH_TITLE = "Role Management";
	private static final String EXP_ADD_ROLE_SCREEN = "Role Management";
	private static final String EXP_SELECT_OPTIONS = "Options selected successfully";
	private static final String EXP_SUBMIT_ROLE = "User role permissions selected and submitted";
	private static final String EXP_SEARCH_ROLE = "Role 'DEMO' should be found and searched successfully";
	private static final String EXP_UPDATE_ROLE = "User role should be updated successfully";
	private static final String EXP_DELETE_ROLE = "User role should be deleted successfully";
	private static final String EXP_PAGINATION = "Pagination works correctly";

	private ExcelUtility excelUtility;
	private RoleManagementPage userRole;
	private CommonMethods comm;
	private SoftAssert softAssert;
	private Executor executor;

	@Override
	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.userRole = new RoleManagementPage(driver, wait);
		this.excelUtility = new ExcelUtility();
		this.softAssert = new SoftAssert();
		this.executor = new Executor(excelUtility, softAssert);
		excelUtility.initializeExcel(SHEET_NAME);
	}

	@Test(priority = 1)
	public void testCompanyLogo() {
		executor.executeTest(TC_LOGO, EXP_LOGO_DISPLAYED,
				() -> comm.verifyWebpageLogo() ? EXP_LOGO_DISPLAYED : "Logo Not Displayed");
	}

	@Test(priority = 2)
	public void testPageTitle() {
		executor.executeTest(TC_PAGE_TITLE, EXP_PAGE_TITLE, comm::verifyPageTitle);
	}

	@Test(priority = 3)
	public void testNavBarLink() {
		executor.executeTest(TC_NAV_BAR, Constants.ROLE_MANAGEMENT, userRole::navBarLink);
	}

	// @Test(priority = 4)
	public void testBackButton() {
		executor.executeTest(TC_BACK_BTN, EXP_BACK_NAVIGATION, () -> {
			userRole.backButton();
			return EXP_BACK_NAVIGATION;
		});
	}

	// @Test(priority = 5)
	public void testRefreshButton() {
		executor.executeTest(TC_REFRESH_BTN, EXP_REFRESH_TITLE, userRole::refreshButton);
	}

	@Test(priority = 6)
	public void testClickAddUserRole() {
		executor.executeTest(TC_ADD_USER_ROLE, EXP_ADD_ROLE_SCREEN, userRole::clickAddUserRoleBtn);
	}

	@Test(priority = 7)
	public void testSelectingOptions() {
		executor.executeTest(TC_SELECT_OPTIONS, EXP_SELECT_OPTIONS, () -> {
			userRole.selectingOptions();
			return EXP_SELECT_OPTIONS;
		});
	}

	@Test(priority = 8)
	public void testSelectOptionsAndSubmit() {
		executor.executeTest(TC_SUBMIT_ROLE, EXP_SUBMIT_ROLE, () -> {
			userRole.selectOptionsAndSubmit();
			return EXP_SUBMIT_ROLE;
		});
	}

	@Test(priority = 9)
	public void testSearchUserRole() {
		executor.executeTest(TC_SEARCH_ROLE, EXP_SEARCH_ROLE, () -> {
			userRole.searchUserRole();
			return EXP_SEARCH_ROLE;
		});
	}

	// @Test(priority = 10)
	public void testUpdateUserRole() {
		executor.executeTest(TC_UPDATE_ROLE, EXP_UPDATE_ROLE, () -> {
			userRole.updateUserRole();
			return EXP_UPDATE_ROLE;
		});
	}

	// @Test(priority = 11)
	public void testDeleteUserRole() {
		executor.executeTest(TC_DELETE_ROLE, EXP_DELETE_ROLE, () -> {
			userRole.deleteUserRole();
			return EXP_DELETE_ROLE;
		});
	}

	// // @Test(priority = 12)
	public void testPagination() {
		executor.executeTest(TC_PAGINATION, EXP_PAGINATION, () -> {
			comm.checkPagination();
			return EXP_PAGINATION;
		});
	}

	// @Test(priority = 13)
	public void testVersion() {
		executor.executeTest("Verify Version Functionality", Constants.EXP_VERSION_TEXT, comm::checkVersion);
	}

	// @Test(priority = 14)
	public void testCopyright() {
		executor.executeTest("Verify Copyright Functionality", Constants.EXP_COPYRIGHT_TEXT, comm::checkCopyright);
	}

	@AfterClass
	public void tearDownAssertions() {
		softAssert.assertAll();
	}
}
