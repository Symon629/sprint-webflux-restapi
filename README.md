# Database Setup
We are using docker based approach for the db\
Step 1: Pull the docker image if you dont have the image locally
```dtd
docker pull mongo:latest
```
Step2: Run the following to start the container
```dtd
docker container run -p  27017: 27017   —name mongodb_webfluxtest -d mongo
```
You can have any name you like --name -p here is the port the first one is your local and the other one is the docker contiainer

Step 3: Verify the container running and Grab the id from there
```dtd
docker container ls or docker ps
```
Step 4: Run the bash inside the mongoshell. The 5370 is the container id from docker container ls
```dtd
docker exec -it 5370 bash
```
So now you are inside the container running bash

Run
```dtd
show dbs
```
to see all the database.

```dtd
use whatever_db_name
```
this creates a database for you 

## Database Configuration
Update this database configuration in application.properties in main/resources/application.properties
```dtd
spring.application.name=spring-webflux-test
spring.data.mongodb.uri=mongodb://127.0.0.1:27017/ems
```