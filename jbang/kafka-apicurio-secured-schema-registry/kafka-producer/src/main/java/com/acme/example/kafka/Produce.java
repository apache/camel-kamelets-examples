/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.acme.example.kafka;

import com.acme.example.kafka.models.Order;
import io.apicurio.registry.serde.SerdeConfig;
import io.apicurio.registry.serde.avro.AvroKafkaSerializer;
import io.apicurio.registry.serde.avro.AvroKafkaSerdeConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.StringSerializer;
import io.apicurio.registry.serde.avro.ReflectAvroDatumProvider;
import io.apicurio.registry.rest.client.exception.RestClientException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Produce {

    public static final String DEFAULT_PROPERTIES_PATH = "../application.properties";

    public static void main(String[] args) throws IOException {
        String propertiesPath = DEFAULT_PROPERTIES_PATH;
        if (args.length >= 1) {
            propertiesPath = args[0];
        }

        Properties properties = new Properties();
        properties.load(Files.newInputStream(Paths.get(propertiesPath)));
        
        String registryUrl = properties.getProperty("schema.registry.url");
        String authServiceUrl = properties.getProperty("keycloak.service.url");
        String authRealm = properties.getProperty("keycloak.realm");
        String clientId = properties.getProperty("keycloak.client.id");
        String clientSecret = properties.getProperty("keycloak.client.secret");
        String apicurioUsername = properties.getProperty("keycloak.apicurio.username");
        String apicurioPassword = properties.getProperty("keycloak.apicurio.password");

        properties.put(SerdeConfig.REGISTRY_URL, registryUrl);
        properties.put(SerdeConfig.AUTH_SERVICE_URL, authServiceUrl);
        properties.put(SerdeConfig.AUTH_REALM, authRealm);
        properties.put(SerdeConfig.AUTH_CLIENT_ID, clientId);
        properties.put(SerdeConfig.AUTH_USERNAME, apicurioUsername);
        properties.put(SerdeConfig.AUTH_PASSWORD, apicurioPassword);
        properties.put(SerdeConfig.AUTH_CLIENT_SECRET, clientSecret);
        properties.put(SerdeConfig.AUTO_REGISTER_ARTIFACT, Boolean.TRUE);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, AvroKafkaSerializer.class);


        properties.put(AvroKafkaSerdeConfig.AVRO_DATUM_PROVIDER, ReflectAvroDatumProvider.class.getName());

        try (KafkaProducer<String, Order> orderProducer = new KafkaProducer<>(properties)) {
            Order order = new Order(1, "item", "user", 3.0, "A really nice item I do love");
            String topic = properties.getProperty("topic");
            ProducerRecord<String, Order> record = new ProducerRecord<>(topic, "key", order);
            RecordMetadata result = orderProducer.send(record).get(5, TimeUnit.SECONDS);
            System.out.println("Sent record with offset " + result.offset());
        } catch (ExecutionException | InterruptedException | TimeoutException | RestClientException e ) {
            e.printStackTrace();
        }
    }
}
