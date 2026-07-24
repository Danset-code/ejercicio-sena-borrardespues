-- ============================================================
-- DATOS DE PRUEBA - AGROMONITOR
-- Proyecto: Sistema de medición y control ambiental
-- Cultivo: Cannabis medicinal
-- ============================================================

USE monitoreo_ambiental;
SELECT * FROM sensores;

-- ============================================================
-- LIMPIAR DATOS ANTERIORES
-- ============================================================

SET FOREIGN_KEY_CHECKS = 0;
SET SQL_SAFE_UPDATES = 0;

DELETE FROM mediciones;
DELETE FROM alertas;
DELETE FROM configuracion_ambiental;
DELETE FROM actuadores;
DELETE FROM sensores;
DELETE FROM usuarios;

SET SQL_SAFE_UPDATES = 1;
SET FOREIGN_KEY_CHECKS = 1;


-- ============================================================
-- USUARIOS
-- ============================================================

INSERT INTO usuarios
(nombre, email, password, rol)
VALUES
('Administrador del Sistema',
 'admin@agromonitor.com',
 'admin123',
 'ADMINISTRADOR'),

('Daniel Pachon',
 'daniel@agromonitor.com',
 '123456',
 'OPERADOR'),

('Operador de Cultivo',
 'operador@agromonitor.com',
 'operador123',
 'OPERADOR');


-- ============================================================
-- SENSORES
-- ============================================================

INSERT INTO sensores
(nombre, tipo, unidad, activo)
VALUES
('Sensor de Temperatura', 'Temperatura', '°C', TRUE),
('Sensor de Humedad Relativa', 'Humedad', '%', TRUE),
('Sensor de CO2', 'CO2', 'ppm', TRUE),
('Sensor de Humedad del Suelo', 'Humedad del suelo', '%', TRUE),
('Sensor de Luminosidad', 'Luminosidad', 'lux', TRUE),
('Sensor de pH', 'pH', 'pH', TRUE);


-- ============================================================
-- ACTUADORES
-- ============================================================

INSERT INTO actuadores
(nombre, tipo, activo)
VALUES

('Sistema de Ventilación',
 'Ventilador',
 TRUE),

('Sistema de Calefacción',
 'Calefactor',
 FALSE),

('Sistema de Riego',
 'Bomba de agua',
 TRUE),

('Sistema de Iluminación',
 'Lámpara LED',
 TRUE),

('Extractor de Aire',
 'Extractor',
 FALSE),

('Humidificador',
 'Humidificador',
 FALSE);


-- ============================================================
-- CONFIGURACIÓN AMBIENTAL
-- ============================================================

INSERT INTO configuracion_ambiental
(variable, minimo, maximo, unidad, alerta_activa)
VALUES

('Temperatura',
 18.0,
 28.0,
 '°C',
 TRUE),

('Humedad Relativa',
 40.0,
 70.0,
 '%',
 TRUE),

('CO2',
 400.0,
 1200.0,
 'ppm',
 TRUE),

('Humedad del Suelo',
 35.0,
 75.0,
 '%',
 TRUE),

('Luminosidad',
 10000.0,
 50000.0,
 'lux',
 TRUE),

('pH',
 5.5,
 6.5,
 'pH',
 TRUE);


-- ============================================================
-- MEDICIONES
-- ============================================================

INSERT INTO mediciones
(sensor_id, valor, fecha)
VALUES

-- Temperatura
(1, 24.5, '2026-07-23 08:00:00'),
(1, 25.1, '2026-07-23 09:00:00'),
(1, 25.8, '2026-07-23 10:00:00'),
(1, 26.2, '2026-07-23 11:00:00'),
(1, 27.0, '2026-07-23 12:00:00'),
(1, 27.5, '2026-07-23 13:00:00'),

-- Humedad relativa
(2, 65.0, '2026-07-23 08:00:00'),
(2, 63.5, '2026-07-23 09:00:00'),
(2, 61.2, '2026-07-23 10:00:00'),
(2, 59.8, '2026-07-23 11:00:00'),
(2, 58.5, '2026-07-23 12:00:00'),
(2, 56.2, '2026-07-23 13:00:00'),

-- CO2
(3, 650.0, '2026-07-23 08:00:00'),
(3, 720.0, '2026-07-23 09:00:00'),
(3, 810.0, '2026-07-23 10:00:00'),
(3, 900.0, '2026-07-23 11:00:00'),
(3, 980.0, '2026-07-23 12:00:00'),
(3, 1050.0, '2026-07-23 13:00:00'),

-- Humedad del suelo
(4, 68.0, '2026-07-23 08:00:00'),
(4, 65.0, '2026-07-23 09:00:00'),
(4, 61.0, '2026-07-23 10:00:00'),
(4, 57.0, '2026-07-23 11:00:00'),
(4, 52.0, '2026-07-23 12:00:00'),
(4, 48.0, '2026-07-23 13:00:00'),

-- Luminosidad
(5, 15000.0, '2026-07-23 08:00:00'),
(5, 22000.0, '2026-07-23 09:00:00'),
(5, 30000.0, '2026-07-23 10:00:00'),
(5, 38000.0, '2026-07-23 11:00:00'),
(5, 45000.0, '2026-07-23 12:00:00'),
(5, 42000.0, '2026-07-23 13:00:00'),

-- pH
(6, 6.1, '2026-07-23 08:00:00'),
(6, 6.0, '2026-07-23 09:00:00'),
(6, 6.2, '2026-07-23 10:00:00'),
(6, 6.1, '2026-07-23 11:00:00'),
(6, 5.9, '2026-07-23 12:00:00'),
(6, 6.0, '2026-07-23 13:00:00');


-- ============================================================
-- ALERTAS
-- ============================================================

INSERT INTO alertas
(variable, valor, prioridad, estado, fecha)
VALUES

('Temperatura',
 29.4,
 'ALTA',
 'ACTIVA',
 '2026-07-23 13:30:00'),

('Humedad Relativa',
 38.5,
 'MEDIA',
 'ACTIVA',
 '2026-07-23 13:15:00'),

('Humedad del Suelo',
 31.0,
 'ALTA',
 'ACTIVA',
 '2026-07-23 12:45:00'),

('CO2',
 1350.0,
 'MEDIA',
 'ATENDIDA',
 '2026-07-22 15:30:00'),

('Temperatura',
 17.2,
 'MEDIA',
 'ATENDIDA',
 '2026-07-22 07:10:00');


-- ============================================================
-- FINAL
-- ============================================================

SELECT 'Datos de prueba insertados correctamente' AS mensaje;

SELECT * FROM sensores;

SELECT * FROM actuadores;

SELECT * FROM mediciones;

SELECT * FROM alertas;

SELECT * FROM configuracion_ambiental;

SELECT * FROM usuarios;