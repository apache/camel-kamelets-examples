output messages_queue_name {
  value = azurerm_servicebus_queue_authorization_rule.messageQueueData.primary_connection_string
  sensitive = true
}
