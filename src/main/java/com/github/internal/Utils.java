package com.github.internal;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.collections.Lists;
import org.testng.collections.Maps;
import org.testng.collections.Sets;
import org.testng.reporters.Files;

public class Utils {

  public static class ExecutionResults {
    private int passed = 0;
    private int failed = 0;
    private int skipped = 0;
    private int ignored = 0;
    private int retried = 0;

    /*int getTotal() {
      return getPassed() + getFailed() + getSkipped() + getIgnored() + getRetried();
    }*/
    int getTotal() {
        return getPassed() + getFailed() + getSkipped() + getRetried();
    }

    int getPassed() {
      return passed;
    }

    int getFailed() {
      return failed;
    }

    int getSkipped() {
      return skipped;
    }

    int getIgnored() {
      return ignored;
    }

    int getRetried() {
      return retried;
    }
  }

  public static ExecutionResults computeOverResults(List<ISuite> suites) {
    // Calculate passed/failed/skipped
    ExecutionResults results = new ExecutionResults();
    for (ISuite s : suites) {
      Map<String, ISuiteResult> suiteResults = s.getResults();
      for (ISuiteResult sr : suiteResults.values()) {
        ITestContext testContext = sr.getTestContext();
        results.passed += testContext.getPassedTests().size();
        results.failed += testContext.getFailedTests().size();
        int retriedPerTest = 0;
        int skippedPerTest = 0;
        for (ITestResult result : testContext.getSkippedTests().getAllResults()) {
          if (result.getAttribute("retried") != null) {
            retriedPerTest++;
          } else {
            skippedPerTest++;
          }
        }
        results.skipped += skippedPerTest;
        results.retried += retriedPerTest;
        results.ignored += testContext.getExcludedMethods().size();
      }
    }
    return results;
  }

  public static void writeToFile(String outputDirectory, String fileContent) throws IOException {
    String fileName = "Metrics-" + new SimpleDateFormat("yyyyMMMdd-HHmm'.html'").format(new Date());
    String tempFile = outputDirectory + File.separator + fileName;
    File file = new File(tempFile);
    Files.writeFile(fileContent, file);
    // write to file with OutputStreamWriter
    System.out.println("TestNG Metrics " + fileName + " is created successfully");
  }

  static Map<String, List<ITestResult>> asMap(Set<ITestResult> testResults) {
    Map<String, List<ITestResult>> map = Maps.newHashMap();
    for (ITestResult result : testResults) {
      String className = result.getTestClass().getName();
      List<ITestResult> list = map.computeIfAbsent(className, k -> Lists.newArrayList());
      list.add(result);
    }
    return map;
  }

  static Set<ITestResult> extractResults(ISuiteResult suiteResult) {
    Set<ITestResult> testResults = Sets.newHashSet();
    ITestContext testContext = suiteResult.getTestContext();
    testResults.addAll(addAllTestResults(testContext.getPassedTests()));
    testResults.addAll(addAllTestResults(testContext.getFailedTests()));
    testResults.addAll(addAllTestResults(testContext.getSkippedTests()));
    testResults.addAll(addAllTestResults(testContext.getPassedConfigurations()));
    testResults.addAll(addAllTestResults(testContext.getSkippedConfigurations()));
    testResults.addAll(addAllTestResults(testContext.getFailedConfigurations()));
    testResults.addAll(addAllTestResults(testContext.getFailedButWithinSuccessPercentageTests()));
    return testResults;
  }

  static String getStatusString(ITestResult result) {
    int testResultStatus = result.getStatus();
    switch (testResultStatus) {
      case ITestResult.SUCCESS:
        return "PASS";
      case ITestResult.FAILURE:
        return "FAIL";
      case ITestResult.SKIP:
        return "SKIP";
      case ITestResult.SUCCESS_PERCENTAGE_FAILURE:
        return "SUCCESS_PERCENTAGE_FAILURE";
      default:
        throw new AssertionError("Unexpected value: " + testResultStatus);
    }
  }

  private static Set<ITestResult> addAllTestResults(IResultMap resultMap) {
    if (resultMap == null) {
      return Sets.newHashSet();
    }

    List<ITestResult> temp = new ArrayList<>(resultMap.getAllResults());
    temp.sort((o1, o2) -> (int) (o1.getStartMillis() - o2.getStartMillis()));

    return new HashSet<>(temp);
  }


}
