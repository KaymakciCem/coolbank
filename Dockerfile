FROM openjdk:17

VOLUME /tmp

ADD ./build/libs/coolbank-0.0.1-SNAPSHOT.jar repaymentplan-service.jar

ENTRYPOINT ["java","-jar","repaymentplan-service.jar"]