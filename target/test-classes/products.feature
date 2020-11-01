Feature: API automation for swagger product
@POST
Scenario Outline: swagger product post
Given user has URI
And user has payload "<name>","<type>",<price>,<shipping>,"<upc>","<description>","<manufacturer>","<model>","<url>","<image>"
When user trigger "<request>" with "<resources>"
Then verify <statuscode> code and response body for "<request>"
Examples:
|request|resources			|statuscode	|name		|type		|price|shipping	|upc		|description|manufacturer	|model	|url		|image	|
|POST		|postProductURI	|201				|string	|string	|0		|0				|string	|string			|string				|string	|string	|string	|

@GET
Scenario Outline: swagger product get
Given user has URI
When user trigger "<request>" with "<resources>"
Then verify <statuscode> code and response body for "<request>"
Examples:
|request|resources			|statuscode	|
|GET		|getProductURI	|200				|