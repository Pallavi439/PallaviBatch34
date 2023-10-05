package Assignment1;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//There is a class in which we have 3 @Test, 1BM and 1AM, what will be the order of execution?
public class Assignment85 {
	
	@BeforeMethod
	public void BM()
	{
		System.out.println("BM");
	}
  
	@AfterMethod
	public void AM()
	{
		System.out.println("AM");	
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
	
}
