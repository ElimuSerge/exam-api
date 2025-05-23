Cahier des Charges - Système de Gestion des Examens

1. Contexte et Objectifs

L'application web gère les examens, notes, et utilisateurs dans un établissement universitaire.
 Elle permet aux administrateurs de gérer cours et inscriptions, aux professeurs de saisir notes et examens,
  et aux étudiants de consulter leurs résultats. 
  
  Objectifs :

Automatiser la gestion des examens et notes.
Offrir des tableaux de bord par rôle (ADMIN, PROFESSEUR, ETUDIANT).
Fournir une API REST pour accès programmatique et des vues web conviviales.

2. Fonctionnalités Principales

Inscription, authentification, gestion cours/examens/notes, tableaux de bord.

2.1 Gestion des Utilisateurs

Inscription et authentification (email, mot de passe).
Rôles : ADMIN (gestion globale), PROFESSEUR (notes, examens), ETUDIANT (consultation).
Sécurité : Authentification via formulaire (web) et Basic Auth (API).

2.2 Gestion des Cours

Création, modification, suppression des cours (nom, description, professeur).
Association des cours à des professeurs et étudiants (inscriptions).

2.3 Gestion des Examens

Création d'examens (titre, date, type, cours associé).
Liste des examens à venir/passés, filtrée par rôle.
Modification et suppression par professeurs.

2.4 Gestion des Notes

Saisie des notes par professeurs (étudiant, examen, valeur).
Consultation des notes par étudiants (par cours/semestre).
Calcul automatique des moyennes (par étudiant, cours, classe).

2.5 Tableaux de Bord

Admin : Vue des cours, inscriptions, utilisateurs.
Professeur : Liste des examens à corriger, notes saisies.
Étudiant : Notes, examens à venir, cours inscrits.

2.6 Rapports et Statistiques

Génération de bulletins PDF (notes par étudiant).
Statistiques : Moyennes, taux de réussite par cours/classe.

2.7 API REST

Endpoints JSON pour étudiants (/api/student), professeurs (/api/teacher), admins (/api/admin).
Accès sécurisé via Basic Auth.

3. Technologies Utilisées

Backend : Spring Boot 3.4.5, Spring Data JPA (Hibernate), Spring Security, Spring MVC, IOC/DI, Data Access,
 ORM,Thymeleaf,Lombok, AOP, Test/Mockito.

Base de données : MySQL 

Frontend : Thymeleaf pour vues web.

Outils : Maven (dépendances), Lombok (code simplifié), iText (PDF, à intégrer).

Tests : JUnit, Mockito (8/8 tests réussis).

4. Structure de la Base de Données

Utilisateur : ID, nom, prénom, email, mot de passe, rôle (ADMIN, PROFESSEUR, ETUDIANT).
Cours : ID, nom, description, professeur (FK Utilisateur).
Examen : ID, titre, date, type, cours (FK Cours).
Note : ID, valeur, étudiant (FK Utilisateur), examen (FK Examen).
Inscription : ID, étudiant (FK Utilisateur), cours (FK Cours).
Relations : Un cours a un professeur et plusieurs étudiants. Un examen est lié à un cours, génère des notes.

5. Contraintes

Sécurité : Protection des endpoints (rôles, authentification).
Performance : Gestion efficace des données (JPA, HikariCP).
Compatibilité : MySQL 8.0.x requis (actuel 5.5.5 obsolète).
Ergonomie : Interfaces web simples (Thymeleaf), API REST documentée.

6. État Actuel

Implémenté : Gestion utilisateurs, cours, examens, notes, tableaux de bord, API partielle (/api/student).
Tests : 8/8 réussis (StudentControllerTest, UserServiceTest).

Problèmes :

Rapports PDF/statistiques non implémentés.
Calcul moyennes absent.



7. Améliorations Requises

Activer Basic Auth (SecurityConfig) pour API.
Ajouter /api/teacher, /api/admin (contrôleurs REST).
Intégrer iText pour bulletins PDF.
Implémenter calcul moyennes (GradeService).

8. Livrables

Application web fonctionnelle (vues MVC, API REST).
Code source (GitHub recommandé).
Documentation : Cahier des charges, guide utilisateur.
Tests unitaires/intégration.

