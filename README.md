# webStore

**Web application project focused on JPA Persistence and Hibernate.**

## Social Midias

- https://www.linkedin.com/in/jpfigueredo/
- https://www.instagram.com/figfig.jpg/

## What you should know beforehand
- Java programming
- Oriented Object Programming
- Network -> HTTP Protocols/Request/Response
- Database


## Summary
- [JPA - Java Persistence API](#JPA_Java-Persistence-API)
- [Configuring Maven](#configuring-maven)
- [Configuring persistence.xml](#configuring-persistence-xml)
- [Hibernate](#Hibernate)

## JPA - Java Persistence API

Before the project starts, here is some theory.

JPA is a specification that "standardizes" ORM(Object Relational Mapping), that means that JPA is responsible for the code's flexibility. With Java Persistence API, the code can remain the same even if the library is changed, that's the hole meaning of JPA.

It has some implementations, they are Hibernate, OpenJPA, EclipseLink. For this project, and as default, Hibernate will be the implementation chosen.

The JPA implementation will be done with Maven, by configuration on pom.xml.


## Configuring Maven

To start the project, open IDE of choice and go **File -> New -> Maven Project**. Check **Create a simple project(skip archetype selection)**. ArtifactID will be the project's folder name, GroupID will be the package's name.

Open pom.xml and add the following configurations.

```java
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>br.com.webstore</groupId>
	<artifactId>loja</artifactId>
	<version>0.0.1-SNAPSHOT</version>

	<build>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.8.0</version>
				<configuration>
					<release>11</release>
				</configuration>
			</plugin>
		</plugins>
	</build>

	<dependencies>
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-entitymanager</artifactId>
			<version>5.4.27.Final</version>
		</dependency>
		
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<version>1.4.200</version>
		</dependency>
	</dependencies>
	
</project>
```

Save, wait for building, **Right-click** project folder on **Project Explorer** and go **Maven -> Update Project**.

## Configurating persistence.xml

On **src/main/resources**, create a folder named **META-INF** and, inside it, a xml file named **persistence.xml**.
***NOTE: The name persistence.xml is mandatory**

Now its time to configurate the **Database** that will be used. On the **persistence.xml** file, input the code below.

```java
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.2"
	xmlns="http://xmlns.jcp.org/xml/ns/persistence"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence 
http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd">
	
	<persistence-unit name="loja" transaction-type="RESOURCE_LOCAL">
  
		<properties>
      <property name="javax.persistence.jdbc.driver" value="org.h2.Driver"/>
			<property name="javax.persistence.jdbc.url" value="h2:mem:loja" />
			<property name="javax.persistence.jdbc.username" value="sa" />
			<property name="javax.persistence.jdbc.password" value="" />
			<property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect" />
      
		</properties>
    
	</persistence-unit>
	
</persistence>
```

The tag **<persistence-unit/>** is to gather persistences configuration, which will represent the database used by the application. Inside this tag is where the database configuration is done.



```java
      <property name="javax.persistence.jdbc.driver" value="org.h2.Driver"/>
```
  
```java
  <property name="javax.persistence.jdbc.url" value="h2:mem:loja" />
```

```java
			<property name="javax.persistence.jdbc.username" value="sa" />
```

```java
			<property name="javax.persistence.jdbc.password" value="" />
```

```java
			<property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect" />
```
  


JTA é para servidor de aplicação, para trabalhar com EJB, JMS e outros tecnologias do JavaEE e o server se encarrega de cuidar da transação













## Hibernate
