# Example fo publishing to EventHubs in Avro format, using Azure Schema Registry

To produce a message, fill in the fields and run Produce.java.

To consume them using a camel route, first install the azure identity maven project:
```bash
cd azure-identity
mvn clean install
```
then run:
```bash
camel run kafka-log.yaml camel-kafka-3.22.0.jar --properties application.properties
```
