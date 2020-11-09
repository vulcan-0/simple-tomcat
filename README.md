# simple-tomcat

## deploy servlet

```shell
javac -verbose -classpath ./lib/javax.servlet-api-3.1.0.jar:./target/classes ./src/main/java/org/vulcan/light/simpletomcat/demo1/servlet/*.java
mv ./src/main/java/org/vulcan/light/simpletomcat/demo1/servlet/*.class ./webroot
```
