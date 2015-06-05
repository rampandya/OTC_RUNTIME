package com.otc.loadtestng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class LoadTestNG {
	private WebDriver driver;
	private String baseUrl;
	private String driverType;
	private String driverLoc;
  @Test (invocationCount=10,threadPoolSize=1)
  public void doLoadTest10 () {
	  System.out.println("Simulating 10 users");
	  System.out.println("Waiting 3 seconds for webpage to load......");
	  driver.get(baseUrl + "/marketplaces/otcqx");
	  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
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
	  System.out.println("Hit the website with thread id = " + Thread.currentThread().getId());
  }
  @Test (invocationCount=50,threadPoolSize=1)
  public void doLoadTest50 () {
	  System.out.println("Simulating 50 users");
	  System.out.println("Waiting 3 seconds for webpage to load......");
	  driver.get(baseUrl + "/marketplaces/otcqx");
	  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
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
	  System.out.println("Hit the website with thread id = " + Thread.currentThread().getId());
  }
  @Test (invocationCount=100,threadPoolSize=1)
  public void doLoadTest100 () {
	  System.out.println("Simulating 100 users");
	  System.out.println("Waiting 3 seconds for webpage to load......");
	  driver.get(baseUrl + "/marketplaces/otcqx");
	  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
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
	  System.out.println("Hit the website with thread id = " + Thread.currentThread().getId());
  }
  @BeforeClass
  public void setup() {
	  driverType = System.getProperty("driverType");
	  driverLoc = System.getProperty("driverLoc");
	  System.out.println("Driver Type = " + driverType);
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
	  baseUrl = "http://www.otcmarkets.com";
	  driver.manage().window().maximize();
	  //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
  }

  @AfterClass
  public void tearDown () {
	  driver.quit();
  }

}
