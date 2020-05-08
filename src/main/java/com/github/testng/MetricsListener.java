package com.github.testng;

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

import com.github.internal.HtmlBuilder;
import com.github.internal.Utils;
import com.github.internal.Utils.ExecutionResults;

public class MetricsListener implements IReporter, ITestListener {
  private StringBuilder builder = new StringBuilder();
  private static String outdir;
  private static String metricsLogo;
  private static String metricsFileName;
  private static String metricsAppendTimeStamp;

  @Override
  public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
      String outputDirectory) {
    ExecutionResults results = Utils.computeOverResults(suites);
    builder.append(HtmlBuilder.buildHeaderAndTitle());
    builder.append(HtmlBuilder.buildDashBoard(results, metricsLogo));
    suites.forEach(this::generateReport);
    builder.append(HtmlBuilder.buildLogsTab());
    builder.append(HtmlBuilder.buildScriptContent());
    try {
      Utils.writeToFile(outputDirectory, builder.toString(), metricsFileName);
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
	    builder.append(HtmlBuilder.appendMetricsFooter());
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
        builder.append(HtmlBuilder.appendMetricsFooter());
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
	    builder.append(HtmlBuilder.appendMetricsFooter());
	  }
	  index++;
    }
  }

  @Override
  public void onStart(ITestContext context) {
	metricsLogo = context.getCurrentXmlTest().getParameter("testng.metrics.report.logo");
	metricsFileName = context.getCurrentXmlTest().getParameter("testng.metrics.report.name");
	metricsAppendTimeStamp = context.getCurrentXmlTest().getParameter("testng.metrics.report.appendTimestamp");
		  
	if (metricsFileName==null) {
		metricsFileName = "testng_metrics.html";
	}
	
	if (metricsLogo==null) {
		metricsLogo = "https://i.ibb.co/9qBkwDF/Testing-Fox-Logo.png";
	}
	
	if (metricsAppendTimeStamp != null) {
		if (metricsAppendTimeStamp.equalsIgnoreCase("true")) {
			String[] desiredText = metricsFileName.split(".html");
			metricsFileName = desiredText[0] + "_" + new SimpleDateFormat("dd_MMM_yy_hh_mm_ss").format(new Date()) + ".html";
		}
	}
	System.out.println("");
	System.out.println("Logo: " + metricsLogo);
    System.out.println("File name: " + metricsFileName);
    System.out.println("Append timestamp?: " + metricsAppendTimeStamp);
    System.out.println("File name(timestamp): " + metricsFileName);
    System.out.println("");
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
