# Tray.io interview task

## Introduction
The following project has been created as part of the assessment provided during recruitment process.

User can use the REST API to invoke methods that allows to create workflows and workflows executions.

## Pre-requisites
Before running the application, user should have the following applications installed:

1. Apache Maven

2. Java 8

## Running
Please use the following command to build and run the application:

```mvn clean install -DSkipTests``` <-- Install dependencies

```mvn spring-boot:run``` <-- Run application

## Unit tests
Please use the following command to run unit and integration tests:

```mvn test```

### Making AJAX calls:
### Create workflow:

URL: http://localhost:8080/workflows

Method: POST

Content: Workflow object in JSON format (as specified in requirements)

### Create workflow execution

URL: http://localhost:8080/workflows/executions

Method: POST

Content: Workflow execution object in JSON format (as specified in requirements)

### Increment current step

URL: http://localhost:8080/workflows/executions/{workflowExecutionId}

_Example URL: http://localhost:8080/workflows/executions/1_

Method: POST

Content: Workflow execution object in JSON format (as specified in requirements)

### Get workflow execution

URL: http://localhost:8080/workflows/executions/{workflowExecutionId}

_Example URL: http://localhost:8080/workflows/executions/1_

Method: GET
