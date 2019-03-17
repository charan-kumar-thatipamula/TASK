package com.kastech.supportlibraries;

import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



import org.openqa.selenium.Alert;
import com.kastech.supportlibraries.Report.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KeywordLibrary{
	@Autowired
	static Util utilObject_s;
	@Autowired
	Util utilObject;
	@Autowired
    Report reportObject;
    @Autowired
	Report report;

	public long timeout = 3000;

	/**
	 * Author: Pradeep Bura
	 * Method Name: findElementByType
	 * Return Type: WebElement
	 * Description: This method finds the element based on the type of identifier given in the object ID preceding the actual object ID and returns a Web element
	 */
	public WebElement findElementByType(WebDriver driver, String scenario, String testCase, String homePath, int currentIteration, String objectID, String browser, String passScreenshot, String browserFolder){
        WebElement object = null;
	    String identifier,actualObjectID = "";
		identifier = objectID.split(":")[0].trim();
		if(objectID.split(":").length>2){
			for(int z=1;z<(objectID.split(":").length);z++){
				if(z!=1){
					actualObjectID = actualObjectID+":"+objectID.split(":")[z].trim();
				}else if (z==1){
					actualObjectID = actualObjectID+objectID.split(":")[z].trim();
				}
			}
		}else{
			actualObjectID = objectID.split(":")[1].trim();
		}

		if(actualObjectID.contains("#getData=")){
			String[] splittedString = actualObjectID.split("#");
			String finalObjectID = "";
			for(int i=0;i<splittedString.length;i++){
				if(splittedString[i].startsWith("getData=")){
					try {
						splittedString[i] = utilObject.getData((splittedString[i]).split("getData=")[1], driver, scenario, testCase, homePath, currentIteration, browser, passScreenshot, browserFolder);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				finalObjectID = finalObjectID+splittedString[i];
			}
			actualObjectID = finalObjectID;
		}

		if(identifier.equalsIgnoreCase("id")){
			object = driver.findElement(By.id(actualObjectID));
		}else if(identifier.equalsIgnoreCase("xpath")){
			object = driver.findElement(By.xpath(actualObjectID));
		}else if(identifier.equalsIgnoreCase("className")){
			object = driver.findElement(By.className(actualObjectID));
		}else if(identifier.equalsIgnoreCase("name")){
			object = driver.findElement(By.name(actualObjectID));
		}else if(identifier.equalsIgnoreCase("css")){
			object = driver.findElement(By.cssSelector(actualObjectID));
		}else if(identifier.equalsIgnoreCase("linkText")){
			object = driver.findElement(By.linkText(actualObjectID));
		}else if(identifier.equalsIgnoreCase("partialLinkText")){
			object = driver.findElement(By.partialLinkText(actualObjectID));
		}
		return object;
	}

	/**
	 * Author: Pradeep Bura
	 * Method Name: findElementsByType
	 * Return Type: List WebElement
	 * Description: This method finds the elements based on the type of identifier given in the object ID preceding the actual object ID and returns a list of Web element
	 */
	public static List findElementsByType(WebDriver driver, String scenario, String testCase, String homePath, int currentIteration, String objectID, String browser, String passScreenshot, String browserFolder){
		List<WebElement> object = null;
		String identifier,actualObjectID = "";
		identifier = objectID.split(":")[0].trim();
		if(objectID.split(":").length>2){
			for(int z=1;z<(objectID.split(":").length);z++){
				if(z!=1){
					actualObjectID = actualObjectID+":"+objectID.split(":")[z].trim();
				}else if (z==1){
					actualObjectID = actualObjectID+objectID.split(":")[z].trim();
				}
			}
		}else{
			actualObjectID = objectID.split(":")[1].trim();
		}

		if(actualObjectID.contains("#getData=")){
			String[] splittedString = actualObjectID.split("#");
			String finalObjectID = "";
			for(int i=0;i<splittedString.length;i++){
				if(splittedString[i].startsWith("getData=")){
					try {
						splittedString[i] = utilObject_s.getData((splittedString[i]).split("getData=")[1], driver, scenario, testCase, homePath, currentIteration, browser, passScreenshot, browserFolder);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				finalObjectID = finalObjectID+splittedString[i];
			}
			actualObjectID = finalObjectID;
		}

		if(identifier.equalsIgnoreCase("id")){
			object = driver.findElements(By.id(actualObjectID));
		}else if(identifier.equalsIgnoreCase("xpath")){
			object = driver.findElements(By.xpath(actualObjectID));
		}else if(identifier.equalsIgnoreCase("className")){
			object = driver.findElements(By.className(actualObjectID));
		}else if(identifier.equalsIgnoreCase("name")){
			object = driver.findElements(By.name(actualObjectID));
		}else if(identifier.equalsIgnoreCase("css")){
			object = driver.findElements(By.cssSelector(actualObjectID));
		}else if(identifier.equalsIgnoreCase("linkText")){
			object = driver.findElements(By.linkText(actualObjectID));
		}else if(identifier.equalsIgnoreCase("partialLinkText")){
			object = driver.findElements(By.partialLinkText(actualObjectID));
		}
		return object;
	}

	/**
	 * Author: Pradeep Bura
	 * Method Name: enter_text
	 * Return Type: Nothing
	 * Description: This method enters the text specified in KeyInData column of Test case
	 * @throws InterruptedException
	 */
	public void enter_text(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws InterruptedException{
		System.out.println("\n\n****** homePath: " + homePath + "; testCase: " +  testCase + "; scenario: " + scenario + "; browser: " + browser + "; objectId: " + objectId + "; objectName: " + objectName + "; dataValue: " + dataValue + "; onPassLog: " + onPassLog + "; onFailLog: " + onFailLog + "; driver: " + driver + "; passScreenshot: " + passScreenshot + "; currentIteration: " + currentIteration + "; error: " + error + "; browserFolder: " + browserFolder + "*******\n\n");
		System.out.println("---  START OF TESTCASE ----");
		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).clear();
		Thread.sleep(100);
		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).sendKeys(dataValue);

		reportObject.Log("Entering text in the text box "+objectName, "Entered the text "+dataValue, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		System.out.println("---  END OF  ----");
	}

	/**
	 * Author: Pradeep Bura
	 * Method Name: search_text
	 * Return Type: Nothing
	 * Description: This method will type(and clicks ENTER) the text specified in KeyInData column of Test case
	 */
	public void search_text(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		System.out.println("---  START OF TESTCASE ----");
		Actions action1= new Actions(driver);

		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).sendKeys(dataValue,Keys.ENTER);

		System.out.println("---  END OF  ----");
	}


	/**
	 * Method Name: Login
	 * Description: This method is to Login to the Application
	 * Parameters: Nothing
	 * Return Type: Nothing
	 * @throws Exception
	 */
	public void Login(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws Exception{

		/*if(driver.findElement(By.xpath(".//input[@data-automation-id='globalSearchInput']")).isDisplayed()){
			reportObject.Log("Verification of Home Page","The user logged in successfully", Status.PASS, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}
		else{
			if(driver.findElement(By.xpath(".//input[@aria-label='Username']")).isDisplayed()){
				reportObject.Log("Verification of Login Page","The Login page is displayed", Status.PASS, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}else{
				//Launching the URL and maximizing the window
				driver.get(utilObject.getValueFromConfigProperties("URL"));
				driver.manage().window().maximize();
				if(driver.findElement(By.xpath(".//input[@aria-label='Username']")).isDisplayed()){
					reportObject.Log("Verification of Login Page","The Login page is displayed", Status.PASS, driver, testCase, scenario, browser, passScreenshot, browserFolder);
				}else{
					reportObject.Log("Verification of Login Page","The Login page is not displayed", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
				}
			}*/
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
	/*}*/

	/**
	 * Method Name: Logout
	 * Description: This method is to Logout of the Application
	 * Parameters: Nothing
	 * Return Type: Nothing
	 */
	public void Logout(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){

		/*	if(driver.findElement(By.xpath("//a[contains(text(),'Log Out') and @title='Log Out']")).isDisplayed()){
				reportObject.Log("Verification of Presence of Logout Link","The Log out link is displayed", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
				driver.navigate().to(utilObject.getValueFromConfigProperties("URL"));
				if(driver.findElements(By.id("username")).size()!=0){
					reportObject.Log("Verification of Login Page","The Login page is displayed", Status.PASS, driver, testCase, scenario, browser, passScreenshot, browserFolder);
				}
			}else{
				reportObject.Log("Verification of Presence of Logout Link","The Log out link is not displayed", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}*/
		driver.findElement(By.xpath(".//button[@data-automation-id='Current_User']/div")).click();
		try {
			waitForXPath(homePath,testCase,scenario,browser,"XPATH:.//button[text()='Sign Out']",objectName,dataValue,onPassLog,onFailLog,driver,passScreenshot,currentIteration,error,browserFolder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath(".//button[text()='Sign Out']")).click();
		reportObject.Log("Logged Out of the session "+objectName, "Logged Out of the session "+dataValue, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}

	/**
	 * Method Name: CloseBrowser
	 * Description: This method is to Closes the Browser
	 * Parameters: Nothing
	 * Return Type: Nothing
	 */
	public void CloseBrowser(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		timeout = 5;
		try {
			waitForXPath(homePath,testCase,scenario,browser,"XPATH:.//input[@aria-label='Username']",objectName,dataValue,onPassLog,onFailLog,driver,passScreenshot,currentIteration,error,browserFolder);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);*/
		reportObject.Log("Closing Browser "+objectName, "closed browser "+dataValue, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);

		driver.close();
	}


	/**
	 * Author: Pradeep Bura
	 * Method Name: mouse_hover
	 * Return Type: Nothing
	 * Description: This method will type(and clicks ENTER) the text specified in KeyInData column of Test case
	 */
	public void mouse_hover(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		System.out.println("---  START OF TESTCASE ----");
		Actions action1= new Actions(driver);

		WebElement hoverElement = findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder);
		action1.moveToElement(hoverElement).perform();
		System.out.println("---  END OF  ----");
	}

	/**
	 * Author: Pradeep Bura
	 * Method Name: enter_number
	 * Return Type: Nothing
	 * Description: This method clears the numeric field and enter the number specified in the Object Repository and referred in the Object ID column
	 */
	public void enter_number(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){

		Actions action1= new Actions(driver);
		WebElement element = findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder);
		element.click();
		action1.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(dataValue).build().perform();
		reportObject.Log("Entering the number in "+objectName, "Entered the number", Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}

	/**
	 * Author: Pradeep Bura
	 * Method Name: type_date
	 * Return Type: Nothing
	 * Description: This method enters the Date specified in KeyInData column of Test case
	 * @throws InterruptedException
	 */
	public void type_date(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws InterruptedException{
		System.out.println("---  START OF TESTCASE ----");
		String month  = dataValue.split("/")[0];
		System.out.println("--- Month ----" + month.charAt(1));
		String day = dataValue.split("/")[1];
		System.out.println("--- Day ----" + day.charAt(1));
		String year = dataValue.split("/")[2];
		System.out.println("--- Year ----" + year);
		WebElement monthfield = findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder);
		WebElement dayfield = findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId.replace("Month", "Day"), browser, passScreenshot, browserFolder);
		WebElement yearfield = findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId.replace("Month", "Year"), browser, passScreenshot, browserFolder);
		Actions action1= new Actions(driver);
		action1.sendKeys(monthfield, month).perform();
		action1.sendKeys(dayfield, day).perform();
		action1.sendKeys(yearfield, year).perform();
		System.out.println("---  END OF  ----");
	}

	/**
	 * Author: Pradeep Bura
	 * Method Name: enter_text
	 * Return Type: Nothing
	 * Description: This method enters the text specified in KeyInData column of Test case
	 */
	public void mouse_over(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){

		Actions action1= new Actions(driver);
		action1.moveToElement(findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder)).build().perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reportObject.Log("Performing Mouse Hover action  "+objectName, "Mouse Hover Action done "+dataValue, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);

	}


	/**
	 * Author: Pradeep Bura
	 * Method Name: confirm_Message
	 * Return Type: Nothing
	 * Description: This method enters the text specified in KeyInData column of Test case
	 */
	public void confirm_Message(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		try{
			Alert alert = driver.switchTo().alert();
			alert.accept();
			reportObject.Log("Confirm Delete Message "+objectName, "Delete Message Confirmed ", Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}catch(Exception e){

		}

	}

	/**
	 * Author: Pradeep Bura
	 * Method Name: confirm_Message
	 * Return Type: Nothing
	 * Description: This method enters the text specified in KeyInData column of Test case
	 */
	public void cancel_Message(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		try{
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
			reportObject.Log("Cancel Alert Message "+objectName, "Alert Message Cancelled", Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}catch(Exception e){

		}

	}

	/**
	 * Author: Pradeep Bura
	 * Method Name: selectByVisibleText
	 * Return Type: Nothing
	 * Description: This method selects the visible text specified in KeyInData column of Test case in a select box
	 */
	public void selectByVisibleText(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Select selectBox = new Select(findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder));
		selectBox.selectByVisibleText(dataValue);
		reportObject.Log("Selecting by visible text in the dropdown "+objectName, "Selected the value "+dataValue+" in the select box ", Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		if(objectName.equalsIgnoreCase("PENDING_CASE_TASKS_RETAG_QUEUE")){
			System.out.println("hi");
		}
	}


	/**
	 * Author: Pradeep Bura
	 * Method Name: selectByIndex
	 * Return Type: Nothing
	 * Description: This method selects the visible text specified in KeyInData column of Test case in a select box
	 */
	public void selectByIndex(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Select selectBox = new Select(findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder));
		selectBox.selectByIndex((int)Float.parseFloat(dataValue));
		reportObject.Log("Selecting by index in the dropdown "+objectName, "Selected the index "+(int)Float.parseFloat(dataValue)+" in the select box", Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}

	/**
	 * Author: Pradeep Bura
	 * Method Name: selectByValue
	 * Return Type: Nothing
	 * Description: This method selects the visible text specified in KeyInData column of Test case in a select box
	 */
	public  void selectByValue(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Select selectBox = new Select(findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder));
		selectBox.selectByValue(dataValue);
		reportObject.Log("Selecting by value in the dropdown "+objectName, "Selected the value "+dataValue+" in the select box", Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}


	/**
	 * Author: Pradeep Bura
	 * Method Name: selectByoption
	 * Return Type: Nothing
	 * Description: This method clicks the option specified in KeyInData column of Test case in a select box
	 */
	public  void selectByoption(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Select selectBox = new Select(findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder));

		List<WebElement> options = selectBox.getOptions();
		for (WebElement option : options) {
		    if (option.getText().toString().equalsIgnoreCase(dataValue)) {
		    	option.click();
		    }
		}
		reportObject.Log("Selecting by option in the dropdown "+objectName, "Selected the option "+dataValue+" in the select box", Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}
	/**
	 * Author: Pradeep Bura
	 * Method Name: click
	 * Return Type: Nothing
	 * Description: This method clicks on an object specified in the Object Repository and referred in the Object ID column
	 */
	public  void click(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		if(objectName.equalsIgnoreCase("PENDING_CORRESPONDENCE_PREVIEW_BUTTON")){
			System.out.println("hi");
		}
		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).click();
		reportObject.Log("Clicking on the Element "+objectName, "Clicked on the object", Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}

	/**
	 * Author: Pradeep Bura
	 * Method Name: scrollDown
	 * Return Type: Nothing
	 * Description: This method clicks on an object specified in the Object Repository and referred in the Object ID column
	 */
	public  void scrollDown(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		//Creating JavaScriptExecuter Interface
	      JavascriptExecutor js = (JavascriptExecutor)driver;
	      String script = "window.scrollBy(0,"+Integer.valueOf(dataValue)+")";
		  js.executeScript(script);
	}

	/**Author:Pradeep Bura */
	public  void Newclick(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		if(objectName.equalsIgnoreCase("PENDING_CORRESPONDENCE_PREVIEW_BUTTON")){
			System.out.println("hi");
		}
		System.out.println("New Click CSS");
		driver.findElement(By.cssSelector("div.GDA-KBQBDQH.GDA-KBQBIPH > div.gwt-Label.GDA-KBQBEQH ")).click();
		reportObject.Log("Clicking on the Element "+objectName, "Clicked on the object", Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}
	/* Function for testing workday POC */
	public  void Hardcodedclick(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		if(objectName.equalsIgnoreCase("PENDING_CORRESPONDENCE_PREVIEW_BUTTON")){
			System.out.println("hi");
		}
		System.out.println("New Click CSS");

		driver.findElement(By.cssSelector("div.GDA-KBQBONL.GDA-KBQBPNL > div.gwt-Label.GDA-KBQBFOL ")).click();
		reportObject.Log("Clicking on the Element "+objectName, "Clicked on the object", Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}

	public  void Click_customXpath(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		if(objectName.equalsIgnoreCase("PENDING_CORRESPONDENCE_PREVIEW_BUTTON")){
			System.out.println("hi");
		}
		System.out.println("New Click CSS");
		driver.findElement(By.xpath("//div[2]/div/ul/li[2]/div[2]/div/span/i")).click();
		reportObject.Log("Clicking on the Element "+objectName, "Clicked on the object", Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}

	public  void Click_customXpathOkbutton(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		if(objectName.equalsIgnoreCase("PENDING_CORRESPONDENCE_PREVIEW_BUTTON")){
			System.out.println("hi");
		}
		System.out.println("New Click CSS");
		driver.findElement(By.xpath("html/body/div[4]/div[1]/div[1]/button[1]")).click();
		reportObject.Log("Clicking on the Element "+objectName, "Clicked on the object", Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}

	public  void NewclickID(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		if(objectName.equalsIgnoreCase("PENDING_CORRESPONDENCE_PREVIEW_BUTTON")){
			System.out.println("hi");
		}
		System.out.println("New Click id");
		driver.findElement(By.id("promptOption")).click();
		reportObject.Log("Clicking on the Element "+objectName, "Clicked on the object", Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}

	/**
	 * Author: Pradeep Bura
	 * Method Name: SwitchFocusToFrame
	 * Return Type: None
	 * Description: This method sets focus to a frame in order to click/select objects inside the frame
	 */
	public void SwitchFocusToFrame(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		driver.switchTo().frame(driver.findElement(By.id("WorkdayApp")));
		/*driver.findElement(By.xpath("html/body/div[1]/div/div/div/table/tbody/tr/td/form/div/div[3]/div/a")).click();
		driver.switchTo().defaultContent();*/
		reportObject.Log("Selecting frame to access objects in it "+objectName, "Selected Frame "+dataValue, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}

	/**
	 * Author: Pradeep Bura
	 * Method Name: SwitchFocusToDefault
	 * Return Type: None
	 * Description: This method switches focus from a frame back to the main window
	 */
	public void SwitchFocusToDefault(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		driver.switchTo().defaultContent();
		reportObject.Log("Switching focus from frame to main window "+objectName, "Switched focus to main window "+dataValue, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}



	/**
	 * Author: Pradeep Bura
	 * Method Name: sendkey
	 * Return Type: Nothing
	 * Description: This method clicks on an object specified in the Object Repository and referred in the Object ID column
	 */
	public void sendkey(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).sendKeys("");
		reportObject.Log("Clicking on the Element "+objectName, "Clicked on the object", Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}



	/**
	 * Author: Pradeep Bura
	 * Method Name: doubleClick
	 * Return Type: Nothing
	 * Description: This method double clicks on an object specified in the Object Repository and referred in the Object ID column
	 */
	public void doubleClick(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Actions ac1 = new Actions(driver);
		ac1.doubleClick(findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder)).perform();
		reportObject.Log("Double Clicking on the Element "+objectName, "Double clicked on the object", Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}


	/**
	 * Author: Pradeep Bura
	 * Method Name: clickByLinkText
	 * Return Type: Nothing
	 * Description: This method clicks on an object using the text of the link in the entire page
	 */
	public void clickByLinkText(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		driver.findElement(By.linkText(dataValue)).click();
		reportObject.Log("Clicking on the link with text "+dataValue, "Clicked on a link having text "+dataValue, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}

	/**
	 * Author: Pradeep Bura
	 * Method Name: waitForPageToLoad
	 * Return Type: Nothing
	 * Description: This method waits for the page to load by waiting for an implicit wait period
	 */
	public void waitForPageToLoad(String homePath, String testCase, String scenario, String browser,String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		timeout = 60000;
		driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);
	}

	/**
	 * Author: Pradeep Bura
	 * Method Name: waitForXPath
	 * Return Type: Nothing
	 * Description: This method waits for the object to appear in the application by waiting for a certain period of time
	 */
	public void waitForXPath(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws Exception{
		try {
			Thread.sleep(1000);
			int count=1;
			while(findElementsByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).size()==0){
				count++;
				Thread.sleep(2000);
				if(count==5) {
					throw new Exception("Unable to find xpath for testcase: " + testCase + "; currentIteration value: " + currentIteration.intValue() + "; objectId" + objectId);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw e;
		}
	}

	/**
	 * Author: Pradeep Bura
	 * Method Name: waitForLinkText
	 * Return Type: Nothing
	 * Description: This method waits for the link with the given text to appear
	 */
	public void waitForLinkText(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		try {
			int count=1;
			while(driver.findElements(By.linkText(dataValue)).size()==0){
				count++;
				Thread.sleep(2000);
				if(count==2)
					break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Author: Pradeep Bura
	 * Method Name: delay
	 * Return Type: Nothings
	 * Description: This method inserts a sleep time of timeout value specified in the WebDriverHelper class
	 */
	public void delay(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		try {
			Thread.sleep(Long.parseLong(Integer.toString(((int)Float.parseFloat(dataValue)))));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Author: Pradeep Bura
	 * Method Name: WriteDataToOutputFile
	 * Return Type: Nothing
	 * Description: This method writes data to Output INI file
	 */
	public void WriteDataToOutputFile(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		String columnValue = dataValue;
		String data = columnValue.split(";")[0].trim();
		String key = columnValue.split(";")[1].trim();

		if(data.startsWith("getData=")){
			try {
				data = utilObject.getData(data.split("getData=")[1].trim(), driver, scenario, testCase, homePath, currentIteration, browser, passScreenshot, browserFolder);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			utilObject.setDataINI(key, data, testCase, scenario);
		}else if(data.startsWith("ObjectText")){
			try {
				data = findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).getText().toString();
			System.out.println("The app no" +data);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			utilObject.setDataINI(key, data, testCase, scenario);
		} else{
			utilObject.setDataINI(key, data, testCase, scenario);
		}
	}

	/**
	 * Author: Pradeep Bura
	 * Method Name: highlight
	 * Return Type: Nothing
	 * Description: This method highlight an object specified in the Object Repository and referred in the Object ID column
	 */
	public void highlight(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws InterruptedException{

	//Creating JavaScriptExecuter Interface
      JavascriptExecutor js = (JavascriptExecutor)driver;

      // Starting loop to highlight and then de-highlight the web element
	   for (int iCnt = 0; iCnt < 2; iCnt++) {
		   //Execute Javascript
		   if(iCnt == 0){
			   js.executeScript("arguments[0].setAttribute('style', arguments[1]);", findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder), "color: yellow; border: 10px solid yellow;");
			   reportObject.Log("Highilighting the Element "+objectName, "Highlighted successfully", Status.highlight, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			   Thread.sleep(3000);
		   }else{
			   js.executeScript("arguments[0].setAttribute('style', arguments[1]);", findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder), "");
		   }
       }
	}

	/**
	 * Author: Pradeep Bura
	 * Method Name: captureScreenshot
	 * Return Type: Nothing
	 * Description: This method captures screenshot of the page
	 */
	public void captureScreenshot(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder) throws InterruptedException{
	   reportObject.Log("Capturing screenshot", "Captured screenshot", Status.done, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}

	//------------------------Verifying keywords

	/**
	 * Author: Pradeep Bura
	 * Method Name: verifyElementPresent
	 * Return Type: Nothing
	 * Description: This method verifies for the existence of an object
	 */
	public void verifyElementPresent(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		if(findElementsByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).size()!=0){
			if(!onPassLog.equalsIgnoreCase("")){
				reportObject.Log("Verifying the Presence of Element "+objectName, onPassLog, Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}else{
				reportObject.Log("Verifying the Presence of Element "+objectName, "The Element "+objectName+" is present", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}
		}else{
			if(!onFailLog.equalsIgnoreCase("")){
				reportObject.Log("Verifying the Presence of Element "+objectName, onFailLog, Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}else{
				reportObject.Log("Verifying the Presence of Element "+objectName, "The Element "+objectName+" is  not present", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}
		}
	}

	/**
	 * Author: Pradeep Bura
	 * Method Name: verifyElementNotPresent
	 * Return Type: Nothing
	 * Description: This method verifies for the non-existence of an object
	 */
	public void verifyElementNotPresent(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		if(findElementsByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).size()==0){
			if(!onPassLog.equalsIgnoreCase("")){
				reportObject.Log("Verifying that "+objectName+" Element is not present", onPassLog, Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}else{
				reportObject.Log("Verifying that "+objectName+" Element is not present", "The Element "+objectName+" is not present", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}

		}else{
			if(!onFailLog.equalsIgnoreCase("")){
				reportObject.Log("Verifying that "+objectName+" Element is not present", onFailLog, Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}else{
				reportObject.Log("Verifying that "+objectName+" Element is not present", "The Element "+objectName+" is  present", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}
		}
	}

	/**
	 * Author: Pradeep Bura
	 * Method Name: verifyElementText
	 * Return Type: Nothing
	 * Description: This method verifies for the text of the element
	 */
	public void verifyElementText(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		if(findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).getText().equals(dataValue)){
			reportObject.Log("Verifying the Text of the object "+objectName, "The element "+objectName+" has the text "+dataValue, Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}else{
			reportObject.Log("Verifying the Text of the object "+objectName, "The element "+objectName+" does not have the text "+dataValue, Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}
	}

	/**
	 * Author: Pradeep Bura
	 * Method Name: verifyElementDisabled
	 * Return Type: Nothing
	 * Description: This method verifies for the editable state of the object
	 */
	public void verifyElementDisabled(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		if(findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).getAttribute("disabled").equals("true")){
			reportObject.Log("Verifying the State of the object "+objectName, "The element "+objectName+" is in disabled state", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}else{
			reportObject.Log("Verifying the State of the object "+objectName, "The element "+objectName+" is not in disabled state", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}
	}


	/**
	 * Author: Pradeep Bura
	 * Method Name: verifyCheckBoxChecked
	 * Return Type: Nothing
	 * Description: This method verifies whether a check box is checked
	 */
	public void verifyCheckBoxChecked(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		if(findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).isSelected()){
			reportObject.Log("Verifying the State of the checkbox "+objectName, "The checkbox "+objectName+" is checked", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}else{
			reportObject.Log("Verifying the State of the checkbox "+objectName, "The checkbox "+objectName+" is not checked", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}
	}

	/**
	 * Method Name: CurrentMonth
	 * Return Type: Nothing
	 * Description: This method is to fetch the current month
	 */
	public void CurrentMonth(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
	{
		Calendar rightNow = Calendar.getInstance();
		java.text.SimpleDateFormat df1 = new java.text.SimpleDateFormat("MM");
		dataValue=(df1.format(rightNow.getTime()));

		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).clear();
		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).sendKeys(dataValue);

		reportObject.Log("Entering text in the text box "+objectName, "Entered the text "+dataValue, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);

	}

	/**
	 * Method Name: CurrentYear
	 * Return Type: Nothing
	 * Description: This method is to fetch the current Year
	 */
	public void CurrentYear(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
	{
		Calendar rightNow = Calendar.getInstance();
		java.text.SimpleDateFormat df2 = new java.text.SimpleDateFormat("YYYY");
		dataValue=df2.format(rightNow.getTime());

			findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).clear();
			findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).sendKeys(dataValue);


			reportObject.Log("Entering text in the text box "+objectName, "Entered the text "+dataValue, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}
	public void genRandom(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		Random rand=new Random();
		int randomNum = rand.nextInt((9999 - 1000) + 1) + 1000;
		dataValue=String.valueOf(randomNum);
		System.out.println(dataValue);

		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).clear();
		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).sendKeys(dataValue);

		utilObject.setDataINI("RANDOM_SSN", dataValue, testCase, scenario);

		//WriteDataToOutputFile(homePath, testCase, scenario, browser, objectId, objectName, dataValue, onPassLog, onFailLog, driver, passScreenshot, currentIteration, error, browserFolder);
		reportObject.Log("Entering text in the text box "+objectName, "Entered the text "+dataValue, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}
	/**

	 * Method Name: checkbox_value
	 * Return Type: Nothing
	 * Description: This method checks the checkbox value
	 */
	public void checkbox_value(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		try{
			findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).click();
			reportObject.Log("selecting checkbox "+objectName, "The checkbox "+objectName+" is checked", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}

	catch(Exception e){

		reportObject.Log("Verifying the State of the checkbox "+objectName, "The checkbox "+objectName+" is not checked", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}
		}
	/**

	 * Method Name: verifyDisplayedPage
	 * Return Type: Nothing
	 * Description: This method registers the title of the page and is a pass if it matches the expected
	 */
	public void verifyDisplayedPage(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
		try{
			if((driver.getPageSource().contains(objectName)) || (driver.getTitle().contains(dataValue))){
				reportObject.Log("Verifying the page that is opened", "The page "+objectName+" has successfully opened", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}
			else{
				 reportObject.Log("Verifying the page that is opened", "The page "+objectName+" has not opened", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}
		}catch(Exception e){
			reportObject.Log("Verifying the page that is opened", ""+objectName+" exxception block", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}
	}

	/**

	 * Method Name: ReadDataFromOutputFile
	 * Return Type: Nothing
	 * Description: This method reads the value from the output file and puts it in the data field
	 */
	public void ReadDataFromOutputFile(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){

		String columnValue = dataValue;
		String sectionName = columnValue.split(";")[0].trim();
		String key = columnValue.split(";")[1].trim();

		dataValue=utilObject.getDataINI(sectionName, key);

		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).clear();
		findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).sendKeys(dataValue);
	}


	/**

	 * Method Name: pickFromDatePicker
	 * Return Type: Nothing
	 * Description: This method reads the value from the output file and puts it in the data field
	 */
	public void pickFromDatePicker(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){

		findElementByType(driver, scenario, testCase, homePath, currentIteration, objectId, browser, passScreenshot, browserFolder).click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		WebElement dateWidget = driver.findElement(By.xpath(objectId));
		List<WebElement> columns=dateWidget.findElements(By.tagName("td"));

		for (WebElement cell:columns){
			if (cell.getText().equals(dataValue)){
			cell.findElement(By.linkText(dataValue)).click();
			break;
			}
		}
	}


	/**
	 * Method Name: pickPreviousDayFromDatePicker
	 * Return Type: Nothing
	 * Description: This method reads the value from the output file and puts it in the data field
	 */
	public void pickPreviousDayFromDatePicker(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){

		findElementByType(driver, scenario, testCase, homePath, currentIteration, objectId, browser, passScreenshot, browserFolder).click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		WebElement dateWidget = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));

		List<WebElement> columns=dateWidget.findElements(By.tagName("td"));

		String date = "";
		Calendar rightNow = Calendar.getInstance();
		java.text.SimpleDateFormat df1 = new java.text.SimpleDateFormat("dd");
		date=(df1.format(rightNow.getTime()));
		if(!(date.equalsIgnoreCase("01") || date.equalsIgnoreCase("1"))){
			date = String.valueOf(Integer.parseInt(date)-1);

			for (WebElement cell:columns){
				if (cell.getText().equals(date)){
				cell.findElement(By.linkText(date)).click();
				break;
				}
			}
		}else if(date.equalsIgnoreCase("01") || date.equalsIgnoreCase("1")){
			driver.findElement(By.xpath("//div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-all']/a[@title='Prev']/span[@class='ui-icon ui-icon-circle-triangle-w']")).click();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			columns=dateWidget.findElements(By.tagName("td"));

			for (WebElement cell:columns){
				if (cell.getText().equals("31")){
					cell.findElement(By.linkText("31")).click();
					break;
				}else{
					if (cell.getText().equals("30")){
						cell.findElement(By.linkText("30")).click();
						break;
					}
				}
			}

		}

	}

/**

	 * Method Name: selectCurrentDateInCustomMonth
	 * Return Type: Nothing
	 * Description: This method selects current date from custom month (difference specified in Test Data column)
	 */
	public void selectCurrentDateInCustomMonth(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){

		findElementByType(driver, scenario, testCase, homePath, currentIteration, objectId, browser, passScreenshot, browserFolder).click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		int number = Integer.parseInt(dataValue);

		if(number<0){
			for(int i=number;i<0;i++){
				driver.findElement(By.xpath("//div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-all']/a[@title='Prev']/span[@class='ui-icon ui-icon-circle-triangle-w']")).click();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if(number>0){
			for(int i=0;i<number;i++){
				driver.findElement(By.xpath("//div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-all']/a[@title='Next']/span[@class='ui-icon ui-icon-circle-triangle-e']")).click();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		WebElement dateWidget = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));

		List<WebElement> columns=dateWidget.findElements(By.tagName("td"));

		String date = "";
		Calendar rightNow = Calendar.getInstance();
		java.text.SimpleDateFormat df1 = new java.text.SimpleDateFormat("dd");
		date=(df1.format(rightNow.getTime()));

		for (WebElement cell:columns){
			if (cell.getText().equals(date)){
			cell.findElement(By.linkText(date)).click();
			break;
			}
		}
	}

	public void verifySelectedValue(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){

		   Select selectBox = new Select(findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder));
	      if(selectBox.getFirstSelectedOption().getText().equalsIgnoreCase(dataValue)){
	            if(!onPassLog.equalsIgnoreCase("")){
	                  report.Log("Verifying the selected value in the dropdown "+objectName, onPassLog, Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	            }else{
	                  report.Log("Verifying the selected value in the dropdown "+objectName, "The dropdown "+objectName+" has the value "+dataValue+" selected", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	            }
	      }else{
	            if(!onFailLog.equalsIgnoreCase("")){
	                  report.Log("Verifying the selected value in the dropdown "+objectName, onFailLog, Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	            }else{
	                  report.Log("Verifying the selected value in the dropdown "+objectName, "The dropdown "+objectName+" does not have the value "+dataValue+" selected", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	            }
	      }
	}

	public void verifyWindowDisplayed(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){

		  String currentWindow = driver.getWindowHandle();

		  driver.findElement(By.xpath("//button[@id='button_Preview']")).click();

		  WebDriverWait wait = new WebDriverWait(driver, 15, 100);

	 	  Set <String> availableWindows = driver.getWindowHandles();
	 	  boolean flag = false;

	 	  if (!availableWindows.isEmpty()) {
	 		    for (String windowId : availableWindows) {
	 			    if (driver.switchTo().window(windowId).getTitle().equalsIgnoreCase(dataValue)) {
	 			    	if(!onPassLog.equalsIgnoreCase("")){
			                  report.Log("Verifying the selected value in the dropdown "+objectName, onPassLog, Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			            }else{
			                  report.Log("Verifying the selected value in the dropdown "+objectName, "The dropdown "+objectName+" has the value "+dataValue+" selected", Status.pass, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			            }
	 			    	flag=true;
	 			    	driver.close();
	 			    	break;
				    }else{
				        flag = false;
				    }
	 		    }
	 		    if(!flag){
	 		    	if(!onFailLog.equalsIgnoreCase("")){
			              report.Log("Verifying the selected value in the dropdown "+objectName, onFailLog, Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			        }else{
			              report.Log("Verifying the selected value in the dropdown "+objectName, "The dropdown "+objectName+" does not have the value "+dataValue+" selected", Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			        }
	 		    }
   	 	  }

	 	  driver.switchTo().window(currentWindow);
	}

	public static ExpectedCondition<Boolean> numberOfWindowsToBe(final int numberOfWindows) {
	    return new ExpectedCondition<Boolean>() {
	      @Override
	      public Boolean apply(WebDriver driver) {
	                driver.getWindowHandles();
	        return driver.getWindowHandles().size() == numberOfWindows;
	      }
	    };
	}

	public void switchWindow(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){
 		String title = dataValue;
 		if(title.equalsIgnoreCase("null")){
			driver.switchTo().window(null);
		}else{
			 String currentWindow = driver.getWindowHandle();
		 	    Set <String> availableWindows = driver.getWindowHandles();
		 	    boolean flag = false;
		 	    if (!availableWindows.isEmpty()) {
		 		    for (String windowId : availableWindows) {
		 			    if (driver.switchTo().window(windowId).getTitle().contains(title)) {
		 			    	driver.switchTo().window(windowId);
		 			    	report.Log("Switching Window", "Switched the control to the Window with the title "+title, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		 			    	flag = true;
		 			    	break;
		 			    }else {
		 			        driver.switchTo().window(currentWindow);
		 			        try {
		 						Thread.sleep(3000);
		 					} catch (InterruptedException e) {
		 						// TODO Auto-generated catch block
		 						e.printStackTrace();
		 					}
		 			    }
		 		    }
		 	    }
		 	    if(!flag)
		 	    	report.Log("Switching Window", "Did not find any window with the title "+title, Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
		}
 	}



	/**
	 * Method Name: pickPreviousDayFromDatePicker
	 * Return Type: Nothing
	 * Description: This method reads the value from the output file and puts it in the data field
	 */
	public void pickPreviousDayFromDatePicker1(String homePath, String testCase, String scenario,String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder){

		findElementByType(driver, scenario, testCase, homePath, currentIteration, objectId, browser, passScreenshot, browserFolder).click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		WebElement dateWidget = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']"));

		List<WebElement> columns=dateWidget.findElements(By.tagName("td"));

		String date = "";
		Calendar rightNow = Calendar.getInstance();
		java.text.SimpleDateFormat df1 = new java.text.SimpleDateFormat("dd");
		date=(df1.format(rightNow.getTime()));
		if(!(date.equalsIgnoreCase("01") || date.equalsIgnoreCase("1"))){
			date = String.valueOf(Integer.parseInt(date)-1);

			for (WebElement cell:columns){
				if (cell.getText().equals(date)){
				cell.findElement(By.linkText(date)).click();
				break;
				}
			}
		}else if(date.equalsIgnoreCase("01") || date.equalsIgnoreCase("1")){
			driver.findElement(By.xpath("//div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-all']/a[@title='Prev']/span[@class='ui-icon ui-icon-circle-triangle-w']")).click();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			columns=dateWidget.findElements(By.tagName("td"));

			for (WebElement cell:columns){
				if (cell.getText().equals("31")){
					cell.findElement(By.linkText("31")).click();
					break;
				}else{
					if (cell.getText().equals("30")){
						cell.findElement(By.linkText("30")).click();
						break;
					}
				}
			}

		}

	}
}