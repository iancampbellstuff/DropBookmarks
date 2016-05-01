DropBookmarks
=============

---

This is a repository for the Udemy tutorial [Getting started with Dropwizard](https://www.udemy.com/getting-started-with-dropwizard "Getting started with Dropwizard").

##### Required dependencies

 - minimum JDK7 or [JDK8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html "Java SE Development Kit 8 Downloads")

 - [Maven](https://maven.apache.org/install.html "Installing Apache Maven")

 - [MySQL](https://dev.mysql.com/doc/refman/5.7/en/installing.html "Installing and Upgrading MySQL")
 
 ---

##### Building the application

```
mvn package
```

##### Starting the application with [run.sh](run.sh)

```
./run.sh
```

---

##### Making ReST requests

###### HTTP

1. Navigate to [localhost:8080/hello](http://localhost:8080/hello)

2. `curl -w "\n" localhost:8080/hello`

3. Navigate to [localhost:8080/hello/secured](http://localhost:8080/hello/secured)

4. `curl -w "\n" localhost:8080/hello/secured -i -u username:p@ssw0rd`
 
5. In [the Chrome Postman plugin](https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop?hl=en), set the Basic Auth username as `username` and password as `p@ssw0rd`, and make a GET request for localhost:8080/hello/secured

6. Copy the base-64 encoded output for `username:p@ssw0rd` from https://www.base64encode.org:

```
dXNlcm5hbWU6cEBzc3cwcmQ=
```

 - In [the Chrome Postman plugin](https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop?hl=en), set the Authorization header as `BASIC dXNlcm5hbWU6cEBzc3cwcmQ=` and make a GET request for localhost:8080/hello/secured

 - `curl -w "\n" localhost:8080/hello/secured -i -H "Authorization: Basic dXNlcm5hbWU6cEBzc3cwcmQ="`
 

###### HTTPS

1. `curl -w "\n" localhost:8080/hello/secured -k -i -u username:p@ssw0rd`

2. Copy the base-64 encoded output for `username:p@ssw0rd` from https://www.base64encode.org:

```
dXNlcm5hbWU6cEBzc3cwcmQ=
```

 - `curl -w "\n" localhost:8080/hello/secured -k -i -H "Authorization: Basic dXNlcm5hbWU6cEBzc3cwcmQ="`
 