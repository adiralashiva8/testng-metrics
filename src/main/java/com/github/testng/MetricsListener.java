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
    Collection<ISuiteResult> suiteResults = suite.getResults().values();
    for (ISuiteResult suiteResult : suiteResults) {
      builder.append(HtmlBuilder.buildClassMetrics(suiteResult));
      builder.append(HtmlBuilder.buildTestMetricsTab(suiteResult));
      builder.append(HtmlBuilder.buildMethodMetricsTab(suiteResult));
    }
  }
}
