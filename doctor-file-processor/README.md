# document-processor

Create an application that processes YAML and JSON documents. Application gets input documents from the “input” directory and through an HTTP POST endpoint it offers. Application should process “new” folder every 2 minutes.


In every case, documents must be inserted into database and validated.

After document processing is finished:

The document should be stored into the “valid” directory or if something fails in the “invalid” directory. Audit file should be generated for each document containing field and message.

To protect the application, limit the number of HTTP POST invocations to 20 per 1 minute. If there were 20 requests processed within the last minute already, reject further requests with an appropriate HTTP status code until enough time passes so that there are less than 20 requests processed within the last timeframe. Write a multi-threaded test for this rate limiting.


Project should use latest Java, Spring Framework, ORM (JPA). For build tool you can use Maven. Structure code into 3 layers (controller, logic, DAO/repository). You can document REST endpoints if you want with Swagger or Spring REST Docs and also expose querying doctor, patients, and list of diseases if you want.

Application should have 2 profiles for testing and development. For testing use in-memory database H2 and for development use Postgres.


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
