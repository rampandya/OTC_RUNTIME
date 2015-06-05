## Run OTC test case
## If you happend to save the otctestcase.jar and TestData.xlsx file in a different directory then
## make sure to update the following with appropriate directory. 

##  To run test case in MS IE configure MS IE as follow:

##  1.  Include the path of the IE driver in the windows PATH variable.
##  2.  Under the view menu of IE the zoom must be set to 100%.
##  3.  For all zones in Tools->Security, Enable Protected Mode.

## Run in Firefox use the following command 
##java -cp "C:\OTC_Runtime\Selenium_Testcase\otctestcase.jar" -DfileName="C:\\OTC_Runtime\\Selenium_Testcase\\TestData.xlsx" -DdriverType=FF -DdriverLoc=none org.junit.runner.JUnitCore  com.otc.testauto.TestGetQuote 

## for Chrome use the following command
java -cp "C:\OTC_Runtime\Selenium_Testcase\otctestcase.jar" -DfileName="C:\\OTC_Runtime\\Selenium_Testcase\\TestData.xlsx" -DdriverType=Chrome -DdriverLoc="C:\\OTC_Runtime\\drivers\\chromedriver.exe" org.junit.runner.JUnitCore  com.otc.testauto.TestGetQuote 

## for IE use the following command
##java -cp "C:\OTC_Runtime\Selenium_Testcase\otctestcase.jar" -DfileName="C:\\OTC_Runtime\\Selenium_Testcase\\TestData.xlsx" -DdriverType=IE -DdriverLoc="C:\\OTC_Runtime\\drivers\\IEDriverServer.exe" org.junit.runner.JUnitCore  com.otc.testauto.TestGetQuote 