package com.aepl.sam.locators;

import org.openqa.selenium.By;

public class DeviceDashboardPageLocators {
	// Shital
	
//	common locators
	public static final By ORG_LOGO = By.cssSelector(".header-logo img");
	public static final By PROJECT_TITLE = By.xpath("//div[contains(@class,'header-main-title')]/h6");
	public static final By REFRESHBTN = By.xpath("//app-device-dashboard//mat-icon[text()='refresh']");
		
	//	navigation bar
	public static final By DEVICE_DASHBOARD = By.cssSelector("nav ul li.active-nav-item a");
	public static final By DEVICE_DASHBOARD_BACKBTN = By.xpath("/html/body/app-root/app-device-dashboard/div/form/div[1]/div/div[1]/mat-icon");
	public static final By DEVICE_DASHBOARD_TITLE = By.xpath("//app-device-dashboard//span[contains(text(),'Device Dashboard')]");
	
		
	// KPI's
	public static final By DEVICE_DASHBOARD_TOTALPRODUCTIONDEVICESKPI = By.xpath("//*[@id=\"TotalProductionDevices\"]/div[2]/span[1]");
	public static final By DEVICE_DASHBOARD_TOTALPRODUCTIONDEVICESKPICOUNT = By.xpath("//*[@id=\"TotalProductionDevices\"]/div[2]/span[2]");
	
	public static final By DEVICE_DASHBOARD_TOTALDISPATCHEDDEVICESKPI = By.id("DispatchedDevices");
	public static final By DEVICE_DASHBOARD_PRODUCTIONDEVICETABLEHEADER = By.xpath("/html/body/app-root/app-device-dashboard/div/form/div[4]/form/div[1]/div/div/div[1]/div/h6");
	public static final By DEVICE_DASHBOARD_DISPATCHEDDEVICESTABLEHEADER  = By.xpath("/html/body/app-root/app-device-dashboard/div/form/div[4]/form/div[1]/div/div/div[1]/div/h6");
	public static final By DEVICE_DASHBOARD_SEARCHBOX = By.xpath("/html/body/app-root/app-device-dashboard/div/form/div[4]/form/div[1]/div/div/div[3]/app-common-component-search/div/div/div/input");
	public static final By DEVICE_DASHBOARD_SEARCHCLICK = By.xpath("/html/body/app-root/app-device-dashboard/div/form/div[4]/form/div[1]/div/div/div[3]/app-common-component-search/div/div/button");
	public static final By DEVICE_DASHBOARD_SEARCHCLEAR = By.xpath("/html/body/app-root/app-device-dashboard/div/form/div[4]/form/div[1]/div/div/div[3]/app-common-component-search/div/div/div/input");
	
}
