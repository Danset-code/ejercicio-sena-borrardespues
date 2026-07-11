package com.taskflow;

/*
 * Clase de prueba principal que verifica que el contexto
 * de Spring Boot pueda cargarse correctamente sin errores.
 */
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Prueba de contexto de aplicación.
 * Verifica que todos los componentes de Spring se carguen
 * correctamente sin conflictos de configuración.
 * 
 * @author TaskFlow Team
 * @version 1.0.0
 */
@SpringBootTest
class TaskflowApplicationTests {

    /**
     * Verifica que el contexto de la aplicación Spring Boot
     * se carga sin errores de configuración o dependencias.
     */
    @Test
    void contextLoads() {
        // Si este test pasa, significa que Spring Boot
        // cargó correctamente todos los beans y configuraciones
    }
}