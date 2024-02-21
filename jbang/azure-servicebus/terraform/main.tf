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
 
provider "azurerm" {
  features {}
}

# Creates the namespace
resource "azurerm_servicebus_namespace" "busNamespace" {
  name                = var.services_bus_namespace_name
  location            = var.location
  resource_group_name = var.resource_group_name
  sku                 = "Basic"
}

# Create Queue for regular messages
resource "azurerm_servicebus_queue" "messageQueue" {
  name             = var.messages_queue_name
  namespace_id     = azurerm_servicebus_namespace.busNamespace.id
  
  enable_partitioning = true
}

# get Data for this queue
resource "azurerm_servicebus_queue_authorization_rule" "messageQueueData" {
  name                = "messageQueue_auth_rule"
  queue_id          = azurerm_servicebus_queue.messageQueue.id
  
  listen = true
  send   = true
  manage = true
}
