package com.github.functional;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FunctionalSample {

  @Test
  public void passingTest() {}

  @Test
  public void failingTest() {
    Assert.fail();
  }

  @Test(dependsOnMethods = {"failingTest"})
  public void skippingTest() {}
}
