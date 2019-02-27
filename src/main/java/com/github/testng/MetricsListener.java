package com.github.testng;

import com.github.internal.HtmlBuilder;
import com.github.internal.Utils;
import com.github.internal.Utils.ExecutionResults;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.xml.XmlSuite;

public class MetricsListener implements IReporter {
  private StringBuilder builder = new StringBuilder();

  @Override
  public void generateReport(
    List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
    String logo = System.getProperty("testng.metrics.logo", "https://i.ibb.co/0j28fBG/tmetricslogo.png");
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
}
