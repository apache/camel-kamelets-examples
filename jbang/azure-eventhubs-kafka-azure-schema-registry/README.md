# Example for consuming from Azure Event Hubs in Avro format, using Azure Schema Registry

This example shows a YAML DSL route for consuming Avro messages from Event Hubs using Azure Schema Registry.
The example also includes a producer for convenience, as well as a wrapper around [DefaultAzureCredentials](https://learn.microsoft.com/en-us/java/api/com.azure.identity.defaultazurecredential?view=azure-java-stable)
to solve the instantiation problem, as the class uses a builder for instantiating.

## Build the infrastructure

Choose a globally unique name for the Event Hubs namespace and edit it in the terraform [script](main.tf).
Then, create the services using the script.

For having a working example you will need to add a role assignment of type "Schema Registry Contributor (Preview)"

This could be done through the following Terraform configuration

```bash
data "azurerm_subscription" "primary" {
}

data "azurerm_client_config" "example" {
}

resource "azurerm_role_assignment" "example" {
  scope                = data.azurerm_subscription.primary.id
  role_definition_name = "Schema Registry Contributor (Preview)"
  principal_id         = data.azurerm_client_config.example.object_id
}
```

This will grant the correct role. You could do that even from the Azure CLI or Azure console.

This step is important to fully run the example.

## Configure the applications

Use [application.properties.template](application.properties.template) to create `application.properties` and define your Event Hubs namespace in there.
After the services have been created, the connection string for the Event Hub can be found on the Azure Console,
or by running:
```bash
az eventhubs eventhub authorization-rule keys list --resource-group "example-rg" --namespace-name "example-namespace" --eventhub-name "my-topic" --name "rw_policy"
```
Set the `primaryConnectionString` as the `connectionstring` in `application.properties`.

## Produce to Event Hubs.

Run [`Produce.java`](./azure-identity/src/main/java/com/acme/example/eventhubs/Produce.java) to produce a message to the Eventhub.

## Consume from Event Hubs.

To consume messages using a Camel route, first install the azure identity maven project:
```bash
cd azure-identity
mvn clean install
```
then run:
```bash
camel run kafka-log.camel.yaml 
```

You can also use the Kamelet for working with Azure Schema Registry and Azure Event Hubs Kafka

```bash
jbang --fresh run camel@apache/camel run kafka-kamelet-log.camel.yaml
```

You can also use the Kamelet for producing to Azure Schema Registry and Azure Event Hubs Kafka 

```bash
jbang --fresh run camel@apache/camel run --local-kamelet-dir=<path_to_your_local_kamelets> azure-kafka-schema-registry-producer.camel.yaml
```

To run the example without JBang, but e.g. Spring Boot, You can export the route:
```bash
camel export application.properties kafka-log.camel.yaml --runtime=spring-boot --directory=code --gav com.acme.example:azure-sr:1.0.0
```
And run as a regular Spring Boot application.
