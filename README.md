# simple-tomcat

## deploy servlet

```shell
javac -verbose -classpath ./lib/javax.servlet-api-3.1.0.jar:./target/classes ./src/main/java/org/vulcan/light/simpletomcat/servlet/*.java
mv ./src/main/java/org/vulcan/light/simpletomcat/servlet/*.class ./webroot
```

## architecture

![image](https://i1.wp.com/howtodoinjava.com/wp-content/uploads/2015/03/Tomcat-Architechture.jpg?w=450&ssl=1 "from https://howtodoinjava.com/tomcat/tomcats-architecture-and-server-xml-configuration-tutorial/")

