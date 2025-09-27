# ---------- Etapa 1: Build de la app ----------
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# ---------- Etapa 2: Imagen final con Spring Boot + PostgreSQL ----------
FROM eclipse-temurin:17-jdk

# Instalar PostgreSQL
USER root
RUN apt-get update && apt-get install -y postgresql postgresql-contrib && rm -rf /var/lib/apt/lists/*

# Variables de entorno para Postgres
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres
ENV POSTGRES_DB=movieapi

# Copiar script inicialización
COPY init.sql /docker-entrypoint-initdb.d/

# Copiar jar de Spring Boot
COPY --from=build /app/target/*.jar app.jar

# Exponer puertos
EXPOSE 8080 5432

# Iniciar Postgres y Spring Boot en el mismo contenedor
CMD service postgresql start && java -jar app.jar
