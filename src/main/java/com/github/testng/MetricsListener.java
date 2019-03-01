package com.github.testng;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
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
        System.getProperty("testng.metrics.logo", "https://i.ibb.co/0j28fBG/tmetricslogo.png");
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
	for (ISuiteResult suiteResult : suiteResults) {
	  if (index == 0) {
	    builder.append(HtmlBuilder.appendTestMetricsHeader());
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
    Method f;
    outdir = "";
    if (archive_report.equalsIgnoreCase("yes") || archive_report.equalsIgnoreCase("true")) {
      String archivePath = "";
      try {
        f = TestNG.class.getMethod("setOutputDirectory", String.class);
        DateFormat dtYearFormat = new SimpleDateFormat("yyyy");
        DateFormat dtMonthFormat = new SimpleDateFormat("M");
        String strCurrYear = dtYearFormat.format(new Date());
        String strCurrMonth = dtMonthFormat.format(new Date());
        archivePath = System.getProperty("user.home") + File.separator + "TestNg_Metrics_Reports"
            + File.separator + File.separator + strCurrYear + File.separator
            + theMonth(Integer.parseInt(strCurrMonth)) + File.separator;
        archivePath +=
            new SimpleDateFormat("ddMMMyy_hhmmss").format(new Date()) + "_" + "TestExecution";
        try {
          if (!(new File(archivePath).mkdirs())) {
            throw new ReportConfigException("Failed to create the archive report directory");
          }
        } catch (Exception e) {
          throw new ReportConfigException(e.getMessage(), e);
        }
        outdir = archivePath;
        Object a[] = {outdir};
        f.invoke(TestNG.getDefault(), a);
      } catch (Exception e) {
        // e.printStackTrace();
        throw new ReportConfigException("Unable to set archive report directory", e);
      }
    } else {
      try {
        f = TestNG.class.getMethod("getOutputDirectory");
        Object a[] = {};
        outdir = (String) f.invoke(TestNG.getDefault(), a);
      } catch (Exception e) {
        // e.printStackTrace();
        throw new ReportConfigException("Unable to read report directory", e);
      }
    }

  }

  @SuppressWarnings("deprecation")
  @Override
  public void onFinish(ITestContext context) {
    Method f;
    try {
      f = TestNG.class.getMethod("getOutputDirectory");
      Object a[] = {};
      outdir = (String) f.invoke(TestNG.getDefault(), a);

    } catch (Exception e) {
      e.printStackTrace();
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

  private static String theMonth(int month) {
    String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August",
        "September", "October", "November", "December"};
    return monthNames[month - 1];
  }

  public static String getOutdir() {
    return outdir;
  }

}
