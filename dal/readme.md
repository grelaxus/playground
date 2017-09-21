This code is working for my environment.  
The goal is to build a simple dal to operate with DB using Spring Data JPA (ideally without Spring Boot which brings in a lot of garbage).  

Following sources may be useful.  
JPA using Spring without Spring Boot:  
https://www.youtube.com/watch?v=PMMyTCjGFjI

https://www.youtube.com/watch?v=PuEZ7OFyDis

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
