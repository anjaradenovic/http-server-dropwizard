# Library web application

This is a simple web server for managing books. It is built using:
- Java 8
- Maven 3.6.1
- Dropwizard 1.3.15
- PostgreSQL 12.0

## API
The application has two routes:

One for getting a book by isbn

```GET /book?isbn={some_isbn_here}```

and the other for adding a book

```POST /book```

A book is represented with a JSON object in the following format:
```json
{
  "title": "Green mile",
  "authors": [
    "Stiven King"
  ],
  "numberOfPages": 326,
  "genre": "horror",
  "isbn": "978-86-10-02326-8"
}
```

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

## Resources used for this project

- https://stackabuse.com/dropwizard-develop-restful-web-services-faster/
- https://stackoverflow.com/questions/29109887/how-can-i-return-404-http-status-from-dropwizard
- https://www.baeldung.com/jersey-jax-rs-client
- https://www.postgresql.org/download/windows/
- https://www.microfocus.com/documentation/idol/IDOL_12_0/MediaServer/Guides/html/English/Content/Getting_Started/Configure/_TRN_Set_up_PostgreSQL.htm
- http://www.postgresqltutorial.com/postgresql-primary-key/
- https://stackoverflow.com/questions/46805427/insert-values-to-postgresql-using-jdbc
- https://jdbc.postgresql.org/documentation/80/connect.html
- https://www.dropwizard.io/en/stable/manual/testing.html
- https://stackoverflow.com/questions/49215416/maven-shade-plugin-cannot-find-resource-in-class-org-apache-maven-plugins-sh