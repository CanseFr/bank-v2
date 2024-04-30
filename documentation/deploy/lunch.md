**RUNTIME**

To launch the application you must refer to the version of java defined in the pom.xml file. Currently Java17

**DATABASE**

To have a database available for testing, please run main to generate the database using the yaml file

First of all, you must be sure that your MySql database is running in the back ground. Once done you have to take a look at the application.yaml file and follow and modify according to your own parameters

```yaml
spring:
datasource:
url: jdbc:mysql://localhost:3306/bank_v3
driver-class-name: com.mysql.cj.jdbc.Driver
username: ???
password: ???
jpa:
hibernate:
ddl-auto: update
    database: mysql
    database-platform: org.hibernate.dialect.MySQL8Dialect
    properties:
      hibernate:
        format_sql: true
        defer-datasource-initialization: true
springdoc:
default-produces-media-type: application/json
```



<p align="right">(<a href="#readme-top">back to top</a>)</p>
