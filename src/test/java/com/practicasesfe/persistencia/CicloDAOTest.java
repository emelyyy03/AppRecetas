package com.practicasesfe.persistencia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CicloDAOTest {
    private CicloDAO cicloDAO; // Instancia de la clase UserDAO que se va a probar.

    @BeforeEach
    void setUp(){
        // Método que se ejecuta antes de cada método de prueba (@Test).
        // Su propósito es inicializar el entorno de prueba, en este caso,
        // creando una nueva instancia de UserDAO para cada prueba.
            cicloDAO = new CicloDAO();
    }

    @Test
    void insertar() {
    }
}