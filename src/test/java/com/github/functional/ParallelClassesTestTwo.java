package com.github.functional;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ParallelClassesTestTwo {

    // Credits of these test cases - https://howtodoinjava.com/testng/testng-executing-parallel-tests/

    @BeforeClass
    public void beforeClass() {
        long id = Thread.currentThread().getId();
        System.out.println("Before test-class. Thread id is: " + id);
    }
 
    @Test
    public void testMethodOne() {
        long id = Thread.currentThread().getId();
        System.out.println("Sample test-method One. Thread id is: " + id);
    }
 
    @Test
    public void testMethodTwo() {
        long id = Thread.currentThread().getId();
        System.out.println("Sample test-method Two. Thread id is: " + id);
    }
 
    @AfterClass
    public void afterClass() {
        long id = Thread.currentThread().getId();
        System.out.println("After test-class. Thread id is: " + id);
    }
}