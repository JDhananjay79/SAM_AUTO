package com.aepl.sam.tests;

import java.util.function.Supplier;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.constants.Constants;
import com.aepl.sam.constants.RoleConstants;
import com.aepl.sam.enums.Result;
import com.aepl.sam.pages.RoleManagementPage;
import com.aepl.sam.utils.CommonMethods;
import com.aepl.sam.utils.ExcelUtility;

public class RoleManagementPageTest extends TestBase implements RoleConstants {

	private ExcelUtility excelUtility;
	private RoleManagementPage userRole;
	private CommonMethods comm;
	private SoftAssert softAssert;

	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.userRole = new RoleManagementPage(driver, wait);
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
		executeTest(TC_LOGO, EXP_LOGO_DISPLAYED,
				() -> comm.verifyWebpageLogo() ? EXP_LOGO_DISPLAYED : "Logo Not Displayed");
	}

	@Test(priority = 2)
	public void testPageTitle() {
		executeTest(TC_PAGE_TITLE, EXP_PAGE_TITLE, comm::verifyPageTitle);
	}

	@Test(priority = 3)
	public void testNavBarLink() {
		executeTest(TC_NAV_BAR, Constants.ROLE_MANAGEMENT, userRole::navBarLink);
	}

	@Test(priority = 4)
	public void testBackButton() {
		executeTest(TC_BACK_BTN, EXP_BACK_NAVIGATION, () -> {
			userRole.backButton();
			return EXP_BACK_NAVIGATION;
		});
	}

	@Test(priority = 5)
	public void testRefreshButton() {
		executeTest(TC_REFRESH_BTN, EXP_REFRESH_TITLE, userRole::refreshButton);
	}

	@Test(priority = 6)
	public void testClickAddUserRole() {
		executeTest(TC_ADD_USER_ROLE, EXP_ADD_ROLE_SCREEN, userRole::clickAddUserRoleBtn);
	}

	@Test(priority = 7)
	public void testSelectingOptions() {
		executeTest(TC_SELECT_OPTIONS, EXP_SELECT_OPTIONS, () -> {
			userRole.selectingOptions();
			return EXP_SELECT_OPTIONS;
		});
	}

	@Test(priority = 8)
	public void testSelectOptionsAndSubmit() {
		executeTest(TC_SUBMIT_ROLE, EXP_SUBMIT_ROLE, () -> {
			userRole.selectOptionsAndSubmit();
			return EXP_SUBMIT_ROLE;
		});
	}

	@Test(priority = 9)
	public void testSearchUserRole() {
		executeTest(TC_SEARCH_ROLE, EXP_SEARCH_ROLE, () -> {
			userRole.searchUserRole();
			return EXP_SEARCH_ROLE;
		});
	}

	@Test(priority = 10)
	public void testUpdateUserRole() {
		executeTest(TC_UPDATE_ROLE, EXP_UPDATE_ROLE, () -> {
			userRole.updateUserRole();
			return EXP_UPDATE_ROLE;
		});
	}

	@Test(priority = 11)
	public void testDeleteUserRole() {
		executeTest(TC_DELETE_ROLE, EXP_DELETE_ROLE, () -> {
			userRole.deleteUserRole();
			return EXP_DELETE_ROLE;
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
		executeTest("Verify Version Functionality", Constants.EXP_VERSION_TEXT, comm::checkVersion);
	}

	@Test(priority = 14)
	public void testCopyright() {
		executeTest("Verify Copyright Functionality", Constants.EXP_COPYRIGHT_TEXT, comm::checkCopyright);
	}
}
