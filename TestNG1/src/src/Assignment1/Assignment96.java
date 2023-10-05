package Assignment1;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Assignment96 {
	ChromeDriver d;

	@BeforeMethod
	public void BM_LauchBrowser()
	{
		 d=new ChromeDriver();
		d.navigate().to("https://www.google.com/");
		d.manage().window().maximize();
				
	}
	@Test(invocationCount=2)
	public void test1()
	{
	d.findElement(By.id("APjFqb")).sendKeys("India");	
	}
	@AfterMethod
	public void AM_QuitBrowser()
	{
	d.quit();	
	}
}
