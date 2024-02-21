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
