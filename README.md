# Booking-Room Core RESTful API

[![engine](https://img.shields.io/badge/JDK-v1.8+-yellow.svg)]()
[![gradle](https://img.shields.io/badge/gradle-v2.13+-blue.svg)]()

---

> `booking-room Core RESTful API` 

---

## Sub-projects

* [booking-room-commons]()
* [booking-room-core-entity]()
* [booking-room-core-repository]()
* [booking-room-core-service]()
* [booking-room-core-api]()
* [booking-room-core-starter]()

## Prerequisites

You will need the following things properly installed on your computer.

* [Git](http://git-scm.com/)
* [Java](https://java.com/en/download/)

## Installation

I use [Gradle](http://www.gradle.org), a cross-platform build automation tool that help with our full development flow. If you prefer [install Gradle](http://www.gradle.org/installation) or use a [Gradle wrapper](http://www.gradle.org/docs/current/userguide/gradle_wrapper.html) inside this project.

* `git clone https://github.com/doradodev/booking-room-core-api.git` this repository
* change into the new directory `booking-room-CORE-API`

## Build

```bash
./gradlew clean build
```

Take a look on generated artifacts, for instance `booking-room-core-starter` subproject:

```bash
ls -1 booking-room-core-starter/build/libs/

booking-room-core-starter.jar
booking-room-core-starter-javadoc.jar
booking-room-core-starter-sources.jar
```


### Generate Javadoc

```bash
./gradlew javaDoc
```

## Distribution

`booking-room Core RESTful API` facilitates creating an executable JVM application. It makes it easy to start the application locally during development, and to packaging the application as ZIP including operating system specific start scripts. 

### Locally

```bash
./gradlew clean :booking-room-core-starter:distZip
```

Take a look on generated dist zipped file:

```bash
ls -1 booking-room-core-starter/build/distributions

booking-room-core-starter.tar
booking-room-core-starter.zip
```

## Configuration

### Database

`booking-room Core RESTful API` uses a [H2](http://www.h2database.com/html/main.html) database embedded to store its data.

The database configuration starts while starting `booking-room Core RESTful API`.


## Run

### Running Core from command line using Gradle

To override default gradle config, please take a look on run task on  [core/booking-room-core-starter/build.gradle](core/booking-room-core-starter/build.gradle) subproject build file.

```bash
tail -n 20 booking-room-core-starter/build.gradle

run {   
    
    environment 'COM_DORADODEV_API_PORT', '8080'

    environment 'COM_DORADODEV_ENV', 'DEV'
}
```

After that:

```bash
./gradlew clean :booking-room-core-starter:run
```

Visit `booking-room Core RESTful API` at [http://localhost:8080/api/*](http://localhost:8080/api).

Visit `booking-room Core RESTful API` Documentation at [http://localhost:8080/docs/index.html](http://localhost:8080/docs/index.html).

## Further reading / Useful links

* [Gradle](http://gradle.org/)
* [Getting started with Gradle](http://gradle.org/getting-started-jvm/)

## Current version

 - 1.0.0

## Getting help

For more info/help, please send email to: *cdorado330@gmail.com*

*Happy coding!*
