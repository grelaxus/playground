This code is working for my environment.  
The goal is to build a simple dal to operate with DB using Spring Data JPA (ideally without Spring Boot which brings in a lot of garbage).  

==================  
Performance measurements (on 9/19)  
Hibernate: insert into customer (name, id) values (?, ?)  
Time spent to save one entity: 3580  

Hibernate: insert into customer (name, id) values (?, ?)  
Time spent to save 2nd entity: 3556  

findAll()...  
Time spent to find all entities: 1842  
Time spent to find all entities 2nd time: 1669  
Done!  



[INFO] com.vk:dal:jar:1.0-SNAPSHOT
[INFO] +- org.springframework.data:spring-data-jpa:jar:1.11.7.RELEASE:compile
[INFO] |  +- org.springframework.data:spring-data-commons:jar:1.13.7.RELEASE:compile
[INFO] |  +- org.springframework:spring-orm:jar:4.3.11.RELEASE:compile
[INFO] |  |  \- org.springframework:spring-jdbc:jar:4.3.11.RELEASE:compile
[INFO] |  +- org.springframework:spring-context:jar:4.3.11.RELEASE:compile
[INFO] |  |  \- org.springframework:spring-expression:jar:4.3.11.RELEASE:compile
[INFO] |  +- org.springframework:spring-aop:jar:4.3.11.RELEASE:compile
[INFO] |  +- org.springframework:spring-tx:jar:4.3.11.RELEASE:compile
[INFO] |  +- org.springframework:spring-beans:jar:4.3.11.RELEASE:compile
[INFO] |  +- org.springframework:spring-core:jar:4.3.11.RELEASE:compile
[INFO] |  +- org.aspectj:aspectjrt:jar:1.8.10:compile
[INFO] |  +- org.slf4j:slf4j-api:jar:1.7.25:compile
[INFO] |  \- org.slf4j:jcl-over-slf4j:jar:1.7.25:runtime
[INFO] +- org.hibernate:hibernate-entitymanager:jar:5.2.11.Final:compile
[INFO] |  +- org.jboss.logging:jboss-logging:jar:3.3.0.Final:compile
[INFO] |  +- org.hibernate:hibernate-core:jar:5.2.11.Final:compile
[INFO] |  |  +- antlr:antlr:jar:2.7.7:compile
[INFO] |  |  +- org.jboss:jandex:jar:2.0.3.Final:compile
[INFO] |  |  \- com.fasterxml:classmate:jar:1.3.0:compile
[INFO] |  +- dom4j:dom4j:jar:1.6.1:compile
[INFO] |  +- org.hibernate.common:hibernate-commons-annotations:jar:5.0.1.Final:compile
[INFO] |  +- org.hibernate.javax.persistence:hibernate-jpa-2.1-api:jar:1.0.0.Final:compile
[INFO] |  +- org.javassist:javassist:jar:3.20.0-GA:compile
[INFO] |  +- net.bytebuddy:byte-buddy:jar:1.6.14:compile
[INFO] |  \- org.jboss.spec.javax.transaction:jboss-transaction-api_1.2_spec:jar:1.0.1.Final:compile
[INFO] +- mysql:mysql-connector-java:jar:6.0.6:compile
[INFO] \- org.springframework.boot:spring-boot-starter-test:jar:1.5.2.RELEASE:test
[INFO]    +- org.springframework.boot:spring-boot-test:jar:1.5.2.RELEASE:test
[INFO]    |  \- org.springframework.boot:spring-boot:jar:1.5.2.RELEASE:test
[INFO]    +- org.springframework.boot:spring-boot-test-autoconfigure:jar:1.5.2.RELEASE:test
[INFO]    |  \- org.springframework.boot:spring-boot-autoconfigure:jar:1.5.2.RELEASE:test
[INFO]    +- com.jayway.jsonpath:json-path:jar:2.2.0:test
[INFO]    |  \- net.minidev:json-smart:jar:2.2.1:test
[INFO]    |     \- net.minidev:accessors-smart:jar:1.1:test
[INFO]    |        \- org.ow2.asm:asm:jar:5.0.3:test
[INFO]    +- junit:junit:jar:4.12:test
[INFO]    +- org.assertj:assertj-core:jar:2.6.0:test
[INFO]    +- org.mockito:mockito-core:jar:1.10.19:test
[INFO]    |  \- org.objenesis:objenesis:jar:2.1:test
[INFO]    +- org.hamcrest:hamcrest-core:jar:1.3:test
[INFO]    +- org.hamcrest:hamcrest-library:jar:1.3:test
[INFO]    +- org.skyscreamer:jsonassert:jar:1.4.0:test
[INFO]    |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO]    \- org.springframework:spring-test:jar:4.3.7.RELEASE:test



dal-1.0-SNAPSHOT.jar 19.6Mb
exclusion: 
- net.bytebuddy:byte-buddy 16.8Mb
- org.jboss:jandex - 16.2Mb/16.6Mb