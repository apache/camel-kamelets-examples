# Example for consuming from EventHubs in Avro format, using Azure Schema Registry

This example shows a YAML DSL route for consuming Avro messages from Eventhubs using Azure Schema Registry.
The exmaple also includes a producer for convenience, as well as a wrapper around [DefaultAzureCredentials](https://learn.microsoft.com/en-us/java/api/com.azure.identity.defaultazurecredential?view=azure-java-stable)
to solve the instantiation problem, as the class uses a builder for instantiating.

## Build the infrastructure

Choose a globally unique name for the eventhubs namespace and edit it in the terraform [script](main.tf).
Then, create the services using the script.

## Configure the applications

Use [application.properties.template](application.properties.template) to create `application.properties` and define YOur eventhubs namespace in there.
After the services have been created, the connection string for the eventhub can be found on the Azure Console,
or by running:
```bash
az eventhubs eventhub authorization-rule keys list --resource-group "example-rg" --namespace-name "example-namespace" --eventhub-name "my-topic" --name "rw_policy"
```
Set the `primaryConnectionString` as the `connectionstring` in `application.properties`.

## Produce to Eventhubs.

Run [`Produce.java`](./azure-identity/src/main/java/com/acme/example/eventhubs/Produce.java) to produce a message to the Eventhub.

## Consume from Eventhubs.

To consume messages using a Camel route, first install the azure identity maven project:
```bash
cd azure-identity
mvn clean install
```
then run:
```bash
camel run kafka-log.yaml camel-kafka-3.22.0.jar --properties application.properties
```
> At the time of writing, there was a [problem](https://github.com/apache/camel-kamelets-examples/issues/21#issuecomment-1732603257) running this example with camel-kafka-4.0.0 so the example was developed using version 3.22.0, which can be found [here](https://repository.apache.org/content/groups/snapshots/org/apache/camel/camel-kafka/3.22.0-SNAPSHOT/)
