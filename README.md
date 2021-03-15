# customers-microservice

A rest api Microservice-based for the customers & countires endpoints.. 

## Prequistis:
* Docker
* Port 8080


## How To Run?
* Please free port 8080 and run the below two commands for building the service and run the docker image on port 8080.
```docker
docker build -t customers .
docker run -p 8080:8080 customers
```
*In order to use Swagger, please navigate to http://localhost:8080/api/swagger-ui/index.html


