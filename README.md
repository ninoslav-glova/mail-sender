# mail-sender
Mail sender for Q

## Instructions for running the project:
Easiest way to run the project is by running it in a docker container.

First build both modules with Gradle. Navigate to project root and run:
    
    gradlew clean build

Then you'll need to build the docker image:

    docker build -t mail-sender:latest .

Run the built docker image:

    docker run -d -p 8080:8080 mail-sender

Navigate your browser to localhost:8080, and you should see main frontend page.



