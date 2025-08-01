package com.aepl.sam.tests;

import java.util.function.Supplier;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.UserManagementConstants;
import com.aepl.sam.enums.Result;
import com.aepl.sam.pages.UserManagementPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class UserManagementPageTest extends TestBase implements UserManagementConstants {

	private ExcelUtility excelUtility;
	private UserManagementPage userManagement;
	private CommonMethods comm;
	private SoftAssert softAssert;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.userManagement = new UserManagementPage(driver, wait, comm);
		this.excelUtility = new ExcelUtility();
		this.softAssert = new SoftAssert();
		excelUtility.initializeExcel(SHEET_NAME);
	}

	private void executeTest(String testCaseName, String expected, Supplier<String> actualSupplier) {
		String actual = "";
		String result = Result.FAIL.getValue();

		logger.info("Executing test case: {}", testCaseName);
		try {
			actual = actualSupplier.get();
			softAssert.assertEquals(actual, expected, testCaseName + " failed!");
			result = expected.equalsIgnoreCase(actual) ? Result.PASS.getValue() : Result.FAIL.getValue();
		} catch (Exception e) {
			logger.error("Error in test case {}: {}", testCaseName, e.getMessage(), e);
			result = Result.ERROR.getValue();
		} finally {
			excelUtility.writeTestDataToExcel(testCaseName, expected, actual, result);
			softAssert.assertAll();
		}
	}

	@Test(priority = 1)
	public void testCompanyLogo() {
		executeTest(TC_LOGO, EXP_LOGO, () -> comm.verifyWebpageLogo() ? EXP_LOGO : "Logo Not Displayed");
	}

	@Test(priority = 2)
	public void testPageTitle() {
		executeTest(TC_TITLE, EXP_TITLE, comm::verifyPageTitle);
	}

	@Test(priority = 3)
	public void testNavBarLink() {
		executeTest(TC_NAVBAR, EXP_NAVBAR, userManagement::navBarLink);
	}

	@Test(priority = 4)
	public void testRefreshButton() {
		executeTest(TC_REFRESH, EXP_REFRESH, userManagement::refreshButton);
	}

	@Test(priority = 5)
	public void testClickAddUserBtn() {
		executeTest(TC_ADD_BTN, EXP_ADD_BTN, userManagement::clickAddUserBtn);
	}

	@Test(priority = 6)
	public void testAddUserProfilePicture() {
		executeTest(TC_PROFILE_PIC, EXP_PROFILE_PIC, () -> {
			userManagement.addUserProfilePicture();
			return EXP_PROFILE_PIC;
		});
	}

	@Test(priority = 7)
	public void testAddNewUser() {
		executeTest(TC_ADD_USER, EXP_ADD_USER, () -> {
			userManagement.addAndUpdateUser("add");
			return EXP_ADD_USER;
		});
	}

	@Test(priority = 8)
	public void testCheckDropdown() {
		executeTest(TC_DROPDOWN, EXP_DROPDOWN, () -> {
			userManagement.checkDropdown();
			return EXP_DROPDOWN;
		});
	}

	@Test(priority = 9)
	public void testSearchUser() {
		executeTest(TC_SEARCH_USER, EXP_SEARCH_USER, () -> {
			userManagement.searchAndViewUser();
			return EXP_SEARCH_USER;
		});
	}

	@Test(priority = 10)
	public void testViewAndUpdateUser() {
		executeTest(TC_UPDATE_USER, EXP_UPDATE_USER, () -> {
			userManagement.addAndUpdateUser("update");
			return EXP_UPDATE_USER;
		});
	}

	@Test(priority = 11)
	public void testDeleteUser() {
		executeTest(TC_DELETE_USER, EXP_DELETE_USER, () -> {
			String actual = userManagement.deleteUser();
			if (actual.equalsIgnoreCase(EXP_DELETE_USER) || actual.startsWith("No user found")) {
				return actual;
			}
			throw new AssertionError("Delete operation failed: " + actual);
		});
	}

	@Test(priority = 12)
	public void testPagination() {
		executeTest(TC_PAGINATION, EXP_PAGINATION, () -> {
			comm.checkPagination();
			return EXP_PAGINATION;
		});
	}

	@Test(priority = 13)
	public void testVersion() {
		executeTest(TC_VERSION, EXP_VERSION, comm::checkVersion);
	}

	@Test(priority = 14)
	public void testCopyright() {
		executeTest(TC_COPYRIGHT, EXP_COPYRIGHT, comm::checkCopyright);
	}
}
