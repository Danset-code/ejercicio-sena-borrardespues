package com.taskflow;

/*
 * Clase principal que inicia la aplicación Spring Boot.
 * La anotación @SpringBootApplication habilita la configuración
 * automática, el escaneo de componentes y la configuración web.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada principal de la aplicación TaskFlow.
 * Esta clase arranca el servidor Tomcat embebido y configura
 * automáticamente todos los componentes de Spring.
 * 
 * @author TaskFlow Team
 * @version 1.0.0
 */
@SpringBootApplication
public class TaskflowApplication {

    /**
     * Método main que inicia la aplicación Spring Boot.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        SpringApplication.run(TaskflowApplication.class, args);
    }
}