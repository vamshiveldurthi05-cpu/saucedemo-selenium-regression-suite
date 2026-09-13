package com.saucedemo.tests;

import org.testng.TestNG;

import java.util.Collections;

// Run this file with "Run As -> Java Application" (NOT TestNG Suite/Test).
// It runs as an ordinary Java program that tells TestNG to execute the
// suite defined in testng.xml, sidestepping the Eclipse TestNG launcher
// entirely, which is useful if that launcher's classpath is misconfigured.
public class TestRunner {
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        testng.setTestSuites(Collections.singletonList("src/test/resources/testng.xml"));
        testng.run();
    }
}
