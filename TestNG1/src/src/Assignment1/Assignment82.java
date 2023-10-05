package Assignment1;
//There is a class which will have Aftermethod,BeforeMethod,Test and AfterSuite and what is the order of execution.

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Assignment82 {
	
	@AfterMethod
	public void Aftermethod()
	{
		System.out.println("Aftermethod");
	}
	
	@BeforeMethod
	public void beforemethod()
	{
		System.out.println("Beforemethod");
	}
	
	@Test
	public void test()
	{
		System.out.println("test");
	}
	@AfterSuite
	public void Fs()
	{
		System.out.println("Aftersuite");
	}

}
