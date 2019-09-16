package com.github.testng;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import com.github.exception.ReportConfigException;
import com.github.internal.HtmlBuilder;
import com.github.internal.Utils;
import com.github.internal.Utils.ExecutionResults;

public class MetricsListener implements IReporter, ITestListener {
  private StringBuilder builder = new StringBuilder();
  private static String outdir;

  @Override
  public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
      String outputDirectory) {
    String logo =
        System.getProperty("testng.metrics.logo", "https://i.ibb.co/9qBkwDF/Testing-Fox-Logo.png");
    ExecutionResults results = Utils.computeOverResults(suites);
    builder.append(HtmlBuilder.buildHeaderAndTitle());
    builder.append(HtmlBuilder.buildDashBoard(results, logo));
    suites.forEach(this::generateReport);
    builder.append(HtmlBuilder.buildLogsTab());
    builder.append(HtmlBuilder.buildEmailTab(results));
    builder.append(HtmlBuilder.buildScriptContent());
    try {
      Utils.writeToFile(outputDirectory, builder.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void generateReport(ISuite suite) {
	  generateClassMetrics(suite);
	  generateTestMetrics(suite);
	  generateMethodMetrics(suite);
  }

  private void generateClassMetrics(ISuite suite){
    Collection<ISuiteResult> suiteResults = suite.getResults().values();
	int index = 0;
	int size = suiteResults.size();
	for (ISuiteResult suiteResult : suiteResults) {
	  if (index == 0) {
	    builder.append(HtmlBuilder.appendClassMetricsHeader());
	  }
	  builder.append(HtmlBuilder.buildClassMetrics(suiteResult));
	  if (index == size-1) {
	    builder.append(HtmlBuilder.appendClassMetricsFooter());
	  }
	  index++;
    }
  }

  private void generateTestMetrics(ISuite suite){
    Collection<ISuiteResult> suiteResults = suite.getResults().values();
    int index = 0;
    int size = suiteResults.size();
    long totalTime = 0;
    for (ISuiteResult eachRslt : suite.getResults().values()) {
      ITestContext ctx = eachRslt.getTestContext();
      Date start = ctx.getStartDate();
      Date end = ctx.getEndDate();
      long ms = end.getTime() - start.getTime();
      totalTime += ms;
    }
    long secs = totalTime / 1000;
    String executionTimeFormat = String.format("%02d:%02d:%02d", secs / 3600, (secs % 3600) / 60, secs % 60);
    
    for (ISuiteResult suiteResult : suiteResults) {
      if (index == 0) {
        builder.append(HtmlBuilder.appendTestMetricsHeader(executionTimeFormat));
      }
      builder.append(HtmlBuilder.buildTestMetricsTab(suiteResult));
      if (index == size-1) {
        builder.append(HtmlBuilder.appendTestMetricsFooter());
      }
      index++;
    }
    
  }

  private void generateMethodMetrics(ISuite suite){
    Collection<ISuiteResult> suiteResults = suite.getResults().values();
	int index = 0;
	int size = suiteResults.size();
	for (ISuiteResult suiteResult : suiteResults) {
	  if (index == 0) {
	    builder.append(HtmlBuilder.appendMethodMetricsHeader());
	  }
	  builder.append(HtmlBuilder.buildMethodMetricsTab(suiteResult));
	  if (index == size-1) {
	    builder.append(HtmlBuilder.appendMethodMetricsFooter());
	  }
	  index++;
    }
  }

  @SuppressWarnings("deprecation")
  @Override
  public void onStart(ITestContext context) {
    String archive_report =
        context.getCurrentXmlTest().getParameter("archive.testng.metrics.report");
    if(archive_report==null){
      archive_report = "false";
    }
    Method customMethod;
    outdir = "";
    if (archive_report.equalsIgnoreCase("yes") || archive_report.equalsIgnoreCase("true")) {
      String archivePath = "";
      try {
    	customMethod = TestNG.class.getMethod("setOutputDirectory", String.class);
        archivePath = System.getProperty("user.dir") + File.separator + "TestNg_Metrics_Reports" + File.separator;
        archivePath += "Results_" + new SimpleDateFormat("dd_MMM_yy_hh_mm_ss").format(new Date());
        try {
          if (!(new File(archivePath).mkdirs())) {
            throw new ReportConfigException("Failed to create the archive report directory");
          }
        } catch (Exception exception) {
          throw new ReportConfigException(exception.getMessage(), exception);
        }
        outdir = archivePath;
        Object customObject[] = {outdir};
        customMethod.invoke(TestNG.getDefault(), customObject);
        System.out.println("Custom output folder created at: " + outdir);
      } catch (Exception exception) {
        // e.printStackTrace();
        throw new ReportConfigException("Unable to set archive report directory", exception);
      }
    } else {
      try {
    	customMethod = TestNG.class.getMethod("getOutputDirectory");
        Object customObject[] = {};
        outdir = (String) customMethod.invoke(TestNG.getDefault(), customObject);
      } catch (Exception exception) {
        // e.printStackTrace();
        throw new ReportConfigException("Unable to read report directory", exception);
      }
    }

  }

  @SuppressWarnings("deprecation")
  @Override
  public void onFinish(ITestContext context) {
    Method customizedFinish;
    try {
      customizedFinish = TestNG.class.getMethod("getOutputDirectory");
      Object customObject[] = {};
      outdir = (String) customizedFinish.invoke(TestNG.getDefault(), customObject);

    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  @Override
  public void onTestStart(ITestResult result) {
    // not implemented
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    // not implemented
  }

  @Override
  public void onTestFailure(ITestResult result) {
    // not implemented
  }

  @Override
  public void onTestSkipped(ITestResult result) {
    // not implemented
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    // not implemented
  }

  @Override
  public void onTestFailedWithTimeout(ITestResult result) {
    onTestFailure(result);
  }

  public static String getOutdir() {
    return outdir;
  }

}
