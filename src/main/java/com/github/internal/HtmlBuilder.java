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
		String scriptContent = " <script>\n"+
				" function createPieChart(passed, failed, skipped, ChartID, ChartName) {\n"+
				" var status = [];\n"+
				" status.push([\'Status\', \'Percentage\']);\n"+
				" status.push([\'PASS\', parseInt(passed)], [\'FAIL\', parseInt(failed)],[\'SKIP\', parseInt(skipped)]);\n"+
				" var data = google.visualization.arrayToDataTable(status);\n"+
				" var options = {\n"+
				" pieHole: 0.6,\n"+
				" legend: \'none\',\n"+
				" chartArea: {\n"+
				" width: \"95%\",\n"+
				" height: \"90%\"\n"+
				" },\n"+
				" colors: [\'#2ecc71\',\'#fc6666\',\'#CCCC00\'],\n"+
				" };\n"+
				" var chart = new google.visualization.PieChart(document.getElementById(ChartID));\n"+
				" chart.draw(data, options);\n"+
				" }\n"+
				" </script>\n"+
				" <script>\n"+
				" (function () {\n"+
				" var textFile = null,\n"+
				" makeTextFile = function (text) {\n"+
				" var data = new Blob([text], {\n"+
				" type: \'text/plain\'\n"+
				" });\n"+
				" if (textFile !== null) {\n"+
				" window.URL.revokeObjectURL(textFile);\n"+
				" }\n"+
				" textFile = window.URL.createObjectURL(data);\n"+
				" return textFile;\n"+
				" };\n"+
				" var create = document.getElementById(\'create\'),\n"+
				" textbox = document.getElementById(\'textbox\');\n"+
				" create.addEventListener(\'click\', function () {\n"+
				" var link = document.getElementById(\'downloadlink\');\n"+
				" link.href = makeTextFile(textbox.value);\n"+
				" link.style.display = \'block\';\n"+
				" }, false);\n"+
				" })();\n"+
				" </script>\n"+
				" <script>\n"+
				" function createBarGraph(tableID, keyword_column, time_column, limit, ChartID, Label, type) {\n"+
				" var status = [];\n"+
				" css_selector_locator = tableID + \' tbody >tr\'\n"+
				" var rows = $(css_selector_locator);\n"+
				" var columns;\n"+
				" var myColors = [\'#4F81BC\',\'#C0504E\',\'#9BBB58\',\'#24BEAA\',\'#8064A1\',\'#4AACC5\',\'#F79647\',\'#815E86\',\'#76A032\',\'#34558B\'];\n"+
				" status.push([type, Label, {role: \'annotation\'}, {role: \'style\'}]);\n"+
				" for (var i = 0; i < rows.length; i++) {\n"+
				" if (i == Number(limit)) {\n"+
				" break;\n"+
				" }\n"+
				" //status = [];\n"+
				" name_value = $(rows[i]).find(\'td\');\n"+
				" time = ($(name_value[Number(time_column)]).html()).trim();\n"+
				" keyword = ($(name_value[Number(keyword_column)]).html()).trim();\n"+
				" status.push([keyword, parseFloat(time), parseFloat(time), myColors[i]]);\n"+
				" }\n"+
				" var data = google.visualization.arrayToDataTable(status);\n"+
				" var options = {\n"+
				" legend: \'none\',\n"+
				" chartArea: { width: \"92%\", height: \"75%\"},\n"+
				" bar: { groupWidth: \'90%\'},\n"+
				" annotations: {\n"+
				" alwaysOutside: true,\n"+
				" textStyle: {\n"+
				" fontName: \'Comic Sans MS\',fontSize: 13,bold: true,italic: true,color: \"black\", },\n"+
				" },\n"+
				" hAxis: {\n"+
				" textStyle: {fontName: \'Arial\',fontSize: 10,}\n"+
				" },\n"+
				" vAxis: {\n"+
				" gridlines: {count: 10},\n"+
				" textStyle: {fontName: \'Comic Sans MS\',fontSize: 10,}\n"+
				" },\n"+
				" };\n"+
				" var chart = new google.visualization.ColumnChart(document.getElementById(ChartID));\n"+
				" chart.draw(data, options);\n"+
				" }\n"+
				" \n"+
				" </script>\n"+
				" \n"+
				" <script>\n"+
				"\t function executeDataTable(tabname,sortCol) {\n"+
				"\t\t\tvar fileTitle;\n"+
				"\t\t\tswitch(tabname) {\n"+
				"\t\t\t\tcase \"#cm\":\n"+
				"\t\t\t\t\tfileTitle = \"ClassMetrics\";\n"+
				"\t\t\t\t\tbreak;\n"+
				"\t\t\t\tcase \"#tm\":\n"+
				"\t\t\t\t\tfileTitle = \"TestMetrics\";\n"+
				"\t\t\t\t\tbreak;\n"+
				"\t\t\t\tcase \"#mm\":\n"+
				"\t\t\t\t\tfileTitle = \"MethodMetrics\";\n"+
				"\t\t\t\t\tbreak;\n"+
				"\t\t\t\tdefault:\n"+
				"\t\t\t\t\tfileTitle = \"metrics\";\n"+
				"\t\t\t}\n"+
				"\n"+
				"\t\t\t$(tabname).DataTable(\n"+
				"\t\t\t\t{\n"+
				"\t\t\t\t\tretrieve: true,\n"+
				"\t\t\t\t\t\"order\": [[ Number(sortCol), \"desc\" ]],\n"+
				"\t\t\t\t\tdom: \'l<\".margin\" B>frtip\',\n"+
				"\t\t\t\t\tbuttons: [\n"+
				"\t\t\t\t\t\t{\n"+
				"\t\t\t\t\t\t\textend: \'copyHtml5\',\n"+
				"\t\t\t\t\t\t\ttext: \'<i class=\"fa fa-files-o\"></i>\',\n"+
				"\t\t\t\t\t\t\tfilename: function() {\n"+
				"\t\t\t\t\t\t\t\treturn fileTitle + \'-\' + new Date().toLocaleString();\n"+
				"\t\t\t\t\t\t\t},\n"+
				"\t\t\t\t\t\t\ttitleAttr: \'Copy\',\n"+
				"\t\t\t\t\t\t\texportOptions: {\n"+
				"\t\t\t\t\t\t\t\tcolumns: \':visible\'\n"+
				"\t\t\t\t\t\t\t}\n"+
				"\t\t\t\t\t\t},\n"+
				"\n"+
				"\t\t\t\t\t\t{\n"+
				"\t\t\t\t\t\t\textend: \'csvHtml5\',\n"+
				"\t\t\t\t\t\t\ttext: \'<i class=\"fa fa-file-text-o\"></i>\',\n"+
				"\t\t\t\t\t\t\ttitleAttr: \'CSV\',\n"+
				"\t\t\t\t\t\t\tfilename: function() {\n"+
				"\t\t\t\t\t\t\t\treturn fileTitle + \'-\' + new Date().toLocaleString();\n"+
				"\t\t\t\t\t\t\t},\n"+
				"\t\t\t\t\t\t\texportOptions: {\n"+
				"\t\t\t\t\t\t\t\tcolumns: \':visible\'\n"+
				"\t\t\t\t\t\t\t}\n"+
				"\t\t\t\t\t\t},\n"+
				"\n"+
				"\t\t\t\t\t\t{\n"+
				"\t\t\t\t\t\t\textend: \'excelHtml5\',\n"+
				"\t\t\t\t\t\t\ttext: \'<i class=\"fa fa-file-excel-o\"></i>\',\n"+
				"\t\t\t\t\t\t\ttitleAttr: \'Excel\',\n"+
				"\t\t\t\t\t\t\tfilename: function() {\n"+
				"\t\t\t\t\t\t\t\treturn fileTitle + \'-\' + new Date().toLocaleString();\n"+
				"\t\t\t\t\t\t\t},\n"+
				"\t\t\t\t\t\t\texportOptions: {\n"+
				"\t\t\t\t\t\t\t\tcolumns: \':visible\'\n"+
				"\t\t\t\t\t\t\t}\n"+
				"\t\t\t\t\t\t},\n"+
				"\t\t\t\t\t\t{\n"+
				"\t\t\t\t\t\t\textend: \'print\',\n"+
				"\t\t\t\t\t\t\ttext: \'<i class=\"fa fa-print\"></i>\',\n"+
				"\t\t\t\t\t\t\ttitleAttr: \'Print\',\n"+
				"\t\t\t\t\t\t\texportOptions: {\n"+
				"\t\t\t\t\t\t\t\tcolumns: \':visible\',\n"+
				"\t\t\t\t\t\t\t\talignment: \'left\',\n"+
				"\t\t\t\t\t\t\t}\n"+
				"\t\t\t\t\t\t},\n"+
				"\t\t\t\t\t\t{\n"+
				"\t\t\t\t\t\t\textend: \'colvis\',\n"+
				"\t\t\t\t\t\t\tcollectionLayout: \'fixed two-column\',\n"+
				"\t\t\t\t\t\t\ttext: \'<i class=\"fa fa-low-vision\"></i>\',\n"+
				"\t\t\t\t\t\t\ttitleAttr: \'Hide Column\',\n"+
				"\t\t\t\t\t\t\texportOptions: {\n"+
				"\t\t\t\t\t\t\t\tcolumns: \':visible\'\n"+
				"\t\t\t\t\t\t\t},\n"+
				"\t\t\t\t\t\t\tpostfixButtons: [ \'colvisRestore\' ]\n"+
				"\t\t\t\t\t\t},\n"+
				"\t\t\t\t\t],\n"+
				"\t\t\t\t\tcolumnDefs: [ {\n"+
				"\t\t\t\t\t\tvisible: false,\n"+
				"\t\t\t\t\t} ]\n"+
				"\t\t\t\t}\n"+
				"\t\t\t);\n"+
				"\t\t}\n"+
				"\t </script>\n"+
				" \n"+
				"\n"+
				"\t <script>\n"+
				"\t function openPage(pageName,elmnt,color) {\n"+
				"\t\t\tvar i, tabcontent, tablinks;\n"+
				"\t\t\ttabcontent = document.getElementsByClassName(\"tabcontent\");\n"+
				"\t\t\tfor (i = 0; i < tabcontent.length; i++) {\n"+
				"\t\t\t\ttabcontent[i].style.display = \"none\";\n"+
				"\t\t\t}\n"+
				"\t\t\ttablinks = document.getElementsByClassName(\"tablink\");\n"+
				"\t\t\tfor (i = 0; i < tablinks.length; i++) {\n"+
				"\t\t\t\ttablinks[i].style.color = \"\";\n"+
				"\t\t\t}\n"+
				"\t\t\tdocument.getElementById(pageName).style.display = \"block\";\n"+
				"\t\t\telmnt.style.color = color;\n"+
				"\n"+
				"\t\t}\n"+
				"\t\t// Get the element with id=\"defaultOpen\" and click on it\n"+
				"\t\tdocument.getElementById(\"defaultOpen\").click();\n"+
				"\t </script>\n"+
				"\t <script>\n"+
				"\t // Get the element with id=\"defaultOpen\" and click on it\n"+
				"\t\tdocument.getElementById(\"defaultOpen\").click();\n"+
				"\t </script>\n"+
				"\t <script>\n"+
				"\t $(window).on(\'load\',function(){$(\'.loader\').fadeOut();});\n"+
				"\t </script>\n"+
				"\n"+
				" </div>\n"+
				"</body>\n"+
				"</html>";
		
		StringBuilder builder = new StringBuilder();
		builder.append(scriptContent);
		return builder;
	}

	public static StringBuilder buildLogsTab() {
		String logHeader="<div class=\"tabcontent\" id=\"log\" style=\"display: block;\"><p style=\"text-align:right\">"+
				"**<b>index.html</b> need to be in current folder in order to display here</p>\n"+
				"<div class=\"embed-responsive embed-responsive-4by3\"><iframe class=\"embed-responsive-item\" src=\"index.html\"></iframe></div></div>";
		
		StringBuilder builder = new StringBuilder();
		builder.append(logHeader);
		return builder;
	}

	public static StringBuilder appendMethodMetricsHeader() {
		String methodMetricsHeader = " <div class=\"tabcontent\" id=\"methodMetrics\">\n"+
				" <h4><b><i class=\"fa fa-table\" style=\"color:STEELBLUE\"></i> Method Metrics</b></h4>\n"+
				" <hr/>\n"+
				" <table class=\"table row-border tablecard\" id=\"mm\">\n"+
				" <thead>\n"+
				" <tr>\n"+
				" <th>Class Name</th>\n"+
				" <th>Method Name</th>\n"+
				" <th>Status</th>\n"+
				" <th>Time(s)</th>\n"+
				" <th>Error Message</th>\n"+
				" </tr>\n"+
				" </thead>\n"+
				" <tbody>\n";
		StringBuilder builder = new StringBuilder();
		System.out.println("4 of 4: Capturing method metrics...");
		builder.append(methodMetricsHeader);
		return builder;
	}

	// "4 of 4: Capturing method metrics..."
	public static StringBuilder buildMethodMetricsTab(ISuiteResult suiteResult) {
		// METHOD METRICS TAB
		StringBuilder builder = new StringBuilder();
		// Method Metrics
		gatherTestInformation(suiteResult, builder, false);
		return builder;
	}

	public static StringBuilder appendTestMetricsHeader(String executionTime) {
		
		String testMetricsHeader = " <div class=\"tabcontent\" id=\"testMetrics\">\n"+
				" <h4><b><i class=\"fa fa-list-alt\" style=\"color:PALEVIOLETRED\"></i> Test Metrics</b></h4>\n"+
				" <h6 style=\"text-align:right\">Total Execution Time: <b style=\"color:Red; text-align:right\">__TIME__</b></h6>\n"+
				" <hr/>\n"+
				" </h6>\n"+
				" <table class=\"table row-border tablecard\" id=\"tm\">\n"+
				" <thead>\n"+
				" <tr>\n"+
				" <th>Class Name</th>\n"+
				" <th>Test Name</th>\n"+
				" <th>Status</th>\n"+
				" <th>Time(s)</th>\n"+
				" <th>Error Message</th>\n"+
				" </tr>\n"+
				" </thead>\n"+
				" <tbody>\n";

		testMetricsHeader = testMetricsHeader.replace("__TIME__", executionTime);
		StringBuilder builder = new StringBuilder();
		System.out.println("3 of 4: Capturing test metrics...");
		builder.append(testMetricsHeader);
		return builder;
	}

	// "3 of 4: Capturing test metrics..."
	public static StringBuilder buildTestMetricsTab(ISuiteResult suiteResult) {
		StringBuilder builder = new StringBuilder();
		// Test Metrics
		gatherTestInformation(suiteResult, builder, true);
		return builder;
	}

	public static StringBuilder appendClassMetricsHeader() {
		System.out.println("2 of 4: Capturing class metrics...");
		String classMetricsHeader = " <div class=\"tabcontent\" id=\"classMetrics\">\n"+
				" <h4><b><i class=\"fa fa-th-large\" style=\"color:CADETBLUE\"></i> Class Metrics</b></h4>\n"+
				" <hr/>\n"+
				" <table class=\"table row-border tablecard\" id=\"cm\">\n"+
				" <thead>\n"+
				" <tr>\n"+
				" <th>Class Name</th>\n"+
				" <th>Total</th>\n"+
				" <th>Passed</th>\n"+
				" <th>Failed</th>\n"+
				" <th>Skipped</th>\n"+
				" <th>Pass (%)</th>\n"+
				" </tr>\n"+
				" </thead>\n"+
				" <tbody>\n";
		
		StringBuilder builder = new StringBuilder();
		builder.append(classMetricsHeader);
		return builder;
	}

	public static StringBuilder appendMetricsFooter() {
		String metricsFooter = " </tbody>\n"+
				" </table>\n"+
				" <div class=\"row\">\n"+
				" <div class=\"col-md-12\" style=\"height:25px;width:auto;\"></div>\n"+
				" </div>\n"+
				" </div>\n";
		StringBuilder builder = new StringBuilder();
		builder.append(metricsFooter);
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

			builder.append("<tr>\r\n")
					.append(String.format(
							"<td style=\"word-wrap: break-word;max-width: 300px; white-space: normal;text-align:left;\">%s</td>\r\n",
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
			float cPassPercentage = (cPassSteps * 100) / cTotalSteps;
			builder.append(String.format("<td style=\"color:teal\">%s</td>\r\n", cTotalSteps))
					.append(String.format("<td style=\"color:green\">%s</td>\r\n", cPassSteps))
					.append(String.format("<td style=\"color:red\">%s</td>\r\n", cFailSteps))
					.append(String.format("<td style=\"color:#9E6B6A\">%s</td>\r\n", cSkipSetps))
					.append(String.format("<td>%s</td>\r\n", cPassPercentage))
					.append("</tr>\r\n ");
		}

		return builder;
	}

	// "1 of 4: Capturing dashboard statistics..."
	public static StringBuilder buildDashBoard(ExecutionResults results, String logo) {
		System.out.println("1 of 4: Capturing dashboard statistics...");
		
		String dashboard = " <div class=\"loader\"></div>\n"+
				" \n"+
				"\t<div class=\"sidenav\">\n"+
				"\t <a><img class=\"wrimagecard\" src=\"__LOGO__\" style=\"height:20vh;max-width:98%;\"/></a>\n"+
				"\t <a class=\"tablink\" href=\"#\" id=\"defaultOpen\" onclick=\"openPage(\'dashboard\', this, \'#fc6666\')\"><i class=\"fa fa-dashboard\" style=\"color:CORNFLOWERBLUE\"></i> Dashboard</a>\n"+
				"\t <a class=\"tablink\" href=\"#\" onclick=\"openPage(\'classMetrics\', this, \'#fc6666\'); executeDataTable(\'#cm\',3)\"><i class=\"fa fa-th-large\" style=\"color:CADETBLUE\"></i> Class Metrics</a>\n"+
				" <a class=\"tablink\" href=\"#\" onclick=\"openPage(\'testMetrics\', this, \'#fc6666\'); executeDataTable(\'#tm\',3)\"><i class=\"fa fa-list-alt\" style=\"color:PALEVIOLETRED\"></i> Test Metrics</a>\n"+
				"\t <a class=\"tablink\" href=\"#\" onclick=\"openPage(\'methodMetrics\', this, \'#fc6666\'); executeDataTable(\'#mm\',3)\"><i class=\"fa fa-table\" style=\"color:STEELBLUE\"></i> Method Metrics</a>\n"+
				"\t <a class=\"tablink\" href=\"#\" onclick=\"openPage(\'log\', this, \'#fc6666\');\"><i class=\"fa fa-wpforms\" style=\"color:CHOCOLATE\"></i> Logs</a>\n"+
				" \t</div>\n"+
				" \n"+
				" <div class=\"main col-md-9 ml-sm-auto col-lg-10 px-4\">\n"+
				" <div class=\"tabcontent\" id=\"dashboard\">\n"+
				" <div class=\"d-flex flex-column flex-md-row align-items-center p-1 mb-3 bg-light border-bottom shadow-sm rowcard\">\n"+
				" <h5 class=\"my-0 mr-md-auto font-weight-normal\"><i class=\"fa fa-dashboard\" style=\"color:CORNFLOWERBLUE\"></i> Dashboard</h5>\n"+
				" </div>\n"+
				" <div class=\"row rowcard\">\n"+
				"\t\t\t<div class=\"col-md-6 border-right\">\n"+
				"\t\t\t\t<table style=\"width:100%;height:100px;text-align: center;\">\n"+
				"\t\t\t\t\t<tbody>\n"+
				"\t\t\t\t\t\t<tr style=\"height:100%\">\n"+
				"\t\t\t\t\t\t\t<td>\n"+
				"\t\t\t\t\t\t\t\t<table style=\"width:100%\">\n"+
				"\t\t\t\t\t\t\t\t\t<tbody>\n"+
				"\t\t\t\t\t\t\t\t\t\t<tr style=\"height:100%\">\n"+
				"\t\t\t\t\t\t\t\t\t\t\t<td style=\"font-size:60px; color:rgb(105, 135, 219)\">__TOTAL__</td>\n"+
				"\t\t\t\t\t\t\t\t\t\t\t<td style=\"font-size:60px; color:#2ecc71\">__PASS__</td>\n"+
				"\t\t\t\t\t\t\t\t\t\t</tr>\n"+
				"\t\t\t\t\t\t\t\t\t\t<tr>\n"+
				"\t\t\t\t\t\t\t\t\t\t\t<td>\n"+
				"\t\t\t\t\t\t\t\t\t\t\t\t<span style=\"color: #999999;font-size:12px\">Total</span>\n"+
				"\t\t\t\t\t\t\t\t\t\t\t</td>\n"+
				"\t\t\t\t\t\t\t\t\t\t\t<td>\n"+
				"\t\t\t\t\t\t\t\t\t\t\t\t<span style=\"color: #999999;font-size:12px\">Pass</span>\n"+
				"\t\t\t\t\t\t\t\t\t\t\t</td>\n"+
				"\t\t\t\t\t\t\t\t\t\t</tr>\n"+
				"\t\t\t\t\t\t\t\t\t</tbody>\n"+
				"\t\t\t\t\t\t\t\t</table>\n"+
				"\t\t\t\t\t\t\t</td>\n"+
				"\t\t\t\t\t\t</tr>\n"+
				"\t\t\t\t\t</tbody>\n"+
				"\t\t\t\t</table>\n"+
				"\t\t\t</div>\n"+
				"\t\t\t<div class=\"col-md-6 borders\">\n"+
				"\t\t\t\t<table style=\"width:100%;height:100px;text-align: center;\">\n"+
				"\t\t\t\t\t<tbody>\n"+
				"\t\t\t\t\t\t<tr style=\"height:100%\">\n"+
				"\t\t\t\t\t\t\t<td>\n"+
				"\t\t\t\t\t\t\t\t<table style=\"width:100%\">\n"+
				"\t\t\t\t\t\t\t\t\t<tbody>\n"+
				"\t\t\t\t\t\t\t\t\t\t<tr style=\"height:100%\">\n"+
				"\t\t\t\t\t\t\t\t\t\t\t<td style=\"font-size:60px; color:#fc6666\">__FAIL__</td>\n"+
				"\t\t\t\t\t\t\t\t\t\t\t<td style=\"font-size:60px; color:#9E6B6A\">__SKIP__</td>\n"+
				"\t\t\t\t\t\t\t\t\t\t</tr>\n"+
				"\t\t\t\t\t\t\t\t\t\t<tr>\n"+
				"\t\t\t\t\t\t\t\t\t\t\t<td>\n"+
				"\t\t\t\t\t\t\t\t\t\t\t\t<span style=\"color: #999999;font-size:12px\">Fail</span>\n"+
				"\t\t\t\t\t\t\t\t\t\t\t</td>\n"+
				"\t\t\t\t\t\t\t\t\t\t\t<td>\t\t\t\t\t\t\t\t\t\t\t\t\n"+
				"\t\t\t\t\t\t\t\t\t\t\t\t<span style=\"color: #999999;font-size:12px\">Skip</span>\n"+
				"\t\t\t\t\t\t\t\t\t\t\t</td>\n"+
				"\t\t\t\t\t\t\t\t\t\t</tr>\n"+
				"\t\t\t\t\t\t\t\t\t</tbody>\n"+
				"\t\t\t\t\t\t\t\t</table>\n"+
				"\t\t\t\t\t\t\t</td>\n"+
				"\t\t\t\t\t\t</tr>\n"+
				"\t\t\t\t\t</tbody>\n"+
				"\t\t\t\t</table>\n"+
				"\t\t\t</div>\n"+
				"\t\t</div>\n"+
				" <hr/>\n"+
				" <div class=\"row rowcard\">\n"+
				" <div class=\"col-md-4\" style=\"height:350px;width:auto;\">\n"+
				" <span style=\"font-weight:bold;color:gray\">Test Status:</span>\n"+
				" <div id=\"testChartID\" style=\"height:280px;width:auto;\"></div>\n"+
				" </div>\n"+
				" <div class=\"col-md-8\" style=\"height:350px;width:auto;\">\n"+
				" <span style=\"font-weight:bold;color:gray\">Top 5 Class Failures:</span>\n"+
				" <div id=\"classBarID\" style=\"height:300px;width:auto;\"></div>\n"+
				" </div>\n"+
				" </div>\n"+
				"\t\t <hr/>\n"+
				" <div class=\"row rowcard\">\n"+
				" <div class=\"col-md-12\" style=\"height:450px;width:auto;\">\n"+
				" <span style=\"font-weight:bold;color:gray\">Top 10 Test Performance(sec):</span>\n"+
				" <div id=\"testsBarID\" style=\"height:400px;width:auto;\"></div>\n"+
				" </div>\n"+
				"\t\t </div>\n"+
				"\t\t <hr/>\n"+
				" <div class=\"row rowcard\">\n"+
				" <div class=\"col-md-12\" style=\"height:450px;width:auto;\">\n"+
				" <span style=\"font-weight:bold;color:gray\">Top 10 Config Methods Performance(sec):</span>\n"+
				" <div id=\"methodsBarID\" style=\"height:400px;width:auto;\"></div>\n"+
				" </div>\n"+
				" </div>\n"+
				"\n"+
				" <div class=\"row\">\n"+
				"\t\t\t<div class=\"col-md-12\" style=\"height:25px;width:auto;\">\n"+
				"\t\t\t\t<p class=\"text-muted\" style=\"text-align:center;font-size:9px\">\n"+
				"\t\t\t\t\t<a href=\"https://github.com/adiralashiva8/testng-metrics\" style=\"color:gray\" target=\"_blank\">testng-metrics</a>\n"+
				"\t\t\t\t</p>\n"+
				"\t\t\t</div>\n"+
				"\t\t</div>\n"+
				"\n"+
				" <script>\n"+
				" window.onload = function(){\n"+
				" executeDataTable(\'#cm\',3);\n"+
				" executeDataTable(\'#tm\',3);\n"+
				" \texecuteDataTable(\'#mm\',3);\n"+
				" \tcreatePieChart(__PASS__,__FAIL__,__SKIP__,\'testChartID\',\'Test Status:\');\n"+
				" \tcreateBarGraph(\'#cm\',0,3,5,\'classBarID\',\'Number of failures \',\'Class\'); \n"+
				" \tcreateBarGraph(\'#tm\',1,3,10,\'testsBarID\',\'Elapsed Time(s) \',\'Test\');\n"+
				" \tcreateBarGraph(\'#mm\',1,3,10,\'methodsBarID\',\'Elapsed Time(s) \',\'Method\');\n"+
				" \t};\n"+
				" \n"+
				" </script> \n"+
				" </div>\n";
		
		dashboard = dashboard.replaceAll("__LOGO__", logo);
		dashboard = dashboard.replaceAll("__TOTAL__", String.valueOf(results.getTotal()));
		dashboard = dashboard.replaceAll("__PASS__", String.valueOf(results.getPassed()));
		dashboard = dashboard.replaceAll("__FAIL__", String.valueOf(results.getFailed()));
		dashboard = dashboard.replaceAll("__SKIP__", String.valueOf(results.getSkipped()));
		StringBuilder builder = new StringBuilder();
		builder.append(dashboard);
		return builder;
	}

	public static StringBuilder buildHeaderAndTitle() {
		String header="<!DOCTYPE doctype html>\n"+
				"<html lang=\"en\">\n"+
				" <head>\n"+
				" <link href=\"https://img.icons8.com/flat_round/64/000000/bar-chart.png\" rel=\"shortcut icon\" type=\"image/x-icon\"/>\n"+
				" <title>TestNG Metrics Report</title>\n"+
				" <meta charset=\"utf-8\"/>\n"+
				" <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\"/>\n"+
				" <link href=\"https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css\" rel=\"stylesheet\"/>\n"+
				" <link href=\"https://cdn.datatables.net/buttons/1.5.2/css/buttons.dataTables.min.css\" rel=\"stylesheet\"/>\n"+
				" <link href=\"https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css\" rel=\"stylesheet\"/>\n"+
				" <link href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\" rel=\"stylesheet\"/>\n"+
				" <script src=\"https://code.jquery.com/jquery-3.3.1.js\" type=\"text/javascript\"></script>\n"+
				" <!-- Bootstrap core Googleccharts -->\n"+
				" <script src=\"https://www.gstatic.com/charts/loader.js\" type=\"text/javascript\"></script>\n"+
				" <script type=\"text/javascript\">\n"+
				" google.charts.load(\'current\', {\n"+
				" packages: [\'corechart\']\n"+
				" });\n"+
				" </script>\n"+
				" <!-- Bootstrap core Datatable-->\n"+
				" <script src=\"https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js\" type=\"text/javascript\"></script>\n"+
				" <script src=\"https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js\" type=\"text/javascript\"></script>\n"+
				" <script src=\"https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js\" type=\"text/javascript\"></script>\n"+
				" <script src=\"https://cdn.datatables.net/buttons/1.5.2/js/buttons.html5.min.js\" type=\"text/javascript\"></script>\n"+
				" <script src=\"https://cdn.datatables.net/buttons/1.5.2/js/buttons.print.min.js\" type=\"text/javascript\"></script>\n"+
				" <script src=\"https://cdn.datatables.net/buttons/1.6.1/js/buttons.colVis.min.js\" type=\"text/javascript\"></script>\n"+
				" <style>\n"+
				" body {font-family: -apple-system, sans-serif;background-color: #eeeeee;}\n"+
				" .sidenav {height: 100%;width: 220px;position: fixed;z-index: 1;top: 0;left: 0;background-color: white;overflow-x: hidden;}\n"+
				" .sidenav a {padding: 12px 10px 8px 12px;text-decoration: none;font-size: 18px;color: Black;display: block;}\n"+
				" .main {padding-top: 10px;}\n"+
				" @media screen and (max-height: 450px) {.sidenav {padding-top: 15px;}.sidenav a {font-size: 18px;}}\n"+
				" .wrimagecard {margin-top: 0;margin-bottom: 0.6rem;border-radius: 10px;transition: all 0.3s ease;background-color: #f8f9fa;}\n"+
				" .rowcard {padding-top: 10px;box-shadow: 12px 15px 20px 0px rgba(46, 61, 73, 0.15);border-radius: 15px;transition: all 0.3s ease;background-color: white;}\n"+
				" .tablecard {background-color: white;font-size: 15px;}tr {height: 40px;}\n"+
				" .dt-buttons {margin-left: 5px;}th, td, tr {text-align:center;vertical-align: middle;}\n"+
				" .loader {position: fixed;left: 0px;top: 0px;width: 100%;height: 100%;z-index: 9999;\n"+
				" background: url(\'https://i.ibb.co/cXnKsNR/Cube-1s-200px.gif\') 50% 50% no-repeat rgb(249, 249, 249);}\n"+
				" </style>\n"+
				" </head>\n"+
				"</html>\n"+
				"<body>\n";
		// append html header and title
		StringBuilder builder = new StringBuilder();
		builder.append(header);
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
				// errorMessage =
				// org.testng.internal.Utils.longStackTrace(result.getThrowable(), false);
				errorMessage = result.getThrowable().getMessage();
			}
			long duration = result.getEndMillis() - result.getStartMillis();
			builder.append(String.format("<tr><td style=\"word-wrap: break-word;max-width: 200px; white-space: normal; text-align:left\">%s</td>\r\n",className))
					.append(String.format("<td style=\"word-wrap: break-word;max-width: 250px; white-space: normal;; text-align:left\">%s</td>\r\n",methodName));

			if (Utils.getStatusString(result) == "PASS") {
				builder.append(String.format("<td style=\"color: green\">%s</td>\r\n", Utils.getStatusString(result)));
			} else if (Utils.getStatusString(result) == "FAIL") {
				builder.append(String.format("<td style=\"color: red\">%s</td>\r\n", Utils.getStatusString(result)));
			} else if (Utils.getStatusString(result) == "SKIP") {
				builder.append(String.format("<td style=\"color: #9E6B6A\">%s</td>\r\n", Utils.getStatusString(result)));
			} else {
				builder.append(String.format("<td>%s</td>\r\n", Utils.getStatusString(result)));
			}

			builder.append(String.format("<td style=\"text-align:center\">%s</td>\r\n", duration / 1000))
					.append(String.format("<td style=\"word-wrap: break-word;max-width: 300px; white-space: normal; text-align:left\">%s</td>\r\n",errorMessage))
					.append("</tr>\r\n");
		}
	}
}
