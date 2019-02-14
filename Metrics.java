import org.w3c.dom.* ;
import org.xml.sax.SAXException;
import javax.xml.parsers.* ;
import java.io.* ;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Metrics {

	static String logo = "https://cdn.pixabay.com/photo/2016/08/02/10/42/wifi-1563009_960_720.jpg";

	public static void main(String[] args) {

		int total = 0;
		int passed = 0;
		int failed = 0;
		int skipped = 0;

		String path = System.getProperty("user.dir") + "/testng-results.xml";
		File testNgResultXmlFile = new File(path);

		//Get Document Builder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch(ParserConfigurationException e) {
			e.printStackTrace();
		}

		//Build Document
		Document document = null;
		try {
			document = builder.parse(testNgResultXmlFile);
		} catch(SAXException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}

		//Normalize the XML Structure;
		document.getDocumentElement().normalize();
		System.out.println("Converting .xml to .html file. This may take few minutes...");

		try {
			//define a HTML String Builder
            StringBuilder htmlStringBuilder=new StringBuilder();
            //append html header and title
            htmlStringBuilder.append(""
            		+ "<!DOCTYPE doctype html>\r\n<html lang=\"en\">\r\n <head>\r\n"
            		+ "<link href=\"https://img.icons8.com/flat_round/64/000000/bar-chart.png\" rel=\"shortcut icon\" type=\"image/x-icon\"/>\r\n"
            		+ "<title> TestNG Metrics Report</title>\r\n  <meta charset=\"utf-8\"/>\r\n  <meta content=\"width=device-width, initial-scale=1, shrink-to-fit=no\" name=\"viewport\"/>\r\n"
            		+ "<meta content=\"width=device-width, initial-scale=1\" name=\"viewport\"/>\r\n"
            		+ "<link href=\"https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css\" rel=\"stylesheet\"/>\r\n"
            		+ "<link href=\"https://cdn.datatables.net/buttons/1.5.2/css/buttons.dataTables.min.css\" rel=\"stylesheet\"/>\r\n"
            		+ "<link href=\"https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css\" rel=\"stylesheet\"/>\r\n"
            		+ "<link href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\" rel=\"stylesheet\"/>\r\n"
            		+ "<script src=\"https://code.jquery.com/jquery-3.3.1.js\" type=\"text/javascript\"></script>\r\n  <!-- Bootstrap core Googleccharts -->\r\n"
            		+ "<script src=\"https://www.gstatic.com/charts/loader.js\" type=\"text/javascript\"></script>\r\n  <script type=\"text/javascript\">\r\n"
            		+ "google.charts.load('current', {packages: ['corechart']});\r\n  </script>\r\n  <!-- Bootstrap core Datatable-->\r\n"
            		+ "<script src=\"https://code.jquery.com/jquery-3.3.1.js\" type=\"text/javascript\"></script>\r\n"
            		+ "<script src=\"https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js\" type=\"text/javascript\"></script>\r\n"
            		+ "<script src=\"https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js\" type=\"text/javascript\"></script>\r\n"
            		+ "<script src=\"https://cdn.datatables.net/buttons/1.5.2/js/buttons.flash.min.js\" type=\"text/javascript\"></script>\r\n"
            		+ "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js\" type=\"text/javascript\"></script>\r\n"
            		+ "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js\" type=\"text/javascript\"></script>\r\n"
            		+ "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js\" type=\"text/javascript\"></script>\r\n"
            		+ "<script src=\"https://cdn.datatables.net/buttons/1.5.2/js/buttons.html5.min.js\" type=\"text/javascript\"></script>\r\n"
            		+ "<script src=\"https://cdn.datatables.net/buttons/1.5.2/js/buttons.print.min.js\" type=\"text/javascript\"></script>\r\n<style>\r\n"
            		+ ".sidebar { position: fixed; top: 0; bottom: 0;left: 0; z-index: 100; box-shadow: inset -1px 0 0 rgba(0, 0, 0, .1);} \r\n"
            		+ ".sidebar-sticky { position: relative;top: 0; height: calc(100vh - 48px); padding-top: .5rem; overflow-x: hidden; overflow-y: auto;}\r\n"
            		+ "@supports ((position: -webkit-sticky) or (position: sticky)) { .sidebar-sticky { position: -webkit-sticky; position: sticky;}}\r\n"
            		+ ".sidebar .nav-link {color: black;}.sidebar .nav-link.active { color: #007bff;}.sidebar .nav-link:hover .feather,\r\n"
            		+ ".sidebar .nav-link.active .feather {color: inherit;} [role=\"main\"] {padding-top: 8px;} body {height: 100%;margin: 0; background-color: white;}\r\n"
            		+ ".tablinkLog {cursor: pointer;}\r\n@import url(https://fonts.googleapis.com/css?family=Droid+Sans);\r\n"
            		+ ".loader { position: fixed; left: 0px; top: 0px; width: 100%;height: 100%;z-index: 9999;\r\n"
            		+ "background: url('http://www.downgraf.com/wp-content/uploads/2014/09/01-progress.gif?e44397') 50% 50% no-repeat rgb(249,249,249);}\r\n"
            		+ "/* TILES */\r\n.tile { width: 100%; float: left;margin: 0px;list-style: none;font-size: 40px;color: #FFF;-moz-border-radius: 5px;\r\n"
            		+ "-webkit-border-radius: 5px;margin-bottom: 5px;position: relative;text-align: center;color: white!important;}\r\n"
            		+ ".tile.tile-fail {background: #f44336!important;}.tile.tile-pass {background: #4CAF50!important;}\r\n"
            		+ ".tile.tile-info {background: #009688!important;}.tile.tile-head {background: #FE6868!important;}.dt-buttons {margin-left: 5px;}\r\n</style></head></html>"
            		+ "");

            //append body
            htmlStringBuilder.append("<body>");
			System.out.println("1 of 4: Capturing dashboard statistics...");
			htmlStringBuilder.append(""
					+ "<div class=\"loader\"></div>\r\n  <div class=\"container-fluid\">\r\n  <div class=\"row\">\r\n"
					+ "<nav class=\"col-md-2 d-none d-md-block bg-light sidebar\" style=\"font-size:16px;\">\r\n"
					+ "<div class=\"sidebar-sticky\">\r\n<ul class=\"nav flex-column\">\r\n"
                    + String.format("<img src=%s style=\"height:18vh!important;width:95%%;\"/>\r\n", logo)
					+ "<br/>\r\n      <h6 class=\"sidebar-heading d-flex justify-content-between align-items-center text-muted\">\r\n"
					+ "<span>Metrics</span><a class=\"d-flex align-items-center text-muted\" href=\"#\"></a></h6>\r\n      <li class=\"nav-item\">\r\n"
					+ "<a class=\"tablink nav-link\" href=\"#\" id=\"defaultOpen\" onclick=\"openPage('dashboard', this, 'orange')\">\r\n\t"
					+ "<i class=\"fa fa-dashboard\"></i> Dashboard</a>\r\n      </li>\r\n      <li class=\"nav-item\">\r\n"
					+ "<a class=\"tablink nav-link\" href=\"#\" onclick=\"openPage('classMetrics', this, 'orange');executeDataTable('#cm',3)\">\r\n\t"
					+ "<i class=\"fa fa-list-alt\"></i> Class Metrics</a>\r\n      </li>\r\n      <li class=\"nav-item\">\r\n"
					+ "<a class=\"tablink nav-link\" href=\"#\" onclick=\"openPage('testMetrics', this, 'orange');executeDataTable('#tm',3)\">\r\n\t"
					+ "<i class=\"fa fa-table\"></i> Test Metrics</a>\r\n      </li>\r\n\t  <li class=\"nav-item\">\r\n"
					+ "<a class=\"tablink nav-link\" href=\"#\" onclick=\"openPage('methodMetrics', this, 'orange');executeDataTable('#mm',3)\">\r\n\t"
					+ "<i class=\"fa fa-table\"></i> Method Metrics</a>\r\n      </li>\r\n      <li class=\"nav-item\">\r\n "
					+ "<a class=\"tablink nav-link\" href=\"#\" onclick=\"openPage('log', this, 'orange');\"><i class=\"fa fa-wpforms\"></i> TestNG Logs</a>\r\n"
					+ "</li>\r\n      <li class=\"nav-item\">\r\n       <a class=\"tablink nav-link\" href=\"#\" onclick=\"openPage('statistics', this, 'orange');\">"
					+ "<i class=\"fa fa-envelope-o\"></i> Email Metrics</a>\r\n      </li>\r\n     </ul>\r\n    </div>\r\n   </nav>\r\n  </div>\r\n </div>"
					+ "");
			
			try {
				total = Integer.parseInt(document.getDocumentElement().getAttribute("total"));
				passed = Integer.parseInt(document.getDocumentElement().getAttribute("passed"));
				failed = Integer.parseInt(document.getDocumentElement().getAttribute("failed"));
				skipped = Integer.parseInt(document.getDocumentElement().getAttribute("skipped"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			

			//DASHBOARD CONTENT
			htmlStringBuilder.append(""
					+ "<div class=\"col-md-9 ml-sm-auto col-lg-10 px-4\" role=\"main\">\r\n  <div class=\"tabcontent\" id=\"dashboard\">\r\n"
					+ "<div class=\"d-flex flex-column flex-md-row align-items-center p-1 mb-3 bg-light border-bottom shadow-sm\">\r\n"
					+ "<h5 class=\"my-0 mr-md-auto font-weight-normal\"><i class=\"fa fa-dashboard\"></i> Dashboard</h5>\r\n"
					+ "<nav class=\"my-2 my-md-0 mr-md-3\" style=\"color:red\">\r\n    <a class=\"p-2\">"
					+ "<b data-toggle=\"tooltip\" style=\"color:black;cursor: pointer;\" title=\".xml file is created by\"> Generated By:</b> TestNG</a>\r\n"
			
					+ String.format("</nav>\r\n   </div>\r\n   <div class=\"row\">\r\n    <div class=\"col-md-3\"><a class=\"tile tile-info\">%s<p style=\"font-size:15px\">Total Tests</p></a>",total)
					+ String.format("</div>\r\n    <div class=\"col-md-3\"><a class=\"tile tile-pass\">%s<p style=\"font-size:15px\">Pass</p></a></div>\r\n\t<div class=\"col-md-3\">",passed)
					+ String.format("<a class=\"tile tile-fail\">%s<p style=\"font-size:15px\">Fail</p></a></div>\r\n\t<div class=\"col-md-3\">",failed)
					+ String.format("<a class=\"tile tile-head\">%s<p style=\"font-size:15px\">Skip</p></a></div>\r\n   </div><hr/>\r\n   <div class=\"row\">\r\n",skipped)
					
					+ "<div class=\"col-md-4\" style=\"background-color:white;height:350px;width:auto;border:groove;\">\r\n"
					+ "<span style=\"font-weight:bold\">Test Status:</span>\r\n     <div id=\"testChartID\" style=\"height:280px;width:auto;\"></div>\r\n"
					+ "</div>\r\n    <div class=\"col-md-8\" style=\"background-color:white;height:350px;width:auto;border:groove;\">\r\n"
					+ "<span style=\"font-weight:bold\">Top 5 Class Failures:</span>\r\n     <div id=\"classBarID\" style=\"height:300px;width:auto;\"></div>\r\n"
					+ "</div>\r\n   </div>\r\n   <div class=\"row\">\r\n    <div class=\"col-md-12\" style=\"background-color:white;height:450px;width:auto;border:groove;\">\r\n"
					+ "<span style=\"font-weight:bold\">Top 10 Test Performance(sec):</span>\r\n     <div id=\"testsBarID\" style=\"height:400px;width:auto;\"></div>\r\n"
					+ "</div>\r\n\t<div class=\"col-md-12\" style=\"background-color:white;height:450px;width:auto;border:groove;\">\r\n"
					+ "<span style=\"font-weight:bold\">Top 10 Config Methods Performance(sec):</span>\r\n     <div id=\"methodsBarID\" style=\"height:400px;width:auto;\"></div>\r\n"
					+ "</div>\r\n   </div>\r\n   <div class=\"row\"><div class=\"col-md-12\" style=\"height:25px;width:auto;\">\r\n"
					+ "<p class=\"text-muted\" style=\"text-align:center;font-size:9px\">testng-metrics</p></div>\r\n   </div>\r\n   <script>\r\n"
					+ "window.onload = function(){\r\n    executeDataTable('#cm',3);\r\n    executeDataTable('#tm',3);\r\n\texecuteDataTable('#mm',3);\r\n\t"
                    + String.format("createPieChart(%1$s,%2$s,%3$s,'testChartID','Test Status:');\r\n\t",passed, failed, skipped)
                    + "createBarGraph('#cm',0,3,5,'classBarID','Number of failures ','Class'); \r\n\t"
					+ "createBarGraph('#tm',1,3,10,'testsBarID','Elapsed Time(s) ','Test');\r\n\t"
					+ "createBarGraph('#mm',1,3,10,'methodsBarID','Elapsed Time(s) ','Method');\r\n\t};\r\n   </script>\r\n  </div>"
					+ "");
				
			System.out.println("2 of 4: Capturing class metrics...");
			
			// CLASS METRICS TAB
			htmlStringBuilder.append(""
					+ "<div class=\"tabcontent\" id=\"classMetrics\">\r\n   <h4><b><i class=\"fa fa-table\"></i> Class Metrics</b></h4><hr/>\r\n"
					+ "<table class=\"table table-striped table-bordered\" id=\"cm\">\r\n<thead><tr>\r\n<th>Class Name</th>\r\n<th>Total Methods</th>\r\n"
					+ "<th>Passed Methods</th>\r\n<th>Failed Methods</th>\r\n\t<th>Skipped Methods</th>\r\n</tr></thead>\r\n<tbody>\r\n"
					+ "");
			
			NodeList cClass = document.getElementsByTagName("class");
	        for (int temp = 0; temp < cClass.getLength(); temp++) {
	            int cTotalSteps = 0;
	            int cPassSteps =  0;
	            int cFailSteps =  0;
	            int cSkipSetps =  0;
	            Node node = cClass.item(temp);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
	                Element eElement = (Element) node;
	                
	                htmlStringBuilder.append("<tr>\r\n"
	                		+ String.format("<td style=\"word-wrap: break-word;max-width: 300px; white-space: normal\">%s</td>\r\n",eElement.getAttribute("name"))
	                		+ "");
	                
	                NodeList cChildNodes = eElement.getElementsByTagName("test-method");
	                for (int ctemp = 0; ctemp < cChildNodes.getLength(); ctemp++) {
	                    Node cnode = cChildNodes.item(ctemp);
	                    if (cnode.getNodeType() == Node.ELEMENT_NODE) {
	                        Element cElement = (Element) cnode;
	                        cTotalSteps ++;
	                        if (cElement.getAttribute("status").equalsIgnoreCase("PASS")) {
	                            cPassSteps ++ ;
	                        } else if (cElement.getAttribute("status").equalsIgnoreCase("FAIL")) {
	                            cFailSteps ++ ;
	                        } else {
	                            cSkipSetps ++ ;
	                        }
	                    }
	                }
	                htmlStringBuilder.append(""
	                		+ String.format("<td>%s</td>\r\n",cTotalSteps)
	                		+ String.format("<td>%s</td>\r\n",cPassSteps)
	                		+ String.format("<td>%s</td>\r\n",cFailSteps)
	                		+ String.format("<td>%s</td>\r\n",cSkipSetps)
	                		+ "</tr>\r\n ");
	            }
	        }
	        
			htmlStringBuilder.append("</tbody>\r\n   "
					+ "</table>\r\n<div class=\"row\"><div class=\"col-md-12\" style=\"height:25px;width:auto;\"></div>"
					+ "</div></div>");
								
			System.out.println("3 of 4: Capturing test metrics...");
			
			// TEST METRICS TAB
			htmlStringBuilder.append(""
					+ "<div class=\"tabcontent\" id=\"testMetrics\">\r\n   <h4><b><i class=\"fa fa-table\"></i> Test Metrics</b></h4><hr/>\r\n"
					+ "<table class=\"table table-striped table-bordered\" id=\"tm\">\r\n    <thead><tr>\r\n      <th>Class Name</th>\r\n"
					+ "<th>Test Name</th>\r\n      <th>Status</th>\r\n      <th>Time(s)</th>\r\n\t  <th>Error Message</th>\r\n    </tr></thead>\r\n"
					+ "<tbody>\r\n     ");
			
			// Test Metrics
			NodeList tMethods = document.getElementsByTagName("test-method");
			for (int temp = 0; temp < tMethods.getLength(); temp++) {
				Node node = tMethods.item(temp);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;

	                if (!eElement.getAttribute("is-config").contains("true")) {
	                    // Get parent element to capture suite name
	                    Element suiteElement = (Element) eElement.getParentNode();
	                    
	                    // Get exception message if exist
						//String error = "";
						String errorMessage = "";
	                    Node eNode = eElement.getElementsByTagName("exception").item(0);
	                    Element exceptionNode = (Element) eNode;
	                    if (exceptionNode != null) {
							//error = exceptionNode.getAttribute("class");
							Node eMNode = exceptionNode.getElementsByTagName("message").item(0);
                            Element exceptionMNode = (Element) eMNode;
                            if (exceptionMNode != null) {
								errorMessage = exceptionMNode.getTextContent().trim();                                
                            }
	                    }
	                    htmlStringBuilder.append("<tr>\r\n      "
	                    		+ String.format("<td style=\"word-wrap: break-word;max-width: 200px; white-space: normal\">%s</td>\r\n",suiteElement.getAttribute("name"))
		                		+ String.format("<td style=\"word-wrap: break-word;max-width: 250px; white-space: normal;\">%s</td>\r\n",eElement.getAttribute("name"))
		                		+ String.format("<td>%s</td>\r\n",eElement.getAttribute("status"))
		                		+ String.format("<td>%s</td>\r\n",Float.parseFloat(eElement.getAttribute("duration-ms"))/1000)
								+ String.format("<td style=\"word-wrap: break-word;max-width: 300px; white-space: normal\">%s</td>\r\n",errorMessage)
	                    		+ "     </tr>\r\n");
	                } else {
	                    continue;
	                }
				}
	        }
			
			htmlStringBuilder.append("</tbody>\r\n   "
					+ "</table>\r\n<div class=\"row\"><div class=\"col-md-12\" style=\"height:25px;width:auto;\"></div>"
					+ "</div></div>");
			
			System.out.println("4 of 4: Capturing method metrics...");
			
			// METHOD METRICS TAB
			htmlStringBuilder.append(""
					+ "<div class=\"tabcontent\" id=\"methodMetrics\">\r\n   <h4><b><i class=\"fa fa-table\"></i> Method Metrics</b></h4><hr/>\r\n"
					+ "<table class=\"table table-striped table-bordered\" id=\"mm\">\r\n    <thead><tr>\r\n      <th>Class Name</th>\r\n"
					+ "<th>Method Name</th>\r\n      <th>Status</th>\r\n      <th>Time(s)</th>\r\n\t  <th>Error Message</th>\r\n    </tr></thead>\r\n"
					+ "<tbody>\r\n     ");
			// Method Metrics
			NodeList mMethods = document.getElementsByTagName("test-method");
			for (int temp = 0; temp < mMethods.getLength(); temp++) {
				Node node = mMethods.item(temp);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					if (eElement.getAttribute("is-config").contains("true")) {
	                    // Get parent element to capture suite name
	                    Element suiteElement = (Element) eElement.getParentNode();
	                    
	                    // Get exception message if exist
						//String error = "";
						String errorMessage = "";
	                    Node eNode = eElement.getElementsByTagName("exception").item(0);
	                    Element exceptionNode = (Element) eNode;
	                    if (exceptionNode != null) {
							//error = exceptionNode.getAttribute("class");
							Node eMNode = exceptionNode.getElementsByTagName("message").item(0);
                            Element exceptionMNode = (Element) eMNode;
                            if (exceptionMNode != null) {
								errorMessage = exceptionMNode.getTextContent().trim();                                
                            }
	                    }
	                    htmlStringBuilder.append("<tr>\r\n      "
	                    		+ String.format("<td style=\"word-wrap: break-word;max-width: 200px; white-space: normal\">%s</td>\r\n",suiteElement.getAttribute("name"))
		                		+ String.format("<td style=\"word-wrap: break-word;max-width: 250px; white-space: normal;\">%s</td>\r\n",eElement.getAttribute("name"))
		                		+ String.format("<td>%s</td>\r\n",eElement.getAttribute("status"))
                                + String.format("<td>%s</td>\r\n",Float.parseFloat(eElement.getAttribute("duration-ms"))/1000)
								+ String.format("<td style=\"word-wrap: break-word;max-width: 300px; white-space: normal\">%s</td>\r\n",errorMessage)
	                    		+ "     </tr>\r\n");
	                } else {
	                    continue;
	                }
				}
	        }
			htmlStringBuilder.append("</tbody>\r\n   "
					+ "</table>\r\n<div class=\"row\"><div class=\"col-md-12\" style=\"height:25px;width:auto;\"></div>"
					+ "</div></div>");
			
			// LOGS TAB 
			htmlStringBuilder.append(""
					+ "<div class=\"tabcontent\" id=\"log\"><p style=\"text-align:right\">**<b>index.html</b> need to be in current folder in order to display here</p>\r\n"
					+ "<div class=\"embed-responsive embed-responsive-4by3\"><iframe class=\"embed-responsive-item\" src=\"index.html\"></iframe></div></div>"
					+ "");
			
			// EMAIL TAB
			htmlStringBuilder.append(""
					+ "<div class=\"tabcontent\" id=\"statistics\">\r\n   <h4><b><i class=\"fa fa-envelope-o\"></i> Email Statistics</b></h4><hr/>\r\n"
					+ "<button class=\"btn btn-primary active inner\" id=\"create\" onclick=\"updateTextArea();this.style.visibility= 'hidden';\" role=\"button\">\r\n"
					+ "<i class=\"fa fa-cogs\"></i> Generate Statistics Email</button>\r\n"
					+ "<a class=\"btn btn-primary active inner\" download=\"message.eml\" id=\"downloadlink\" role=\"button\" style=\"display: none; width: 300px;\">\r\n"
					+ "<i class=\"fa fa-download\"></i>Click Here To Download Email</a>\r\n   <script>\r\n    function updateTextArea() {\r\n"
					+ "var suite = \"<b>Top 5 Class Failures:</b><br><br>\" + $(\"#classBarID table\")[0].outerHTML;\r\n"
					+ "var test = \"<b>Top 10 Test Performance:</b><br><br>\" + $(\"#testsBarID table\")[0].outerHTML;\r\n\t"
					+ "var method = \"<b>Top 10 Method Performance:</b><br><br>\" + $(\"#methodsBarID table\")[0].outerHTML;\r\n"
					+ "var saluation=\"<pre><br>Please refer TestNG Metrics Report for detailed statistics.<br><br>Regards,<br>QA Team</pre></body></html>\";\r\n"
					+ "document.getElementById(\"textbox\").value += \"<br>\" + suite + \"<br>\" + test + \"<br>\" + method + saluation;\r\n"
					+ "$(\"#create\").click(function(){\r\n    $(this).remove();\r\n    });\r\n}\r\n   </script>\r\n"
					+ "<textarea class=\"col-md-12\" id=\"textbox\" style=\"height: 400px; padding:1em;\">\r\nTo: myemail1234@email.com\r\n"
					+ "Subject: Automation Execution Status\r\nX-Unsent: 1\r\nContent-Type: text/html\r\n\r\n\r\n"
					+ "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n\r\n"
					+ "<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n<head>\r\n<title>Test Email Sample</title>\r\n"
					+ "<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>\r\n<meta content=\"IE=edge\" http-equiv=\"X-UA-Compatible\"/>\r\n"
					+ "<meta content=\"width=device-width, initial-scale=1.0 \" name=\"viewport\"/>\r\n  <style>body {background-color:#F2F2F2; }\r\n\t"
					+ "body, html, table,pre,b { font-family: Calibri, Arial, sans-serif; font-size: 1em; }.pastdue { color: crimson; }\r\n\t"
					+ "table {border: 1px solid silver;padding: 6px; margin-left: 30px; width: 600px;}\r\n\t thead"
					+ "{text-align: center;font-size: 1.1em;background-color: #B0C4DE;font-weight: bold;color: #2D2C2C;}\r\n\t tbody"
					+ "{text-align: center;}th {width: 25%;word-wrap:break-word;}\r\n</style></head>\r\n"
					+ "<body><pre>Hi Team,\r\nFollowing are the last build execution statistics.\r\n\r\n<b>Metrics:<b>\r\n\r\n</b></b></pre>\r\n\t<table>\r\n\t"
					+ "<thead>\r\n\t\t<th style=\"width: 25%;\">Total</th>\r\n\t\t<th style=\"width: 25%;\">Pass</th>\r\n\t\t"
					+ "<th style=\"width: 25%;\">Fail</th>\r\n\t\t<th style=\"width: 25%;\">Skip</th>\r\n\t </thead>\r\n\t"
					+ "<tbody><tr>\r\n\t\t<td style=\"text-align: left;font-weight: bold;\"> TESTS </td>\r\n\t\t"
                    + String.format("<td style=\"background-color: #90EE90;text-align: center;\">%s</td>\r\n\t\t",passed)
                    + String.format("<td style=\"background-color: #F08080;text-align: center;\">%s</td>",failed)
                    + String.format("\r\n\t\t<td style=\"background-color: #F5DEB3;text-align: center;\">%s</td>\r\n\t",skipped)
                    + "</tr></tbody>\r\n\t</table>\r\n\r\n</body></html></textarea>\r\n</div></div>"
					+ "");
			
			// SCRIPT CONTENT
			htmlStringBuilder.append(""
					+ "<script>\r\n function createPieChart(passed_count, failed_count, skipped_count, ChartID, ChartName) {\r\n"
					+ "var status = [];\r\n  status.push(['Status', 'Percentage']);\r\n"
					+ "status.push(['PASS', parseInt(passed_count)], ['FAIL', parseInt(failed_count)],['SKIP', parseInt(skipped_count)]);\r\n"
					+ "var data = google.visualization.arrayToDataTable(status);\r\n  var options = {\r\n    pieHole: 0.6,\r\n    legend: 'none',\r\n"
					+ "chartArea: {\r\n      width: \"95%\",\r\n      height: \"90%\"\r\n    },\r\n    colors: ['#4CAF50','#f44336','#FE6868'],\r\n  };\r\n"
					+ "var chart = new google.visualization.PieChart(document.getElementById(ChartID));\r\n  chart.draw(data, options);\r\n}\r\n</script>\r\n"
					+ "<script>\r\n  (function () {\r\n  var textFile = null,\r\n    makeTextFile = function (text) {\r\n      var data = new Blob([text], {\r\n"
					+ "type: 'text/plain'\r\n      });\r\n      if (textFile !== null) {\r\n        window.URL.revokeObjectURL(textFile);\r\n      }\r\n"
					+ "textFile = window.URL.createObjectURL(data);\r\n      return textFile;\r\n    };\r\n  var create = document.getElementById('create'),\r\n"
					+ "textbox = document.getElementById('textbox');\r\n  create.addEventListener('click', function () {\r\n"
					+ "var link = document.getElementById('downloadlink');\r\n    link.href = makeTextFile(textbox.value);\r\n"
					+ "link.style.display = 'block';\r\n  }, false);\r\n })();\r\n </script>\r\n <script>\r\n"
					+ "function createBarGraph(tableID, keyword_column, time_column, limit, ChartID, Label, type) {\r\n"
					+ "var status = [];\r\n  css_selector_locator = tableID + ' tbody >tr'\r\n  var rows = $(css_selector_locator);\r\n"
					+ "var columns;\r\n  var myColors = ['#4F81BC','#C0504E','#9BBB58','#24BEAA','#8064A1','#4AACC5','#F79647','#815E86','#76A032','#34558B'];\r\n"
					+ "status.push([type, Label, {role: 'annotation'}, {role: 'style'}]);\r\n  for (var i = 0; i < rows.length; i++) {\r\n    if (i == Number(limit)) {\r\n"
					+ "break;\r\n    }\r\n    //status = [];\r\n    name_value = $(rows[i]).find('td');\r\n    time = ($(name_value[Number(time_column)]).html()).trim();\r\n"
					+ "keyword = ($(name_value[Number(keyword_column)]).html()).trim();\r\n    status.push([keyword, parseFloat(time), parseFloat(time), myColors[i]]);\r\n"
					+ "}\r\n  var data = google.visualization.arrayToDataTable(status);\r\n  var options = {\r\n    legend: 'none',\r\n"
					+ "chartArea: { width: \"92%\", height: \"75%\"},\r\n    bar: { groupWidth: '90%'},\r\n    annotations: {\r\n"
					+ "alwaysOutside: true,\r\n      textStyle: {\r\n        fontName: 'Comic Sans MS',fontSize: 13,bold: true,italic: true,color: \"black\", },\r\n"
					+ "},\r\n    hAxis: {\r\n      textStyle: {fontName: 'Arial',fontSize: 10,}\r\n    },\r\n    vAxis: {\r\n      gridlines: {count: 10},\r\n"
					+ "textStyle: {fontName: 'Comic Sans MS',fontSize: 10,}\r\n    },\r\n  };\r\n"
					+ "var chart = new google.visualization.ColumnChart(document.getElementById(ChartID));\r\n"
					+ "chart.draw(data, options);\r\n }\r\n </script>\r\n <script>\r\n  function executeDataTable(tabname, sortCol) {\r\n"
					+ "var fileTitle;\r\n  switch (tabname) {\r\n    case \"#cm\":\r\n      fileTitle = \"ClassMetrics\";\r\n"
					+ "break;\r\n    case \"#tm\":\r\n      fileTitle = \"TestMetrics\";\r\n      break;\r\n    default:\r\n"
					+ "fileTitle = \"metrics\";\r\n  }\r\n  $(tabname).DataTable({\r\n    retrieve: true,\r\n\t\"order\": [[Number(sortCol), \"desc\"]],\r\n"
					+ "dom: 'l<\".margin\" B>frtip',\r\n    buttons: [\r\n      'copy',\r\n      {\r\n        extend: 'csv',\r\n"
					+ "filename: function () {\r\n          return fileTitle + '-' + new Date().toLocaleString();\r\n"
					+ "},\r\n        title: '',\r\n      },\r\n      {\r\n        extend: 'excel',\r\n        filename: function () {\r\n"
					+ "return fileTitle + '-' + new Date().toLocaleString();\r\n        },\r\n        title: '',\r\n      },\r\n      {\r\n"
					+ "extend: 'pdf',\r\n        filename: function () {\r\n          return fileTitle + '-' + new Date().toLocaleString();\r\n"
					+ "},\r\n        title: '',\r\n      },\r\n      {\r\n        extend: 'print',\r\n        title: '',\r\n      },\r\n"
					+ "],\r\n  });\r\n }\r\n </script>\r\n <script>\r\n function openPage(pageName, elmnt, color) {\r\n  var i, tabcontent, tablinks;\r\n"
					+ "tabcontent = document.getElementsByClassName(\"tabcontent\");\r\n  for (i = 0; i < tabcontent.length; i++) {\r\n"
					+ "tabcontent[i].style.display = \"none\";\r\n  }\r\n  tablinks = document.getElementsByClassName(\"tablink\");\r\n"
					+ "for (i = 0; i < tablinks.length; i++) {\r\n    tablinks[i].style.backgroundColor = \"\";\r\n  }\r\n"
					+ "document.getElementById(pageName).style.display = \"block\";\r\n  elmnt.style.backgroundColor = color;\r\n }\r\n"
					+ "// Get the element with id=\"defaultOpen\" and click on it\r\n document.getElementById(\"defaultOpen\").click();\r\n"
					+ "</script>\r\n <script>\r\n document.getElementById(\"defaultOpen\").click();\r\n </script>\r\n <script>\r\n"
					+ "$(window).on('load',function(){$('.loader').fadeOut();});\r\n </script>"
					+ "\r\n </div>");
			htmlStringBuilder.append("\r\n</body>\n</html>");
			
			//write html string content to a file
			WriteToFile(htmlStringBuilder.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void WriteToFile(String fileContent) throws IOException {
        String projectPath = System.getProperty("user.dir");
		String fileName = "Metrics-" + new SimpleDateFormat("yyyyMMMdd-HHmm'.html'").format(new Date());
        String tempFile = projectPath + File.separator+fileName;
        File file = new File(tempFile);
        //write to file with OutputStreamWriter
        OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
        Writer writer=new OutputStreamWriter(outputStream);
        writer.write(fileContent);
        writer.close();
		System.out.println("TestNG Metrics " + fileName + " is created successfully");
    }
}