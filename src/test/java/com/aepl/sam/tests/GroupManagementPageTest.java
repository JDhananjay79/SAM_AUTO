package com.aepl.sam.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.utils.Constants;
import com.aepl.sam.pages.CommonMethods;
import com.aepl.sam.pages.GroupManagementPage;
import com.aepl.sam.utils.ExcelUtility;

public class GroupManagementPageTest extends TestBase {
	private static final String SHEET_NAME = "Group_Management_Test";

	private static final String TC_LOGO = "Verify Company Logo on Webpage";
	private static final String TC_PAGE_TITLE = "Verify Page Title on Webpage";
	private static final String TC_NAV_BAR = "Verify Navigation Bar Click Functionality";
	private static final String TC_BACK = "Verify Back Button Functionality";
	private static final String TC_REFRESH = "Verify Refresh Button Functionality";
	private static final String TC_ADD_ROLE = "Verify Add User Role Functionality";
	private static final String TC_SEARCH_ROLE = "Verify Search Role Group Functionality";
	private static final String TC_DELETE_ROLE = "Verify Delete Role Group Functionality";
	private static final String TC_PAGINATION = "Verify Pagination Functionality";
	private static final String TC_VERSION = "Verify Version Functionality";
	private static final String TC_COPYRIGHT = "Verify Copyright Functionality";

	private static final String EXP_LOGO_DISPLAYED = "Logo Displayed";
	private static final String EXP_PAGE_TITLE = "AEPL Sampark Diagnostic Cloud";
	private static final String EXP_NAV_BAR = Constants.ROLE_GROUP;
	private static final String EXP_BACK = Constants.ROLE_GROUP;
	private static final String EXP_REFRESH = Constants.ROLE_GROUP;
	private static final String EXP_ADD_ROLE = "User Role Group Added Successfully";
	private static final String EXP_SEARCH_ROLE = "Role group found";
	private static final String EXP_DELETE_ROLE = "Role group deleted successfully";
	private static final String EXP_PAGINATION = "Pagination works correctly";

	private ExcelUtility excelUtility;
	private GroupManagementPage groupManagement;
	private CommonMethods comm;
	private SoftAssert softAssert;
	private Executor executor;

	@Override
	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.groupManagement = new GroupManagementPage(driver, wait, comm);
		this.excelUtility = new ExcelUtility();
		this.softAssert = new SoftAssert();
		this.executor = new Executor(excelUtility, softAssert);
		excelUtility.initializeExcel(SHEET_NAME);
		logger.info("Setup completed for GroupManagementPageTest");
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
	public void testClickNavBar() {
		executor.executeTest(TC_NAV_BAR, EXP_NAV_BAR, groupManagement::navBarLink);
	}

	@Test(priority = 4)
	public void testBackButton() {
		executor.executeTest(TC_BACK, EXP_BACK, groupManagement::backButton);
	}

	@Test(priority = 5)
	public void testRefreshButton() {
		executor.executeTest(TC_REFRESH, EXP_REFRESH, () -> {
			comm.clickRefreshButton();
			return driver.getCurrentUrl();
		});
	}

	@Test(priority = 6)
	public void testAddUserRole() {
		executor.executeTest(TC_ADD_ROLE, EXP_ADD_ROLE, () -> {
			groupManagement.addGroup();
			return EXP_ADD_ROLE;
		});
	}

	@Test(priority = 7)
	public void testSearchgroupManagement() {
		executor.executeTest(TC_SEARCH_ROLE, EXP_SEARCH_ROLE, () -> {
			return groupManagement.isGroupManagementFound("QA") ? EXP_SEARCH_ROLE : "Role group not found";
		});
	}

	// Uncomment and enable this test if deletion functionality is required
	// @Test(priority = 8)
	public void testDeleteRoleGroup() {
		executor.executeTest(TC_DELETE_ROLE, EXP_DELETE_ROLE, groupManagement::deleteRoleGroup);
	}

	@Test(priority = 9)
	public void testPagination() {
		executor.executeTest(TC_PAGINATION, EXP_PAGINATION, () -> {
			comm.checkPagination();
			return EXP_PAGINATION;
		});
	}

	@Test(priority = 10)
	public void testVersion() {
		executor.executeTest(TC_VERSION, Constants.EXP_VERSION_TEXT, comm::checkVersion);
	}

	@Test(priority = 11)
	public void testCopyright() {
		executor.executeTest(TC_COPYRIGHT, Constants.EXP_COPYRIGHT_TEXT, comm::checkCopyright);
	}

	@AfterClass
	public void tearDownAssertions() {
		softAssert.assertAll();
	}
}
