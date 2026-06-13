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