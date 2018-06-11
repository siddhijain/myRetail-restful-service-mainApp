# myRetail-restful-service-mainApp
RESTful service which aggregates product data from multiple sources and return it as JSON to the caller. 

Download myRetail-restful-service-mainApp from https://github.com/siddhijain/myRetail-restful-service-mainApp/

Download myRetail-restful-service-productDetailsApp from https://github.com/siddhijain/myRetail-restful-service-productDetailsApp/

Install Java 1.8

Install maven

For productDetailsApp project -> 
  mvn clean
  mvn install
  mvn spring-boot:run

For mainApp project ->
  mvn clean 
  mvn install
  mvn spring-boot-run

Use Postman or RestClient to test the service.
http://localhost:8080/products/16483589

Some Valid IDs:
15117729
16483589
16696652
15643793
12345632
11111111
23223232
55334455
22222222
33333333
