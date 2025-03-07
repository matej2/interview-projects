# doctor-file-processor

 

Create an application that processes XML and JSON documents. Application gets input documents from the “input” directory and through an HTTP POST endpoint it offers. Application should process “input” folder every 90 seconds.

 

Elements must be inserted into database and validated. Create tables that correspond to the example provided at the end of the page.

 

After successful execution:

The document should be stored into the “out” directory or if something fails in the “error” directory
Some info on the process should be stored in the “document_report” table:
execution time (i.e. document processing duration)
doctor ID
error – if any error happens during execution
document source (folder or HTTP request)

To protect the application, limit the number of HTTP POST invocations to 20 per 5 seconds. If there were 20 requests processed within the last 5 seconds already, reject further requests with an appropriate HTTP status code until enough time passes so that there are less than 20 requests processed within the last 5 seconds. (Do not use an off-the-shelf solution for this; we would like to see some lower-level concurrency mechanisms being used.) Write a multi-threaded test for this rate limiting.

 

Project should use latest Kotlin (or Java), Spring Framework, ORM (JPA). For build tool you can use Gradle or Maven. Use Spring Boot. Structure code into 3 layers (controller, logic, DAO/repository). You can document REST endpoints if you want with Swagger or Spring REST Docs and also expose querying doctor, patients, and list of diseases if you want.

 

Application should have 2 profiles for testing and development. For testing use in-memory database H2 or HSQL and for development use Postgres.

 

Write good tests!

 

Sample XML:

 

<doctor id="100" department="better">

    <patients>

        <patient>

            <id>1</id>

            <first_name>John</first_name>

            <last_name>Smith</last_name>

            <diseases>

                <disease>nice_to_people</disease>

                <disease>long_legs</disease>

            </diseases>

        </patient>

        <patient>

            <id>2</id>

            <first_name>Mary</first_name>

            <last_name>Novak</last_name>

            <diseases>

                <disease>used_to_have_dredds</disease>

                <disease>nice_to_people</disease>

            </diseases>

        </patient>

        <patient>

            <id>3</id>

            <first_name>Alice</first_name>

            <last_name>Woods</last_name>

            <diseases>

                <disease>chocaholic</disease>

                <disease>great_haircut</disease>

            </diseases>

        </patient>

    </patients>

</doctor>
