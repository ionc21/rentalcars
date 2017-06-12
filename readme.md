You need to have Java 8 (JDK 1.8) installed on your machine.

Run provect with Maven
Enter following commend in cmd or run as mvn project from IDE:
cmd: mvn clean install camel:run
IDE: clean install camel:run


Paths to test

When the server is fully started, test in postman on following paths
default path: http://localhost:9090/vehicle/

http://localhost:9090/vehicle/nameAndPrice?sort=desc: Print a list of all the cars, in descending price order
http://localhost:9090/vehicle/nameAndPrice?sort=asc: Print a list of all the cars, in ascending price order
http://localhost:9090/vehicle/spec: Print the cars specification
http://localhost:9090/vehicle/highestRatedSupplier: Print out the highest rated supplier per car type, descending order
http://localhost:9090/vehicle/score: Print out a list of cars, ordered by the sum of the scores in descending order

