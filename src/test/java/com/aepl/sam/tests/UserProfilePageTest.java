package com.aepl.sam.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aepl.sam.base.TestBase;
import com.aepl.sam.utils.Constants;
import com.aepl.sam.pages.CommonMethods;
import com.aepl.sam.pages.UserProfilePage;
import com.aepl.sam.utils.ExcelUtility;

public class UserProfilePageTest extends TestBase {

	private static final String SHEET_NAME = "User_Profile_Test";

	private static final String TC_LOGO = "Verify Company Logo on Webpage";
	private static final String EXP_LOGO = "Logo Displayed";

	private static final String TC_PAGE_TITLE = "Verify Page Title on Webpage";
	private static final String EXP_PAGE_TITLE = "AEPL Sampark Diagnostic Cloud";

	private static final String TC_NAVBAR = "Verify Navbar Link Navigation";
	private static final String EXP_NAVBAR = Constants.USR_PROFILE;

	private static final String TC_BACK_BUTTON = "Verify Back Button Navigation";
	private static final String EXP_BACK_BUTTON = "Back button navigated successfully.";

	private static final String TC_REFRESH = "Verify Refresh Button Functionality";
	private static final String EXP_REFRESH = "Page refreshed successfully.";

	private static final String TC_CHANGE_PASSWORD = "Verify Change Password Functionality";
	private static final String EXP_CHANGE_PASSWORD = "Password changed successfully.";

	private static final String TC_UPLOAD_PROFILE_PIC = "Verify Upload Profile Picture Functionality";
	private static final String EXP_UPLOAD_PROFILE_PIC = "Profile picture uploaded successfully.";

	private static final String TC_UPDATE_PROFILE = "Verify Update Profile Details Functionality";
	private static final String EXP_UPDATE_PROFILE = "Profile updated successfully.";

	private static final String TC_PAGINATION = "Verify Pagination Functionality";
	private static final String EXP_PAGINATION = "Pagination works correctly";

	private static final String TC_VERSION = "Verify Version Functionality";
	private static final String EXP_VERSION = Constants.EXP_VERSION_TEXT;

	private static final String TC_COPYRIGHT = "Verify Copyright Functionality";
	private static final String EXP_COPYRIGHT = Constants.EXP_COPYRIGHT_TEXT;

	private static final String TC_VALIDATE_BUTTONS = "Verify Buttons on Customer Master Page";
	private static final String EXP_VALIDATE_BUTTONS = "All buttons are displayed and enabled successfully.";

	private static final String TC_USER_PROFILE_DATA = "Test the user profile data";
	private static final String EXP_USER_PROFILE_DATA = "User Verified successfully";

	private ExcelUtility excelUtility;
	private UserProfilePage userProf;
	private CommonMethods comm;
	private SoftAssert softAssert;
	private Executor executor;

	@Override
	@BeforeClass
	public void setUp() {
		super.setUp();
		this.comm = new CommonMethods(driver, wait);
		this.userProf = new UserProfilePage(driver, wait);
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
		executor.executeTest(TC_PAGE_TITLE, EXP_PAGE_TITLE, comm::verifyPageTitle);
	}

	@Test(priority = 3)
	public void testNavBarLink() {
		executor.executeTest(TC_NAVBAR, EXP_NAVBAR, userProf::navBarLink);
	}

	@Test(priority = 4)
	public void testRefreshButton() {
		executor.executeTest(TC_REFRESH, EXP_REFRESH, () -> {
			userProf.refreshButton();
			return EXP_REFRESH;
		});
	}

	@Test(priority = 5)
	public void testButtons1() {
		executor.executeTest(TC_VALIDATE_BUTTONS, EXP_VALIDATE_BUTTONS, comm::validateButtons);
	}

	@Test(priority = 6)
	public void testUploadProfilePicture() {
		executor.executeTest(TC_UPLOAD_PROFILE_PIC, EXP_UPLOAD_PROFILE_PIC, () -> {
			boolean isUploaded = userProf.uploadProfilePicture();
			return isUploaded ? EXP_UPLOAD_PROFILE_PIC : "Profile picture upload failed.";
		});
	}

	@Test(priority = 7)
	public void testUserProfileData() {
		executor.executeTest(TC_USER_PROFILE_DATA, EXP_USER_PROFILE_DATA, userProf::validateUserData);
	}

	@Test(priority = 8)
	public void testUpdateProfileDetails() {
		executor.executeTest(TC_UPDATE_PROFILE, EXP_UPDATE_PROFILE, () -> {
			boolean isUpdated = userProf.updateProfileDetails();
			return isUpdated ? EXP_UPDATE_PROFILE : "Profile update failed.";
		});
	}

	@Test(priority = 9)
	public void testChangePassword() {
		executor.executeTest(TC_CHANGE_PASSWORD, EXP_CHANGE_PASSWORD, () -> {
			userProf.changePassword();
			return EXP_CHANGE_PASSWORD;
		});
	}

	@Test(priority = 10)
	public void testVersion() {
		executor.executeTest(TC_VERSION, EXP_VERSION, comm::checkVersion);
	}

	@Test(priority = 11)
	public void testCopyright() {
		executor.executeTest(TC_COPYRIGHT, EXP_COPYRIGHT, comm::checkCopyright);
	}

	@AfterClass
	public void tearDownAssertions() {
		softAssert.assertAll();
	}
}
