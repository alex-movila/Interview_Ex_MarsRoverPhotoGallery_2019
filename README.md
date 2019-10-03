Time limit

For regular developers takes approximate 2 hours.
For fullstack developers takes approximate 3 hours.
Setting up your environment and reading documentation must not be taken into account.
Setup

First of all, you'll need an API-key from NASA. You'll need to request by using this page.
Get familiar with the NASA API by trying out the resources here using i.e. Postman.
More information on api.nasa.gov

Assignment

The assignment consists of two parts, a web user interface and a back-end application supporting this web user interface.
Web user interface
Please create a simple web user interface which could search and show Mars Rover photos. You'll need to read the following documentation.

Backend API

A backend API must support the web user interface. Based on the search criteria's, the backend will make the calls to NASA. The results must be parsed from JSON.
Each API call must be logged into a database. An in-memory database should be sufficient.
Make sure that this will not affect the application to stop working. Criteria for storing the API call request:
•	Generated ID
•	Requested method name
•	Response time in milliseconds
•	Date created

Configuration

The application must be configurable, this means that there should be using some kind of configuration file.
Testing
It is not possible to cover the application entirely with tests, but at least cover the most important part of the application like services or controllers.

Bonus points

•	Make the application multilingual (EN, NL or any other language)
•	Add more?

Building the API

In order to build the API a simple gradle build will do.
Running the API locally
gradle bootRun
If port 8080 is already bound you can change te port number. Go to the file application.yml and update the following setting:
server:
    port: 8090

The application is available with the URL: http://localhost:8080

Objectives:

Check on the following aspects:
•	Dependency injection
•	Clean coding
•	Usage of enumerations
•	REST solution
•	Catching exceptions
•	Usage of collections
•	Externalize property configuration

TODOs:
- read ahead next page, keep in cache previous page
- add a layout for web page 
- show more info on web page: earth date etc
- remove warnings
- sol edit box should show integer
- fix need press next twice before disable at end of pages 
- sol edit values are limited to max 1000 
- add more tests with edge cases
- convert to gradle project 
- use Aspects to hook logging of API calls
- add Mockito unit tests
- handle exceptions

