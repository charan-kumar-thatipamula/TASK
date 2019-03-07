package com.kastech.DriverScript;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

//import org.apache.poi.ddf.EscherColorRef.SysIndexSource;
////import org.apache.poi.hslf.model.Sheet;
//import org.apache.poi.util.SystemOutLogger;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.openqa.selenium.Platform;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.safari.SafariDriver;

import com.kastech.supportlibraries.POI_ReadExcel;
import com.kastech.supportlibraries.Report;
import com.kastech.supportlibraries.ExecuteScripts;
import com.kastech.supportlibraries.Util;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StandaloneDriver {

	static String resultsFolder = "";

	@Autowired
	POI_ReadExcel poiObject;
	@Autowired
    Util util;
	@Autowired
    ExecuteScripts driverObject;
	@Autowired
	ExecuteScripts testngDriver;

	// localhost:3000/runTests
	public void execute(String userName,String passWord,String runId, String tenant, String browsertype, Boolean rescind) {

//		String userName;
//		String passWord;
//		String runId;
//		String tenant;
//		Boolean rescind;
		String port="";

		//Util util = new Util();
		String gridSetup = util.getValueFromConfigProperties("GridSetup");
		if(gridSetup.equalsIgnoreCase("Yes")){
			JOptionPane.showMessageDialog(null,"Grid Setup is Set to Yes in Config file, please execute testNG.xml", "Configuration Error", JOptionPane.ERROR_MESSAGE);
		}else{
			String homePath="";
			Map<String, List<String>> result1=null;
			XSSFWorkbook workBook = null;

			int numberOfSheets=0;
			int numberOfExecutedTCs=0;
			try {
				homePath = new File (".").getCanonicalPath();
				String path = homePath+"\\TestCaseSelection.xlsx";

//				POI_ReadExcel poiObject = new POI_ReadExcel();
				FileInputStream file = new FileInputStream(new File(path));
				workBook = new XSSFWorkbook(file);
				numberOfSheets = workBook.getNumberOfSheets();

				workBook.close();

				numberOfExecutedTCs = 0;

				for(int j=0;j<numberOfSheets;j++){
					List<String> whereClause1 = new ArrayList<String>();
					whereClause1.add("Execute::Yes");
					result1 = poiObject.fetchWithCondition(path, workBook.getSheetName(j), whereClause1);
					numberOfExecutedTCs = numberOfExecutedTCs+result1.get("Execute").size();
				}

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String[] ListOfBrowsers = null;
			ListOfBrowsers = util.getValueFromConfigProperties("Browser").split(",");

			//ExecuteScripts driverObject = new ExecuteScripts();
			WebDriver driver = null;
			for(int i=0;i<ListOfBrowsers.length;i++){

				if(numberOfExecutedTCs!=0){
					driver = driverObject.SelectDriver(ListOfBrowsers[i],port);
                    driver.manage().window().maximize();
					if(i==0)
						resultsFolder = testngDriver.CreateExecutionFolder(homePath);

					for(int k=0;k<numberOfSheets;k++){
						if(util.getValueFromConfigProperties("TestCaseSelection_Format").equalsIgnoreCase("SingleSheet")){
							driverObject.ExecuteDriver(driver,workBook.getSheetName(k),ListOfBrowsers[i],resultsFolder);
						}else if(util.getValueFromConfigProperties("TestCaseSelection_Format").equalsIgnoreCase("Modular")){
							driverObject.ExecuteModularDriver(driver,workBook.getSheetName(k),ListOfBrowsers[i],resultsFolder);
						}

					}
				}

				driver.manage().deleteAllCookies();

				try {
					driver.close();
					driver.quit();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Report reportObject = new Report();
			reportObject.CloseSummary(resultsFolder);

		}

	}
	/**
	 * @param args
	 * Author: Pradeep Bura
	 * Description: Execute for Stand alone execution when Grid Setup is marked No in Config.properties file
	 */
//	public static void main(String[] args) {
//		execute();
//////		String userName;
//////		String passWord;
//////		String runId;
////		String port="";
////		// TODO Auto-generated method stub
////		Util util = new Util();
////		String gridSetup = util.getValueFromConfigProperties("GridSetup");
////		if(gridSetup.equalsIgnoreCase("Yes")){
////			JOptionPane.showMessageDialog(null,"Grid Setup is Set to Yes in Config file, please execute testNG.xml", "Configuration Error", JOptionPane.ERROR_MESSAGE);
////		}else{
////			String homePath="";
////			Map<String, List<String>> result1=null;
////			XSSFWorkbook workBook = null;
////
////			int numberOfSheets=0;
////			int numberOfExecutedTCs=0;
////			try {
////				homePath = new File (".").getCanonicalPath();
////				String path = homePath+"\\TestCaseSelection.xlsx";
////
////				POI_ReadExcel poiObject = new POI_ReadExcel();
////				FileInputStream file = new FileInputStream(new File(path));
////				workBook = new XSSFWorkbook(file);
////				numberOfSheets = workBook.getNumberOfSheets();
////
////				workBook.close();
////
////				numberOfExecutedTCs = 0;
////
////				for(int j=0;j<numberOfSheets;j++){
////					List<String> whereClause1 = new ArrayList<String>();
////					whereClause1.add("Execute::Yes");
////					result1 = poiObject.fetchWithCondition(path, workBook.getSheetName(j), whereClause1);
////					numberOfExecutedTCs = numberOfExecutedTCs+result1.get("Execute").size();
////				}
////
////			} catch (FileNotFoundException e1) {
////				// TODO Auto-generated catch block
////				e1.printStackTrace();
////			} catch (IOException e1) {
////				// TODO Auto-generated catch block
////				e1.printStackTrace();
////			}
////
////			String[] ListOfBrowsers = null;
////			ListOfBrowsers = util.getValueFromConfigProperties("Browser").split(",");
////
////			ExecuteScripts driverObject = new ExecuteScripts();
////			WebDriver driver = null;
////			for(int i=0;i<ListOfBrowsers.length;i++){
////				ExecuteScripts testngDriver = new ExecuteScripts();
////
////				if(numberOfExecutedTCs!=0){
////					driver = driverObject.SelectDriver(ListOfBrowsers[i],port);
////					driver.manage().window().maximize();
////					if(i==0)
////						resultsFolder = testngDriver.CreateExecutionFolder(homePath);
////
////					for(int k=0;k<numberOfSheets;k++){
////						if(util.getValueFromConfigProperties("TestCaseSelection_Format").equalsIgnoreCase("SingleSheet")){
////							driverObject.ExecuteDriver(driver,workBook.getSheetName(k),ListOfBrowsers[i],resultsFolder);
////						}else if(util.getValueFromConfigProperties("TestCaseSelection_Format").equalsIgnoreCase("Modular")){
////							driverObject.ExecuteModularDriver(driver,workBook.getSheetName(k),ListOfBrowsers[i],resultsFolder);
////						}
////
////					}
////				}
////
////				driver.manage().deleteAllCookies();
////
////				try {
////					driver.close();
////					driver.quit();
////				} catch (Exception e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
////			}
////			Report reportObject = new Report();
////			reportObject.CloseSummary(resultsFolder);
////
////		}
//
//	}
}

	/**
	 * Method Name: custYear
	 * Return Type: Nothing
	 * Description: This method is to fetch the current Year
	 */
	/*public void custYear(String homePath, String testCase, String scenario, String browser, String objectId, String objectName, String dataValue, String onPassLog, String onFailLog, WebDriver driver, String passScreenshot, Integer currentIteration, Boolean error, String browserFolder)
	{
		Report reportObject = new Report();
		Calendar prev3Year = Calendar.getInstance();
		int minusValue=Integer.valueOf(dataValue);
		  prev3Year.add(Calendar.YEAR, -minusValue);
		  dataValue=String.valueOf(prev3Year.get(Calendar.YEAR));
		  System.out.println(dataValue);

			findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).clear();
			findElementByType(driver, scenario, testCase, homePath, currentIteration.intValue(), objectId, browser, passScreenshot, browserFolder).sendKeys(dataValue);

			reportObject.Log("Entering text in the text box "+objectName, "Entered the text "+dataValue, Status.DONE, driver, testCase, scenario, browser, passScreenshot, browserFolder);
	}
}*/