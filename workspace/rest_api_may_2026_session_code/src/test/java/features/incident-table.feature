Feature: User should able to perform CRUD operations in the incident table

Scenario: User should able to retrieve all records from the incident table
Given set base uri "https://dev195091.service-now.com"
And set base path "/api/now/table"
And add basic authentication username "admin" and password "@1DGu+KDi8wv"
And add path parameter variable name "tableName" and value "incident"
When hit get method of the "/{tableName}" endpoint
Then status code is 200
And status name is "OK"
And response format is "JSON"

Scenario: User should able to retrieve all records from the incident table in XML response formate
Given set base uri "https://dev195091.service-now.com"
And set base path "/api/now/table"
And add basic authentication username "admin" and password "@1DGu+KDi8wv"
And add path parameter variable name "tableName" and value "incident"
And add header key "Accept" and value "application/xml"
When hit get method of the "/{tableName}" endpoint
Then status code is 200
And status name is "OK"
And response format is "XML"

Scenario: User should able to create new record inside the incident table passing request payload as string format
Given set base uri "https://dev195091.service-now.com"
And set base path "/api/now/table"
And add basic authentication username "admin" and password "@1DGu+KDi8wv"
And add path parameter variable name "tableName" and value "incident"
And add header key "Content-Type" and value "application/json"
When add the request payload in the json string 
"""
{
 "short_description":"Create Opp",
 "description":"Create new record using post method"
}
"""
And hit post method of the "/{tableName}" endpoint
Then status code is 201
And status name is "Created"
And response format is "JSON"

Scenario: User should able to create new record inside the incident table passing request payload as file format
Given set base uri "https://dev195091.service-now.com"
And set base path "/api/now/table"
And add basic authentication username "admin" and password "@1DGu+KDi8wv"
And add path parameter variable name "tableName" and value "incident"
And add header key "Content-Type" and value "application/json"
When hit post method of the "/{tableName}" endpoint and request payload as file format the location is "src/main/resources/request_payload/create-incident.json" 
Then status code is 201
And status name is "Created"
And response format is "JSON"

Scenario: User should able to create new record inside the incident table passing request payload as POJO object
Given set base uri "https://dev195091.service-now.com"
And set base path "/api/now/table"
And add basic authentication username "admin" and password "@1DGu+KDi8wv"
And add path parameter variable name "tableName" and value "incident"
And add header key "Content-Type" and value "application/json"
And set short description value as "Create new record"
And set description value as "Create new record using post method"
And set category value as "database"
When add the request payload in the create incident pojo object
And hit post method of the "/{tableName}" endpoint
Then status code is 201
And status name is "Created"
And response format is "JSON"