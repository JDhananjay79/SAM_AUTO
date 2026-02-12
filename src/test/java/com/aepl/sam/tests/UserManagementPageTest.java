package com.aepl.sam.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.utils.Constants;
import com.aepl.sam.pages.CommonMethods;
import com.aepl.sam.pages.UserManagementPage;
import com.aepl.sam.utils.ExcelUtility;

public class UserManagementPageTest extends TestBase {
	private static final String SHEET_NAME = "User_Management_Test";

	private static final String TC_LOGO = "Verify Company Logo on Webpage";
	private static final String EXP_LOGO = "Logo Displayed";

	private static final String TC_TITLE = "Verify Page Title on Webpage";
	private static final String EXP_TITLE = "AEPL Sampark Diagnostic Cloud";

	private static final String TC_NAVBAR = "Verify Navigation Bar Link";
	private static final String EXP_NAVBAR = Constants.USR_MAN;

	private static final String TC_REFRESH = "Verify Refresh Button Functionality";
	private static final String EXP_REFRESH = "User Management";

	private static final String TC_ADD_BTN = "Verify Add User Button Click";
	private static final String EXP_ADD_BTN = "Add User Button Clicked Successfully";

	private static final String TC_PROFILE_PIC = "Verify Add User Profile Picture";
	private static final String EXP_PROFILE_PIC = "Profile picture uploaded successfully.";

	private static final String TC_ADD_USER = "Verify Add New User Functionality";
	private static final String EXP_ADD_USER = "User 'add' operation completed successfully.";

	private static final String TC_DROPDOWN = "Verify Dropdown Options Functionality";
	private static final String EXP_DROPDOWN = "Dropdown options verified successfully.";

	private static final String TC_SEARCH_USER = "Verify User Search and View Functionality";
	private static final String EXP_SEARCH_USER = "User search and view successful.";

	private static final String TC_UPDATE_USER = "Verify Update User Functionality";
	private static final String EXP_UPDATE_USER = "User 'update' operation completed successfully.";

	private static final String TC_DELETE_USER = "Verify Delete User Functionality";
	private static final String EXP_DELETE_USER = "User deleted successfully";

	private static final String TC_PAGINATION = "Verify Pagination Functionality";
	private static final String EXP_PAGINATION = "Pagination works correctly";

	private static final String TC_VERSION = "Verify Version Functionality";
	private static final String EXP_VERSION = Constants.EXP_VERSION_TEXT;

	private static final String TC_COPYRIGHT = "Verify Copyright Functionality";
	private static final String EXP_COPYRIGHT = Constants.EXP_COPYRIGHT_TEXT;

	private ExcelUtility excelUtility;
	private UserManagementPage userManagement;
	private CommonMethods comm;
	private SoftAssert softAssert;
	private Executor executor;

	@Override
	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.userManagement = new UserManagementPage(driver, wait, comm);
		this.excelUtility = new ExcelUtility();
		this.softAssert = new SoftAssert();
		this.executor = new Executor(excelUtility, softAssert);
		excelUtility.initializeExcel(SHEET_NAME);
	}

	@Test(priority = 1)
	public void testCompanyLogo() {
		executor.executeTest(TC_LOGO, EXP_LOGO, () -> comm.verifyWebpageLogo() ? EXP_LOGO : "Logo Not Displayed");
	}

	@Test(priority = 2)
	public void testPageTitle() {
		executor.executeTest(TC_TITLE, EXP_TITLE, comm::verifyPageTitle);
	}

	@Test(priority = 3)
	public void testNavBarLink() {
		executor.executeTest(TC_NAVBAR, EXP_NAVBAR, userManagement::navBarLink);
	}

	@Test(priority = 4)
	public void testRefreshButton() {
		executor.executeTest(TC_REFRESH, EXP_REFRESH, userManagement::refreshButton);
	}

	@Test(priority = 5)
	public void testClickAddUserBtn() {
		executor.executeTest(TC_ADD_BTN, EXP_ADD_BTN, userManagement::clickAddUserBtn);
	}

	@Test(priority = 6)
	public void testAddUserProfilePicture() {
		executor.executeTest(TC_PROFILE_PIC, EXP_PROFILE_PIC, () -> {
			userManagement.addUserProfilePicture();
			return EXP_PROFILE_PIC;
		});
	}

	@Test(priority = 7)
	public void testAddNewUser() {
		executor.executeTest(TC_ADD_USER, EXP_ADD_USER, () -> {
			userManagement.addAndUpdateUser("add");
			return EXP_ADD_USER;
		});
	}

	@Test(priority = 8)
	public void testCheckDropdown() {
		executor.executeTest(TC_DROPDOWN, EXP_DROPDOWN, () -> {
			userManagement.checkDropdown();
			return EXP_DROPDOWN;
		});
	}

	@Test(priority = 9)
	public void testSearchUser() {
		executor.executeTest(TC_SEARCH_USER, EXP_SEARCH_USER, () -> {
			userManagement.searchAndViewUser();
			return EXP_SEARCH_USER;
		});
	}

	@Test(priority = 10)
	public void testViewAndUpdateUser() {
		executor.executeTest(TC_UPDATE_USER, EXP_UPDATE_USER, () -> {
			userManagement.addAndUpdateUser("update");
			return EXP_UPDATE_USER;
		});
	}

	@Test(priority = 11)
	public void testDeleteUser() {
		executor.executeTest(TC_DELETE_USER, EXP_DELETE_USER, () -> {
			String actual = userManagement.deleteUser();
			if (actual.equalsIgnoreCase(EXP_DELETE_USER) || actual.startsWith("No user found")) {
				return actual;
			}
			throw new AssertionError("Delete operation failed: " + actual);
		});
	}

	@Test(priority = 12)
	public void testPagination() {
		executor.executeTest(TC_PAGINATION, EXP_PAGINATION, () -> {
			comm.checkPagination();
			return EXP_PAGINATION;
		});
	}

	@Test(priority = 13)
	public void testVersion() {
		executor.executeTest(TC_VERSION, EXP_VERSION, comm::checkVersion);
	}

	@Test(priority = 14)
	public void testCopyright() {
		executor.executeTest(TC_COPYRIGHT, EXP_COPYRIGHT, comm::checkCopyright);
	}

	@AfterClass
	public void tearDownAssertions() {
		softAssert.assertAll();
	}
}
