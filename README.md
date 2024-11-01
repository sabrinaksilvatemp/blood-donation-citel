
## Getting Started


### Requirements
- Java v21
- MySQL server

### Running locally
- Create a local DB, and set its name into DB_NAME env var, its port into DB_PORT, and username and password into DB_USERNAME and DB_PASSWORD env vars

    - Alternatively, use the default values ​​for DB_PORT, DB_NAME, DB_USERNAME, and DB_PASSWORD as follows, configured in application.yaml:
        - DB_PORT = 3306
        - DB_NAME = blooddonationdb
        - DB_USERNAME = root
        - DB_PASSWORD= 1234
- Run, in your created database, the SQL commands located in src/main/resources/static/seed.sql to insert the data from the table provided for the technical test into the DB.
- Install dependencies from pom.xml using maven
- Perform a GET to http://localhost:8050/blood-donation/candidates/process, specifying the appropriate json in the body
