package com.aepl.sam.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aepl.sam.constants.Constants;
import com.aepl.sam.locators.DeviceDetailsPageLocators;
import com.aepl.sam.utils.CommonMethods;

public class DeviceDetailsPage extends DeviceDetailsPageLocators {
	private WebDriver driver;
	private WebDriverWait wait;
	private CommonMethods comm;

	public DeviceDetailsPage(WebDriver driver, WebDriverWait wait, CommonMethods comm) {
		this.driver = driver;
		this.wait = wait;
		this.comm = comm;
	}

	public void searchAndViewDevice() {
		try {
			WebElement canvas = driver.findElement(By.tagName("canvas"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", canvas);
			WebElement serachField = driver.findElement(SEARCH_BOX_INPUT);
			Thread.sleep(2000);
			comm.highlightElement(serachField, "Green");
			serachField.clear();
			serachField.sendKeys(Constants.IMEI);
			WebElement searchButton = driver.findElement(SEARCH_BOX_BTN);
			searchButton.click();
			comm.highlightElement(serachField, "Green");
			Thread.sleep(1000); // Wait for search results to load
			WebElement eyeIcon = driver.findElement(EYE_ICON);
			eyeIcon.click();
		} catch (Exception e) {
			System.out.println("An error occurred while searching and viewing the device.");
		}
	}

	public Boolean allComponentDetails() {
		List<WebElement> listOfComponents = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ALL_COMPONENT));

		WebElement componentElement = listOfComponents.getFirst();
		String componentText = componentElement.getText();

		System.out.println("Component Text : " + componentText);

		if (componentText.contains("IMEI") && componentText.contains(Constants.IMEI))
			return true;

		return false;
	}

	public void validateExportButton() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement exportBtn = driver.findElement(EXPORT_BTN);

		js.executeScript("arguments[0].scrollIntoView(true);", exportBtn);
		comm.highlightElement(exportBtn, "Green");

		for (int i = 0; i < 3; i++) {
			if (exportBtn.isDisplayed()) {
				exportBtn.click();

				Alert alert = driver.switchTo().alert();
				alert.accept();
			}
		}
	}

	public String viewLoginPacket() {
		try {
			WebElement eyeIcon = driver.findElement(EYE_ICON);
			eyeIcon.click();

			// Wait for the frame to be present and switch to it
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("loginPacketDetails")));

			// Get the details from an element inside the frame
			List<WebElement> detailsElement = driver.findElements(By.xpath("//div[@class='component-body'][.//table]"));
			WebElement frameElement = detailsElement.getLast();// Adjust selector as needed
			String loginPacketDetails = frameElement.getText();
			System.out.println(loginPacketDetails);

			// Switch back to the default content
			driver.switchTo().defaultContent();

			return "Login packet details are displayed successfully";
		} catch (Exception e) {
			e.printStackTrace();
			driver.switchTo().defaultContent();
			return "Failed to display login packet details";
		}
	}

}
