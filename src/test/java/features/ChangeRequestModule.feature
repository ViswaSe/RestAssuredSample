Feature: Change Request Module

Scenario: TS_001_Get All Change Request
And Add the parameters required
When Call the GET API to get all the change request
Then Verify the status code as 200
And Verify the content type json
And Print the response along with response status code

Scenario Outline: TS_002_Create a New Change Request
And Add the parameters required
And Add the body param as '<short_description>' and '<category>' for the POST
When Call the POST API to create a new change request
Then Verify the status code as <statuscode>
And Verify the content type as '<content_type>'
And Print the response along with response status code
And Store the resulted sys_id value and print it

Examples:
|short_description|category|statuscode|content_type|
|Create CR1|Hardware|201|Json|
|Create CR2|Software|201|Json|

Scenario Outline: TS_003_Update the existing Change Request
And Add the parameters required
And Add the body param as '<short_description>' and '<description>' and '<category>' for the PATCH
When Call the PATCH API to update the change request
Then Verify the status code as <statuscode>
And Verify the content type as '<content_type>'
And Print the response along with response status code
And Store the resulted sys_id value and print it

Examples:
|short_description|description|category|statuscode|content_type|
|Update CR1|CR1 PATCH|Hardware|200|json|
|Update CR2|CR2 PATCH|Software|200|json|

Scenario: TS_004_Delete Change request
And Add the parameters required
When Call the DELETE API to delete the change request
Then Verify the status code as 204
And Print the response along with response status code