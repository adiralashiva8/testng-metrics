package com.github.internal;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.testng.ISuiteResult;
import org.testng.ITestResult;

import com.github.internal.Utils.ExecutionResults;

public class HtmlBuilder {

  public static StringBuilder buildScriptContent() {
    // SCRIPT CONTENT
    StringBuilder builder = new StringBuilder();
    builder
        .append(
            "<script>\r\n function createPieChart(passed, failed, skipped, ChartID, ChartName) {\r\n")
        .append("var status = [];\r\n  status.push(['Status', 'Percentage']);\r\n")
        .append(
            "status.push(['PASS', parseInt(passed)], ['FAIL', parseInt(failed)],['SKIP', parseInt(skipped)]);\r\n")
        .append(
            "var data = google.visualization.arrayToDataTable(status);\r\n  var options = {\r\n    pieHole: 0.6,\r\n    legend: 'none',\r\n")
        .append(
            "chartArea: {\r\n      width: \"95%\",\r\n      height: \"90%\"\r\n    },\r\n    colors: ['#4CAF50','#f44336','#CCCC00'],\r\n  };\r\n")
        .append(
            "var chart = new google.visualization.PieChart(document.getElementById(ChartID));\r\n  chart.draw(data, options);\r\n}\r\n</script>\r\n")
        .append(
            "<script>\r\n  (function () {\r\n  var textFile = null,\r\n    makeTextFile = function (text) {\r\n      var data = new Blob([text], {\r\n")
        .append(
            "type: 'text/plain'\r\n      });\r\n      if (textFile !== null) {\r\n        window.URL.revokeObjectURL(textFile);\r\n      }\r\n")
        .append(
            "textFile = window.URL.createObjectURL(data);\r\n      return textFile;\r\n    };\r\n  var create = document.getElementById('create'),\r\n")
        .append(
            "textbox = document.getElementById('textbox');\r\n  create.addEventListener('click', function () {\r\n")
        .append(
            "var link = document.getElementById('downloadlink');\r\n    link.href = makeTextFile(textbox.value);\r\n")
        .append(
            "link.style.display = 'block';\r\n  }, false);\r\n })();\r\n </script>\r\n <script>\r\n")
        .append(
            "function createBarGraph(tableID, keyword_column, time_column, limit, ChartID, Label, type) {\r\n")
        .append(
            "var status = [];\r\n  css_selector_locator = tableID + ' tbody >tr'\r\n  var rows = $(css_selector_locator);\r\n")
        .append(
            "var columns;\r\n  var myColors = ['#4F81BC','#C0504E','#9BBB58','#24BEAA','#8064A1','#4AACC5','#F79647','#815E86','#76A032','#34558B'];\r\n")
        .append(
            "status.push([type, Label, {role: 'annotation'}, {role: 'style'}]);\r\n  for (var i = 0; i < rows.length; i++) {\r\n    if (i == Number(limit)) {\r\n")
        .append(
            "break;\r\n    }\r\n    //status = [];\r\n    name_value = $(rows[i]).find('td');\r\n    time = ($(name_value[Number(time_column)]).html()).trim();\r\n")
        .append(
            "keyword = ($(name_value[Number(keyword_column)]).html()).trim();\r\n    status.push([keyword, parseFloat(time), parseFloat(time), myColors[i]]);\r\n")
        .append(
            "}\r\n  var data = google.visualization.arrayToDataTable(status);\r\n  var options = {\r\n    legend: 'none',\r\n")
        .append(
            "chartArea: { width: \"92%\", height: \"75%\"},\r\n    bar: { groupWidth: '90%'},\r\n    annotations: {\r\n")
        .append(
            "alwaysOutside: true,\r\n      textStyle: {\r\n        fontName: 'Comic Sans MS',fontSize: 13,bold: true,italic: true,color: \"black\", },\r\n")
        .append(
            "},\r\n    hAxis: {\r\n      textStyle: {fontName: 'Arial',fontSize: 10,}\r\n    },\r\n    vAxis: {\r\n      gridlines: {count: 10},\r\n")
        .append("textStyle: {fontName: 'Comic Sans MS',fontSize: 10,}\r\n    },\r\n  };\r\n")
        .append(
            "var chart = new google.visualization.ColumnChart(document.getElementById(ChartID));\r\n")
        .append(
            "chart.draw(data, options);\r\n }\r\n </script>\r\n <script>\r\n  function executeDataTable(tabname, sortCol) {\r\n")
        .append(
            "var fileTitle;\r\n  switch (tabname) {\r\n    case \"#cm\":\r\n      fileTitle = \"ClassMetrics\";\r\n")
        .append(
            "break;\r\n    case \"#tm\":\r\n      fileTitle = \"TestMetrics\";\r\n      break;\r\n    default:\r\n")
        .append(
            "fileTitle = \"metrics\";\r\n  }\r\n  $(tabname).DataTable({\r\n    retrieve: true,\r\n\t\"order\": [[Number(sortCol), \"desc\"]],\r\n")
        .append(
            "dom: 'l<\".margin\" B>frtip',\r\n    buttons: [\r\n      'copy',\r\n      {\r\n        extend: 'csv',\r\n")
        .append(
            "filename: function () {\r\n          return fileTitle + '-' + new Date().toLocaleString();\r\n")
        .append(
            "},\r\n        title: '',\r\n      },\r\n      {\r\n        extend: 'excel',\r\n        filename: function () {\r\n")
        .append(
            "return fileTitle + '-' + new Date().toLocaleString();\r\n        },\r\n        title: '',\r\n      },\r\n      {\r\n")
        .append(
            "extend: 'pdf',\r\n        filename: function () {\r\n          return fileTitle + '-' + new Date().toLocaleString();\r\n")
        .append(
            "},\r\n        title: '',\r\n      },\r\n      {\r\n        extend: 'print',\r\n        title: '',\r\n      },\r\n")
        .append(
            "],\r\n  });\r\n }\r\n </script>\r\n <script>\r\n function openPage(pageName, elmnt, color) {\r\n  var i, tabcontent, tablinks;\r\n")
        .append(
            "tabcontent = document.getElementsByClassName(\"tabcontent\");\r\n  for (i = 0; i < tabcontent.length; i++) {\r\n")
        .append(
            "tabcontent[i].style.display = \"none\";\r\n  }\r\n  tablinks = document.getElementsByClassName(\"tablink\");\r\n")
        .append(
            "for (i = 0; i < tablinks.length; i++) {\r\n    tablinks[i].style.backgroundColor = \"\";\r\n  }\r\n")
        .append(
            "document.getElementById(pageName).style.display = \"block\";\r\n  elmnt.style.backgroundColor = color;\r\n }\r\n")
        .append(
            "// Get the element with id=\"defaultOpen\" and click on it\r\n document.getElementById(\"defaultOpen\").click();\r\n")
        .append(
            "</script>\r\n <script>\r\n document.getElementById(\"defaultOpen\").click();\r\n </script>\r\n <script>\r\n")
        .append("$(window).on('load',function(){$('.loader').fadeOut();});\r\n </script>")
        .append("\r\n </div>");
    builder.append("\r\n</body>\n</html>");
    return builder;
  }

  public static StringBuilder buildEmailTab(ExecutionResults results) {
    StringBuilder builder = new StringBuilder();
    builder
        .append(
            "<div class=\"tabcontent\" id=\"statistics\">\r\n   <h4><b><i class=\"fa fa-envelope-o\"></i> Email Statistics</b></h4><hr/>\r\n")
        .append(
            "<button class=\"btn btn-primary active inner\" id=\"create\" onclick=\"updateTextArea();this.style.visibility= 'hidden';\" role=\"button\">\r\n")
        .append("<i class=\"fa fa-cogs\"></i> Generate Statistics Email</button>\r\n")
        .append(
            "<a class=\"btn btn-primary active inner\" download=\"message.eml\" id=\"downloadlink\" role=\"button\" style=\"display: none; width: 300px;\">\r\n")
        .append(
            "<i class=\"fa fa-download\"></i> Click Here To Download Email</a>\r\n   <script>\r\n    function updateTextArea() {\r\n")
        .append(
            "var suite = \"<b>Top 5 Class Failures:</b><br><br>\" + $(\"#classBarID table\")[0].outerHTML;\r\n")
        .append(
            "var test = \"<b>Top 10 Test Performance:</b><br><br>\" + $(\"#testsBarID table\")[0].outerHTML;\r\n\t")
        .append(
            "var method = \"<b>Top 10 Method Performance:</b><br><br>\" + $(\"#methodsBarID table\")[0].outerHTML;\r\n")
        .append(
            "var saluation=\"<pre><br>Please refer TestNG Metrics Report for detailed statistics.<br><br>Regards,<br>QA Team</pre></body></html>\";\r\n")
        .append(
            "document.getElementById(\"textbox\").value += \"<br>\" + suite + \"<br>\" + test + \"<br>\" + method + saluation;\r\n")
        .append(
            "$(\"#create\").click(function(){\r\n    $(this).remove();\r\n    });\r\n}\r\n   </script>\r\n")
        .append(
            "<textarea class=\"col-md-12\" id=\"textbox\" style=\"height: 400px; padding:1em;\">\r\nTo: myemail1234@email.com\r\n")
        .append(
            "Subject: Automation Execution Status\r\nX-Unsent: 1\r\nContent-Type: text/html\r\n\r\n\r\n")
        .append(
            "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n\r\n")
        .append(
            "<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n<head>\r\n<title>Test Email Sample</title>\r\n")
        .append(
            "<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>\r\n<meta content=\"IE=edge\" http-equiv=\"X-UA-Compatible\"/>\r\n")
        .append(
            "<meta content=\"width=device-width, initial-scale=1.0 \" name=\"viewport\"/>\r\n  <style>body {background-color:#F2F2F2; }\r\n\t")
        .append(
            "body, html, table,pre,b { font-family: Courier New, Arial, sans-serif; font-size: 1em; }.pastdue { color: crimson; }\r\n\t")
        .append(
            "table {border: 1px solid silver;padding: 6px; margin-left: 30px; width: 600px;}\r\n\t thead")
        .append(
            "{text-align: center;font-size: 1.1em;background-color: #B0C4DE;font-weight: bold;color: #2D2C2C;}\r\n\t tbody")
        .append("{text-align: center;}th {width: 25%;word-wrap:break-word;}\r\n</style></head>\r\n")
        .append(
            "<body><pre>Hi Team,\r\nFollowing are the last build execution statistics.\r\n\r\n<b>Test Status:<b>\r\n\r\n</b></b></pre>\r\n\t<table>\r\n\t")
        .append(
            "<thead>\r\n\t\t<th style=\"width: 25%;\">Total</th>\r\n\t\t<th style=\"width: 25%;\">Pass</th>\r\n\t\t")
        .append(
            "<th style=\"width: 25%;\">Fail</th>\r\n\t\t<th style=\"width: 25%;\">Skip</th>\r\n\t"
            + " </thead>\r\n\t<tbody><tr>\r\n\t\t")
        .append(
            String.format(
                "<td style=\"background-color: #009688;text-align: center;\"> %s </td>\r\n\t\t",
                    results.getTotal()))
        .append(
            String.format(
                "<td style=\"background-color: #4CAF50;text-align: center;\">%s</td>\r\n\t\t",
                results.getPassed()))
        .append(
            String.format(
                "<td style=\"background-color: #f44336;text-align: center;\">%s</td>", results.getFailed()))
        .append(
            String.format(
                "\r\n\t\t<td style=\"background-color: #CCCC00;text-align: center;\">%s</td>\r\n\t",
                results.getSkipped()))
        .append("</tr></tbody>\r\n\t</table>\r\n\r\n</body></html></textarea>\r\n</div></div>");
    return builder;
  }

  public static StringBuilder buildLogsTab() {
    StringBuilder builder = new StringBuilder();
    builder
        .append(
            "<div class=\"tabcontent\" id=\"log\"><p style=\"text-align:right\">**<b>index.html</b> need to be in current folder in order to display here</p>\r\n")
        .append(
            "<div class=\"embed-responsive embed-responsive-4by3\"><iframe class=\"embed-responsive-item\" src=\"index.html\"></iframe></div></div>");
    return builder;
  }

  public static StringBuilder appendMethodMetricsHeader(){
    StringBuilder builder = new StringBuilder();
	System.out.println("4 of 4: Capturing method metrics...");
	builder
    .append(
        "<div class=\"tabcontent\" id=\"methodMetrics\">\r\n   <h4><b><i class=\"fa fa-table\"></i> Method Metrics</b></h4><hr/>\r\n")
    .append(
        "<table class=\"table table-striped table-bordered\" id=\"mm\">\r\n    <thead><tr>\r\n      <th>Class Name</th>\r\n")
    .append(
        "<th>Method Name</th>\r\n      <th>Status</th>\r\n      <th>Time(s)</th>\r\n\t  <th>Error Message</th>\r\n    </tr></thead>\r\n")
    .append("<tbody>\r\n     ");
	return builder;
  }

  public static StringBuilder appendMethodMetricsFooter(){
    StringBuilder builder = new StringBuilder();
    builder.append(
        "</tbody>\r\n   "
            + "</table>\r\n<div class=\"row\"><div class=\"col-md-12\" style=\"height:25px;width:auto;\"></div>"
            + "</div></div>");
	return builder;
  }

  // "4 of 4: Capturing method metrics..."
  public static StringBuilder buildMethodMetricsTab(ISuiteResult suiteResult) {
    // METHOD METRICS TAB
	StringBuilder builder = new StringBuilder();
    // Method Metrics
    gatherTestInformation(suiteResult, builder,false);
    return builder;
  }

  public static StringBuilder appendTestMetricsHeader(String executionTime){
    StringBuilder builder = new StringBuilder();
    System.out.println("3 of 4: Capturing test metrics...");
    
	builder
    .append(
        "<div class=\"tabcontent\" id=\"testMetrics\">\r\n   <h4><b><i class=\"fa fa-table\"></i> Test Metrics</b></h4>"
        + "<h6 style=\"text-align:right\">Total Execution Time: <b style=\"color:Red; text-align:right\">" + executionTime + "</b></h6><hr/> </h6>\r\n")
    .append(
        "<table class=\"table table-striped table-bordered\" id=\"tm\">\r\n    <thead><tr>\r\n      <th>Class Name</th>\r\n")
    .append(
        "<th>Test Name</th>\r\n      <th>Status</th>\r\n      <th>Time(s)</th>\r\n\t  <th>Error Message</th>\r\n    </tr></thead>\r\n")
    .append("<tbody>\r\n     ");
	return builder;
  }

  public static StringBuilder appendTestMetricsFooter(){
    StringBuilder builder = new StringBuilder();
    builder
    .append("</tbody>\r\n   ")
    .append(
        "</table>\r\n<div class=\"row\"><div class=\"col-md-12\" style=\"height:25px;width:auto;\"></div>")
    .append("</div></div>");
	return builder;
  }

  // "3 of 4: Capturing test metrics..."
  public static StringBuilder buildTestMetricsTab(ISuiteResult suiteResult) {
    StringBuilder builder = new StringBuilder();
    // Test Metrics
    gatherTestInformation(suiteResult, builder,true);
    return builder;
  }

  public static StringBuilder appendClassMetricsHeader() {
	  System.out.println("2 of 4: Capturing class metrics...");
	  StringBuilder builder = new StringBuilder();
	  builder
      .append(
          "<div class=\"tabcontent\" id=\"classMetrics\">\r\n   <h4><b><i class=\"fa fa-table\"></i> Class Metrics</b></h4><hr/>\r\n")
      .append(
          "<table class=\"table table-striped table-bordered\" id=\"cm\">\r\n<thead><tr>\r\n<th>Class Name</th>\r\n<th>Total</th>\r\n")
      .append(
          "<th>Passed</th>\r\n<th>Failed</th>\r\n\t<th>Skipped</th>\r\n"
          + "<th>Pass (%)</th>\r\n</tr></thead>\r\n<tbody>\r\n");
	  return builder;
  }

  public static StringBuilder appendClassMetricsFooter() {
	  StringBuilder builder = new StringBuilder();
	  builder
      .append("</tbody>\r\n   ")
      .append(
          "</table>\r\n<div class=\"row\"><div class=\"col-md-12\" style=\"height:25px;width:auto;\"></div>")
      .append("</div></div>");
	  return builder;
  }

  // "2 of 4: Capturing class metrics..."
  public static StringBuilder buildClassMetrics(ISuiteResult suiteResult) {
    // CLASS METRICS TAB
    StringBuilder builder = new StringBuilder();

    Set<ITestResult> allResults = Utils.extractResults(suiteResult);
    Map<String, List<ITestResult>> classMap = Utils.asMap(allResults);
    for (Entry<String, List<ITestResult>> each : classMap.entrySet()) {
      int cTotalSteps = 0;
      int cPassSteps = 0;
      int cFailSteps = 0;
      int cSkipSetps = 0;

      String fullClassName = each.getKey();
      String className = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);

      builder
          .append("<tr>\r\n")
          .append(
              String.format(
                  "<td style=\"word-wrap: break-word;max-width: 300px; white-space: normal\">%s</td>\r\n",
                  className));
      for (ITestResult eachResult : each.getValue()) {
	    if (!eachResult.getMethod().isTest()) {
		  continue;
		}
        switch (eachResult.getStatus()) {
          case ITestResult.SUCCESS:
            cPassSteps++;
            break;
          case ITestResult.FAILURE:
            cFailSteps++;
            break;
          case ITestResult.SKIP:
            cSkipSetps++;
        }
      }
      cTotalSteps = cPassSteps + cFailSteps + cSkipSetps;
      float cPassPercentage = (cPassSteps*100)/cTotalSteps;
      builder
          .append(String.format("<td style=\"text-align:center\">%s</td>\r\n", cTotalSteps))
          .append(String.format("<td style=\"text-align:center\">%s</td>\r\n", cPassSteps))
          .append(String.format("<td style=\"text-align:center\">%s</td>\r\n", cFailSteps))
          .append(String.format("<td style=\"text-align:center\">%s</td>\r\n", cSkipSetps))
          .append(String.format("<td style=\"text-align:center\">%s</td>\r\n", cPassPercentage))
          .append("</tr>\r\n ");
    }

    return builder;
  }

  // "1 of 4: Capturing dashboard statistics..."
  public static StringBuilder buildDashBoard(ExecutionResults results, String logo) {
    StringBuilder builder = new StringBuilder();

    System.out.println("1 of 4: Capturing dashboard statistics...");
    builder
        .append(
            "<div class=\"loader\"></div>\r\n  <div class=\"container-fluid\">\r\n  <div class=\"row\">\r\n")
        .append(
            "<nav class=\"col-md-2 d-none d-md-block bg-light sidebar\" style=\"font-size:16px;\">\r\n")
        .append("<div class=\"sidebar-sticky\">\r\n<ul class=\"nav flex-column\">\r\n")
        .append(
            String.format("<img src=%s style=\"height:18vh!important;width:95%%;\"/>\r\n", logo))
        .append(
            "<br/>\r\n      <h6 class=\"sidebar-heading d-flex justify-content-between align-items-center text-muted\">\r\n")
        .append(
            "<span>Metrics</span><a class=\"d-flex align-items-center text-muted\" href=\"#\"></a></h6>\r\n      <li class=\"nav-item\">\r\n")
        .append(
            "<a class=\"tablink nav-link\" href=\"#\" id=\"defaultOpen\" onclick=\"openPage('dashboard', this, 'orange')\">\r\n\t")
        .append(
            "<i class=\"fa fa-dashboard\"></i> Dashboard</a>\r\n      </li>\r\n      <li class=\"nav-item\">\r\n")
        .append(
            "<a class=\"tablink nav-link\" href=\"#\" onclick=\"openPage('classMetrics', this, 'orange');executeDataTable('#cm',3)\">\r\n\t")
        .append(
            "<i class=\"fa fa-list-alt\"></i> Class Metrics</a>\r\n      </li>\r\n      <li class=\"nav-item\">\r\n")
        .append(
            "<a class=\"tablink nav-link\" href=\"#\" onclick=\"openPage('testMetrics', this, 'orange');executeDataTable('#tm',3)\">\r\n\t")
        .append(
            "<i class=\"fa fa-table\"></i> Test Metrics</a>\r\n      </li>\r\n\t  <li class=\"nav-item\">\r\n")
        .append(
            "<a class=\"tablink nav-link\" href=\"#\" onclick=\"openPage('methodMetrics', this, 'orange');executeDataTable('#mm',3)\">\r\n\t")
        .append(
            "<i class=\"fa fa-table\"></i> Method Metrics</a>\r\n      </li>\r\n      <li class=\"nav-item\">\r\n ")
        .append(
            "<a class=\"tablink nav-link\" href=\"#\" onclick=\"openPage('log', this, 'orange');\"><i class=\"fa fa-wpforms\"></i> TestNG Logs</a>\r\n")
        .append(
            "</li>\r\n      <li class=\"nav-item\">\r\n       <a class=\"tablink nav-link\" href=\"#\" onclick=\"openPage('statistics', this, 'orange');\">")
        .append(
            "<i class=\"fa fa-envelope-o\"></i> Email Metrics</a>\r\n      </li>\r\n")
         .append("     </ul>\r\n    </div>\r\n   </nav>\r\n  </div>\r\n </div>");
    	
    // DASHBOARD CONTENT
    builder
        .append(
            "<div class=\"col-md-9 ml-sm-auto col-lg-10 px-4\" role=\"main\">\r\n  <div class=\"tabcontent\" id=\"dashboard\">\r\n")
        .append(
            "<div class=\"d-flex flex-column flex-md-row align-items-center p-1 mb-3 bg-light border-bottom shadow-sm\">\r\n")
        .append(
            "<h5 class=\"my-0 mr-md-auto font-weight-normal\"><i class=\"fa fa-dashboard\"></i> Dashboard</h5>\r\n")
        .append("<nav class=\"my-2 my-md-0 mr-md-3\" style=\"color:red\">\r\n")
        
        .append(buildViewInfo())
        .append(
            String.format(
                "</nav>\r\n   </div>\r\n   <div class=\"row\">\r\n    <div class=\"col-md\"><a class=\"tile tile-info\">%s<p style=\"font-size:15px\">Total Tests</p></a>",
                results.getTotal()))
        .append(
            String.format(
                "</div>\r\n    <div class=\"col-md\"><a class=\"tile tile-pass\">%s<p style=\"font-size:15px\">Pass</p></a></div>\r\n\t",
                results.getPassed()))
        .append(
            String.format(
                "<div class=\"col-md\"><a class=\"tile tile-fail\">%s<p style=\"font-size:15px\">Fail</p></a></div>\r\n\t",
                results.getFailed()))
        .append(
            String.format(
                "<div class=\"col-md\"><a class=\"tile tile-skip\">%s<p style=\"font-size:15px\">Skip</p></a></div>\r\n   ",
                results.getSkipped()))
        .append(
            "</div><hr/>\r\n<div class=\"row\">\r\n<div class=\"col-md-4\" style=\"background-color:white;height:350px;width:auto;border:groove;\">\r\n")
        .append("<span style=\"font-weight:bold\">Test Status:</span>\r\n     <div id=\"testChartID\" style=\"height:280px;width:auto;\"></div>\r\n")
        .append(
            "</div>\r\n    <div class=\"col-md-8\" style=\"background-color:white;height:350px;width:auto;border:groove;\">\r\n")
        .append(
            "<span style=\"font-weight:bold\">Top 5 Class Failures:</span>\r\n     <div id=\"classBarID\" style=\"height:300px;width:auto;\"></div>\r\n")
        .append(
            "</div>\r\n   </div>\r\n   <div class=\"row\">\r\n    <div class=\"col-md-12\" style=\"background-color:white;height:450px;width:auto;border:groove;\">\r\n")
        .append(
            "<span style=\"font-weight:bold\">Top 10 Test Performance(sec):</span>\r\n     <div id=\"testsBarID\" style=\"height:400px;width:auto;\"></div>\r\n")
        .append(
            "</div>\r\n\t<div class=\"col-md-12\" style=\"background-color:white;height:450px;width:auto;border:groove;\">\r\n")
        .append(
            "<span style=\"font-weight:bold\">Top 10 Config Methods Performance(sec):</span>\r\n     <div id=\"methodsBarID\" style=\"height:400px;width:auto;\"></div>\r\n")
        .append(
            "</div>\r\n   </div>\r\n   <div class=\"row\"><div class=\"col-md-12\" style=\"height:25px;width:auto;\">\r\n")
        .append(
            "<p class=\"text-muted\" style=\"text-align:center;font-size:9px\">testng-metrics</p></div>\r\n   </div>\r\n   <script>\r\n")
        .append(
            "window.onload = function(){\r\n    executeDataTable('#cm',3);\r\n    executeDataTable('#tm',3);\r\n\texecuteDataTable('#mm',3);\r\n\t")
        .append(
            String.format(
                "createPieChart(%1$s,%2$s,%3$s,'testChartID','Test Status:');\r\n\t",
                results.getPassed(), results.getFailed(), results.getSkipped()))
        .append("createBarGraph('#cm',0,3,5,'classBarID','Number of failures ','Class'); \r\n\t")
        .append("createBarGraph('#tm',1,3,10,'testsBarID','Elapsed Time(s) ','Test');\r\n\t")
        .append("createBarGraph('#mm',1,3,10,'methodsBarID','Elapsed Time(s) ','Method');\r\n\t};\r\n   </script>")
        .append("  \r\n</div>");
    return builder;
  }
  
  public static StringBuilder buildViewInfo() {
      StringBuilder builder = new StringBuilder();
      // append html header and title
      builder
          .append("<button type=\"button\" class=\"btn\" data-toggle=\"modal\" data-target=\"#myModal\"><i class=\"fa fa-desktop\"></i> View Info</button>\r\n")
          .append("<!-- The Modal -->\r\n  <div class=\"modal\" id=\"myModal\">\r\n    <div class=\"modal-dialog\">\r\n")
          .append("<div class=\"modal-content\">\r\n      \r\n        <!-- Modal Header -->\r\n        <div class=\"modal-header\">\r\n")
          .append("<h4 class=\"modal-title\">Execution Info</h4>\r\n          <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>\r\n")
          .append("</div>\r\n   \r\n        <!-- Modal body -->\r\n")
          .append("<div class=\"modal-body\">\r\n\t\t\t<table class=\"table\">\r\n\t\t\t\t<tbody>\r\n\t\t\t\t")
          .append(
                  String.format(
            	      "</tr>\r\n\t\t\t\t  <tr>\r\n\t\t\t\t\t<td>Executed By:</td>\r\n\t\t\t\t\t<td>%s</td>\r\n\t\t\t\t",System.getProperty("user.name")))
          .append(
                  String.format(
            	      "</tr>\r\n\t\t\t\t  <tr>\r\n\t\t\t\t\t<td>OS Name:</td>\r\n\t\t\t\t\t<td>%s</td>\r\n\t\t\t\t",System.getProperty("os.name")))
          .append(
              String.format(
        	      "</tr>\r\n\t\t\t\t  <tr>\r\n\t\t\t\t\t<td>Java Version:</td>\r\n\t\t\t\t\t<td>%s</td>\r\n\t\t\t\t",System.getProperty("java.version")))
          .append(
                  String.format(
            	      "</tr>\r\n\t\t\t\t  <tr>\r\n\t\t\t\t\t<td>Generated Time:</td>\r\n\t\t\t\t\t<td>%s</td>\r\n\t\t\t\t",new java.util.Date()))
          .append("</tr>\r\n\t\t\t\t</tbody>\r\n\t\t\t</table>\r\n        </div>\r\n")
          .append("\r\n        <!-- Modal footer -->\r\n        <div class=\"modal-footer\">\r\n")
          .append("<button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">Close</button>\r\n")
          .append("</div>\r\n        \r\n      </div>\r\n    </div>\r\n  </div>");
    return builder;
  }
  
  public static StringBuilder buildHeaderAndTitle() {
    StringBuilder builder = new StringBuilder();
    // append html header and title
    builder
        .append("<!DOCTYPE doctype html>\r\n<html lang=\"en\">\r\n <head>\r\n")
        .append(
            "<link href=\"https://img.icons8.com/flat_round/64/000000/bar-chart.png\" rel=\"shortcut icon\" type=\"image/x-icon\"/>\r\n")
        .append(
            "<title> TestNG Metrics Report</title>\r\n  <meta charset=\"utf-8\"/>\r\n  <meta content=\"width=device-width, initial-scale=1, shrink-to-fit=no\" name=\"viewport\"/>\r\n")
        .append("<meta content=\"width=device-width, initial-scale=1\" name=\"viewport\"/>\r\n")
        .append(
            "<link href=\"https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css\" rel=\"stylesheet\"/>\r\n")
        .append(
            "<link href=\"https://cdn.datatables.net/buttons/1.5.2/css/buttons.dataTables.min.css\" rel=\"stylesheet\"/>\r\n")
        .append(
            "<link href=\"https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css\" rel=\"stylesheet\"/>\r\n")
        .append(
            "<link href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\" rel=\"stylesheet\"/>\r\n")
        .append(
            "<script src=\"https://code.jquery.com/jquery-3.3.1.js\" type=\"text/javascript\"></script>\r\n  <!-- Bootstrap core Googleccharts -->\r\n")
        .append(
            "<script src=\"https://www.gstatic.com/charts/loader.js\" type=\"text/javascript\"></script>\r\n  <script type=\"text/javascript\">\r\n")
        .append(
            "google.charts.load('current', {packages: ['corechart']});\r\n  </script>\r\n  <!-- Bootstrap core Datatable-->\r\n")
        .append(
            "<script src=\"https://code.jquery.com/jquery-3.3.1.js\" type=\"text/javascript\"></script>\r\n")
        .append(
            "<script src=\"https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js\" type=\"text/javascript\"></script>\r\n")
        .append(
            "<script src=\"https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js\" type=\"text/javascript\"></script>\r\n")
        .append(
            "<script src=\"https://cdn.datatables.net/buttons/1.5.2/js/buttons.flash.min.js\" type=\"text/javascript\"></script>\r\n")
        .append(
            "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js\" type=\"text/javascript\"></script>\r\n")
        .append(
            "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js\" type=\"text/javascript\"></script>\r\n")
        .append(
            "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js\" type=\"text/javascript\"></script>\r\n")
        .append(
            "<script src=\"https://cdn.datatables.net/buttons/1.5.2/js/buttons.html5.min.js\" type=\"text/javascript\"></script>\r\n")
        .append(
            "<script src=\"https://cdn.datatables.net/buttons/1.5.2/js/buttons.print.min.js\" type=\"text/javascript\"></script>"
            + "\r\n<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js\"></script>\r\n<style>\r\n")
        .append(
            ".sidebar { position: fixed; top: 0; bottom: 0;left: 0; z-index: 100; box-shadow: inset -1px 0 0 rgba(0, 0, 0, .1);} \r\n")
        .append(
            ".sidebar-sticky { position: relative;top: 0; height: calc(100vh - 48px); padding-top: .5rem; overflow-x: hidden; overflow-y: auto;}\r\n")
        .append(
            "@supports ((position: -webkit-sticky) or (position: sticky)) { .sidebar-sticky { position: -webkit-sticky; position: sticky;}}\r\n")
        .append(
            ".sidebar .nav-link {color: black;}.sidebar .nav-link.active { color: #007bff;}.sidebar .nav-link:hover .feather,\r\n")
        .append(
            ".sidebar .nav-link.active .feather {color: inherit;} [role=\"main\"] {padding-top: 8px;} body {height: 100%;margin: 0; background-color: white;}\r\n")
        .append(
            ".tablinkLog {cursor: pointer;}\r\n@import url(https://fonts.googleapis.com/css?family=Droid+Sans);\r\n")
        .append(
            ".loader { position: fixed; left: 0px; top: 0px; width: 100%;height: 100%;z-index: 9999;\r\n")
        .append(
            "background: url('http://www.downgraf.com/wp-content/uploads/2014/09/01-progress.gif?e44397') 50% 50% no-repeat rgb(249,249,249);}\r\n")
        .append(
            "/* TILES */\r\n.tile { width: 100%; float: left;margin: 0px;list-style: none;font-size: 40px;color: #FFF;-moz-border-radius: 5px;\r\n")
        .append(
            "-webkit-border-radius: 5px;margin-bottom: 5px;position: relative;text-align: center;color: white!important;}\r\n")
        .append(
            ".tile.tile-fail {background: #f44336!important;}.tile.tile-pass {background: #4CAF50!important;}\r\n")
        .append(
            ".tile.tile-info {background: #009688!important;}.tile.tile-skip {background: #CCCC00!important;}.dt-buttons {margin-left: 5px;}\r\n</style></head></html>");
    builder.append("<body>");
    return builder;
  }

  private static void gatherTestInformation(ISuiteResult suiteResult, StringBuilder builder, boolean isTest) {
    Set<ITestResult> results = Utils.extractResults(suiteResult);
    for (ITestResult result : results) {
      if (isTest) {
        if (!result.getMethod().isTest()) {
          continue;
        }
      } else {
        if (result.getMethod().isTest()) {
          continue;
        }
      }

      String fullClassName = result.getTestClass().getName();
      String className = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);
	  String methodName = result.getMethod().getMethodName();
	  String errorMessage = "";
	  Object[] testParameters = result.getParameters();

	  if (testParameters.length > 0 && testParameters != null) {
		  methodName = methodName + " (" + Arrays.toString(testParameters) + ")";
	  }

      if (result.getThrowable() != null) {
    	//errorMessage = org.testng.internal.Utils.longStackTrace(result.getThrowable(), false);
        errorMessage = result.getThrowable().getMessage();
      }
      long duration = result.getEndMillis() - result.getStartMillis();
      builder
          .append("<tr>\r\n      ")
          .append(
              String.format(
                  "<td style=\"word-wrap: break-word;max-width: 200px; white-space: normal\">%s</td>\r\n",
                  className))
          .append(
              String.format(
                  "<td style=\"word-wrap: break-word;max-width: 250px; white-space: normal;\">%s</td>\r\n",
                  methodName))
          .append(String.format("<td style=\"text-align:center\">%s</td>\r\n", Utils.getStatusString(result)))
          .append(String.format("<td style=\"text-align:center\">%s</td>\r\n", duration / 1000))
          .append(
              String.format(
                  "<td style=\"word-wrap: break-word;max-width: 300px; white-space: normal\">%s</td>\r\n",
                  errorMessage))
          .append("     </tr>\r\n");
    }
  }
}
