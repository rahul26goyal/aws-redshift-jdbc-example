#A simple Java Application
 ##To demo Connection with AWS RedShift via JDBC-Connector

###Project Overview
* Uses maven to manage dependency. Refer to pom.xml.

###How to Get Started?
* Clone this repo.
* If you are using any IDE, import the pom.xml file in it. It will take 1-2 mins to download the 
dependencies.
* check the source code of RedShiftConnector class.
* You will need a active Red Shift Cluster running on AWS, a database , username and password.
* You will need AWS access key and secret.
* Refer to this example: https://docs.aws.amazon.com/redshift/latest/dg/c_sampledb.html

###How to Compile and create the jar.
* install maven 
* mvn clean
* mvn compile
You will see a `target` directory being populated.
* mvn package
You will see 2 jars created.
`redshift-jdbc-app-1.0-SNAPSHOT-shaded.jar` & `uber-redshift-jdbc-app-1.0-SNAPSHOT-shaded.jar`

###How to RUN?
* we will run the `uber` jar as it has all the dependencies.

``java -jar ./target/uber-redshift-jdbc-app-1.0-SNAPSHOT.jar``

Thank you.






