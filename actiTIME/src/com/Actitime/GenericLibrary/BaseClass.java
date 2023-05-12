package com.Actitime.GenericLibrary;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.Actitime.ObjectRepository.LoginPage;

public class BaseClass {
public static WebDriver driver;
FileLibrary f=new FileLibrary();

@BeforeSuite
public void databaseConnection() {
	Reporter.log("database is connected",true);
}

@BeforeClass
public void launchBrowser() throws IOException {
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	String URL = f.readdataFromPropertyFile("url");
	driver.get(URL);
	Reporter.log("Browser Launched",true);
}

@BeforeMethod
public void login() throws IOException {
	LoginPage lp=new LoginPage(driver);
	lp.getUntbx().sendKeys("admin");
	lp.getPwdtbx().sendKeys("manager");
	lp.getLgbtn().click();	
	Reporter.log("Logged in successfully",true);
}

@AfterMethod
public void logout() {
	driver.findElement(By.id("logoutLink")).click();
	Reporter.log("Logged out Successfully",true);
	}

@AfterClass
public void closeBrowser() {
	driver.close();
	Reporter.log("Browser closed",true);
}
	
@AfterSuite
public void databaseDisconnection() {
	Reporter.log("database is Disconnected",true);
}
}