# TestNg Metrics Report

 > TestNg Metrics is a custom report generated using TestNG Listener. Which generate awesome html report without making any changes in your existing automation code

![Maven Central](https://img.shields.io/maven-central/v/com.github.adiralashiva8/testng-metrics.svg?label=Maven%20Central)
![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)
![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)
![Open Source Love png1](https://badges.frapsoft.com/os/v1/open-source.png?v=103)
[![HitCount](http://hits.dwyl.io/adiralashiva8/testng-metrics.svg)](http://hits.dwyl.io/adiralashiva8/testng-metrics)

---

  - __Sample Report__ [link](https://testng-metrics.netlify.com/)
  > Best viewed in desktop!

---

### Key Features

 - Dashboard View of execution results
 - Top 10 Test Performances
 - Sort and Search Results
 - Export Results (pdf, excel, csv, print)
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
     <version>1.6</version>
   </dependency>
   ```
2. Perform maven install

3. Execute test cases

4. TestNg Metrics report file will be created
   > By default report will be generated at TestNG's output directory. i.e., `test-output/metric.html`

---

### Not a Maven Project?

1. Download `testng-metrics.jar` from [here](https://oss.sonatype.org/service/local/repositories/releases/content/com/github/adiralashiva8/testng-metrics/1.6/testng-metrics-1.6.jar)

2. Add `testng-metrics.jar` in your project
   > - Open Eclipse → Right click on the project
   > - Go to Property → Build Path → Configure Build Path
   > - Add the `testng-metrics.jar` in the libraries using Add External Jar button

3. Execute test cases

4. TestNg Metrics report will be generated

---

### Customize Report:

 - __Custom Logo__: Logo can be modified by modifying:

   > `testng.metrics.report.logo` parameter value in `testng.xml`

    Example:
    ```
      <suite name="Suite">
      <parameter name="testng.metrics.report.logo" value = "https://i.ibb.co/FqtQyC5/rfh.png"/>
        <test name="Test">
          <classes>
            <class name="com.github.functional.FunctionalSample"/>
          </classes>
        </test>
      </suite>
    ```


 - __Custom Report Name__: Similar to __Custom Logo__ user can be modify report name by modifying:

   > `testng.metrics.report.name` parameter value in `testng.xml`

    Example:
    ```
    <parameter name="testng.metrics.report.name" value = "regression.html"/>
    ```
    > This helps in create different reports for different cases


 - __Report With Timestamp__: Helps to create new file by appending timestamp to report every time

   > `testng.metrics.report.appendTimestamp` parameter value in `testng.xml`

     Example:
     ```
     <parameter name="testng.metrics.report.appendTimestamp" value = "True"/>
     ```

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

*Special Thanks To:*

*Contributors:*

1. [Krishnan Mahadevan](https://www.linkedin.com/in/krmahadevan/)
    > - Mavenised the project ( To consume it from Maven library )
    > - Converted the utility into a TestNg listener
    > - Guided to publish the library into Maven Central

2. [Saurabh Gupta](https://www.linkedin.com/in/saurabh-gupta-24769929/)
    > - Contributed source to display data-provider info in Test Metrics
    > - Added brand LOGO for TestNG Metrics

3. [Nainappa Illi](https://www.linkedin.com/in/nainappa-illi-97673231/)
    > - Contributed source to archive test-output folder and display execution time

*Feedback:*

1. [Venkateswara Reddy G](https://www.linkedin.com/in/gvreddyreddy/)

2. [Shyam]()

2. [Testng Users Community](https://groups.google.com/forum/#!forum/testng-users)

---

  > :star: repo if you like it

---

Inspired from [robotframework-metrics](https://github.com/adiralashiva8/robotframework-metrics)

---
