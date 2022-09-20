# gift-card-app
App to demonstrate CQRS &amp; ES based on Spring Boot and Axon

### What is the cqrs pattern ? 

**CQRS** stands for Command Query Responsibility Segregation is a design pattern that aims to separate the Read and Write operations.

* Queries: a Read only operation - no state is updated after executing queries
* Commands: a Writing operation - state is updated after executing commands


### What is the Event Sourcing Pattern ?
Event Sourcing aims to persist the state of a business entity as a sequence of state-changing events. Every action performed on a business entity should be persisted. The application reconstructs an entity’s current state by replaying the events

### Axon Server
We will use Axon Server to be our Event store and our dedicated command, event and query routing solution.

As an Event Store, it gives us the ideal characteristics required when storing events.

As a Message Routing solution, it gives us the option to connect several instances together without focusing on configuring things like a RabbitMQ or a Kafka topic to share and dispatch messages.

axon server jar [download](https://download.axoniq.io/axonserver/AxonServer.zip)

### Application Features
A GiftCard app where a user can issue card, redeem card, reload card and cancel card.

#### Endpoints 
    POST /api/gift_cards
    POST /api/gift_cards/cancel/{id}

    PUT /api/gift_cards/redeem/{id}
    PUT /api/gift_cards/reload/{id}

    GET /api/gift_cards
    GET /api/gift_cards/{id}
    GET /api/gift_cards/{id}/events



### Getting Started Guides
The following guides illustrate how to run this repo locally :

##### step 1:
`git clone https://github.com/MdShohanurRahman/gift-card-app.git`

##### step 2
add database credentials in `application.properties`

##### step 3
download axon server jar [download](https://download.axoniq.io/axonserver/AxonServer.zip).
unzip and goto this folder and run command `java -jar axonserver.jar`

##### step 4
run gift-card-app project with this command `mvn spring-boot:run` or run by your idea.Finally, you are ready to go.

##### step 5
hit this url `http://localhost:8081/swagger-ui/` and explore giftCard apis


### Reference Documentation
For further reference, please consider the following sections:

* [Official Axon documentation](https://docs.axoniq.io/reference-guide/)
