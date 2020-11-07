Feature: API automation for swagger product
@POST
Scenario Outline: swagger product post
Given user has URI
And user has payload "<name>","<type>",<price>,<shipping>,"<upc>","<description>","<manufacturer>","<model>","<url>","<image>"
When user trigger "<request>" with "<resources>"
Then verify <statuscode> code and response body for "<request>"
Examples:
|request|resources			|statuscode	|name		|type		|price|shipping	|upc		|description|manufacturer	|model	|url						|image	|
|POST		|postProductURI	|201				|honor	|mobile	|15000|50				|NA			|handset		|Huwaii				|XXYX		|www.horonr.com	|NA	|

@GET
Scenario Outline: swagger product get
Given user has URI
When user trigger "<request>" with "<resources>"
Then verify <statuscode> code and response body for "<request>"
Examples:
|request|resources			|statuscode	|
|GET		|getProductURI	|200				|

@DELETE
Scenario Outline: swagger product get
Given user has URI
When user trigger "<request>" with "<resources>" for "<id>"
Then verify <statuscode> code and response body for "<request>"
Examples:
|request|resources				|id				|statuscode	|
|DELETE	|deleteProductURI	|9999822	|200				|

@PATCH
Scenario Outline: swagger product get
Given user has URI
And user has payload "<name>","<type>",<price>,<shipping>,"<upc>","<description>","<manufacturer>","<model>","<url>","<image>"
When user trigger "<request>" with "<resources>" for "<id>"
Then verify <statuscode> code and response body for "<request>"
Examples:
|request|resources				|id				|statuscode	|name		|type		|price|shipping	|upc		|description|manufacturer	|model	|url		|image	|
|PATCH	|patchProductURI	|9999832	|200				|Dubey	|Rajan	|0		|0				|string	|string			|string				|string	|string	|string	|