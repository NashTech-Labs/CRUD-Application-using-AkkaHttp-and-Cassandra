# CRUD-Application-using-AkkaHttp-and-Cassandra

This template is basically on Akka http and Cassandra.
In this template I just created a CRUD application using Akka Http as a server and Cassandra as a database.

**HOW TO RUN** 

To run this application first you need to clone this project on you local and start cassandra on default port.
Now you have to create keyspace and table in your local machine for that you can use these CQL queries.

Create Keyspace query -

**CREATE KEYSPACE IF NOT EXISTS College
WITH REPLICATION = {
'class' : 'SimpleStrategy',
'replication_factor': 1
};**

Create table query - 

**CREATE TABLE College.Students (
rollNo int,
name text,
address text,
subjects int,
course text,
PRIMARY KEY ((rollNo),course) );**

Then insert some records using query - 

**INSERT INTO College.Students (rollNo,name,address,subjects,course)
VALUES (1,'Utkarsh','Noida',5,'Btech');**

Now after starting Cassandra and creating keyspace and table on you local machine goto your project directory and run the command '**sbt run**'.

sbt run will start the scala class - **com.knoldus.AkkaHttpServer** which start the server on **http://localhost:8080** 

1) CREATE 
    To insert data into cassandra open your terminal and hit
   **curl -H "Content-Type: application/json" -X POST -d '{"address": "Delhi","course": "Bca","name": "Rohit","rollno": 10,"subjects": 5}' http://localhost:8080/insertStudent**
         

2) READ
   To read data from cassandra goto your browser/Postman and hit **http://localhost:8080/getStudents**

3) DELETE
   To delete record from cassandra - **curl -X DELETE http://localhost:8080/removeStudent/10**

4) UPDATE
    Update will only be work when we have https rather than http.
    command for update - **curl -X UPDATE http://localhost:8080/updateStudent/19/UPSC**
    As I don't have any certificate for https so If I will try to execute the command for updating data it will through an exception that - **Illegal request, responding with status '501 Not Implemented': Unsupported HTTP method: UPDATE**


