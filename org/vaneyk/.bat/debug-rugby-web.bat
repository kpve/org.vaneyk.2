@echo on

java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=y  -jar .\rugby\web\target\vaneyk-rugby-web-0.0.1-SNAPSHOT.jar

