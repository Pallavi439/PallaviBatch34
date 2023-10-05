package Assignment1;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

//For 5 countries use @Test annotation

public class Assignment95 {
	
	
	@Test(timeOut=1000)
	public void test()
	{
		 ChromeDriver d=new ChromeDriver();
		d.get("https://www.google.com/");
		d.manage().window().maximize();
		WebElement wb=d.findElement(By.id("APjFqb"));
		wb.sendKeys("India");
		wb.sendKeys(Keys.ENTER);

}
	@Test()
	public void test1()
	{
		 ChromeDriver d=new ChromeDriver();
		d.get("https://www.google.com/");
		d.manage().window().maximize();
		WebElement wb=d.findElement(By.id("APjFqb"));
		wb.sendKeys("France");
		wb.sendKeys(Keys.ENTER);

}
	@Test()
	public void tes2()
	{
		 ChromeDriver d=new ChromeDriver();
		d.get("https://www.google.com/");
		d.manage().window().maximize();
		WebElement wb=d.findElement(By.id("APjFqb"));
		wb.sendKeys("Canada");
		wb.sendKeys(Keys.ENTER);

}

}
