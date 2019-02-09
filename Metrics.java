import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Metrics{

	static String logo = "https://cdn.pixabay.com/photo/2016/08/02/10/42/wifi-1563009_960_720.jpg";

	public static void main(String[] args) {

		int total, passed, failed, skipped = 0;

		String path = System.getProperty("user.dir")+"/test-output/testng-results.xml";
	    File testNgResultXmlFile = new File(path);		

	  	//Get Document Builder
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

	    //Build Document
	    Document document = null;
		try {
			document = builder.parse(testNgResultXmlFile);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Normalize the XML Structure;
	    document.getDocumentElement().normalize();
		System.out.println("Converting .xml to .html file. This may take few minutes...");
		
	    // *************** DASHBOARD CONTENT **********************
		try {
			//define a HTML String Builder
            StringBuilder htmlStringBuilder=new StringBuilder();
            //append html header and title
            htmlStringBuilder.append(""
            		+ "<html><head>\r\n  <link href=\"https://img.icons8.com/ios/64/000000/selenium-test-automation.png\" rel=\"shortcut icon\" type=\"image/x-icon\"/>\r\n"
            		+ "<title>TestNG Metrics Report</title>\r\n  <meta charset=\"utf-8\"/>\r\n  <meta content=\"width=device-width, initial-scale=1, shrink-to-fit=no\" name=\"viewport\"/>\r\n"
            		+ "<meta content=\"width=device-width, initial-scale=1\" name=\"viewport\"/>\r\n  <link href=\"https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css\" rel=\"stylesheet\"/>\r\n "
            		+ "<link href=\"https://cdn.datatables.net/buttons/1.5.2/css/buttons.dataTables.min.css\" rel=\"stylesheet\"/>\r\n"
            		+ "<link href=\"https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css\" rel=\"stylesheet\"/>\r\n"
            		+ "<link href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\" rel=\"stylesheet\"/>\r\n"
            		+ "<script src=\"https://code.jquery.com/jquery-3.3.1.js\" type=\"text/javascript\">\r\n  </script>\r\n"
            		+ "<!-- Bootstrap core Googleccharts -->\r\n  <script src=\"https://www.gstatic.com/charts/loader.js\" type=\"text/javascript\">\r\n  </script>\r\n"
            		+ "<script type=\"text/javascript\">\r\n   google.charts.load('current', {packages: ['corechart']});\r\n  </script>\r\n"
            		+ "<!-- Bootstrap core Datatable-->\r\n  <script src=\"https://code.jquery.com/jquery-3.3.1.js\" type=\"text/javascript\">\r\n  </script>\r\n"
            		+ "<script src=\"https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js\" type=\"text/javascript\">\r\n  </script>\r\n"
            		+ "<script src=\"https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js\" type=\"text/javascript\">\r\n  </script>\r\n"
            		+ "<script src=\"https://cdn.datatables.net/buttons/1.5.2/js/buttons.flash.min.js\" type=\"text/javascript\">\r\n  </script>\r\n"
            		+ "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js\" type=\"text/javascript\">\r\n </script>\r\n  "
            		+ "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js\" type=\"text/javascript\">\r\n </script>\r\n"
            		+ "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js\" type=\"text/javascript\">\r\n"
            		+ "</script>\r\n  <script src=\"https://cdn.datatables.net/buttons/1.5.2/js/buttons.html5.min.js\" type=\"text/javascript\">\r\n </script>\r\n"
            		+ "</script>\r\n  <script src=\"https://cdn.datatables.net/buttons/1.5.2/js/buttons.print.min.js\" type=\"text/javascript\">\r\n  </script>\r\n\r\n  "
            		+ "<style>\r\n   .sidebar {\r\nposition: fixed;\r\ntop: 0;\r\nbottom: 0;\r\nleft: 0;\r\n"
            		+ "z-index: 100; /* Behind the navbar */\r\nbox-shadow: inset -1px 0 0 rgba(0, 0, 0, .1);\r\n}\r\n\r\n"
            		+ ".sidebar-sticky {\r\n position: relative;\r\ntop: 0;\r\nheight: calc(100vh - 48px);\r\n"
            		+ "padding-top: .5rem;\r\noverflow-x: hidden;\r\noverflow-y: auto; /* Scrollable contents if viewport is shorter than content. */\r\n        }"
            		+ "\r\n@supports ((position: -webkit-sticky) or (position: sticky)) {\r\n.sidebar-sticky {\r\nposition: -webkit-sticky;\r\n"
            		+ "position: sticky;\r\n}\r\n}\r\n\r\n.sidebar .nav-link {\r\ncolor: black;\r\n}\r\n"
            		+ "\r\n.sidebar .nav-link.active {\r\ncolor: #007bff;\r\n}\r\n\r\n "
            		+ ".sidebar .nav-link:hover .feather,\r\n.sidebar .nav-link.active "
            		+ ".feather {\r\ncolor: inherit;\r\n}\r\n\r\n[role=\"main\"] {\r\npadding-top: 8px;\r\n}\r\n"
            		+ "\r\n   tbackground-color: white;\r\n\t\t}\r\n\r\n\t\t/* Style tab links */\r\n\t\t.tablinkLog {\r\n\t\t\tcursor: pointer;\r\n\t\t}\r\n\t\t\r\n"
            		+ "@import url(https://fonts.googleapis.com/css?family=Droid+Sans);\r\n\t\t.loader"
            		+ " {\r\n\t\t\tposition: fixed;\r\n\t\t\tleft: 0px;\r\n\t\t\ttop: 0px;\r\n\t\t\twidth: 100%;\r\n\t\t\theight: 100%;\r\n\t\t\tz-index: 9999;\r\n\t\t\t"
            		+ "background: url('http://www.downgraf.com/wp-content/uploads/2014/09/01-progress.gif?e44397') 50% 50% no-repeat rgb(249,249,249);\r\n\t\t}"
            		+ "\r\n\r\n\t\t/* TILES */\r\n\t\t.tile {\r\n\t\t  width: 100%;\r\n\t\t  float: left;\r\n\t\t  margin: 0px;\r\n\t\t  list-style: none;\r\n\t\t"
            		+ "  font-size: 40px;\r\n\t\t  color: #FFF;\r\n\t\t  -moz-border-radius: 5px;\r\n\t\t  -webkit-border-radius: 5px;\r\n\t\t  margin-bottom: 5px;\r\n\t\t"
            		+ "  position: relative;\r\n\t\t  text-align: center;\r\n\t\t  color: white!important;\r\n\t\t}\r\n\r\n\t\t.tile.tile-fail {\r\n\t\t"
            		+ "  background: #f44336!important;\r\n\t\t}\r\n\t\t.tile.tile-pass {\r\n\t\t  background: #4CAF50!important;\r\n\t\t}\r\n\t\t.tile.tile-skip {\r\n\t\t"
            		+ "  background: #009688!important;\r\n\t\t}\r\n\t\t.tile.tile-head {\r\n\t\t  background: #616161!important;\r\n\t\t}\r\n"
            		+ ".dt-buttons {\r\nmargin-left: 5px;\r\n}\r\n  </style></head>");
        	
            //append body
            htmlStringBuilder.append("<body>");
			System.out.println("1 of 2: Capturing dashboard statistics...");
			htmlStringBuilder.append(""
					+ "<nav class=\"col-md-2 d-none d-md-block bg-light sidebar\" style=\"font-size:16px;\">\r\n"
					+ "<div class=\"sidebar-sticky\">\r\n<ul class=\"nav flex-column\">\r\n"			
					+ String.format("<img src=%s style=\"height:18vh!important;width:95%%;\"/>\r\n", logo)
					+ "\r\n\t\t\t\t<br>\r\n\t\t\t\t\r\n\t\t\t\t<h6 class=\"sidebar-heading d-flex justify-content-between align-items-center text-muted\">\r\n"
					+ "<span>Metrics</span>\r\n<a class=\"d-flex align-items-center text-muted\" href=\"#\"></a>\r\n"
					+ "</h6>\r\n<li class=\"nav-item\">\r\n<a class=\"tablink nav-link\" href=\"#\" id=\"defaultOpen\" onclick=\"openPage('dashboard', this, 'orange')\">"
					+ "\r\n\t\t\t\t\t\t\t\t<i class=\"fa fa-dashboard\"></i> Dashboard\r\n\t\t\t\t\t\t\t</a>\r\n"
					+ "</li>\r\n<li class=\"nav-item\">\r\n<a class=\"tablink nav-link\" href=\"#\" onclick=\"openPage('testMetrics', this, 'orange');executeDataTable('#tm',3)\">"
					+ "\r\n\t\t\t\t\t\t\t  <i class=\"fa fa-list-alt\"></i> Test Metrics\r\n\t\t\t\t\t\t\t</a>\r\n"
					+ "</li>\r\n<li class=\"nav-item\">\r\n<a class=\"tablink nav-link\" href=\"#\" onclick=\"openPage('statistics', this, 'orange');\">\r\n\t\t\t\t\t\t\t"
					+ "<i class=\"fa fa-envelope-o\"></i> Email Metrics\r\n\t\t\t\t\t\t\t</a>\r\n"
					+ "</li>\r\n</ul>\r\n</div>\r\n</nav>\r\n</div>\r\n</div>\n");

			total = Integer.parseInt(document.getDocumentElement().getAttribute("total"));
			passed = Integer.parseInt(document.getDocumentElement().getAttribute("passed"));
			failed = Integer.parseInt(document.getDocumentElement().getAttribute("failed"));
	   		skipped = Integer.parseInt(document.getDocumentElement().getAttribute("skipped"));

			//DASHBOARD CONTENT
			htmlStringBuilder.append(""
					+ "<div class=\"col-md-9 ml-sm-auto col-lg-10 px-4\" role=\"main\">\r\n<div class=\"tabcontent\" id=\"dashboard\">\r\n\r\n"
					+ "<div class=\"d-flex flex-column flex-md-row align-items-center p-1 mb-3 bg-light border-bottom shadow-sm\">\r\n"
					+ "<h5 class=\"my-0 mr-md-auto font-weight-normal\"><i class=\"fa fa-dashboard\"></i> Dashboard</h5>\r\n"

					+ String.format("</div>\r\n\r\n<div class=\"row\">\r\n<div class=\"col-md-3\">\r\n<a class=\"tile tile-head\"> %s\r\n<p style=\"font-size:15px\">Total</p>\r\n",total)
					+ String.format("</a>\r\n</div>\r\n<div class=\"col-md-3\">\r\n<a class=\"tile tile-pass\">%s\r\n<p style=\"font-size:15px\">Pass</p>\r\n",passed)
					+ String.format("</a>\r\n </div>\t\t\t\t\t\t\r\n <div class=\"col-md-3\">\r\n<a class=\"tile tile-fail\">%s\r\n<p style=\"font-size:15px\">Fail</p>\r\n",failed)
					+ String.format("</a>\r\n </div>\r\n <div class=\"col-md-3\"> \r\n <a class=\"tile tile-skip\">%s\r\n <p style=\"font-size:15px\">Skip</p>\r\n </a>\r\n",skipped));
			
			htmlStringBuilder.append(""
					+ "</div>\r\n </div>\r\n\r\n <hr></hr>\r\n <div class=\"row\">\r\n <div class=\"col-md-12\" style=\"background-color:white;height:450px;width:auto;border:groove;\">\r\n"
					+ "<span style=\"font-weight:bold\">Top 10 Test Performance(sec):</span>\r\n <div id=\"testsBarID\" style=\"height:400px;width:auto;\"></div>\r\n"
					+ "</div>\r\n </div>\r\n <div class=\"row\">\r\n <div class=\"col-md-12\" style=\"height:25px;width:auto;\">\r\n"
					+ "<p class=\"text-muted\" style=\"text-align:center;font-size:9px\">testng-metrics</p>\r\n </div>\r\n </div>\r\n \r\n <script>\r\n window.onload = function(){\r\n executeDataTable('#tm',3);\t\r\n"
					+ "createBarGraph('#tm',1,3,10,'testsBarID','Elapsed Time(s): ','Test');\r\n };\r\n </script>\r\n <script>\r\n function openInNewTab(url,element_id) {\r\n"
					+ "var element_id= element_id;\r\n var win = window.open(url, '_blank');\r\n win.focus();\r\n $('body').scrollTo(element_id); \r\n }\r\n </script>\r\n</div>\n");
			
			System.out.println("2 of 2: Capturing test metrics...");
			
			// TEST METRICS TAB
			htmlStringBuilder.append(""
					+ "<div class=\"tabcontent\" id=\"testMetrics\">\r\n <h4><b><i class=\"fa fa-table\"></i> Test Metrics</b></h4>\r\n <hr/>\r\n"
					+ "<table class=\"table table-striped table-bordered\" id=\"tm\">\r\n <thead>\r\n <tr>\r\n <th>Suite Name</th>\r\n <th>Test Case</th>\r\n"
					+ "<th>Status</th>\r\n  <th>Time (s)</th>\r\n <th>Error</th>\r\n </tr>\r\n </thead>\r\n <tbody>");

			NodeList tMethods = document.getElementsByTagName("test-method");

			String suiteName, testName, testStatus, testDuration, testError = null;
			for (int temp = 0; temp < tMethods.getLength(); temp++){
				Node node = tMethods.item(temp);
				if (node.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) node;
					// Get parent element to capture suite name
					Element suiteElement = (Element) eElement.getParentNode();
					 
					suiteName = suiteElement.getAttribute("name");
					// Get test case name
					testName = eElement.getAttribute("name");
					// Get test case status
					testStatus = eElement.getAttribute("status");
					// Get test case duration
					testDuration = eElement.getAttribute("duration-ms");
					// Get exception message if exist
					Node eNode = eElement.getElementsByTagName("exception").item(0);
					Element exceptionNode = (Element) eNode;
					if (exceptionNode != null) {
						testError = exceptionNode.getAttribute("class");
					} else {
						testError = "";
					}

					htmlStringBuilder.append(""
					        + String.format("<tr>\r\n\t<td style=\"word-wrap: break-word;max-width: 200px; white-space: normal\">%s</td>\r\n",suiteName)
							+ String.format("<td style=\"word-wrap: break-word;max-width: 250px; white-space: normal;\">%s</td>\r\n",testName)
							+ String.format("<td>%s</td>\r\n",testStatus)
							+ String.format("<td>%s</td>\r\n",Float.parseFloat(testDuration)/1000)
							+ String.format("<td style=\"word-wrap: break-word;max-width: 200px; white-space: normal\">%s</td>\r\n</tr>",testError));
				}
			}


			htmlStringBuilder.append("</tbody></table><div class=\"row\"> <div class=\"col-md-12\" style=\"height:25px;width:auto;\"> </div> </div></div>");
			// EMAIL TAB CONTENT
			htmlStringBuilder.append(""
					+ "<div class=\"tabcontent\" id=\"statistics\">\r\n<h4>\r\n <b>\r\n <i class=\"fa fa-envelope-o\">\r\n </i>\r\n Email Statistics\r\n </b>\r\n </h4>\r\n"
					+ "<hr/>\r\n <button class=\"btn btn-primary active inner\" id=\"create\" onclick=\"updateTextArea();this.style.visibility= 'hidden';\" role=\"button\">\r\n"
					+ "<i class=\"fa fa-cogs\">\r\n </i>\r\n Generate Statistics Email\r\n </button>\r\n <a class=\"btn btn-primary active inner\" download=\"message.eml\" id=\"downloadlink\" role=\"button\" style=\"display: none; width: 300px;\">\r\n"
					+ "<i class=\"fa fa-download\">\r\n </i>\r\n Click Here To Download Email\r\n </a>\r\n <script>\r\n function updateTextArea() {\r\n"
					+ "var test = \"<b>Top 10 Test Performance:</b><br><br>\" + $(\"#testsBarID table\")[0].outerHTML;\r\n"
					+ "var saluation=\"<pre><br>Please refer TestNG Metrics Report for detailed statistics.<br><br>Regards,<br>QA Team</pre></body></html>\";\r\n"
					+ "document.getElementById(\"textbox\").value += \"<br>\" + test + saluation;\r\n $(\"#create\").click(function(){\r\n $(this).remove();\r\n });\r\n}\r\n"
					+ "</script>\r\n <textarea class=\"col-md-12\" id=\"textbox\" style=\"height: 400px; padding:1em;\">\r\nTo: myemail1234@email.com\r\n"
					+ "Subject: Automation Execution Status\r\nX-Unsent: 1\r\nContent-Type: text/html\r\n\r\n\r\n<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\""
					+ "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n\r\n<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n<head>\r\n"
					+ "<title>Test Email Sample</title>\r\n<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>\r\n<meta content=\"IE=edge\""
					+ "http-equiv=\"X-UA-Compatible\"/>\r\n<meta content=\"width=device-width, initial-scale=1.0 \" name=\"viewport\"/>\r\n <style>\r\n body {\r\n"
					+ "background-color:#F2F2F2; \r\n }\r\n body, html, table,pre,b {\r\n font-family: Calibri, Arial, sans-serif;\r\n font-size: 1em; \r\n"
					+ "}\r\n .pastdue { color: crimson; }\r\n table {\r\n border: 1px solid silver;\r\n padding: 6px;\r\n margin-left: 30px;\r\n width: 600px;\r\n"
					+ "}\r\n thead {\r\n text-align: center;\r\n font-size: 1.1em; \r\n background-color: #B0C4DE;\r\n font-weight: bold;\r\n color: #2D2C2C;\r\n"
					+ "}\r\n tbody {\r\n text-align: center;\r\n }\r\n th {\r\n width: 25%;\r\n word-wrap:break-word;\r\n }\r\n </style>\r\n</head>\r\n"
					+ "<body><pre>Hi Team,\r\nFollowing are the last build execution statistics.\r\n\r\n<b>Metrics:<b>\r\n\r\n</b></b></pre>\r\n"
					+ "<table>\r\n <thead>\r\n <th style=\"width: 25%;\">Total</th>\r\n <th style=\"width: 25%;\">Pass</th>\r\n <th style=\"width: 25%;\">Fail</th>\r\n"
					+ "<th style=\"width: 25%;\">Skip</th>\r\n </thead>\r\n <tbody>\r\n <tr>\r\n "
					+ String.format("<td style=\"background-color: #F5DEB3;text-align: center;\">%s</td>\r\n",total)
					+ String.format("<td style=\"background-color: #90EE90;text-align: center;\">%s</td>\r\n",passed)
					+ String.format("<td style=\"background-color: #F08080;text-align: center;\">%s</td>\r\n",failed)
					+ String.format("<td style=\"background-color: #F08000;text-align: center;\">%s</td>\r\n",skipped)
					+ "</tr>\r\n </tbody>\r\n </table>\r\n</body></html></textarea>\r\n </div>\r\n </div>\n");
			
			htmlStringBuilder.append(""
					+ "<script>\r\n(function () {\r\nvar textFile = null,\r\n makeTextFile = function (text) {\r\n  var data = new Blob([text], {type: 'text/plain'});\r\n"
					+ "if (textFile !== null) {\r\n window.URL.revokeObjectURL(textFile);\r\n }\r\n textFile = window.URL.createObjectURL(data);\r\n"
					+ "return textFile;\r\n };\r\n \r\n var create = document.getElementById('create'),\r\n textbox = document.getElementById('textbox');\r\n"
					+ "create.addEventListener('click', function () {\r\n var link = document.getElementById('downloadlink');\r\n"
					+ "link.href = makeTextFile(textbox.value);\r\n link.style.display = 'block';\r\n }, false);\r\n })();\r\n </script>\r\n"
					+ "<script>\r\n function createBarGraph(tableID,keyword_column,time_column,limit,ChartID,ChartName,type){\r\n\t\t"
					+ "var status = [];\r\n\t\tcss_selector_locator = tableID + ' tbody >tr'\r\n\t\tvar rows = $(css_selector_locator);\r\n\t\t"
					+ "var columns;\r\n\t\tvar myColors = [\r\n\t\t\t'#4F81BC',\r\n '#C0504E',\r\n '#9BBB58',\r\n '#24BEAA',\r\n '#8064A1',\r\n '#4AACC5',\r\n '#F79647',\r\n"
					+ "'#815E86',\r\n '#76A032',\r\n '#34558B'\r\n\t\t];\r\n\t\tstatus.push([type, 'Elapsed Time(s)',{ role: 'annotation'}, {role: 'style'}]);\r\n\t\t"
					+ "for (var i = 0; i < rows.length; i++) {\r\n\t\t\tif (i == Number(limit)){\r\n\t\t\t\tbreak;\r\n\t\t\t}\r\n\t\t\t"
					+ "//status = [];\r\n\t\t\tname_value = $(rows[i]).find('td'); \r\n\t\t  \r\n\t\t\ttime=($(name_value[Number(time_column)]).html()).trim();\r\n\t\t\t"
					+ "keyword=($(name_value[Number(keyword_column)]).html()).trim();\r\n\t\t\tstatus.push([keyword,parseFloat(time),parseFloat(time),myColors[i]]);\r\n\t\t  }"
					+ "\r\n\t\t  var data = google.visualization.arrayToDataTable(status);\r\n\r\n\t\t  var options = {\r\n"
					+ "legend: 'none',\r\n chartArea: {width: \"92%\",height: \"75%\"},\r\n bar: {\r\n groupWidth: '90%'\r\n },\r\n\t\t\tannotations: {\r\n\t\t\t\t"
					+ "alwaysOutside: true,\r\n textStyle: {\r\n fontName: 'Comic Sans MS',\r\n fontSize: 13,\r\n bold: true,\r\n italic: true,\r\n"
					+ "color: \"black\", // The color of the text.\r\n },\r\n\t\t\t},\r\n hAxis: {\r\n textStyle: {\r\n fontName: 'Arial',\r\n fontSize: 10,\r\n"
					+ "}\r\n },\r\n vAxis: {\r\n gridlines: { count: 10 },\r\n textStyle: { \r\n fontName: 'Comic Sans MS',\r\n fontSize: 10,\r\n }\r\n },\r\n\t\t}; \r\n\r\n"
					+ "// Instantiate and draw the chart.\r\n var chart = new google.visualization.ColumnChart(document.getElementById(ChartID));\r\n"
					+ "chart.draw(data, options);\r\n }\r\n\r\n </script>\r\n\r\n<script>\r\n  function executeDataTable(tabname,sortCol) {\r\n"
					+ "var fileTitle = \"TestMetrics\";\r\n\r\n $(tabname).DataTable(\r\n {\r\n retrieve: true,\r\n \"order\": [[ Number(sortCol), \"desc\" ]],\r\n"
					+ "dom: 'l<\".margin\" B>frtip',\r\nbuttons: [\r\n'copy',\r\n{\r\nextend: 'csv',\r\n"
					+ "filename: function() {\r\n return fileTitle + '-' + new Date().toLocaleString();\r\n },\r\n title : '',\r\n },\r\n {\r\n extend: 'excel',\r\n"
					+ "filename: function() {\r\n return fileTitle + '-' + new Date().toLocaleString();\r\n },\r\n title : '',\r\n },\r\n {\r\n extend: 'pdf',\r\n"
					+ "filename: function() {\r\n return fileTitle + '-' + new Date().toLocaleString();\r\n },\r\n title : '',\r\n },\r\n {\r\n extend: 'print',\r\n"
					+ "title : '',\r\n },\r\n ],\r\n } \r\n );\r\n}\r\n </script>\r\n\r\n <script>\r\n  function openPage(pageName,elmnt,color) {\r\n var i, tabcontent, tablinks;\r\n"
					+ "tabcontent = document.getElementsByClassName(\"tabcontent\");\r\n    for (i = 0; i < tabcontent.length; i++) {\r\n"
					+ "tabcontent[i].style.display = \"none\";\r\n    }\r\n    tablinks = document.getElementsByClassName(\"tablink\");\r\n"
					+ "for (i = 0; i < tablinks.length; i++) {\r\n tablinks[i].style.backgroundColor = \"\";\r\n}\r\n document.getElementById(pageName).style.display = \"block\";\r\n"
					+ "elmnt.style.backgroundColor = color;\r\n\r\n}\r\n// Get the element with id=\"defaultOpen\" and click on it\r\n"
					+ "document.getElementById(\"defaultOpen\").click();\r\n </script>\r\n <script>\r\n // Get the element with id=\"defaultOpen\" and click on it\r\ndocument.getElementById(\"defaultOpen\").click();\r\n"
					+ "</script>\r\n <script>\r\n$(window).on('load',function(){$('.loader').fadeOut();});\r\n</script");
			
			htmlStringBuilder.append("</body>");
			
			//write html string content to a file
			WriteToFile(htmlStringBuilder.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void WriteToFile(String fileContent) throws IOException {
        String projectPath = System.getProperty("user.dir");
		String fileName = "Metrics-" + new SimpleDateFormat("yyyy-MMM-dd-HH-mm'.html'").format(new Date());
        String tempFile = projectPath + File.separator+fileName;
        File file = new File(tempFile);
        //write to file with OutputStreamWriter
        OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
        Writer writer=new OutputStreamWriter(outputStream);
        writer.write(fileContent);
        writer.close();
		System.out.println("TestNG Metrics reported is created successfully");
    }
}