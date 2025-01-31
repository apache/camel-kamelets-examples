# Open API contract-first example

This example shows how to do _contrat first_ with Camel and OpenAPI.

The `petstore-v3.json` is the OpenAPI contract which we want to implement with Camel.

## Petstore example

Run the examples running

```
camel run petstore-v3.json petstore.camel.yaml application.properties
```

Or run via:

```
camel run *
```


Then you can test by calling to get pet with id 123:

```
$ curl -i http://localhost:8080/api/v3/pet/123
HTTP/1.1 200 OK
Accept: */*
User-Agent: curl/8.1.2
transfer-encoding: chunked
Content-Type: application/json

{
  "pet": "donald the dock"
}
```

If you get a pet with any other ID than 123, such as 444, you will get the response from the example that are inlined in the OpenAPI specification file `petstore-v3.json`.

```
$ curl -i http://localhost:8080/api/v3/pet/444
HTTP/1.1 200 OK
Accept: */*
User-Agent: curl/8.1.2
transfer-encoding: chunked
Content-Type: application/json

{
  "pet": "Jack the cat"
}
```

## Dummy data

The example will return an empty response if you request non implemented API endpoints.
The petstore has 18 apis, and this example has none implemented.

The returned response is loaded from disk in the `camel-mock` folder, as you can see it has pet/123.json as a file,
that will be returned when you call `/api/v3/pet/123`.

## API Contract

The API contract is made public (see source), which can be accessed via:

```
$ curl -i http://localhost:8080/api-doc
```

