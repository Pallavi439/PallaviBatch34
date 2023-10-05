package Assignment1;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Assignment81 {
	
	@BeforeSuite
	public void testsuite()
	{
		System.out.println("Beforesuite");
	}
	@BeforeClass
	public void testsuite1()
	{
		System.out.println("BeforeClass");
	}
	@BeforeTest
	public void Test1()
	{
		System.out.println("TestMethod");	
	}
	@BeforeMethod
	public void testsuite2()
	{
		System.out.println("BeforeMethod");
	}
	@AfterSuite
	public void testsuite3()
	{
		System.out.println("Aftersuite");
	}
	@AfterClass
	public void testsuite4()
	{
		System.out.println("AfterClass");
	}
	@AfterTest
	public void ATest1()
	{
		System.out.println("Atest");	
	}
	
	@AfterMethod
	public void testsuite5()
	{
		System.out.println("afterMethod");
	}
	@Test
   public void Zbc()
   {
		System.out.println("Testzbc");
		
   }

	
	

}
