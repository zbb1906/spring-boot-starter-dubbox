### REST风格远程调用

```xml
<dependency>
    <groupId>org.jboss.resteasy</groupId>
    <artifactId>resteasy-jaxrs</artifactId>
    <version>3.0.7.Final</version>
</dependency>
<dependency>
    <groupId>org.jboss.resteasy</groupId>
    <artifactId>resteasy-client</artifactId>
    <version>3.0.7.Final</version>
</dependency>
<dependency>
    <groupId>javax.validation</groupId>
    <artifactId>validation-api</artifactId>
    <version>1.0.0.GA</version>
</dependency>

<!-- 如果要使用json序列化 -->
<dependency>
    <groupId>org.jboss.resteasy</groupId>
    <artifactId>resteasy-jackson-provider</artifactId>
    <version>3.0.7.Final</version>
</dependency>

<!-- 如果要使用xml序列化 -->
<dependency>
    <groupId>org.jboss.resteasy</groupId>
    <artifactId>resteasy-jaxb-provider</artifactId>
    <version>3.0.7.Final</version>
</dependency>

<!-- 如果要使用netty server -->
<dependency>
    <groupId>org.jboss.resteasy</groupId>
    <artifactId>resteasy-netty</artifactId>
    <version>3.0.7.Final</version>
</dependency>

<!-- 如果要使用Sun HTTP server -->
<dependency>
    <groupId>org.jboss.resteasy</groupId>
    <artifactId>resteasy-jdk-http</artifactId>
    <version>3.0.7.Final</version>
</dependency>

<!-- 如果要使用tomcat server -->
<dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-core</artifactId>
    <version>8.0.11</version>
</dependency>
<dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-logging-juli</artifactId>
    <version>8.0.11</version>
</dependency>
```

### Kyro序列化

```xml
<dependency>
    <groupId>com.esotericsoftware.kryo</groupId>
    <artifactId>kryo</artifactId>
    <version>2.24.0</version>
</dependency>
<dependency>
    <groupId>de.javakaffee</groupId>
    <artifactId>kryo-serializers</artifactId>
    <version>0.26</version>
</dependency>
```

### FST序列化

```xml
<dependency>
    <groupId>de.ruedigermoeller</groupId>
    <artifactId>fst</artifactId>
    <version>1.55</version>
</dependency>
```

### Jackson序列化

```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-core</artifactId>
    <version>2.3.3</version>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.3.3</version>
</dependency>
```