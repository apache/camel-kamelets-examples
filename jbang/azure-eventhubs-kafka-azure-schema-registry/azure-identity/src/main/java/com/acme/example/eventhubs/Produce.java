package com.acme.example.eventhubs;

import com.acme.example.azure.DefaultAzureCredentialWrapper;
import com.acme.example.eventhubs.models.Order;
import com.azure.core.credential.TokenCredential;
import com.microsoft.azure.schemaregistry.kafka.avro.KafkaAvroSerializer;
import com.microsoft.azure.schemaregistry.kafka.avro.KafkaAvroSerializerConfig;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Produce {

    public static void main(String[] args) {
        TokenCredential credential = new DefaultAzureCredentialWrapper();
        String namespace = "eventhubs-namespace";
        String username = "$ConnectionString";
        String password = "actual-connection-string"; // Replace this with the connection string.
        String saslJaas = "org.apache.kafka.common.security.plain.PlainLoginModule required " +
                "username=\"" + username + "\" " +
                "password=\"" + password + "\";";

        Properties properties = new Properties();
        properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, namespace + ".servicebus.windows.net:9093");

        properties.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        properties.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        properties.put(SaslConfigs.SASL_JAAS_CONFIG, saslJaas);

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);

        properties.put(KafkaAvroSerializerConfig.SCHEMA_GROUP_CONFIG, "avro");
        properties.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "https://" + namespace + ".servicebus.windows.net");
        properties.put(KafkaAvroSerializerConfig.AUTO_REGISTER_SCHEMAS_CONFIG, true);
        properties.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_CREDENTIAL_CONFIG, credential);

        try (KafkaProducer<String, Order> orderProducer = new KafkaProducer<>(properties)) {
            Order order = new Order(1, "item", "user", 3.0);
            ProducerRecord<String, Order> record = new ProducerRecord<>("my-topic", "key", order);
            RecordMetadata result = orderProducer.send(record).get(5, TimeUnit.SECONDS);
            System.out.println("Sent record with offset " + result.offset());
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}