package com.mousiki.dataprovider;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.mousiki.testbase.TestBase;

public class TestCaseData extends TestBase {
	@DataProvider(name="testdata", parallel=true)
	public Object[][] getinput(Method m) throws Throwable {
		String testngtestname = System.getProperty("testngtestname");
	    System.out.println("Test Name - " + testngtestname); // it prints "Check name test"
//	    test = extent.createTest(testContext.getName());
	    String testsuitename= System.getProperty("testphase");
		Object data[][] = getExcelData(testngtestname, m.getName(), testsuitename);
		return data;
	}
}
