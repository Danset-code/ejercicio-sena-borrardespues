-- ============================================================================
-- Script de inicialización de datos para la base de datos H2.
-- Se ejecuta automáticamente al iniciar la aplicación (spring.sql.init.mode=always).
-- Inserta datos de ejemplo para probar la API inmediatamente.
-- ============================================================================

-- Limpiar datos existentes (por si quedan de ejecuciones anteriores)
DELETE FROM tareas;

-- Insertar tareas de ejemplo con diferentes estados y prioridades
INSERT INTO tareas (titulo, descripcion, estado, prioridad, fecha_creacion, fecha_actualizacion) VALUES
('Diseñar diagrama de clases', 'Crear el diagrama UML de clases para el módulo de usuarios del sistema', 'COMPLETADA', 3, NOW(), NOW()),
('Implementar login con JWT', 'Desarrollar el endpoint de autenticación usando tokens JWT con refresh token', 'EN_PROGRESO', 3, NOW(), NOW()),
('Crear pruebas unitarias', 'Escribir tests unitarios para los servicios de tareas usando JUnit 5 y Mockito', 'PENDIENTE', 2, NOW(), NULL),
('Configurar base de datos en producción', 'Instalar y configurar MySQL en el servidor de producción con respaldo automático', 'PENDIENTE', 2, NOW(), NULL),
('Diseñar interfaz de usuario', 'Crear los mockups en Figma para las pantallas principales del gestor de tareas', 'COMPLETADA', 3, NOW(), NOW()),
('Documentar API con Swagger', 'Integrar SpringDoc OpenAPI para generar documentación interactiva de la API REST', 'PENDIENTE', 1, NOW(), NULL),
('Revisar código del sprint', 'Realizar code review de los pull requests del sprint actual en GitHub', 'EN_PROGRESO', 2, NOW(), NOW()),
('Optimizar consultas SQL', 'Analizar y mejorar el rendimiento de las consultas más lentas usando EXPLAIN', 'CANCELADA', 1, NOW(), NOW());