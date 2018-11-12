# project-one


Consider an application that a marketing company called ECMA uses to host and organise events with the help of partner companies. Members of ECMA are users of the system. The system also holds information about the partner companies and their employees and allows the corresponding users to maintain their data. Users from a given company tagged as “Admin” can edit the information of the company, and manage the users from that same partner company. Any user (even an employee from a partner company) may introduce a new event proposal in the system. A proposal involves members of the host company (users) and members of the partner company. A proposal goes through an approval process before becoming public to all users. The reviewing is performed by adding reviews to the event proposal, the assignment of reviewers to proposals is a biding process, the approval is performed by a staff user tagged “Approver”. A proposal consists of an arbitrary sequence of document sections (e.g. title, description, goals, needed materials, budget, work-plan). A proposal has a team that consists of staff and partner members. A proposal has a related stream of comments that fire email notifications to the involved users to respond using a link.

####Swagger UI: localhost:8080/swagger-ui.html

### Credencials

#### Superadmin
* username = admin
* password = password

#TODO

###Especificação

* ~~Adicionar GET /proposals (TBD)~~
* __Adicionar PUT /proposals/{id}__

###Servidor

* ~~Mudar a verificação dos pedidos dos serviços para o controlador~~
* ~~Implementar os brokers~~
* ~~Experimentar meter a tag @Autowired em todos os serviços, repositórios e controladores~~
* ~~Colocar a verificação id != null nos POST~~
* ~~Approver não faz reviews~~
* ~~Meter PLACED, REVIEW_PERIOD, ACCEPTED, DECLINED nas propostas e escolher automaticamente bids tendo em conta a mudança de estado de placed -> review_period~~
* ~~Meter chave estrangeira da partner company na proposal~~
* ~~Distinguir o ADMIN do spring do admin de uma emppresa~~
* ~~Remover GET /proposals (TBD)~~
* ~~Por o approver na proposal~~
* ~~Verificar se o approver faz parte da company da proosal~~
* ~~Enumerado de roles~~
* ~~Adicionar pasta 'annotations' à pasta 'security'~~
* ~~Adicionar testes~~ 
* __Verificar as politicas de segurança__
* ~~segurança do addProposal~~
* atualizar ER

###Developers

* André Lopes nº 45617
* Nelson Coquenim nº 45694
* Simão Dolores nº 45020