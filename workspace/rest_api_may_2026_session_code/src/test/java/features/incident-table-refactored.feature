Feature: User should able to perform CRUD operations in the incident table

Background:
Given set base uri "https://dev195091.service-now.com"
And set base path "/api/now/table"
And add basic authentication username "admin" and password "@1DGu+KDi8wv"
And add path parameter variable name "tableName" and value "incident"

Scenario: User should able to retrieve all records from the incident table
When hit get method of the "/{tableName}" endpoint
Then validate the response with following expected value
| statusCode | statusLine | responseFormat |
| 200        | OK         | JSON           |

Scenario: User should able to retrieve all records from the incident table in XML response formate
Given add header key "Accept" and value "application/xml"
When hit get method of the "/{tableName}" endpoint
Then validate the response with following expected value
| statusCode | statusLine | responseFormat |
| 200        | OK         | XML            |

Scenario: User should able to create new record inside the incident table passing request payload as string format
Given add header key "Content-Type" and value "application/json"
When add the request payload in the json string 
"""
{
 "short_description":"Create Opp",
 "description":"Create new record using post method"
}
"""
And hit post method of the "/{tableName}" endpoint
Then validate the response with following expected value
| statusCode | statusLine | responseFormat |
| 201        | Created    | JSON           |

Scenario: User should able to create new record inside the incident table passing request payload as file format
Given add header key "Content-Type" and value "application/json"
When hit post method of the "/{tableName}" endpoint and request payload as file format the location is "src/main/resources/request_payload/create-incident.json" 
Then validate the response with following expected value
| statusCode | statusLine | responseFormat |
| 201        | Created    | JSON           |

Scenario: User should able to create new record inside the incident table passing request payload as POJO object
Given add header key "Content-Type" and value "application/json"
And set short description value as "Create new record"
And set description value as "Create new record using post method"
And set category value as "database"
When add the request payload in the create incident pojo object
And hit post method of the "/{tableName}" endpoint
Then validate the response with following expected value
| statusCode | statusLine | responseFormat |
| 201        | Created    | JSON           |