# Inventory Master using JPA and Hibernate

This is a simple inventory managment software based on Hibernate (JPA), papared by Maven. Its uses MySQL. You look at my invetory-master-JDBC repository for its JDBC version.

The project allows you to keep track of a product inventory with supplier information by loggin the invetory movements. Therefore, it includes three entities: Product, Supplier and Log. There is an one-to-many relationship between them.  

In terms JPA features, the project runs queries using SQL, JPQL, NamedQueries, stored procedure, NamedStoredProcedure, as well ass Entity, Embeddable and SecondaryTable.

You can run the project using JPA (Persistence, EntityManagerFactory, EntityManager, persistance.xml) or native Hibernate (Configuration, SessionFactory,Session, hibernate.cfg.xml, hibernate.properties) using the factory BaseRepository

The project has an MVC like architecture in which CLI (view), Service (Controller) and Repository (Model) are seperated. It also applies Repository Pattern.

Before running the project, you first need to install MySQL (port: 3306) and then create the inventorymaster_jpa_db using the SQL import file given in the project folder.

For now, the project only includes  command line interface.

List of Commands:
<ul>
<li>help</li>
<li>exit</li> 
<br/>
<li>list [product/supplier]</li>
<li>list morethan [QUANTITY] </li>
<li>list lessthan [QUANTITY]</li>
<li>list equals [QUANTITY]</li>
<li>list depleted</li>
<br/>
<li>find [product/supplier] [NAME]</li>
<li>getindex [product/supplier] [INDEX]</li>
<li>getid [product/supplier] [ID]</li>
<br/>
<li>inc_inv [ID] [QUANTITY]</li>
<li>dec_inv [ID] [QUANTITY]</li>
<li>log all/[ID]</li>
<br/>
<li>update [product/supplier] [ID]</li>
<li>add [product/supplier]</li>
<li>delete [product/supplier] [ID]</li></li>
<li>delete_all [product/supplier]  [ID]+</li>
</ul>
