#What is this?

This project contains a demo of Java EE 7 + JAX-RS + CDI + EJB + JPA + Spring Data

#Notes

- `User` and `Role` are JPA Entities with a one to many relationship.
- `SpringDataEntityManagerProducer` is a CDI producer which Spring Data asked for. Without it, Spring Data doesn't know which entity manager (or rather persistence unit) it should be using.
- `UserRepository` is a Spring Data Repository. See http://docs.spring.io/spring-data/jpa/docs/1.10.5.RELEASE/reference/html/ for more information.
- `UserResource` is a REST service which can be GETted at [http://localhost:8080/jee7webappwithspringdata/rs/employees/all](). It shows how you can use JPA or Spring Data to interact with the database.
- To add Spring Data to your project see the two entries in the POM - one under Dependency Management, the other under Dependencies.

#Prerequisites and deployment

- A JBoss 8.2 or later, with a Datasource with the JNDI name ``.
- Database, with some data:

        mysql> desc USER;
        +----------+--------------+------+-----+---------+-------+
        | Field    | Type         | Null | Key | Default | Extra |
        +----------+--------------+------+-----+---------+-------+
        | ID       | bigint(20)   | NO   | PRI | NULL    |       |
        | EMAIL    | varchar(255) | NO   | UNI | NULL    |       |
        | PASSWORD | varchar(255) | NO   |     | NULL    |       |
        | NAME     | varchar(255) | NO   |     | NULL    |       |
        +----------+--------------+------+-----+---------+-------+
        4 rows in set (0.00 sec)
        
        mysql> desc ROLE;
        +---------+--------------+------+-----+---------+-------+
        | Field   | Type         | Null | Key | Default | Extra |
        +---------+--------------+------+-----+---------+-------+
        | ID      | bigint(20)   | NO   | PRI | NULL    |       |
        | EMAIL   | varchar(255) | NO   | MUL | NULL    |       |
        | ROLE    | varchar(255) | NO   |     | NULL    |       |
        | USER_ID | bigint(20)   | YES  | MUL | NULL    |       |
        +---------+--------------+------+-----+---------+-------+
        4 rows in set (0.00 sec)

        mysql> select * from USER;
        +----+----------------+--------------------------+------+
        | ID | EMAIL          | PASSWORD                 | NAME |
        +----+----------------+--------------------------+------+
        |  1 | john@maxant.ch | kS7LOSeSlQQaNSVq1cA== | John |
        |  2 | jane@maxant.ch | kS7LOSeSlQQaNSVq1cA== | Jane |
        +----+----------------+--------------------------+------+
        2 rows in set (0.00 sec)
        
        mysql> select * from ROLE;
        +----+----------------+------------+---------+
        | ID | EMAIL          | ROLE       | USER_ID |
        +----+----------------+------------+---------+
        |  1 | john@maxant.ch | registered |       1 |
        |  2 | jane@maxant.ch | registered |       2 |
        |  3 | jane@maxant.ch | admin      |       2 |
        +----+----------------+------------+---------+
        3 rows in set (0.00 sec)
