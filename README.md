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
spring.data.mongodb.uri=mongodb://127.0.0.1:27018/ems
```
I changed the port number since i have mongo installed locally and so that fiddles with the one in docker continer

Now what happens when you stop a container using
```dtd
docker container stop <container_number>;
```
![img.png](img.png)

You can restart it using 
```dtd
docker container restart <container-image_number>
```
You can get the previously stopped container image number using
```dtd
docker container ls -l
```
this should give you all the containers that are in your machine and are running also stopped as well