package com.kastech.ReusableFunctions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.kastech.supportlibraries.Report;
import com.kastech.supportlibraries.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Set;

import com.kastech.supportlibraries.Report.Status;
import org.springframework.beans.factory.annotation.Autowired;

public class ReusableFunctions{
	@Autowired
	Report reportObject;
    @Autowired
	Util utilObject;
    @Autowired
    Util util;

	public long timeout = 30000;

	/**
	 * Method Name: Login
	 * Description: This method is to Login to the Application
	 * Parameters: Nothing
	 * Return Type: Nothing
	 * @throws Exception
	 */
	public void Login(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws Exception{

		Thread.sleep(5000);

		if(driver.findElements(By.xpath(".//input[@data-automation-id='globalSearchInput']")).size()!=0){
			reportObject.Log("Verification of Home Page","The user logged in successfully", Status.PASS, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}
		else{
			if(driver.findElements(By.xpath(".//input[@aria-label='Username']")).size()!=0){
				reportObject.Log("Verification of Login Page","The Login page is displayed", Status.PASS, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}else{
				//Launching the URL and maximizing the window
				driver.get(utilObject.getValueFromConfigProperties("URL"));
				driver.manage().window().maximize();
				if(driver.findElement(By.id("username")).isDisplayed()){
					reportObject.Log("Verification of Login Page","The Login page is displayed", Status.PASS, driver, testCase, scenario, browser, passScreenshot, browserFolder);
				}else{
					reportObject.Log("Verification of Login Page","The Login page is not displayed", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
				}
			}
			driver.findElement(By.xpath(".//input[@aria-label='Username']")).clear();
			driver.findElement(By.xpath(".//input[@aria-label='Username']")).sendKeys(utilObject.getData("Username", driver, scenario, testCase, homePath, currentIteration, browser, passScreenshot, browserFolder));
			driver.findElement(By.xpath(".//input[@type='password']")).clear();
			driver.findElement(By.xpath(".//input[@type='password']")).sendKeys(utilObject.getData("Password", driver, scenario, testCase, homePath, currentIteration, browser, passScreenshot, browserFolder));

			driver.findElement(By.xpath(".//*[@data-automation-id='goButton']")).click();
			driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);


			if(driver.findElements(By.xpath(".//input[@data-automation-id='globalSearchInput']")).size()!=0){
				reportObject.Log("Verification of Home Page","The user logged in successfully", Status.PASS, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}else{
				reportObject.Log("Verification of Home Page","The user did not log in successfully", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}
		}
	}

	public boolean switchWindow(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws IOException {
		String title = dataValue;
	    String currentWindow = driver.getWindowHandle();
	    Set <String> availableWindows = driver.getWindowHandles();
	    if (!availableWindows.isEmpty()) {
		    for (String windowId : availableWindows) {
			    if (driver.switchTo().window(windowId).getTitle().equals(title)) {
			    	driver.switchTo().window(windowId);
			    	reportObject.Log("Switching Window", "Switched the control to the Window with the title "+title, Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			    	return true;
			    }else {
			        driver.switchTo().window(currentWindow);

			    }
		    }
	    }
	    reportObject.Log("Switching Window", "Did not find a window with the title "+title+". Hence, staying in the current window itself", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	    return false;
	    }

	/**
	 * Method Name: Logout
	 * Description: This method is to Logout of the Application
	 * Parameters: Nothing
	 * Return Type: Nothing
	 */
	public void Logout(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){

			if(driver.findElement(By.xpath("//a[contains(text(),'Log Out') and @title='Log Out']")).isDisplayed()){
				reportObject.Log("Verification of Presence of Logout Link","The Log out link is displayed", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
				/*driver.findElement(By.xpath("//a[contains(text(),'Log Out') and @title='Log Out']")).click();
				try {
					Alert alert = driver.switchTo().alert();
					alert.accept();
				} catch (Exception e) {
					// TODO Auto-generated catch block

				}
				driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);*/
				driver.navigate().to(utilObject.getValueFromConfigProperties("URL"));
				if(driver.findElements(By.id("username")).size()!=0){
					reportObject.Log("Verification of Login Page","The Login page is displayed", Status.PASS, driver, testCase, scenario, browser, passScreenshot, browserFolder);
				}
			}else{
				reportObject.Log("Verification of Presence of Logout Link","The Log out link is not displayed", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}
	}

	public void selectPhoneType(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){

		try {
			if(browser.equalsIgnoreCase("Firefox")){
				try {
					driver.findElement(By.cssSelector("#new_row_PhoneType")).click();
					driver.findElement(By.cssSelector("#new_row_PhoneType")).sendKeys(Keys.ARROW_DOWN);
					Thread.sleep(2000);
					driver.findElement(By.cssSelector("#new_row_PhoneType")).sendKeys(Keys.TAB);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				driver.findElement(By.cssSelector("#new_row_PhoneType")).click();
				Select selectBox = new Select(driver.findElement(By.cssSelector("#new_row_PhoneType")));
				selectBox.selectByVisibleText(util.getData("PhoneType", driver, scenario, testCase, homePath, currentIteration, browser, passScreenshot, browserFolder));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void selectAllMissingVerification(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){

		try {

			if(driver.findElements(By.xpath("//table[@id='grid0']/tbody/tr")).size()!=0){
				for(int i=0;i<(driver.findElements(By.xpath("//table[@id='grid0']/tbody/tr")).size()-1);i++){
					driver.findElement(By.xpath("//table[@id='grid0']/tbody/tr["+(i+2)+"]/td[9]/input[@type='checkbox']/following-sibling::label")).click();
					driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

public void writeCaseNumber(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){

	String applicationNum = "";
	String caseNumber = "";
		try {
			applicationNum = utilObject.getDataINI(utilObject.getData("SectionName", driver, scenario, testCase, homePath, currentIteration, browser, passScreenshot, browserFolder), "APPLICATION_NUMBER");
			caseNumber = applicationNum.substring(1,applicationNum.length());
			utilObject.setDataINI("CASE_NUMBER", caseNumber, testCase, scenario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}