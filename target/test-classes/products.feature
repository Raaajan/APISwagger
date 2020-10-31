Feature: API automation for swagger product
Scenario Outline: swagger product post
Given user has URI
When user trigger "request"
Then user get response code and response body
Examples:
|request|
|post|
