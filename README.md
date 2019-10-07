# Library web application

This is a simple web server for managing books. It is built using:
- Java 8
- Maven 3.6.1
- Dropwizard 1.3.15
- PostgreSQL 12.0

## How to build the project
Run the following command in the root of the project
```
mvn package
```
## How to test
Run the following command in the root of the project
```
mvn test
```
**Note**: In order to run the tests, it is necessary to initialize the
PostgreSQL database using [db_init.sql](db_init.sql) script. Default
configuration for the database is:
- **username**: postgresql
- **password**: postgresql
- **database name**: postgresql

These settings can be modified in the [config.yml](config.yml) and [test-config.yml](server/src/main/java/com/anjastanojevic/server/resources/test-config.yml)
files.

## How to run locally
From the IDE, start the main class in server-module [Main.java](server/src/main/java/com/anjastanojevic/server/Main.java). 
You can then run the main class in the client-module [Client.java](/client/src/main/java/com/anjastanojevic/client/Main.java).