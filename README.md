
# NAB Web

## Environment Preparation
1. Install JAVA
2. Install Maven
3. SDK 16.0

## How it work
Please open source file in Intellij

In **configuration**, import one runner using **JUnit** with configure bellow

Test kind: **Class**

Class: **Runner.Runner**

If IntelliJ does not recognize PageManagement is Test Sources Root, please mark as Test Sources Root


In VM Command, please insert this command:

    -ea Dcucumber.filter.tags="@NABTest"

** Note: You can replace the tag by the specificed Tag in feature file to run it.

Then, click run

The Test will contain all the steps from
- Navigate to https://openweathermap.org
- Type "thanh pho Ho Chi Minh, VN" into Search box on Navigation Bar
- Click Enter key
- Verify returned records.

## Framework Structure

**src.test:**
- **PageManagement**
  
  -- **Common folder**: store all file which has common function in here (E.g: read proeperties file)
  
  -- **Core Management folder:** Store setup driver, and all action reference with driver, Iterface Page and Abstract Page
  
  -- **Page Definition folder**: Stroe all definition needed for Cucumber running 
  
  -- **Page Object Management**: Store all page object function (action/assertion).

  -- **Runner**: store runner configuration
- **Resources**
  -- Store all needed properties file
- **Test Suite**
    - **F_01_Search**: contains the test script

Config file locate at same level at src folder in project structure:
- **config.properties**
  
  -- MAIN_URL = https://openweathermap.org
  
  -- BROWSER = Chrome
 
  -- VERSION = 89

The browser in the framework is only support Chrome and Firefox

** Note: There also has a another way to set Browser and Version by VM Command.

    -ea -DBROWSER=Chrome -DVERSION=89 Dcucumber.filter.tags="@NABTest"

## Report
This framework using Allure Report.

1. Install Allure Report.
2. Using Terminal (on Mac) or Command Prompt (on Windows) and navigate to the source code. After run successfully. the report will generate into target/allure-results.
3. Navigate into target folder, and using command `allure serve` to generate the report.

