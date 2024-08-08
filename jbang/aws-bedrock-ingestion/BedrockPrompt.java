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
 
//DEPS org.apache.camel:camel-bom:4.8.0-SNAPSHOT@pom
//DEPS org.apache.camel:camel-aws-bedrock
//DEPS org.apache.camel:camel-endpointdsl
//DEPS org.apache.camel:camel-rest

import org.apache.camel.BindToRegistry;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestParamType;
import org.apache.camel.component.aws2.bedrock.BedrockModels;
import org.apache.camel.component.aws2.bedrock.runtime.BedrockConstants;
import org.apache.camel.component.aws2.bedrock.agent.BedrockAgentConstants;

public class BedrockPrompt extends RouteBuilder {

@Override
public void configure() throws Exception {

    Counter counter = new Counter();
    
    rest("/bedrock")
        .get("/prompt").to("direct:invoke-bedrock")
        .get("/ingestion_status/{id}").param().name("id").type(RestParamType.path).description("The ingestion job id").dataType("string").endParam().to("direct:invoke-ingestion-status");

    from("direct:invoke-bedrock")
        .setBody(constant("{{prompt}}"))
        .to("aws-bedrock-agent-runtime:label?useDefaultCredentialsProvider=true&region=us-east-1&operation=retrieveAndGenerate&knowledgeBaseId={{knowledgeBaseId}}&modelId="
                            + BedrockModels.ANTROPHIC_CLAUDE_INSTANT_V1.model);           
                            
    from("direct:invoke-ingestion-status")
        .toD("aws-bedrock-agent:label?useDefaultCredentialsProvider=true&region=us-east-1&operation=getIngestionJob&knowledgeBaseId={{knowledgeBaseId}}&dataSourceId={{dataSourceId}}&ingestionJobId=${header.id}").setBody(simple("${body.status.name}"));   
        
    from("aws2-sqs:{{sqsArn}}?deleteAfterRead=true&useDefaultCredentialsProvider=true")
       .unmarshal().json()
       .setProperty("s3-event-name", jsonpath("$.detail.reason"))
       .choice()
         .when(simple("${exchangeProperty.s3-event-name} == 'PutObject'"))
         .bean(counter, "getCount()")
       .end()
       .choice()
         .when(simple("${bean:counter?method=getCount} == 11"))
              .log("Detected file upload in AWS S3. Starting ingestion process to AWS Bedrock Knowledge Base.")
              .setHeader(BedrockAgentConstants.KNOWLEDGE_BASE_ID, constant("{{knowledgeBaseId}}"))
              .setHeader(BedrockAgentConstants.DATASOURCE_ID, constant("{{dataSourceId}}"))
              .to("aws-bedrock-agent:label?useDefaultCredentialsProvider=true&region=us-east-1&operation=startIngestionJob")
              .log("The Ingestion Job Id is ${body}")
         .end();
         
}
}
