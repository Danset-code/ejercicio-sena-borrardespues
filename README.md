# AA4-EV03 - Monitoreo Ambiental

Proyecto académico con React + Vite en Front-End y Java + Spring Boot + MySQL en Back-End.

## Requisitos
- Java 21
- Maven
- Node.js y npm
- MySQL

## Base de datos
CREATE DATABASE monitoreo_ambiental;

Editar `backend-spring/src/main/resources/application.properties` y cambiar la contraseña de MySQL.

## Backend
cd backend-spring
mvn spring-boot:run

API: http://localhost:8080/api

## Frontend
cd frontend-react
npm install
npm run dev

Aplicación: http://localhost:5173

## Rutas React
/login
/dashboard
/monitoreo
/control
/historial
/alertas
/configuracion

## Nota
La autenticación incluida es demostrativa. Para una entrega productiva se recomienda integrar Spring Security y JWT.
