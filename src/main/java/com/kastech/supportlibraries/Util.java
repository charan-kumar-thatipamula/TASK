package com.kastech.supportlibraries;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.imageio.ImageIO;
import org.ini4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Util{
    @Autowired
    POI_ReadExcel poiObject;
    @Autowired
    Report reportObject;

	public Properties prop = null;
	public FileInputStream propFile = null;

	/**
	 * Author: Pradeep Bura
	 * Method Name: getData
	 * Description: This method is to fetch Data from the column in the Input sheet against the test case that is currently executing based on the current iteration
	 * Return Type: String
	 * @throws Exception
	 */
	public String getData(String colName, WebDriver driver, String scenario, String testCase, String homePath, Integer currentIteration, String browser, String passScreenshot, String browserFolder) throws Exception{
		String dataValue = "";

		//String dbPath = homePath + "\\TestData\\"+testCase+".xlsx";
		String dbPath = homePath + "\\TestData\\"+scenario+".xlsx";

		try {

			List<String> whereClause = new ArrayList<String>();
			whereClause.add("TestScript::"+testCase);
			whereClause.add("Iteration::"+currentIteration.intValue());
			Map<String, List<String>> result = poiObject.fetchWithCondition(dbPath, "TestData", whereClause);

			if(result.get("TestScript").size()==0){
				reportObject.Log("Blank column in Test Data", "There is no data in the column "+colName+" for the Iteration "+currentIteration+" of test case "+testCase, Report.Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			}

			for(int i=0; i<result.get("TestScript").size(); i++){
				if(testCase.equalsIgnoreCase(result.get("TestScript").get(i)) && currentIteration.intValue()==Integer.parseInt(result.get("Iteration").get(i))){
					dataValue = result.get(colName).get(i);
					break;
				}
			}

			return dataValue;
		} catch (Exception e) {
			reportObject.Log("Unhandled Exception in reading Data", "There has been an unhandled exception "+e+" while reading data from the Test Data sheet", Report.Status.FAIL, driver, testCase, scenario, browser, passScreenshot, browserFolder);
			throw e;
		}

	}

	/**
	 * Author: Pradeep Bura
	 * Method Name: getCurrentDate
	 * Description: This method is get the Current Date in the format 'MM-dd-yyyy'
	 * Return Type: String
	 */
	public String getCurrentDate(){

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		String date = sdf.format(cal.getTime()).toString();
		return date;

	}

	/**
	 * Author: Pradeep Bura
	 * Method Name: getCurrentTime
	 * Description: This method is get the Current Time in the format 'hh-mm-ssa'
	 * Return Type: String
	 */
	public String getCurrentTime(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("hh-mm-ssa");
		String time = sdf.format(cal.getTime()).toString();
		return time;

	}


	/**
	 * Author: Pradeep Bura
	 * Method Name: CaptureScreenshot
	 * Description: This method is capture the screenshot of the current page
	 * Return Type: Nothing
	 */
	public void CaptureScreenshot(String screenshotPath, WebDriver driver){

		try {
			if(getValueFromConfigProperties("SnapshotType").equalsIgnoreCase("Desktop")){
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Dimension screenSize = toolkit.getScreenSize();
				Robot robot = new Robot();
				Rectangle rectangle = new Rectangle(0, 0, screenSize.width-15, screenSize.height);
				BufferedImage image = robot.createScreenCapture(rectangle);
				ImageIO.write(image, "png", new File(screenshotPath));
			}else if(getValueFromConfigProperties("SnapshotType").equalsIgnoreCase("Browser")){
				File screenshot = null;
				if(getValueFromConfigProperties("GridSetup").equalsIgnoreCase("Yes")){
					WebDriver augmentedDriver = new Augmenter().augment(driver);
					screenshot = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
				}else{
					screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				}
				FileUtils.copyFile(screenshot, new File(screenshotPath));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Author: Pradeep Bura
	 * Method Name: getValue
	 * Description: This method is read the value of a key from Properties.ini file in the framework
	 * Return Type: String
	 */
	public String getDataINI(String sectionName, String key){

		String value = "";
		try{
			String homePath = new File (".").getCanonicalPath();
			File f = new File(homePath+"\\OutputDataINI.ini");

			//Ini a = new Ini(f);
			//a.load(f);
            Ini ini = new Ini();
			ini.load(new FileReader(f));
            Ini.Section section = ini.get(sectionName);
			value = section.get(key);
		}catch(Exception e){
			e.printStackTrace();
		}
		return value;

	}

	/**
	 * Author: Pradeep Bura
	 * Method Name: setDataINI
	 * Description: This method is to write a value of a key to OutputData.ini file in the framework
	 * Return Type: Nothing
	 */
	public void setDataINI(String key,String value, String testCase, String scenario){

		try{

			String homePath = new File (".").getCanonicalPath();
			File f = new File(homePath+"\\OutputDataINI.ini");

            Ini a = new Ini();
			a.load(new FileReader(f));
			a.remove(scenario+"_"+testCase);

            Ini.Section section = a.add(scenario+"_"+ testCase);
			section.put(key, value);
			//a.put(scenario+"_"+ testCase,key, value);

			a.store(new FileOutputStream(f));

		}catch(Exception e){
			e.printStackTrace();
		}
	}


	/**
	 * Author: Pradeep Bura
	 * Method Name: getValueFromConfigProperties
	 * Description: This method is to retrieve value from Config.properties file for the key given
	 * Return Type: String
	 */
	public String getValueFromConfigProperties(String key){
        prop = new Properties();
		String value = "";
		try {
			propFile = new FileInputStream (new File(".").getCanonicalPath()+"\\Config.properties");
			prop.load(propFile);
			value = prop.getProperty(key);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return value;
	}

	/**
	 * Author: Pradeep Bura
	 * Method Name: getObjectFromObjectMap
	 * Description: This method is to retrieve object from the particular module ObjectMap.properties file for the key given
	 * Return Type: String
	 */
	public String getObjectFromObjectMap(String key, String scenario){
        prop = new Properties();
		String value = "";
		try {
			propFile = new FileInputStream (new File(".").getCanonicalPath()+"\\ObjectRepository\\"+scenario+".properties");
			prop.load(propFile);
			if(key!=null)
				value = prop.getProperty(key);
			else
				value = null;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			//Report.Log("Object Reference Name Wrong","The Object Reference Name given is not present/matching with Object Map", Status.FAIL);
		}
		return value;
	}
}