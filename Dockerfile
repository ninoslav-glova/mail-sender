FROM eclipse-temurin:17-jre-alpine AS build
WORKDIR /tmp/expanded
COPY build/libs/mail-sender-*.jar mailsender.jar
RUN java -Djarmode=layertools -jar mailsender.jar extract


FROM eclipse-temurin:17-jre

# Spring Tomcat working directory
VOLUME /tmp

RUN locale-gen en_US.UTF-8
ENV LANG='en_US.UTF-8' LANGUAGE='en_US:UTF-8' LC_ALL='en_US.UTF-8'
ARG APP_DIR='/usr/src/mailsender'
RUN mkdir -p $APP_DIR

WORKDIR $APP_DIR
ARG EXPANDED_DIR='/tmp/expanded'
COPY --from=build $EXPANDED_DIR/dependencies/ ./
COPY --from=build $EXPANDED_DIR/spring-boot-loader/ ./
COPY --from=build $EXPANDED_DIR/snapshot-dependencies/ ./
COPY --from=build $EXPANDED_DIR/application/ ./
EXPOSE 8080
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
