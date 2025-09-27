CREATE DATABASE movieapi;
\c midb;
CREATE USER postgres WITH PASSWORD 'postgres';
GRANT ALL PRIVILEGES ON DATABASE movieapi TO postgres;