
package com.Actitime.TestScripts;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Actitime.GenericLibrary.BaseClass;
import com.Actitime.GenericLibrary.FileLibrary;
import com.Actitime.ObjectRepository.HomePage;
import com.Actitime.ObjectRepository.TaskPage;

public class Task extends BaseClass {
	@Test
	public void createCustomer() throws EncryptedDocumentException, IOException, InterruptedException
	{
		HomePage hp=new HomePage(driver);
		hp.getTasktab().click();
		TaskPage tp=new TaskPage(driver);
		tp.getAddnewbtn().click();
		tp.getNewcust().click();
		
		FileLibrary f=new FileLibrary();
		String createcustomer = f.readDataFromExcel("Sheet1", 1, 1);
		Thread.sleep(5000);
		
		tp.getCustname().sendKeys(createcustomer);
		String description = f.readDataFromExcel("Sheet1", 1, 2);
		tp.getCustdesp().sendKeys(description);
		tp.getCreatecust().click();
		String expectedresult = createcustomer;
		String actualresult = driver.findElement(By.xpath("(//div[.='"+createcustomer+"'])[2]")).getText();
		SoftAssert s=new SoftAssert();
		s.assertEquals(actualresult, expectedresult);
		s.assertAll();
		
		
		
		
	}

}