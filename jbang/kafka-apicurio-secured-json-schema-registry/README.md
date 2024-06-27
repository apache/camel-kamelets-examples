# Example for consuming from Kafka with the usage of Apicurio Schema Registry secured with Keycloak and JSON Schema

You'll need a running Kafka instance and an Apicurio Registry

## Kafka instance

You could use a plain Kafka archive or use an Ansible role

## Apicurio Registry settings

In the `docker-compose` folder run

```bash
docker compose -f config-registry.yml up
```

This will bring up the required bits for making an Apicurio Registry secured with Keycloak up and running.

Once everything is up and running, access the Keycloak instance located at http://YOUR_IP:8080

The username and password is admin/admin.

Select the realm registry and create a new user called registry-account with password registry.

The client Id from Keycloak will be registry-api.

In the client page, select credentials and copy the client secret value.

In application.properties file copy the client secret value into keycloak.client.secret.

The rest of the options could be used as-is.

### Adding the JSON Schema

The Apicurio Registry doesn't support the auto creation of artifacts for Json Schema so you'll have to create it by hand.

Open a browser and point to http://YOUR_IP:8081/ui/

In the artifacts section select upload artifact

For the group the input will be "default"

For the artifact id the input will be "product-topic-value", since we are pointing to a product-topic topic.

For the type the input will be "JSON Schema"

In the artifact section insert the following Schema

```json
{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "title": "Product",
  "description": "A product from Acme's catalog",
  "type": "object",
  "properties": {
    "id": {
      "description": "The unique identifier for a product",
      "type": "integer"
    },
    "name": {
      "description": "Name of the product",
      "type": "string"
    }
  },
  "required": [
    "id",
    "name"
  ]
}
```

Now click "upload" and the Json Schema will be uploaded and the artifact will be ready to use.

## Configure the applications

In `application.properties` set the Kafka instance address.

## Produce to Kafka.

Run [`Produce.java`](./kafka-producer/src/main/java/com/acme/example/kafka/Produce.java) to produce a message to the Kafka.

```bash
mvn compile exec:java -Dexec.mainClass="com.acme.example.kafka.Produce"
```

```bash
[com.acme.example.kafka.Produce.main()] INFO org.apache.kafka.clients.producer.KafkaProducer - [Producer clientId=producer-1] Instantiated an idempotent producer.
[com.acme.example.kafka.Produce.main()] INFO org.apache.kafka.clients.producer.ProducerConfig - These configurations '[apicurio.registry.auto-register, keycloak.client.secret, keycloak.realm, keycloak.client.id, apicurio.auth.client.id, keycloak.apicurio.username, apicurio.auth.client.secret, apicurio.auth.service.url, schema.registry.url, apicurio.registry.url, apicurio.auth.realm, keycloak.apicurio.password, topic, apicurio.auth.username, keycloak.service.url, apicurio.auth.password]' were supplied but are not used yet.
[com.acme.example.kafka.Produce.main()] INFO org.apache.kafka.common.utils.AppInfoParser - Kafka version: 3.5.1
[com.acme.example.kafka.Produce.main()] INFO org.apache.kafka.common.utils.AppInfoParser - Kafka commitId: 2c6fb6c54472e90a
[com.acme.example.kafka.Produce.main()] INFO org.apache.kafka.common.utils.AppInfoParser - Kafka startTimeMs: 1715758646947
[kafka-producer-network-thread | producer-1] INFO org.apache.kafka.clients.Metadata - [Producer clientId=producer-1] Cluster ID: cQJRW1YgSxubt8WdH4p2Tw
[kafka-producer-network-thread | producer-1] INFO org.apache.kafka.clients.producer.internals.TransactionManager - [Producer clientId=producer-1] ProducerId set to 21 with epoch 0
Sent record with offset 0
[com.acme.example.kafka.Produce.main()] INFO org.apache.kafka.clients.producer.KafkaProducer - [Producer clientId=producer-1] Closing the Kafka producer with timeoutMillis = 9223372036854775807 ms.
[com.acme.example.kafka.Produce.main()] INFO org.apache.kafka.common.metrics.Metrics - Metrics scheduler closed
[com.acme.example.kafka.Produce.main()] INFO org.apache.kafka.common.metrics.Metrics - Closing reporter org.apache.kafka.common.metrics.JmxReporter
[com.acme.example.kafka.Produce.main()] INFO org.apache.kafka.common.metrics.Metrics - Metrics reporters closed
[com.acme.example.kafka.Produce.main()] INFO org.apache.kafka.common.utils.AppInfoParser - App info kafka.producer for producer-1 unregistered
[WARNING] 
java.io.UncheckedIOException: java.io.IOException: Error validating data against json schema with message: $: requested property 'id' not found $: requested property 'name' not found
    at io.apicurio.registry.serde.AbstractKafkaSerializer.serialize (AbstractKafkaSerializer.java:96)
    at org.apache.kafka.clients.producer.KafkaProducer.doSend (KafkaProducer.java:1015)
    at org.apache.kafka.clients.producer.KafkaProducer.send (KafkaProducer.java:962)
    at org.apache.kafka.clients.producer.KafkaProducer.send (KafkaProducer.java:847)
    at com.acme.example.kafka.Produce.main (Produce.java:86)
    at org.codehaus.mojo.exec.ExecJavaMojo.lambda$execute$0 (ExecJavaMojo.java:283)
    at java.lang.Thread.run (Thread.java:833)
Caused by: java.io.IOException: Error validating data against json schema with message: $: requested property 'id' not found $: requested property 'name' not found
    at io.apicurio.registry.serde.jsonschema.JsonSchemaValidationUtil.validateDataWithSchema (JsonSchemaValidationUtil.java:45)
    at io.apicurio.registry.serde.jsonschema.JsonSchemaKafkaSerializer.serializeData (JsonSchemaKafkaSerializer.java:138)
    at io.apicurio.registry.serde.AbstractKafkaSerializer.serialize (AbstractKafkaSerializer.java:88)
    at org.apache.kafka.clients.producer.KafkaProducer.doSend (KafkaProducer.java:1015)
    at org.apache.kafka.clients.producer.KafkaProducer.send (KafkaProducer.java:962)
    at org.apache.kafka.clients.producer.KafkaProducer.send (KafkaProducer.java:847)
    at com.acme.example.kafka.Produce.main (Produce.java:86)
    at org.codehaus.mojo.exec.ExecJavaMojo.lambda$execute$0 (ExecJavaMojo.java:283)
    at java.lang.Thread.run (Thread.java:833)
```

The first record will work because the validation will be passed, the second one won't pass, since the record is an instance of the class WrongProduct instead of Product.


## Produce to Kafka without Kamelets

To consume messages using a Camel route, first install the kafka-producer maven project:
```bash
cd kafka-producer
mvn clean install
```

then run:
```bash
jbang run camel@apache/camel run --properties=application.properties --local-kamelet-dir=. kafka-apicurio-kamelet.camel.yaml
```

You should see something like

```bash
2024-05-15 09:36:38.372  INFO 24390 --- [           main] e.camel.impl.engine.AbstractCamelContext : Routes startup (total:1 started:1 kamelets:2)
2024-05-15 09:36:38.372  INFO 24390 --- [           main] e.camel.impl.engine.AbstractCamelContext :     Started kafka-to-apicurio-log (kamelet://kafka-not-secured-apicurio-registry-json-source)
2024-05-15 09:36:38.373  INFO 24390 --- [           main] e.camel.impl.engine.AbstractCamelContext : Apache Camel 4.6.0 (kafka-apicurio-kamelet) started in 217ms (build:0ms init:0ms start:217ms)
2024-05-15 09:36:38.669  INFO 24390 --- [[product-topic]] he.kafka.clients.consumer.ConsumerConfig : These configurations '[retry.backoff.max.ms, apicurio.auth.service.url, apicurio.auth.realm, apicurio.auth.password, apicurio.auth.client.id, apicurio.auth.client.secret, apicurio.registry.url, apicurio.auth.username]' were supplied but are not used yet.
2024-05-15 09:36:38.670  INFO 24390 --- [[product-topic]] .apache.kafka.common.utils.AppInfoParser : Kafka version: 3.5.1
2024-05-15 09:36:38.670  INFO 24390 --- [[product-topic]] .apache.kafka.common.utils.AppInfoParser : Kafka commitId: 2c6fb6c54472e90a
2024-05-15 09:36:38.670  INFO 24390 --- [[product-topic]] .apache.kafka.common.utils.AppInfoParser : Kafka startTimeMs: 1715758598669
2024-05-15 09:36:38.678  INFO 24390 --- [[product-topic]] .support.classic.AssignmentAdapterHelper : Using NO-OP resume strategy
2024-05-15 09:36:38.680  INFO 24390 --- [[product-topic]] .camel.component.kafka.KafkaFetchRecords : Searching for a custom subscribe adapter on the registry
2024-05-15 09:36:38.681  INFO 24390 --- [[product-topic]] port.subcription.DefaultSubscribeAdapter : Subscribing to topic(s) product-topic
2024-05-15 09:36:38.682  INFO 24390 --- [[product-topic]] che.kafka.clients.consumer.KafkaConsumer : [Consumer clientId=consumer-016aa1d1-831c-47a9-9e26-39191e5a1d4d-1, groupId=016aa1d1-831c-47a9-9e26-39191e5a1d4d] Subscribed to topic(s): product-topic
2024-05-15 09:36:38.911  WARN 24390 --- [[product-topic]] org.apache.kafka.clients.NetworkClient   : [Consumer clientId=consumer-016aa1d1-831c-47a9-9e26-39191e5a1d4d-1, groupId=016aa1d1-831c-47a9-9e26-39191e5a1d4d] Error while fetching metadata with correlation id 2 : {product-topic=LEADER_NOT_AVAILABLE}
2024-05-15 09:36:38.912  INFO 24390 --- [[product-topic]] org.apache.kafka.clients.Metadata        : [Consumer clientId=consumer-016aa1d1-831c-47a9-9e26-39191e5a1d4d-1, groupId=016aa1d1-831c-47a9-9e26-39191e5a1d4d] Cluster ID: cQJRW1YgSxubt8WdH4p2Tw
2024-05-15 09:36:38.913  INFO 24390 --- [[product-topic]] s.consumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-016aa1d1-831c-47a9-9e26-39191e5a1d4d-1, groupId=016aa1d1-831c-47a9-9e26-39191e5a1d4d] Discovered group coordinator ghost.homenet.telecomitalia.it:9092 (id: 2147483647 rack: null)
2024-05-15 09:36:38.930  INFO 24390 --- [[product-topic]] s.consumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-016aa1d1-831c-47a9-9e26-39191e5a1d4d-1, groupId=016aa1d1-831c-47a9-9e26-39191e5a1d4d] (Re-)joining group
2024-05-15 09:36:38.946  INFO 24390 --- [[product-topic]] s.consumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-016aa1d1-831c-47a9-9e26-39191e5a1d4d-1, groupId=016aa1d1-831c-47a9-9e26-39191e5a1d4d] Request joining group due to: need to re-join with the given member-id: consumer-016aa1d1-831c-47a9-9e26-39191e5a1d4d-1-b2c32bb9-ed6b-4929-988b-dcc93abf723a
2024-05-15 09:36:38.947  INFO 24390 --- [[product-topic]] s.consumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-016aa1d1-831c-47a9-9e26-39191e5a1d4d-1, groupId=016aa1d1-831c-47a9-9e26-39191e5a1d4d] Request joining group due to: rebalance failed due to 'The group member needs to have a valid member id before actually entering a consumer group.' (MemberIdRequiredException)
2024-05-15 09:36:38.947  INFO 24390 --- [[product-topic]] s.consumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-016aa1d1-831c-47a9-9e26-39191e5a1d4d-1, groupId=016aa1d1-831c-47a9-9e26-39191e5a1d4d] (Re-)joining group
2024-05-15 09:36:38.949  INFO 24390 --- [[product-topic]] s.consumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-016aa1d1-831c-47a9-9e26-39191e5a1d4d-1, groupId=016aa1d1-831c-47a9-9e26-39191e5a1d4d] Successfully joined group with generation Generation{generationId=1, memberId='consumer-016aa1d1-831c-47a9-9e26-39191e5a1d4d-1-b2c32bb9-ed6b-4929-988b-dcc93abf723a', protocol='range'}
2024-05-15 09:36:39.040  INFO 24390 --- [[product-topic]] s.consumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-016aa1d1-831c-47a9-9e26-39191e5a1d4d-1, groupId=016aa1d1-831c-47a9-9e26-39191e5a1d4d] Finished assignment for group at generation 1: {consumer-016aa1d1-831c-47a9-9e26-39191e5a1d4d-1-b2c32bb9-ed6b-4929-988b-dcc93abf723a=Assignment(partitions=[product-topic-0])}
2024-05-15 09:36:39.049  INFO 24390 --- [[product-topic]] s.consumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-016aa1d1-831c-47a9-9e26-39191e5a1d4d-1, groupId=016aa1d1-831c-47a9-9e26-39191e5a1d4d] Successfully synced group in generation Generation{generationId=1, memberId='consumer-016aa1d1-831c-47a9-9e26-39191e5a1d4d-1-b2c32bb9-ed6b-4929-988b-dcc93abf723a', protocol='range'}
2024-05-15 09:36:39.050  INFO 24390 --- [[product-topic]] s.consumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-016aa1d1-831c-47a9-9e26-39191e5a1d4d-1, groupId=016aa1d1-831c-47a9-9e26-39191e5a1d4d] Notifying assignor about the new Assignment(partitions=[product-topic-0])
2024-05-15 09:36:39.053  INFO 24390 --- [[product-topic]] s.consumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-016aa1d1-831c-47a9-9e26-39191e5a1d4d-1, groupId=016aa1d1-831c-47a9-9e26-39191e5a1d4d] Adding newly assigned partitions: product-topic-0
2024-05-15 09:36:39.066  INFO 24390 --- [[product-topic]] s.consumer.internals.ConsumerCoordinator : [Consumer clientId=consumer-016aa1d1-831c-47a9-9e26-39191e5a1d4d-1, groupId=016aa1d1-831c-47a9-9e26-39191e5a1d4d] Found no committed offset for partition product-topic-0
2024-05-15 09:36:39.074  INFO 24390 --- [[product-topic]] nts.consumer.internals.SubscriptionState : [Consumer clientId=consumer-016aa1d1-831c-47a9-9e26-39191e5a1d4d-1, groupId=016aa1d1-831c-47a9-9e26-39191e5a1d4d] Resetting offset for partition product-topic-0 to position FetchPosition{offset=0, offsetEpoch=Optional.empty, currentLeader=LeaderAndEpoch{leader=Optional[ghost.homenet.telecomitalia.it:9092 (id: 0 rack: null)], epoch=0}}.

```

and after a message has been produced to Kafka you should see

```bash
2024-05-15 09:37:27.748  INFO 24390 --- [[product-topic]] log-sink                                 : Exchange[
  ExchangePattern: InOnly
  Headers: {apicurio.value.globalId=, apicurio.value.msgType=com.acme.example.kafka.models.Product, CamelMessageTimestamp=1715758647152, kafka.HEADERS=RecordHeaders(headers = [RecordHeader(key = apicurio.value.globalId, value = [0, 0, 0, 0, 0, 0, 0, 4]), RecordHeader(key = apicurio.value.msgType, value = [99, 111, 109, 46, 97, 99, 109, 101, 46, 101, 120, 97, 109, 112, 108, 101, 46, 107, 97, 102, 107, 97, 46, 109, 111, 100, 101, 108, 115, 46, 80, 114, 111, 100, 117, 99, 116])], isReadOnly = false), kafka.KEY=test, kafka.OFFSET=0, kafka.PARTITION=0, kafka.TIMESTAMP=1715758647152, kafka.TOPIC=product-topic}
  BodyType: com.acme.example.kafka.models.Product
  Body: Product{id=1, name='product'}
]
```

As you might see the WrongProduct record won't be in the product-topic since it won't pass the validation.

