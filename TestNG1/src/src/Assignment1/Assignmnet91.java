package Assignment1;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

//How to set priority to @Test annotation?
public class Assignmnet91 {
	
	
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
	
	@Test(priority=-1)
	public void Test2()
	{
		System.out.println("Test2");	
	}
	@Test
	public void Aest3()
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
