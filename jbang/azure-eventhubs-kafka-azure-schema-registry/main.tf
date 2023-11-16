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

# We strongly recommend using the required_providers block to set the
# Azure Provider source and version being used
terraform {
  required_providers {
    azurerm = {
      source  = "hashicorp/azurerm"
      version = "=3.75.0"
    }
  }
}

# Configure the Microsoft Azure Provider
provider "azurerm" {
  features {}
}

# Resource group.
resource "azurerm_resource_group" "example" {
  name     = "example-test12345-rg"
  location = "West Europe"
}

# Eventhubs Namepsace.
resource "azurerm_eventhub_namespace" "example" {
  name                = "example-test12345-namespace"
  location            = azurerm_resource_group.example.location
  resource_group_name = azurerm_resource_group.example.name
  sku                 = "Standard"
  capacity            = 1
}

# Eventhub (topic)
resource "azurerm_eventhub" "example" {
  name                = "my-topic"
  namespace_name      = azurerm_eventhub_namespace.example.name
  resource_group_name = azurerm_resource_group.example.name
  partition_count     = 1
  message_retention   = 1
}

# Read-Write policy to create a connection string.
resource "azurerm_eventhub_authorization_rule" "example" {
  name                = "rw_policy"
  namespace_name      = azurerm_eventhub_namespace.example.name
  eventhub_name       = azurerm_eventhub.example.name
  resource_group_name = azurerm_resource_group.example.name
  listen              = true
  send                = true
  manage              = false
}

# Schema group to utilize Azure Schema Registry.
resource "azurerm_eventhub_namespace_schema_group" "example" {
  name                 = "avro"
  namespace_id         = azurerm_eventhub_namespace.example.id
  schema_compatibility = "Forward"
  schema_type          = "Avro"
}
