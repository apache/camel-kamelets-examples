# Open API examples

Find useful examples about how to expose an Open API specification in a Camel integration.

## Greetings example

Run the examples running

```
camel run --open-api greetings-api.json greetings.groovy
```

Then you can test by calling the hello endpoint, ie:

```
$ curl -i http://localhost:8080/camel/greetings/jack
HTTP/1.1 200 OK
Accept: */*
name: hello
User-Agent: curl/7.68.0
transfer-encoding: chunked

Hello from jack
```