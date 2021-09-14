# expense-restful-service project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Deploy this quarkus app in Openshift

This application will be deployed in the Openshift that you are logged in, to not do this, comment these lines on the application.properties before you construct the application:

#quarkus.kubernetes.deploy=true
#quarkus.openshift.expose=true
#quarkus.s2i.base-jvm-image=registry.access.redhat.com/ubi8/openjdk-11

If you want to deploy this app on the openshift, first log in
 - oc login -u $USER -p $PASSWORD https://api-cluster.example.com:6443

create a project or go to a project that was already created for this application:
 - oc new-project expenses

then execute the maven command from inside the project folder:

 - mvn clean package -DskipTests

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `expense-restful-service-1.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/expense-restful-service-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/expense-restful-service-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.

