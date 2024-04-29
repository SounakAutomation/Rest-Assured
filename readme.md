#Rest Assured ProjectInformation

This is API Automation test for Restful-Booker website. This can be used to test functionality & feature of Restful-Booker website

#Deployment

>_Run the project in CMD_

* Extract the file in the desired location.
* Open Cmd in the project directory.
* Run the following command.


```
mvn clean
mvn install

```
>_Run Project On Eclipse_

* Step 1: Import the project into eclipse
* Step 2: Right click on the testng.xml file.
* Step 3: Select **Run**  option.


>Run Project using batch file

* Step 1: Go to Run folder.
* Step 2: Right click on RestAssuredBatch.bat file and go to edit 
* Step 3: After cd set the project patch accordingly and save it
* Step 4: Click on RestAssuredBatch.bat file to run the project. 


**Note -** Please make sure the path in the batch file according to your project location.

#Project Info

* Extent Report folder contain ReportLogger Class for all reporting and log related data 
* Request folder contain all pojo classes
* Restfulbooker folder contain all esential folder 
* api folder contain all request 
* apihelper contain all api related class 
* Base folder contain Test Base class 
* Data factory contain class data loader store all variable in one place
* payload contain payload that used in any request
* utilities folder contain utillities 
* resourse folder contain all properties file 

 
#Reports

* Extent report gets generated after run project ./Current_test_results/testreport.html
* TestNG report as "emailable-report.html" gets generated under ./test-output.


#Important Information

>Path for important file
 
 * Configuration File:./Resources/Config.properties
 * RestAssuredBatch File: ./Run/RestAssuredBatch.bat



>





