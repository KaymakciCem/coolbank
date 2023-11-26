# cool-bank assignment
___
### Spring Boot Application

#### 
- This project provides an API where users can calculate repayment plans for their loans
- The API exposes a REST endpoint.
- Following information need to be provided to the API. Then api responds back the payment plan.
    - loan amount
    - nominal interest
    - duration
    - start date

### Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- Restful API
- Mysql Db
- Docker
- JUnit 5
- k8s deployment file (configurations added for the k8s cluster. the required image is on dockerhub)

### Prerequisites

---
- Gradle
- MySql
- Docker

### Build & Run

first go to the terminal and open up the project directory. "~/coolbank"

### build

./gradlew clean build

### Run tests

./gradlew test

### Docker

to run the project
 - docker-compose up -d --build

to stop the project
 - docker-compose down

### explicitly building docker image
docker build -t repaymentplan-service .

### explicitly run docker image
docker-compose up --build


### API DOCUMENTATION (Swagger)

- After project runs you will be able to reach the url below where you can see the API doc.
- http://localhost:8080/swagger-ui/index.html

### Metrics

- Some metrics are enabled on the actuator api. We can observe the system on production.
- http://localhost:8089/actuator

### Prometheus
http://localhost:8080/actuator/prometheus


### curl command to send a request to the api
curl --location 'http://localhost:8089/generate-plan' \
--header 'Content-Type: application/json' \
--data '{
"loanAmount": "5000",
"nominalRate": "5.0",
"duration": 24,
"startDate": "01-01-2024"
}'
