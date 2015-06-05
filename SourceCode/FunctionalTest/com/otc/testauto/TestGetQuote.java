package com.otc.testauto;

/**
 * This test case gets the quote for different symbol.  JUnit framework is used
 * to run this test case.  
 * Process Flow:
 * Invoke firefox/IE or Chrome driver to run the test
 * Read symbol from a MS Excel file
 * Check for any Alert and close it
 * Check for SurvveyMonkey survey and if found close it
 * loop through each symbol and get a quote
 * Validate that Previous close, open, Daily Range, 52 week Range etc. are present
 *   
 * 
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;

import com.home.readmsdocs.*;

import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

import com.home.readmsdocs.ReadFromExcel;

public class TestGetQuote {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private String fileName;
  private String driverType;
  private String driverLoc;

  @Before
  public void setUp() throws Exception {
	  driverType = System.getProperty("driverType");
	  fileName = System.getProperty("fileName");
	  driverLoc = System.getProperty("driverLoc");
	  System.out.println("Driver Type = " + driverType);
	  System.out.println("File Name = " + fileName);
	  System.out.println("Driver location  =" + driverLoc);
	  if (driverType.equals("IE")) {
		  System.out.println("Running test in IE browswer");
		  System.setProperty("webdriver.ie.driver",driverLoc);
		  driver = new InternetExplorerDriver();  
	  }
	  else if (driverType.equals("Chrome")) {
		  System.out.println("Running test in Chrome");
		  System.setProperty("webdriver.chrome.driver",driverLoc);
		  driver = new ChromeDriver();
	  }
	  else {
		  System.out.println("Running test in FireFox");
		  driver = new FirefoxDriver();
	  }
    baseUrl = "http://www.otcmarkets.com/";
    driver.manage().window().maximize();
    //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  System.out.println("The filename is = " + fileName);
	  if (fileName != null) {
		  Assert.assertFalse("Must supply the Excel file name", fileName.isEmpty());
	  }
	  else {
		  Assert.assertNotNull("Must supply file name",fileName);
	  }
	  Assert.assertTrue("File must be excel file", fileName.endsWith(".xlsx"));
	
  }

  @Test
  public void testGetQuote() throws Exception {
	 
	  ReadFromExcel rfe = new ReadFromExcel(fileName);
	  ArrayList <String> tickers;
	  //read data from the Excel spreadsheet 
	  System.out.println("Reading data from a Excel spread sheet");
	  tickers = rfe.getTickers();
	  Iterator <String> itr = tickers.iterator();
	  String symbol; 
	  
    //driver.get(baseUrl + "/research/companyDirectory?symbol=QUOTE");
	  System.out.println("Waiting for page to load.............");
    driver.get(baseUrl + "home");
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    Set <String> windows = driver.getWindowHandles();
    System.out.println("Nuber of windows = " + windows.size());
    //if pop up found then close it
    try {
    	if (driver.findElement(By.id("div_survey")).isDisplayed()) {
		  System.out.println("out of loop Close Surveymonkey popup "); 
		  driver.findElement(By.id("otcm_survey_not_interested")).click();
    	}
    	else {
    		System.out.println("Surveymonkey Pop up does not exist");
    	}
    }
    catch (NoSuchElementException nsee) {	
    	System.out.println("SurveyMonkey Element does not exist");
    }
    //Iterate through all the symbol read from the excel file
    //System.out.println("Just before while loop");
    while (itr.hasNext()) {
    	  symbol = itr.next();
    	  if (isAlertPresent()) {
    		  System.out.println("clsoe present alert");
    		  closeAlertAndGetItsText();
    	  }
    	  try {
    	    	if (driver.findElement(By.id("div_survey")).isDisplayed()) {
    			  System.out.println("out of loop Close Surveymonkey popup "); 
    			  driver.findElement(By.id("otcm_survey_not_interested")).click();
    	    	}
    	    	else {
    	    		System.out.println("Surveymonkey Pop up does not exist");
    	    	}
    	    }
    	    catch (NoSuchElementException nsee) {	
    	    	System.out.println("SurveyMonkey Element does not exist");
    	    }
    	  System.out.println("Processing symbol = " + symbol);
		  driver.findElement(By.id("quoteBox")).clear();
		  driver.findElement(By.id("quoteBox")).sendKeys(symbol);
		  driver.findElement(By.id("quoteGo")).click();  
		  //perform validation 
		  if (validateData()) {
			  System.out.println("Validation for symbol = " + symbol + " passed");
		  }
		  else {
			  System.out.println("Validation for symbol = " + symbol + " failed");
		  }
	}
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
  /** 
   * This Method performs the validation for the quote that has been 
   * selected and makes sure that all the fields are returned and displayed
   * on the wed page. 
   * @return
   */
  private boolean validateData () {
	  boolean pass = true;
	  
	  if (isElementPresent(By.xpath("//*[@id='tradeSummary']/div[1]/strong[1]"))) {
		  System.out.println("Previous close is found");
	  }
	  else {
		  pass = false;
	  }
	  if (isElementPresent(By.xpath("//*[@id='tradeSummary']/div[1]/strong[2]"))) {
		  System.out.println("Open is found");
	  }
	  else {
		  pass = false;
	  }
	  if (isElementPresent(By.xpath("//*[@id='tradeSummary']/div[2]/strong[1]"))) {
		  System.out.println("Daily range is found");
	  }
	  else {
		  pass = false;
	  }
	  if (isElementPresent(By.xpath("//*[@id='tradeSummary']/div[2]/strong[2]"))) {
		  System.out.println("52 week range is found");
	  }
	  else {
		  pass = false;
	  }
	  if (isElementPresent(By.xpath("//*[@id='tradeSummary']/div[3]/strong[1]"))) {
		  System.out.println("Volume is found");
	  }
	  else {
		  pass = false;
	  }
	  if (isElementPresent(By.xpath("//*[@id='tradeSummary']/div[3]/strong[2]"))) {
		  System.out.println("Dividend is found");
	  }
	  else {
		  pass = false;
	  }
	  if (isElementPresent(By.xpath("//*[@id='tradeSummary']/div[4]/strong[1]"))) {
		  System.out.println("Average Volume is found");
	  }
	  else {
		  pass = false;
	  }
	  if (isElementPresent(By.xpath("//*[@id='tradeSummary']/div[4]/strong[2]"))) {
		  System.out.println("Net Dividend is found");
	  }
	  else {
		  pass = false;
	  }

	  return pass;
	  
  }
}
 