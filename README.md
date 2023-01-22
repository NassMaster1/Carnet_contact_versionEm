# Carnet contacts <br>

**Pour lancer notre application, il faut :**

## Pré-requis:
1. JDK 17
2. Avoir une base de données MySQL nommée *`carnet_contact`*.

## Installation
1. Cloner le projet sur votre machine.
2. Modifier les lignes 21 et 24 de *`src/main/resources/META-INF/persistence.xml`*, en renseignant ses propres données. <br><br>

## Exécution
1. Lancer la commande : *`mvn clean spring-boot:run`*.
2. Cloner le projet [https://github.com/NassMaster1/carnet_contact_front](url), puis suivre les indications se trouvant dans son README. 
3. Sur un navigateur, aller sur [localhost:4200/accueil](url). <br><br>


## **Fonctionnalités réalisées :** <br>
- Les CRUD pour la gestion des Contacts, ContactsGroups et PhoneNumbers.
- Lister tous les contacts.
- Possibilité de récupérer un contact avec un des attributs suivant : firstname ou lastname ou email ou keyword, selon les besoins. (3 services différents)
- Récupérer un contact avec les deux attributs : firstname et lastname.
- Ajouter un contact à un groupe de contacts.
- Lister tous les groupes.
- Récupérer le groupe à partir de son nom ou de son ID. (2 services différents)
- Supprimer un contact d'un groupe.
- Ajouter, mettre à jour et supprimer un numéro d'un contact. <br><br>


## Architecture 
![Architecture](https://user-images.githubusercontent.com/70312284/213913820-c7e6e0e2-f823-462f-99bf-586c97b4bd7f.png) <br><br>

