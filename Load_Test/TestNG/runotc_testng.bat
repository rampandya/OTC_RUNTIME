## Run OTC test case
## Must supply one of the follwoing parameters depending on Browser of your choice. 
## For Chrome use the following parms 
## -DdriverType=Chrome -DdriverLoc="C:\\OTC_Runtime\\drivers\\chromedriver.exe"
## For IE use the following parms
## -DdriverType=IE -DdriverLoc="C:\\OTC_Runtime\\drivers\\IEDriverServer.exe"
## For Firefox, us the following parms
## -DdriverType=FF -DdriverLoc=None
## If you want to run all the three then uncomment all of the following commands.
## For now, it is conigured to run with Chrome for 10 users. 

##  To run test case in MS IE configure MS IE as follow:

##  1.  Include the path of the IE driver in the windows PATH variable.
##  2.  Under the view menu of IE the zoom must be set to 100%.
##  3.  For all zones in Tools->Security, Enable Protected Mode.

# Use following command to simulate 10 users
java -cp "C:\OTC_Runtime\Load_Test\TestNG\otcloadtestng.jar" -DdriverType=Chrome -DdriverLoc="C:\\OTC_Runtime\\drivers\\chromedriver.exe" org.testng.TestNG  "C:\OTC_Runtime\Load_Test\TestNG\testng_10.xml" 

# Use following command to simulate 50 users 
#java -cp "C:\OTC_Runtime\Load_Test\TestNG\otcloadtestng.jar" -DdriverType=Chrome -DdriverLoc="C:\\OTC_Runtime\\drivers\\chromedriver.exe" org.testng.TestNG  "C:\OTC_Runtime\Load_Test\TestNG\testng_50.xml" 

# Use following command to simulate 100 users
#java -cp "C:\OTC_Runtime\Load_Test\TestNG\otcloadtestng.jar" -DdriverType=Chrome -DdriverLoc="C:\\OTC_Runtime\\drivers\\chromedriver.exe" org.testng.TestNG  "C:\OTC_Runtime\Load_Test\TestNG\testng_100.xml" 
