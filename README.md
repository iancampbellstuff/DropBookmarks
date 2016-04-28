DropBookmarks
=============

This is a repository for the Udemy tutorial [Getting started with Dropwizard](https://www.udemy.com/getting-started-with-dropwizard "Getting started with Dropwizard").

##### Required dependencies

 - minimum JDK7 or [JDK8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html "Java SE Development Kit 8 Downloads")
 - [Maven](https://maven.apache.org/install.html "Installing Apache Maven")
 - [MySQL](https://dev.mysql.com/doc/refman/5.7/en/installing.html "Installing and Upgrading MySQL")
 
 ---
 
#### Notes

##### Section 3, Lecture 7:  Creating a Dropwizard project using Maven and CLI

```
mvn archetype:generate \
 -DgroupId=com.udemy \
 -DartifactId=DropBookmarks \
 -Dpackage=com.udemy.dropbookmarks \
 -Dname=DropBookmarks \
 -DarchetypeGroupId=io.dropwizard.archetypes \
 -DarchetypeArtifactId=java-simple \
 -DarchetypeVersion=0.8.2 \
 -DinteractiveMode=false
```

---

##### Section 3, Lecture 11:  Hello World with Dropwizard

###### 1 -- Build the application:

```
mvn package
```

###### 2 -- Start the application with [run.sh](run.sh):

```
./run.sh
```

###### 3 -- To see the value returned from [+HelloResource#getGreeting():String](src/main/java/com/udemy/dropbookmarks/resources/HelloResource.java), either navigate to [localhost:8080/hello](http://localhost:8080/hello) or:

```
curl -w "\n" localhost:8080/hello
```
