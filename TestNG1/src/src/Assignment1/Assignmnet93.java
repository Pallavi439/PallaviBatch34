package Assignment1;

import org.testng.annotations.Test;

//Program for passing invocationCount parameter @Test

public class Assignmnet93 {
	
	@Test(invocationCount=3)
	public void test1()
	{
		System.out.println("test1");
	}

}
