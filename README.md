# Java(Kotlin) Paycom integration template

## Prerequisites:
````
- Spring Boot 1.5.2
- Java 8 JDK
- Kotlin 1.3
- Gradle 3.5
````
## Get the template:
````
git clone git@github.com:PaycomUZ/paycom-integration-java-template.git
````
## Go to the template root folder
Edit your properties `\src\main\resources\application.yaml` <br>
````
security:
  user:
    name: Paycom
    password: YourCashBoxKeyHere
````
## Run
````
gradle bootRun
````
## Test
````
curl -H "Content-Type:application/json" -d '{"id":"1", "jsonrpc":"2.0", "method":"CheckPerformTransaction","params":{"amount":50000, "account":{"order":100}}}' http://yourHost:8080/api
````
Paycom test tool - [Merchant Test](http://test.paycom.uz/) <br>
You can also run tests from `MerchantApplicationTests.kt`

## Docs
http://paycom.uz/api/
