# SeleniumPageObjectModel

### Steps to use
Update the drivers in "src/main/resources" corresponding to your Browser version
Change the url as per your requirements in config.properties


### Framework Approach
Page objects are maintained in com.nagp.selenium.PageObjects package, things related to
particular page like the Controls, functions realted to controls for page have been
maintained there.


Controls are a kind of wrapper for the webelement with different functionality.
Control implementation helps in keeping the code cleaner and pageObjects small and interacble to much level
For the same Custom Page factory has been implemented to get the wrapped webelement in the form of control interface
on using the @Find By Custom annotation

SuiteListeners have been implemented for dealing with suite related works.
TestListeners have been implemented for dealing the all test run scenarios.
InvokeMEthos Listeners have been implemented for ending and starting the test for each test case in ExtentReports

Test data is maintained in testData.properties in resources folder.
@dataproviders used for getting the test data foreach test case

To run suite from command line have implemented maven sureFire
plugin, command for same is: "mvn compile test"
