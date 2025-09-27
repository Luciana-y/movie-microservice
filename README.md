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

## 🧪 4) Probar con cURL

### 🎬 Películas

#### Crear nueva película
```bash
curl -X POST http://localhost:8080/movies \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Inception",
    "genre": "Ciencia Ficción",
    "duration": 148,
    "premiere": true
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
    "title": "Inception (Extended)",
    "genre": "Ciencia Ficción",
    "duration": 160,
    "premiere": false
  }'
```

#### Actualizar estado de premiere
```bash
curl -X PATCH http://localhost:8080/movies/1/premiere \
  -H "Content-Type: application/json" \
  -d '{"premiere": true}'
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
    "date": "2025-10-01",
    "time": "20:30",
    "cinema": "Cineplanet"
  }'
```

#### Obtener showtimes por película
```bash
curl -X GET http://localhost:8080/movies/1/showtimes
```

## 5) Lista completa de endpoints

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

## 6) Solución de problemas
- Error de conexión a Postgres: verifica que el contenedor `postgres` esté corriendo y que las variables en `.env` sean correctas.

- Puerto en uso (8080 o 5432): edita el `docker-compose.yml` y cambia los puertos expuestos.

- Swagger no carga: confirma que la API esté corriendo (`docker logs movie_microservice`).

- Base de datos vacía: usa los endpoints `POST` para cargar datos iniciales de películas y showtimes.
