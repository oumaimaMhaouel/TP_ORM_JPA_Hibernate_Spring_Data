# TP ORM / JPA / Hibernate / Spring Data

Application Spring Boot illustrant la persistance des données avec **JPA** et **Hibernate**, l'accès aux données via **Spring Data JPA**, et l'exposition d'une API REST pour la gestion de produits.

## Technologies

| Technologie | Version / usage |
|---|---|
| Java | 17 |
| Spring Boot | 3.5.14 |
| Spring Data JPA | Persistance et repositories |
| Hibernate | Implémentation JPA |
| H2 | Base de données en mémoire (développement) |
| MySQL Connector | Disponible pour une base MySQL en production |
| Lombok | Réduction du code boilerplate |
| SpringDoc OpenAPI | Documentation interactive de l'API |

## Architecture

```
src/main/java/com/example/tp_orm_jpa_hibernate_spring_data/
├── TpOrmJpaHibernateSpringDataApplication.java   # Point d'entrée Spring Boot
├── entities/
│   └── Product.java                              # Entité JPA (@Entity)
├── dtos/
│   └── ProductDto.java                           # Objet de transfert (DTO)
├── repositories/
│   └── ProductRepository.java                    # Repository Spring Data JPA
├── service/
│   ├── ProductService.java                       # Interface métier
│   └── serviceImpl/
│       └── ProductServiceImpl.java               # Implémentation métier
└── web/
    └── ProductRestController.java                # Contrôleur REST
```

### Couches

1. **Entité (`Product`)** — Mappée sur la table `PRODUCTS` via JPA/Hibernate.
2. **Repository (`ProductRepository`)** — Étend `JpaRepository` et expose une requête dérivée `findByNameContainingIgnoreCase`.
3. **Service (`ProductServiceImpl`)** — Contient la logique métier et convertit les entités en DTOs via `ObjectMapper`.
4. **Contrôleur (`ProductRestController`)** — Expose les endpoints REST sous `/api/product`.

## Prérequis

- **JDK 17** ou supérieur
- **Maven 3.6+** (ou utiliser le wrapper `./mvnw`)

## Configuration

Les paramètres sont définis dans `src/main/resources/application.properties` :

| Propriété | Valeur | Description |
|---|---|---|
| `server.port` | `8084` | Port du serveur |
| `spring.datasource.url` | `jdbc:h2:mem:product-db` | Base H2 en mémoire |
| `spring.jpa.hibernate.ddl-auto` | `update` | Création/mise à jour automatique du schéma |
| `spring.h2.console.enabled` | `true` | Console H2 activée |

### Console H2

Une fois l'application démarrée, la console est accessible à :

```
http://localhost:8084/h2-console
```

Paramètres de connexion :

- **JDBC URL** : `jdbc:h2:mem:product-db`
- **User Name** : `sa`
- **Password** : *(laisser vide)*

## Lancement

```bash
# Avec Maven Wrapper (Windows)
.\mvnw.cmd spring-boot:run

# Avec Maven Wrapper (Linux / macOS)
./mvnw spring-boot:run

# Ou avec Maven installé globalement
mvn spring-boot:run
```

L'application démarre sur **http://localhost:8084**.

## Documentation API (Swagger)

SpringDoc OpenAPI est configuré. Une fois l'application lancée :

- **Swagger UI** : http://localhost:8084/swagger-ui.html
- **OpenAPI JSON** : http://localhost:8084/v3/api-docs

## Endpoints REST

Base URL : `http://localhost:8084/api/product`

| Méthode | Endpoint | Description |
|---|---|---|
| `GET` | `/all-product` | Liste tous les produits |
| `GET` | `/{id}` | Récupère un produit par son identifiant |
| `GET` | `/find-product?kw={mot-clé}` | Recherche les produits dont le nom contient le mot-clé (insensible à la casse) |
| `POST` | `/save` | Crée un nouveau produit |
| `PUT` | `/update` | Met à jour un produit existant |
| `DELETE` | `/{id}` | Supprime un produit par son identifiant |

### Modèle `ProductDto`

```json
{
  "id": 1,
  "name": "Ordinateur portable",
  "price": 899.99,
  "quantity": 10
}
```

### Exemples de requêtes

**Créer un produit**

```bash
curl -X POST http://localhost:8084/api/product/save \
  -H "Content-Type: application/json" \
  -d '{"name": "Clavier", "price": 49.99, "quantity": 25}'
```

**Lister tous les produits**

```bash
curl http://localhost:8084/api/product/all-product
```

**Rechercher par nom**

```bash
curl "http://localhost:8084/api/product/find-product?kw=clav"
```

**Mettre à jour un produit**

```bash
curl -X PUT http://localhost:8084/api/product/update \
  -H "Content-Type: application/json" \
  -d '{"id": 1, "name": "Clavier mécanique", "price": 79.99, "quantity": 15}'
```

**Supprimer un produit**

```bash
curl -X DELETE http://localhost:8084/api/product/1
```


## Objectifs pédagogiques

Ce TP permet de mettre en pratique :

- Le mapping objet-relationnel avec **JPA** et **Hibernate** (`@Entity`, `@Table`, `@Id`)
- L'utilisation de **Spring Data JPA** (`JpaRepository`, requêtes dérivées)
- La séparation des responsabilités en couches (entité, DTO, repository, service, contrôleur)
- L'exposition d'une **API REST** avec Spring Web
- La configuration d'une base de données **H2** pour le développement
