# billiemission2120

## Summary

This is a test suite for Billie Mission 2120 task. 

This suite goes through all CRUD operations of Restful-Booker API.
There are 10 test cases implemented in total, and 4 of them are currently failing. Failing test cases are negative paths, and all of the cases' descriptions are explained in the 
test documentation file.

https://github.com/eylulakar/billiemission2120/BillieMission2120TestFramework/src/docs/BillieMission2120TestCasesDocumentation.docx

In my professional opinion as a QA Expert, those cases should not pass and must be reported as defects. Please see the report or github workflow results.

This is an example view of test execution results with cucumber in IntelliJ:
 
![exampleExecution](https://user-images.githubusercontent.com/6651987/106719323-35535e80-6613-11eb-8f56-c524b32f321b.JPG)


## How to Run

Test suite is available \src\test\resources\features directory. There are four feature files with multiple test cases. 
To run the whole suite either run the following under source folder:

	mvn package

or the following in main folder.

	sh run.sh


## Automation

This repository is built for working with github workflows. Each commit to this repository will trigger a github action which will run all the tests.
You may see all individual result in the following link.

https://github.com/eylulakar/billiemission2120/actions


## Reports

After running the suite a report will be generated under the following folder:
	
	\target\surefire-reports

Alternatively you may access the report in the following link:
	
https://eylulakar.github.io/billiemission2120/


## Technologies

1. Java & Maven
1. Cucumber
1. TestNG
1. Okhttp3
	
