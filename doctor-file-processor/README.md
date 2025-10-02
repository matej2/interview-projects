## Project Overview

*Acme Events Ltd.* uses a shared network drive to manage JSON-based event documents. However, it frequently encounters issues where files are either missing mandatory fields or do not conform to the latest schema.

To address this, you are tasked with building a Spring Boot application that will automatically process and validate these documents. The application should:

- Periodically scan a designated directory for new files.
- Expect new files to appear in a subdirectory named `new/`.
- After validation, move each file to either `valid/` or `invalid/` depending on the result.
- Generate an audit file for each processed document, containing:
    - Field name
    - Validation message

## HTTP Validation Endpoint

For external integrations, the application must expose an HTTP endpoint that supports on-demand document validation. This endpoint should:

- Accept a JSON document via POST request.
- Return validation results in a structured format.
- Enforce rate limiting: maximum 5 requests per minute.
- If the limit is exceeded, respond with an appropriate HTTP 429 (Too Many Requests) status code.
- Do not use third-party libraries for rate limiting—implement a custom solution.
- Include a multi-threaded unit test to verify the rate limiting logic under concurrent load.

## Technical Specifications

- Java 21 (or latest stable release)
- Spring Boot (latest version)
- JPA (Hibernate)
- Maven for build and dependency management
- Modular architecture with clearly separated layers:
    - Controller (REST endpoints)
    - Service/Logic (business rules and validation)
    - DAO/Repository (data access)
- Two Spring profiles:
    - `dev` for development
    - `test` for automated testing
- Extensive unit and integration tests using JUnit and Mockito
- Custom exception handling and logging for traceability

## Future Improvements

- Add support for schema versioning
- Integrate with external schema registry
- Provide a web dashboard for audit history and validation stats
