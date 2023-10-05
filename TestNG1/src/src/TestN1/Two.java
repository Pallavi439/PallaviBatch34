package TestN1;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Two {
	
	@BeforeMethod
	public void method1()
	{
		System.out.print("method1");
	}
	@AfterMethod
	public void method2()
	{
		System.out.print("method2");	
	}
	@Test
	public void Test1()
	{
		System.out.print("method3");	
	}
	@Test
	public void Test2()
	{
		System.out.print("method4");
	}

}
