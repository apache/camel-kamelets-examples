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
      version = "=3.0.0"
    }
  }
}

# Configure the Microsoft Azure Provider
provider "azurerm" {
  features {}
}

resource "azurerm_resource_group" "kamelets" {
  name     = "kamelets-resources"
  location = "West Europe"
}

resource "azurerm_storage_account" "kamelets" {
  name                     = "kameletsaccount"
  resource_group_name      = azurerm_resource_group.kamelets.name
  location                 = azurerm_resource_group.kamelets.location
  account_tier             = "Standard"
  account_replication_type = "LRS"
}

resource "azurerm_storage_container" "camelkamelets" {
  name                  = "kamelets"
  storage_account_name  = azurerm_storage_account.kamelets.name
  container_access_type = "private"
}

resource "azurerm_servicebus_namespace" "example" {
  name                = "kamelets-servicebus-namespace"
  location            = azurerm_resource_group.kamelets.location
  resource_group_name = azurerm_resource_group.kamelets.name
  sku                 = "Standard"
}

resource "azurerm_servicebus_queue" "example" {
  name         = "kamelets_servicebus_queue"
  namespace_id = azurerm_servicebus_namespace.example.id

  enable_partitioning = true
}

resource "azurerm_eventgrid_event_subscription" "example" {
  name  = "example-aees"
  scope = azurerm_storage_account.kamelets.id
  
  service_bus_queue_endpoint_id = azurerm_servicebus_queue.example.id
  
    included_event_types = [
    "Microsoft.Storage.BlobCreated", "Microsoft.Storage.BlobDeleted"
  ]
}	
