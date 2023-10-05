package Assignment1;
//There is a class in which we have 3 @Test and only 1BM, what will be the order of execution?

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Assignment87 {
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

}
