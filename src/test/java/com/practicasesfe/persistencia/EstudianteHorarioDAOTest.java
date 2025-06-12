package com.practicasesfe.persistencia;

import com.practicasesfe.dominio.Estudiantes;
import com.practicasesfe.dominio.Horario;
import com.practicasesfe.dominio.EstudianteHorario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class EstudianteHorarioDAOTest {

    private EstudianteHorarioDAO dao;

    @BeforeEach
    void setUp() {
        dao = new EstudianteHorarioDAO();
    }

    @Test
    void testAsignarHorario() {
        int idEstudiante = 1;
        int idHorario = 1;
        EstudianteHorario asignacion = new EstudianteHorario(idEstudiante, idHorario);

        try {
            EstudianteHorario resultado = dao.asignarHorario(asignacion);
            assertNotNull(resultado);
            assertEquals(idEstudiante, resultado.getIdEstudiante());
            assertEquals(idHorario, resultado.getIdHorario());
        } catch (SQLException e) {
            fail("Excepción al asignar horario: " + e.getMessage());
        }
    }

    @Test
    void testGetHorariosByEstudiante() {
        int idEstudiante = 1;
        int idHorario = 1;
        EstudianteHorario asignacion = new EstudianteHorario(idEstudiante, idHorario);

        try {
            dao.delete(asignacion); // Asegura que no esté ya asignado
            dao.asignarHorario(asignacion);

            ArrayList<Horario> lista = dao.getHorariosByEstudiante(idEstudiante);
            assertNotNull(lista);
            assertFalse(lista.isEmpty());
            assertTrue(lista.stream().anyMatch(h -> h.getIdHorario() == idHorario));
        } catch (SQLException e) {
            fail("Excepción al obtener horarios por estudiante: " + e.getMessage());
        }
    }

    @Test
    void testGetEstudiantesByHorario() {
        int idEstudiante = 1;
        int idHorario = 1;
        EstudianteHorario asignacion = new EstudianteHorario(idEstudiante, idHorario);

        try {
            dao.delete(asignacion); // Limpia si ya estaba
            dao.asignarHorario(asignacion);

            ArrayList<Integer> estudiantes = dao.getEstudiantesByHorario(idHorario);
            assertNotNull(estudiantes);
            assertFalse(estudiantes.isEmpty());
            assertTrue(estudiantes.contains(idEstudiante));
        } catch (SQLException e) {
            fail("Excepción al obtener estudiantes por horario: " + e.getMessage());
        }
    }

    @Test
    void testEliminarAsignacion() {
        int idEstudiante = 1;
        int idHorario = 1;
        EstudianteHorario asignacion = new EstudianteHorario(idEstudiante, idHorario);

        try {
            // Elimina directamente la asignación si ya existe
            boolean eliminado = dao.delete(asignacion);
            assertTrue(eliminado, "La asignación debería haber sido eliminada");
        } catch (SQLException e) {
            fail("Excepción al eliminar asignación: " + e.getMessage());
        }
    }

}