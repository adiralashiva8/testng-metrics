<p align="center"><img src="https://i.ibb.co/0j28fBG/tmetricslogo.png" width="300" height="200"></p>

TestNg Metrics is a custom report generated using TestNG Listener. Which generate awesome html report without making any changes in your existing automation code

![Maven Central](https://img.shields.io/maven-central/v/com.github.adiralashiva8/testng-metrics.svg?label=Maven%20Central)
![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)
![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)
![Open Source Love png1](https://badges.frapsoft.com/os/v1/open-source.png?v=103)

---

  - __Sample Report__ [link](https://testng-metrics.netlify.com/)
  > Best viewed in desktop!

---

### Key Features

 - Dashboard View of execution results
 - Top 10 Test Performances
 - Sort and Search Results
 - Export Results (pdf, excel,csv, print)
 - Generate email (.eml) with statistics
 - No Code changes required
 - Metrics of `After*` and `Before*`
 - Display dataprovider info for tests

---

### Pre-requisite

 - JDK 8+
 - TestNG 6+

---

### How to use in Project:

### Maven:

1. Add testng-metrics dependency in pom.xml
   ```
   <dependency>
     <groupId>com.github.adiralashiva8</groupId>
     <artifactId>testng-metrics</artifactId>
     <version>1.5</version>
   </dependency>
   ```
2. Perform maven install

3. Execute test cases

4. TestNG Metrics report __metric-timestamp.html__ file will be created
   > By default, the report will be generated at TestNG's output directory. i.e., `test-output/metric-timestamp.html`

### ANT

   ```
    <dependency org="com.github.adiralashiva8" name="testng-metrics" rev="1.5" />
   ```

### GRADLE Groovy DSL
   ```
    implementation 'com.github.adiralashiva8:testng-metrics:1.5'
   ```

### GRADLE Kotlin DSL
   ```
    compile("com.github.adiralashiva8:testng-metrics:1.5")
   ```

---

### Not a Maven Project?

1. Download `testng-metrics.jar` from [here](http://central.maven.org/maven2/com/github/adiralashiva8/testng-metrics/1.5/testng-metrics-1.5.jar)

2. Add `testng-metrics.jar` in your project
   > - Open Eclipse → Right Click on the project
   > - Go to Property → Build Path → Configure Build Path
   > - Add the `testng-metrics.jar` in the libraries using Add External Jar button

3. Execute test cases

4. TestNG Metrics report __metric-timestamp.html__ file will be created

---

### Customize LOGO:

Want to use Custom LOGO in testng-metrics then update `testng.metrics.logo` JVM variable

 - From command line: 
   > `mvn clean test -Dtestng.metrics.logo="https://mycompanylog.jpg"`

 - From testng.xml: Place following lines in testng.xml
```
     <method-selectors>
       <method-selector>
         <script language="beanshell">
           <![CDATA[ System.setProperty("testng.metrics.logo",
             "https://mycompanylog.jpg");return true;]]>
         </script>
       </method-selector>
     </method-selectors>
```

---

### Archiving Reports:

If you want to archive the entire `test-output` folder along with `testng-metrics report`, add the below parameter at suite level in your `testng.xml` file 

 > `<parameter name="archive.testng.metrics.report" value = "true"/>`.

You can turn off this feature by either removing this parameter from testng file or by changing the value to `false`.

Refer the testng.xml file in the repo.

This will create a folder in your: 

> `.current.dir/TestNg_Metrics_Reports/<Results_dd_MMM_yy_hh_mm_ss>` 

---

Thanks for using testng-metrics!

 - What is your opinion of this report?
 - What’s the feature I should add?

If you have any questions / suggestions / comments on the report, please feel free to reach me at

 - Email: <a href="mailto:adiralashiva8@gmail.com?Subject=Testng%20Metrics" target="_blank">`adiralashiva8@gmail.com`</a> 
 - LinkedIn: <a href="https://www.linkedin.com/in/shivaprasadadirala/" target="_blank">`shivaprasadadirala`</a>
 - Twitter: <a href="https://twitter.com/ShivaAdirala" target="_blank">`@ShivaAdirala`</a>
 - Mailing List (google group): <a href="https://groups.google.com/forum/embed/?place=forum/testng-metrics" target="_blank">`testng-metrics`</a>

---

*Credits:*

1. TestNG [link](https://testng.org/doc/index.html)
2. Stackoverflow [link](http://stackoverflow.com)
3. Google charts [link](https://developers.google.com/chart/)
4. DataTable [link](https://datatables.net/examples/basic_init/table_sorting.html)
5. Java [link](https://www.java.com)
6. Jquery | JavaScript [link](https://www.jqueryscript.net)
7. Bootstrap [link](http://getbootstrap.com/docs/4.1/examples/dashboard/)
8. Icons8 [link](https://icons8.com/)
9. FontAwesome [link](https://fontawesome.com)

Note: Testng-metrics uses above mentioned open-source libraries in report.

---

*Special Thanks To:*

*Contributors:*

1. [Krishnan Mahadevan](https://www.linkedin.com/in/krmahadevan/) [Automation GURU]
    > - Mavenised the project ( To consume it from Maven library )
    > - Converted the utility into a TestNG listener
    > - Guided to publish the library onto Maven Central

2. [Saurabh Gupta](https://www.linkedin.com/in/saurabh-gupta-24769929/)
    > - Contributed source to display data-provider info in Test Metrics
    > - Added brand LOGO for TestNG Metrics

3. [Nainappa Illi](https://www.linkedin.com/in/nainappa-illi-97673231/)
    > - Contributed source to archive test-output folder and display execution time

*Feedback:*

1. [Venkateswara Reddy G](https://www.linkedin.com/in/gvreddyreddy/)

2. [Testng Users Community](https://groups.google.com/forum/#!forum/testng-users)

---

  > `STAR` repo to appreciate us!

---

Inspired from [robotframework-metrics](https://github.com/adiralashiva8/robotframework-metrics)

---
