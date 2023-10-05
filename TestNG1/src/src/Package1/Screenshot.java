package Package1;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Screenshot {
//	@Test
//	public void test1() throws IOException
//	{
//	ChromeDriver driver =new ChromeDriver();
//	driver.get("https://www.google.com/");
//	driver.manage().window().maximize();
//	WebElement searchtextfield = driver.findElement(By.id("APjFqb"));
//	searchtextfield.sendKeys("India");
//	 //Step 1: - upcasting driver to take ss
//	  ChromeDriver ts= (ChromeDriver) driver;
//
//	//step 2:
//	  File source = ts.getScreenshotAs(OutputType.FILE);
//	  
//	//step 3:
//	  File destination = new File("/Users/user/eclipse-workspace/TestNg/Screenshot/pallavi.png");
//
//	 //step 4:
//	  FileUtils.copyFile(source, destination);
//	
//	
//	}
	
	@BeforeMethod
	public void bm()
	{
		ChromeDriver driver =new ChromeDriver();
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
	}
	@AfterMethod
	public void Am() throws IOException
	{
		WebElement driver = null;
		WebElement searchtextfield = driver.findElement(By.id("APjFqb"));
		searchtextfield.sendKeys("India");
	//  Step 1: - upcasting driver to take ss
		  ChromeDriver ts= (ChromeDriver) driver;

		//step 2:
		  File source = ts.getScreenshotAs(OutputType.FILE);
		  
		//step 3:
		  File destination = new File("/Users/user/eclipse-workspace/TestNg/Screenshot/pallavi.png");

		 //step 4:
		  FileUtils.copyFile(source, destination);
	}
	//Apache POi libarary   https://archive.apache.org/dist/poi/release/bin/
   
	
}





