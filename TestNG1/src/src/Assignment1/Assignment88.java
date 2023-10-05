package Assignment1;
//There is a class in which 1BS, 1AS and 3 @Test, 1AM, 1BM, What will be the order of execution?

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Assignment88 {
	@BeforeSuite
	public void BS()
	{
		System.out.println("Bs");
	}
	@BeforeMethod
	public void BM()
	{
		System.out.println("BM");	
	}
	@Test
	public void Test1()
	{
		System.out.println("Test1");
	}
	@Test
	public void Test2()
	{
		System.out.println("Test2");	
	}
	@Test
	public void Test3()
	{
		System.out.println("Test3");	
	}
	@AfterSuite
	public void AS()
	{
		System.out.println("As");
	}
	@AfterMethod
	public void AM()
	{
		System.out.println("AM");	
	}
	
}
