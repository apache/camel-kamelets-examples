# Example for consuming from Kafka with the usage of Apicurio Schema Registry secured with Keycloak and Avro

You'll need a running Kafka instance and an Apicurio Registry

## Kafka instance

You could use a plain Kafka archive or use an Ansible role

## Apicurio Registry settings

Follow the guide here to have a fully running Apicurio Registry with Keycloak: https://github.com/Apicurio/apicurio-registry/tree/main/distro/docker-compose#docker-compose-and-quarkus-based-installation

Once everything is up and running, access the Keycloak instance located at `http://YOUR_IP:8080`

The username and password is admin/admin.

Select the realm `registry` and create a new user called `registry-account` with password `registry`.

The client Id from Keycloak will be `registry-api`.

In the client page, select credentials and copy the client secret value.

In `application.properties` file copy the client secret value into `keycloak.client.secret`.

The rest of the options could be used as-is.

## Configure the applications

In `application.properties` set the Kafka instance address.

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
jbang run camel@apache/camel run --local-kamelet-dir=<local_path_to_camel_kamelets> kafka-apicurio-kamelet.yaml
```

You should see something like

```bash
2024-02-23 11:53:11.840  INFO 39989 --- [           main] el.impl.engine.AbstractCamelContext : Routes startup (started:3)
2024-02-23 11:53:11.841  INFO 39989 --- [           main] el.impl.engine.AbstractCamelContext :     Started kafka-to-apicurio-log (kamelet://kafka-not-secured-apicurio-registry-source)
2024-02-23 11:53:11.841  INFO 39989 --- [           main] el.impl.engine.AbstractCamelContext :     Started kafka-not-secured-apicurio-registry-source-1 (kafka://my-topic)
2024-02-23 11:53:11.841  INFO 39989 --- [           main] el.impl.engine.AbstractCamelContext :     Started log-sink-2 (kamelet://source)
2024-02-23 11:53:11.841  INFO 39989 --- [           main] el.impl.engine.AbstractCamelContext : Apache Camel 4.4.0 (kafka-apicurio-kamelet) started in 216ms (build:0ms init:0ms start:216ms)
2024-02-23 11:53:12.083  INFO 39989 --- [sumer[my-topic]] fka.clients.consumer.ConsumerConfig : These configurations '[apicurio.registry.avroDatumProvider, apicurio.auth.service.url, apicurio.auth.realm, apicurio.auth.password, apicurio.auth.client.id, apicurio.auth.client.secret, apicurio.registry.url, apicurio.auth.username]' were supplied but are not used yet.
2024-02-23 11:53:12.085  INFO 39989 --- [sumer[my-topic]] he.kafka.common.utils.AppInfoParser : Kafka version: 3.5.1
2024-02-23 11:53:12.085  INFO 39989 --- [sumer[my-topic]] he.kafka.common.utils.AppInfoParser : Kafka commitId: 2c6fb6c54472e90a
2024-02-23 11:53:12.085  INFO 39989 --- [sumer[my-topic]] he.kafka.common.utils.AppInfoParser : Kafka startTimeMs: 1708685592083
2024-02-23 11:53:12.090  INFO 39989 --- [sumer[my-topic]] ort.classic.AssignmentAdapterHelper : Using NO-OP resume strategy
2024-02-23 11:53:12.090  INFO 39989 --- [sumer[my-topic]] l.component.kafka.KafkaFetchRecords : Subscribing my-topic-Thread 0 to topic my-topic
2024-02-23 11:53:12.091  INFO 39989 --- [sumer[my-topic]] afka.clients.consumer.KafkaConsumer : [Consumer clientId=consumer-2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160-1, groupId=2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160] Subscribed to topic(s): my-topic
2024-02-23 11:53:12.328  INFO 39989 --- [sumer[my-topic]] org.apache.kafka.clients.Metadata   : [Consumer clientId=consumer-2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160-1, groupId=2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160] Cluster ID: tM9KiIzXSHOsmkLYHKEB_g
2024-02-23 11:53:12.329  INFO 39989 --- [sumer[my-topic]] sumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160-1, groupId=2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160] Discovered group coordinator ghost:9092 (id: 2147483647 rack: null)
2024-02-23 11:53:12.333  INFO 39989 --- [sumer[my-topic]] sumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160-1, groupId=2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160] (Re-)joining group
2024-02-23 11:53:12.343  INFO 39989 --- [sumer[my-topic]] sumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160-1, groupId=2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160] Request joining group due to: need to re-join with the given member-id: consumer-2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160-1-08829d9f-0409-424b-8a8b-3dfd94379fba
2024-02-23 11:53:12.343  INFO 39989 --- [sumer[my-topic]] sumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160-1, groupId=2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160] Request joining group due to: rebalance failed due to 'The group member needs to have a valid member id before actually entering a consumer group.' (MemberIdRequiredException)
2024-02-23 11:53:12.343  INFO 39989 --- [sumer[my-topic]] sumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160-1, groupId=2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160] (Re-)joining group
2024-02-23 11:53:12.346  INFO 39989 --- [sumer[my-topic]] sumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160-1, groupId=2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160] Successfully joined group with generation Generation{generationId=1, memberId='consumer-2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160-1-08829d9f-0409-424b-8a8b-3dfd94379fba', protocol='range'}
2024-02-23 11:53:12.357  INFO 39989 --- [sumer[my-topic]] sumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160-1, groupId=2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160] Finished assignment for group at generation 1: {consumer-2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160-1-08829d9f-0409-424b-8a8b-3dfd94379fba=Assignment(partitions=[my-topic-0])}
2024-02-23 11:53:12.369  INFO 39989 --- [sumer[my-topic]] sumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160-1, groupId=2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160] Successfully synced group in generation Generation{generationId=1, memberId='consumer-2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160-1-08829d9f-0409-424b-8a8b-3dfd94379fba', protocol='range'}
2024-02-23 11:53:12.370  INFO 39989 --- [sumer[my-topic]] sumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160-1, groupId=2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160] Notifying assignor about the new Assignment(partitions=[my-topic-0])
2024-02-23 11:53:12.373  INFO 39989 --- [sumer[my-topic]] sumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160-1, groupId=2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160] Adding newly assigned partitions: my-topic-0
2024-02-23 11:53:12.379  INFO 39989 --- [sumer[my-topic]] sumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160-1, groupId=2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160] Found no committed offset for partition my-topic-0
2024-02-23 11:53:12.388  INFO 39989 --- [sumer[my-topic]] onsumer.internals.SubscriptionState : [Consumer clientId=consumer-2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160-1, groupId=2b2daf0d-5ce6-4fe3-8f3d-253d7cd92160] Resetting offset for partition my-topic-0 to position FetchPosition{offset=21, offsetEpoch=Optional.empty, currentLeader=LeaderAndEpoch{leader=Optional[ghost:9092 (id: 0 rack: null)], epoch=0}}.

```

and after a message has been produced to Kafka you should see

```bash
2024-02-23 11:53:27.247  INFO 39989 --- [sumer[my-topic]] log-sink                            : Exchange[
  ExchangePattern: InOnly
  Headers: {apicurio.value.encoding=BINARY, apicurio.value.globalId=, CamelMessageTimestamp=1708685606746, kafka.HEADERS=RecordHeaders(headers = [RecordHeader(key = apicurio.value.globalId, value = [0, 0, 0, 0, 0, 0, 0, 2]), RecordHeader(key = apicurio.value.encoding, value = [66, 73, 78, 65, 82, 89])], isReadOnly = false), kafka.KEY=key, kafka.OFFSET=21, kafka.PARTITION=0, kafka.TIMESTAMP=1708685606746, kafka.TOPIC=my-topic}
  BodyType: org.apache.avro.generic.GenericData.Record
  Body: {"orderId": 1, "itemId": "item", "userId": "user", "quantity": 3.0, "description": "A really nice item I do love"}
```

## Produce to Kafka with Kamelets

You might also want to try out the specialized Kamelet sink for Apicurio Registry

Follow the same approach but run

then run:
```bash
jbang run camel@apache/camel run --local-kamelet-dir=<local_path_to_camel_kamelets> kafka-apicurio-producer-kamelet.yaml
```

