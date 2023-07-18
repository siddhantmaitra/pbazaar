# pbazaar
Automation test suite for www.policybazaar.com

## Status
- [x] Requirement 1: Get 3 lowest student travel insurance plans (names & price)
- [x] Requirement 2: Get error message when filling car insurance form with invalid phone/email
- [x] Requirement 3: Get health insurance menu items

## Details: 
1. Find travel insurance plan for students, for 2 people (Age 22 & 21) & any European country, fill further dummy details & display three lowest international  travel insurance plan with amount and insurance provider company
2. Get a Car Insurance quote, proceed without  car number, keep filling details, give invalid email or phone number & capture the error message
3.  Retrieve all Health Insurance menu items and store in a List; Display the same.

(Suggested Site: policybazaar.com however you are free to use any other legitimate site)

## Features: 
1. Automation with Selenium and Java
2. Page Object Model design pattern implemented by using `PageFactory`
3. Data-Driven testing using : `.json` and `.properties` as data source
4. Use of TestNG for creation of test suite
5. Apache POI used for writing data to Excel spreadsheet
6. Multi-browser testing support: Chrome and Edge
7. Extent Report generation
8. Screenshot taken after each test and attached to extent report.
9. Maven integration. Test suite can be run by using `mvn test`

## Technologies Used:
1. Selenium
2. TestNG
3. Apache POI
4. ExtentReports

