package com.github.functional;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
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
}
