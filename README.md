# 🎬 Movie API - Microservicio de Películas y Showtimes

API REST desarrollada en **Spring Boot + PostgreSQL** para gestionar películas y sus showtimes (funciones).  
Soporta despliegue en **Docker** y exposición de documentación mediante **Swagger**.

---

## 🚀 Requisitos
- Java 17+
- Maven 3.9+
- Docker y Docker Compose instalados y corriendo
- Git instalado

---

## 📂 1) Clonar y compilar el proyecto
```bash
git clone https://github.com/<TU-USUARIO>/<TU-REPO>.git
cd movieMicroservice/
```
## 🐳 2) Levantar Postgres y la API con Docker
Construir y ejecutar los contenedores:
```bash
docker compose build
docker compose up -d

```
Verificar que ambos servicios están corriendo:
```bash
docker ps
```
Deberías ver:

- `postgres_container`
- `movie_microservice`

## 🌐 3) Acceder a la API

La API expone la documentación Swagger en:
👉 http://localhost:8080/swagger-ui.html

Puedes reemplazar `localhost` por la ip de tu máquina virtual

Incluye:

- ✅ Endpoints de gestión de películas
- ✅ Endpoints de showtimes (funciones)
- ✅ Ejemplos de request/response
- ✅ Validaciones y códigos de estado HTTP

## 4) Explicación de las entidades
### 🎬 Películas
La tabla Movies cuenta con los siguientes campos:
```bash
{
  "name": string
  "genre": enum
  "description": string
  "time": int
  "ageRestriction": enum
  "premiere":  boolean
  "showTimes": lista de showTimes
}
```
Los valores que `genre` puede tomar son:
```bash
    ACCION
    COMEDIA
    DRAMA
    CIENCIA_FICCION
    ROMANCE
    TERROR
    SUSPENSO
    ANIMACION
    DOCUMENTAL
    AVENTURA
    MUSICAL
    FANTASIA
    CRIMEN
    HISTORICA
```

Los valores que ageRestriction puede tomar son:
```bash
    ATP
    MAYORES_7
    MAYORES_13
    MAYORES_16
    MAYORES_18
```

### ⏰ShowTimes
La tabla ShowTime cuenta con los siguientes campos:
```bash
{
  "startTime": string ("yyyy-MM-dd HH:mm")
  "prince": double
  "cinemaId": int
  "movie": int
}
```
* `cinemaId`: No es una llave dentro de la base de datos, ya que pertenece a una tabla en otro microservicio
* `movie`: Es el ID de la tabla Movies, es el join entre ambas tablas, tienen una relación de ManyToOne

## 🧪 5) Probar con cURL

### 🎬 Películas

#### Crear nueva película
```bash
curl -X POST http://localhost:8080/movies \
  -H "Content-Type: application/json" \
  -d '{
  "name": "Avatar 2",
  "genre": "ACCION",
  "description": "Nueva entrega de la franquicia Avatar",
  "time": 190,
  "ageRestriction": "MAYORES_16",
  "premiere":  true
}'
```
#### Listar todas las películas
```bash
curl -X GET http://localhost:8080/movies
```

#### Obtener película por ID
```bash
curl -X GET http://localhost:8080/movies/1
```

#### Actualizar película
```bash
curl -X PUT http://localhost:8080/movies/1 \
  -H "Content-Type: application/json" \
  -d '{
  "name": "Avatar 2",
  "genre": "ACCION",
  "description": "Nueva entrega de la franquicia Avatar",
  "time": 190,
  "ageRestriction": "MAYORES_18",
  "premiere":  true
}'
```

#### Actualizar estado de premiere
```bash
curl -X PATCH http://localhost:8080/movies/1/premiere \
  -H "Content-Type: application/json" \
  -d '{"premiere": false}'
```

#### Eliminar película
```bash
curl -X DELETE http://localhost:8080/movies/1
```

### ⏰ Showtimes

#### Crear nuevo showtime para una película
```bash
curl -X POST http://localhost:8080/movies/1/showtimes \
  -H "Content-Type: application/json" \
  -d '{
  "startTime": "2025-09-26 18:30",
  "cinemaId": 1}'
```

#### Obtener showtimes por película
Este endpoint te permite
```bash
curl -X GET http://localhost:8080/movies/1/showtimes
```

## 6) Lista completa de endpoints

### 🎬 Películas
- `POST /movies` - Crear nueva película  
- `GET /movies` - Listar todas las películas  
- `GET /movies/{id}` - Obtener película por ID  
- `PUT /movies/{id}` - Actualizar película
- `PATCH /movies/{id}/premiere` - Actualizar si la película esta en premiere
- `DELETE /movies/{id}` - Eliminar película  

### ⏰ Showtimes
- `POST /movies/{movieid}/showtimes` - Crear nuevo showtime para una película
- `GET /movie/{movieId}/showtimes` - Obtener showtimes por película  

## 7) Solución de problemas
- Error de conexión a Postgres: verifica que el contenedor `postgres` esté corriendo y que las variables en `.env` sean correctas.

- Puerto en uso (8080 o 5432): edita el `docker-compose.yml` y cambia los puertos expuestos.

- Swagger no carga: confirma que la API esté corriendo (`docker logs movie_microservice`).

- Base de datos vacía: usa los endpoints `POST` para cargar datos iniciales de películas y showtimes.
