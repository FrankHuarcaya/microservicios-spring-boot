# Microservicios Spring Boot - Guia del Proyecto

## Arquitectura General

Proyecto de microservicios educativo con Spring Boot 3.x y Spring Cloud.

### Servicios de Infraestructura

| Servicio | Puerto | Descripcion |
|---|---|---|
| config-server | 8888 | Configuracion centralizada (Spring Cloud Config) |
| microservicios-eureka | 8761 | Descubrimiento de servicios (Netflix Eureka) |
| microservices-gateway | 8090 | API Gateway (Spring Cloud Gateway + Resilience4j) |

### Servicios de Negocio

| Servicio | Puerto | Base de Datos | Tipo |
|---|---|---|---|
| microservices-users | 8081 | MySQL 8 | CRUD Personas (JPA) |
| microservicios-cursos | 8082 | PostgreSQL 15 | Cursos + Matriculas (JPA) |
| microservices-exams | 8083 | MongoDB 7 | Examenes + Preguntas |
| microservices-payments | 8084 | MongoDB 7 | Pagos (WebFlux reactivo) |

### Base de Datos (Docker)

| Servicio | Puerto Externo | Contenedor |
|---|---|---|
| MySQL | 3307 | mysql-users |
| PostgreSQL | 5433 | postgres-cursos |
| MongoDB (exams) | 27018 | mongo-services |
| MongoDB (payments) | 27017 | mongo-payments |

## Comunicacion entre Servicios

```
Gateway (8090)
  ├── /api/v1/persons/**    → Users (8081)
  ├── /api/v1/courses/**    → Cursos (8082)
  ├── /api/v1/enrollments/** → Cursos (8082)
  ├── /api/v1/exams/**      → Exams (8083)
  ├── /api/v1/questions/**  → Exams (8083)
  └── /api/v1/payments/**   → Payments (8084)

Cursos ──Feign──> Users (obtiene datos de persona)
Exams  ──Feign──> Cursos (obtiene datos de curso)
Payments ──WebClient──> Users + Cursos
```

## Tecnologias

- **Java 17** + **Spring Boot 3.3-3.4**
- **Spring Cloud 2023-2024** (Config, Eureka, Gateway, OpenFeign)
- **Resilience4j** (Circuit Breaker en Gateway y Cursos)
- **SpringDoc OpenAPI** (Swagger en Cursos)
- **Lombok** + **ModelMapper**
- **Docker** + **Docker Compose**

## Ejecucion con Docker

### Levantar todo con un solo comando

```bash
docker-compose up --build
```

### Verificar servicios

```bash
docker-compose ps
```

### URLs de los servicios

- Config Server: http://localhost:8888
- Eureka Dashboard: http://localhost:8761
- API Gateway: http://localhost:8090
- Swagger Cursos: http://localhost:8082/swagger-ui.html

### Ejecutar en segundo plano

```bash
docker-compose up --build -d
```

### Detener todo

```bash
docker-compose down
```

### Detener y eliminar volumenes

```bash
docker-compose down -v
```

## Arquitectura de Cada Servicio

Cada servicio sigue la capa:
```
api/           → Controllers REST
domain/
  entities/    → Entidades JPA/Mongo
  persistence/ → Repositorios
  services/    → Interfaces de servicio
services/      → Implementaciones
dto/           → Data Transfer Objects
mapping/       → Mapeo entre entidades y DTOs
client/        → Clientes Feign/WebClient para otros servicios
shared/        → Excepciones globales, modelos compartidos
```

## Endpoints REST

### Users (`/api/v1/persons`)
- `GET /` - Listar personas
- `POST /` - Crear persona
- `GET /{id}` - Obtener persona por ID
- `PUT /{id}` - Actualizar persona
- `DELETE /{id}` - Eliminar persona

### Cursos (`/api/v1/courses`)
- `GET /` - Listar cursos
- `POST /` - Crear curso
- `GET /{id}` - Obtener curso por ID
- `PUT /{id}` - Actualizar curso
- `DELETE /{id}` - Eliminar curso

### Matriculas (`/api/v1/enrollments`)
- `GET /` - Listar matriculas
- `POST /` - Matricular estudiante

### Examenes (`/api/v1/exams`)
- `GET /` - Listar examenes
- `POST /` - Crear examen
- `GET /{id}` - Obtener examen por ID
- `DELETE /{id}` - Eliminar examen

### Preguntas (`/api/v1/questions`)
- `GET /` - Listar preguntas
- `POST /` - Crear pregunta
- `GET /{id}` - Obtener pregunta por ID
- `DELETE /{id}` - Eliminar pregunta

### Pagos (`/api/v1/payments`)
- `GET /` - Listar pagos
- `POST /` - Crear pago
- `GET /{id}` - Obtener pago por ID
- `DELETE /{id}` - Eliminar pago
