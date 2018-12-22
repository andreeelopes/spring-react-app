# Event Management Application

### Requirements
Consider an application that a marketing company called ECMA uses to host and organise events with the help of partner companies.
Members of ECMA are users of the system.
The system also holds information about the partner companies and their employees and allows the corresponding users to maintain their data.
Users from a given company tagged as “Admin” can edit the information of the company, and manage the users from that same partner company. 
Any user (even an employee from a partner company) may introduce a new event proposal in the system.
A proposal involves members of the host company (users) and members of the partner company.
A proposal goes through an approval process before becoming public to all users. 
The reviewing is performed by adding reviews to the event proposal, the assignment of reviewers to proposals is a biding process, the approval is performed by a staff user tagged “Approver”.
A proposal consists of an arbitrary sequence of document sections (e.g. title, description, goals, needed materials, budget, work-plan). A proposal has a team that consists of staff and partner members.
A proposal has a related stream of comments that fire email notifications to the involved users to respond using a link.

## Documentation
The documentation of server and client is present in the doc folder, which includes the reports of the client and server applications.

## Server

### The latests specification can be accessed on the following url: 
#### Swagger UI: localhost:8080/swagger-ui.html

### Credentials

#### Superadmin
* __username__ = admin
* __password__ = password

### Directory strucutre of the Spring aplication
###
```
server
├───logs
├───src
│   ├───main
│   │   ├───java/pt/unl/fct/ecma
│   │   │   ├───api						(api based on the Swagger specification)
│   │   │   ├───brokers					(allows the decoupling of services)
│   │   │   ├───conf					(general configurations)
│   │   │   ├───controllers				(request handlers)
│   │   │   ├───errors					(custom exceptions for HTTP errors)
│   │   │   ├───models					(models of the data)
│   │   │   ├───repositories			(allows queries to the db)
│   │   │   ├───security				(authorization and authentication services)
│   │   │   │   └───annotations			(Spring Security annotations)
│   │   │   └───services				(services that the logic of the application)
│   │   └───resources					(contains the db configuration)
│   └───test/java/pt/unl/fct/ecma		(Spring Boot Tests)
│       └───utils						(some utilities used on the tests
```



## Client

Implemented using react+redux+typescript. 


###Developers

* André Lopes nº 45617
* Nelson Coquenim nº 45694
* Simão Dolores nº 45020