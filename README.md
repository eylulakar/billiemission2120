# billiemission2120

## Summary

This is a test suit for Billie Mission 2120 task. 

This suite goes through all CRUD operations of Restful-Booker API.
There are 15 test cases implemented, 4 of them are currently failing.

In my professional opinion as a QA Expert, those cases should not pass and must be reported as defects. Please see the report or github workflow results.


## How to Run

Test suite is available under -- folder. To run the suite either run the following under source folder:

	mvn package

or the following in main folder.

	sh run.sh


## Automation

This repository is built for working with github workflows. Each commit to this repository will trigger a github action which will run all the tests.
You may see all individual resutl in the following link.

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
	
