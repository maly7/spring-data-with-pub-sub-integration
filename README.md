Spring Data Publishing using JMS and Spring Integration
--------------------------------------------------------

To Run the example:
1. `spring.datasource.url` in the publisher [config](publisher/src/main/resources/application.yml)
1. run `./gradlew :p:startActiveMQ` to start up ActiveMQ
1. run `./gradlew :s:bootRun` to start up the subscriber app
1. run `./gradlew :p:test`, this runs a test that will cause a BookEvent to be created with the resulting event published through spring integration via JMS to the subscriber app. In the Subscriber logs you'll see a message that looks like: `Received Book [$bookId]`


### Work left for the example:
1. Setup solr and via spring-data-solr in the subscriber app
1. Publish Delete messages from the publisher app
