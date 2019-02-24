package com.github.functional;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FunctionalSample {
  
  @BeforeClass
  public void beforeClass() {}
  
  @AfterClass
  public void afterClass() {}
  
  @Test
  public void passingTest() {}

  @Test
  public void failingTest() {
    Assert.assertEquals("Shiva", "Prasad");
  }

  @Test(dependsOnMethods = {"failingTest"})
  public void skippingTest() {}
  
  	@DataProvider(name = "dataProviderTest")
	public static Object[][] credentials() {

		return new Object[][] { { "testuser_1", "Test@123" }, { "testuser_2", "Test@321" } };

	}

	@Test(dataProvider = "dataProviderTest")
	public void dataProviderTest(String sUsername, String sPassword) {

		if (sUsername.contentEquals("testuser_2")) {
			System.out.println("Failing if user is testuser_2");
			Assert.assertEquals("Shiva", "Prasad");
			

		}
	}
}
