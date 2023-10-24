# Example for consuming from Kafka with the usage of Apicurio Schema Registry and Avro

You'll need a running Kafka instance and an Apicurio Registry

## Kafka instance

You could use a plain Kafka archive or use an Ansible role

## Apicurio Registry

```bash
docker run -it -p 8080:8080 apicurio/apicurio-registry-mem:2.4.12.Final
```

This will run an Apicurio Registry instance with in-memory persistence.

## Configure the applications

In `application.properties` set the Kafka instance address and the Apicurio schema registry URL.

## Produce to Kafka.

Run [`Produce.java`](./kafka-producer/src/main/java/com/acme/example/kafka/Produce.java) to produce a message to the Kafka.

```bash
mvn compile exec:java -Dexec.mainClass="com.acme.example.kafka.Produce"
```

## Produce to Kafka without Kamelets

To consume messages using a Camel route, first install the kafka-producer maven project:
```bash
cd kafka-producer
mvn clean install
```
then run:
```bash
camel run kafka-log.yaml 
```

You should see something like

```bash
2023-10-10 13:44:09.810  INFO 71770 --- [           main] el.impl.engine.AbstractCamelContext : Routes startup (started:2)
2023-10-10 13:44:09.810  INFO 71770 --- [           main] el.impl.engine.AbstractCamelContext :     Started kafka-to-log (kafka://my-topic)
2023-10-10 13:44:09.810  INFO 71770 --- [           main] el.impl.engine.AbstractCamelContext :     Started log-sink-1 (kamelet://source)
2023-10-10 13:44:09.810  INFO 71770 --- [           main] el.impl.engine.AbstractCamelContext : Apache Camel 4.0.1 (kafka-log) started in 187ms (build:0ms init:0ms start:187ms)
2023-10-10 13:44:10.018  WARN 71770 --- [sumer[my-topic]] fka.clients.consumer.ConsumerConfig : These configurations '[apicurio.registry.avroDatumProvider, apicurio.registry.url]' were supplied but are not used yet.
2023-10-10 13:44:10.019  INFO 71770 --- [sumer[my-topic]] he.kafka.common.utils.AppInfoParser : Kafka version: 3.4.0
2023-10-10 13:44:10.019  INFO 71770 --- [sumer[my-topic]] he.kafka.common.utils.AppInfoParser : Kafka commitId: 2e1947d240607d53
2023-10-10 13:44:10.019  INFO 71770 --- [sumer[my-topic]] he.kafka.common.utils.AppInfoParser : Kafka startTimeMs: 1696938250018
2023-10-10 13:44:10.023  INFO 71770 --- [sumer[my-topic]] ort.classic.AssignmentAdapterHelper : Using NO-OP resume strategy
2023-10-10 13:44:10.023  INFO 71770 --- [sumer[my-topic]] l.component.kafka.KafkaFetchRecords : Subscribing my-topic-Thread 0 to topic my-topic
2023-10-10 13:44:10.024  INFO 71770 --- [sumer[my-topic]] afka.clients.consumer.KafkaConsumer : [Consumer clientId=consumer-my-consumer-group-1, groupId=my-consumer-group] Subscribed to topic(s): my-topic
2023-10-10 13:44:10.254  INFO 71770 --- [sumer[my-topic]] org.apache.kafka.clients.Metadata   : [Consumer clientId=consumer-my-consumer-group-1, groupId=my-consumer-group] Resetting the last seen epoch of partition my-topic-0 to 0 since the associated topicId changed from null to PP5gKKwZTTOwYYvKftvhgA
2023-10-10 13:44:10.256  INFO 71770 --- [sumer[my-topic]] org.apache.kafka.clients.Metadata   : [Consumer clientId=consumer-my-consumer-group-1, groupId=my-consumer-group] Cluster ID: LGe3ByI8SLSis9Sm9zcCVg
2023-10-10 13:44:10.257  INFO 71770 --- [sumer[my-topic]] sumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-my-consumer-group-1, groupId=my-consumer-group] Discovered group coordinator ghost:9092 (id: 2147483647 rack: null)
2023-10-10 13:44:10.263  INFO 71770 --- [sumer[my-topic]] sumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-my-consumer-group-1, groupId=my-consumer-group] (Re-)joining group
2023-10-10 13:44:10.276  INFO 71770 --- [sumer[my-topic]] sumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-my-consumer-group-1, groupId=my-consumer-group] Request joining group due to: need to re-join with the given member-id: consumer-my-consumer-group-1-88145d04-879c-4cd9-9f5a-53a2c6778033
2023-10-10 13:44:10.278  INFO 71770 --- [sumer[my-topic]] sumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-my-consumer-group-1, groupId=my-consumer-group] Request joining group due to: rebalance failed due to 'The group member needs to have a valid member id before actually entering a consumer group.' (MemberIdRequiredException)
2023-10-10 13:44:10.278  INFO 71770 --- [sumer[my-topic]] sumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-my-consumer-group-1, groupId=my-consumer-group] (Re-)joining group
2023-10-10 13:44:10.283  INFO 71770 --- [sumer[my-topic]] sumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-my-consumer-group-1, groupId=my-consumer-group] Successfully joined group with generation Generation{generationId=19, memberId='consumer-my-consumer-group-1-88145d04-879c-4cd9-9f5a-53a2c6778033', protocol='range'}
2023-10-10 13:44:10.285  INFO 71770 --- [sumer[my-topic]] sumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-my-consumer-group-1, groupId=my-consumer-group] Finished assignment for group at generation 19: {consumer-my-consumer-group-1-88145d04-879c-4cd9-9f5a-53a2c6778033=Assignment(partitions=[my-topic-0])}
2023-10-10 13:44:10.292  INFO 71770 --- [sumer[my-topic]] sumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-my-consumer-group-1, groupId=my-consumer-group] Successfully synced group in generation Generation{generationId=19, memberId='consumer-my-consumer-group-1-88145d04-879c-4cd9-9f5a-53a2c6778033', protocol='range'}
2023-10-10 13:44:10.294  INFO 71770 --- [sumer[my-topic]] sumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-my-consumer-group-1, groupId=my-consumer-group] Notifying assignor about the new Assignment(partitions=[my-topic-0])
2023-10-10 13:44:10.298  INFO 71770 --- [sumer[my-topic]] sumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-my-consumer-group-1, groupId=my-consumer-group] Adding newly assigned partitions: my-topic-0
2023-10-10 13:44:10.314  INFO 71770 --- [sumer[my-topic]] sumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-my-consumer-group-1, groupId=my-consumer-group] Setting offset for partition my-topic-0 to the committed offset FetchPosition{offset=11, offsetEpoch=Optional[0], currentLeader=LeaderAndEpoch{leader=Optional[ghost:9092 (id: 0 rack: null)], epoch=0}}
```

and after a message has been produced to Kafka you should see

```bash
2023-10-10 13:44:10.519  INFO 71770 --- [sumer[my-topic]] log-sink                            : Exchange[
  ExchangePattern: InOnly
  Headers: {apicurio.value.encoding=[B@474baada, apicurio.value.globalId=[B@28e32105, CamelMessageTimestamp=1696938203819, kafka.HEADERS=RecordHeaders(headers = [RecordHeader(key = apicurio.value.globalId, value = [0, 0, 0, 0, 0, 0, 0, 3]), RecordHeader(key = apicurio.value.encoding, value = [66, 73, 78, 65, 82, 89])], isReadOnly = false), kafka.KEY=key, kafka.OFFSET=11, kafka.PARTITION=0, kafka.TIMESTAMP=1696938203819, kafka.TOPIC=my-topic}
  BodyType: org.apache.avro.generic.GenericData.Record
  Body: {"orderId": 1, "itemId": "item", "userId": "user", "quantity": 3.0, "description": "A really nice item I do love"}
]
```

## Produce to Kafka with Kamelets

You might also want to try out the specialized Kamelet sink for Apicurio Registry

Follow the same approach but run

then run:
```bash
jbang run camel@apache/camel run --local-kamelet-dir=<local_path_to_camel_kamelets> --kamelets-version=4.1.0-SNAPSHOT kafka-apicurio-producer-kamelet.yaml
```

## Consume from Kafka with Kamelets

You might also want to try out the specialized Kamelet for Apicurio Registry

Follow the same approach but run

then run:
```bash
jbang run camel@apache/camel run --local-kamelet-dir=<local_path_to_camel_kamelets> --kamelets-version=4.1.0-SNAPSHOT kafka-apicurio-kamelet.yaml
```

