# ✈ Flight Planner

REST API using Java, Spring Boot, PostgreSQL & H2.

### Configuration

Edit [application.properties](./src/main/resources/application.properties)

Set `flight-planner.store-type=[in-memory or database]` to use H2 in-memory DB or PostgreSQL.

### Running

```shell
gradle bootRun
```

### Availible endpoints

Public

- /api/flights/{id} [GET]
- /api/flights/search [POST]
- /api/airports [GET]

Admin

- /admin-api/flights [PUT]
- /admin-api/flights/{id} [GET]
- /admin-api/flights/{id} [DELETE]

Testing

- /testing-api/clear [POST]
