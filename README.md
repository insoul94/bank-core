# bank-core
Small core banking solution.

- Account keeps track of current accounts, balances, and transaction history.
- Capability to publish messages into RabbitMQ for other consumers.

REST APIs:
* POST  /account
* GET   /account/{id}
* POST  /transaction
* GET   /transaction/{id}

Technologies used:
* Java 17
* SpringBoot
* MyBatis
* Gradle
* Postgres
* RabbitMQ
* JUnit, Mockito, Hamcrest
