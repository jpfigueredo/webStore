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
- [JPA - Java Persistence API](#JPA---Java-Persistence-API)
- [Configuring Maven](#configuring-maven)
- [Configuring persistence xml](#configuring-persistence-xml)
- [Mapping Entity](#mapping-entity)



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

## Configuring persistence xml

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
			<property name="javax.persistence.jdbc.url" value="jdbc:h2:mem:loja" />
			<property name="javax.persistence.jdbc.user" value="sa" />
			<property name="javax.persistence.jdbc.password" value="" />
			<property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect" />
      
		</properties>
    
	</persistence-unit>
	
</persistence>
```

The tag **<persistence-unit/>** is to gather persistences configuration, which will represent the database used by the application. Inside this tag is where the database configuration is done.

The first property is to sett the Driver for the database, for this project H2 will be used and, therefore, H2 Driver will be used.

```java
      <property name="javax.persistence.jdbc.driver" value="org.h2.Driver"/>
```
  
The property below will define the url that the database will use. In this case, mem(memory):loja(project name).
  
```java
  <property name="javax.persistence.jdbc.url" value="jdbc:h2:mem:loja" />
```

Both properties below are used to set database username and password, "sa" and "" are default for H2 database.

```java
<property name="javax.persistence.jdbc.user" value="sa" />
<property name="javax.persistence.jdbc.password" value="" />
```

Dialect is set to adjust the communication with the database. If the database is different the dialect must be too. In this case, H2Dialect will be chosen.

```java
<property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect" />
```

For now, all set!

## Mapping Entity

Now, a model class is needed, one that will represent a Table on the Database. JPA Mapping is done through annotation and, for an Entity to be created, the annotation @Entity must be written on out model class, as a way of showing to the database that it is a table, as seen below.

***NOTE:* ALWAYS CHOOSE javax.persistence as import, because that import calls JPA, not Hibernate, otherwise, if Hibernate is chosen, the project will be stuck to Hibernate.**

If the **Entity** does not have the same name as the **Table** on the database, another annotation is required, passing the **database Table's** name as parameter. That is, if the **class** is called **Product** and the **DB** calls it **Products**, the following must be done **@Table(name="products")**.
Another annotation that can be done is to define a specific column where the value of the attribute should be put, the annotation is **@Column(name="attribute")**, this is done in case that that the attribute and the column on the **DB** have **different names** 
**@Id** annotation says which of the attributes is the **Primary Key** for the **Database** 
**@GeneratedValue(strategy=GenerationType.IDENTITY)** does the autoincrement for the ID and sorts it as added.

***NOTE*: 
- If a existing ID is passed as parameter, the values at the column will be replaced by the new values
- IF the ID does not exist and is far from the last ID, say "ID = 100" is put at a Table that have only 4 ID's, the ID will be changed to 5 and put at the last position.**

The code below serves as an example:

```java
@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Column(name="description")
	private String descricao;
	
	private BigDecimal price;
```

So that the model is finished, all getters and setters are called.

**NOTE: CTRL+3 opens a tab, if "Getters and Setters" are written on it, it will lead to a screen were they can be called.
	CTRL+SHIFT+O imports what is needed.
	CTRL+SHIFT+F fixes identation and spaces.**

As default, JPA requires that the class is also added on persistence.xml, inside persistence-unit.
***NOTE:* Hibernate does not require for it to be implemented on persistence-unit, but, JPA does. So, if the project is going to be used with other databases, it is recommended that the tag is implemented.** It should look like this.
***NOTE:*IF one entity class is added on persistence.xml, all Entities must be added too.**

```java
	<persistence-unit name="webStore" transaction-type="RESOURCE_LOCAL">
		<class>br.com.webStore.model.Produto</class>
		<properties>
			...
		</properties>
	</persistence-unit>
```








JTA é para servidor de aplicação, para trabalhar com EJB, JMS e outros tecnologias do JavaEE e o server se encarrega de cuidar da transação
```java

```
