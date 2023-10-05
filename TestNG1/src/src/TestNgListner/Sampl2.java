package TestNgListner;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Sampl2 {

	
		ChromeDriver driver1;
		
		@Test(timeOut=1000)
		public void test1()
		{
			driver1=new ChromeDriver();
			driver1.get("https://www.google.com/");
			driver1.manage().window().maximize();
			WebElement wb=driver1.findElement(By.id("APjFqb"));
			wb.sendKeys("canada");
			wb.sendKeys(Keys.ENTER);
			
			
		}
	}


