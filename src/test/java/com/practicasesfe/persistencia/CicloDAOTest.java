package com.practicasesfe.persistencia;

import com.practicasesfe.dominio.Ciclo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class CicloDAOTest {
    private CicloDAO cicloDAO;

    @BeforeEach
    void setUp() {
        cicloDAO = new CicloDAO();
    }

    private Ciclo create(Ciclo ciclo) throws SQLException {
        Ciclo res = cicloDAO.create(ciclo);
        assertNotNull(res, "El ciclo creado no debería ser nulo.");
        assertEquals(ciclo.getNombreCiclo(), res.getNombreCiclo(), "El nombre del ciclo debe coincidir.");
        assertEquals(ciclo.getFechaInicio(), res.getFechaInicio(), "La fecha de inicio debe coincidir.");
        assertEquals(ciclo.getFechaFin(), res.getFechaFin(), "La fecha de fin debe coincidir.");
        return res;
    }

    private void update(Ciclo ciclo) throws SQLException {
        // Aumentar días a las fechas usando LocalDate
        LocalDate fechaInicio = ciclo.getFechaInicio().toLocalDate().plusDays(1);
        LocalDate fechaFin = ciclo.getFechaFin().toLocalDate().plusDays(1);

        // Actualizar valores
        ciclo.setNombreCiclo(ciclo.getNombreCiclo() + "Actualizado");
        ciclo.setFechaInicio(Date.valueOf(fechaInicio));
        ciclo.setFechaFin(Date.valueOf(fechaFin));

        // Ejecutar actualización en la base de datos
        boolean res = cicloDAO.update(ciclo);
        assertTrue(res, "La actualización del ciclo debería ser exitosa.");

        // Validar que se actualizó correctamente
        getById(ciclo);
    }

    private void getById(Ciclo ciclo) throws SQLException {
        Ciclo res = cicloDAO.getById(ciclo.getIdCiclo());
        assertNotNull(res, "El ciclo obtenido no debería ser nulo.");
        assertEquals(ciclo.getIdCiclo(), res.getIdCiclo(), "El ID del ciclo debe coincidir.");
        assertEquals(ciclo.getNombreCiclo(), res.getNombreCiclo(), "El nombre del ciclo debe coincidir.");
        assertEquals(ciclo.getFechaInicio(), res.getFechaInicio(), "La fecha de inicio debe coincidir.");
        assertEquals(ciclo.getFechaFin(), res.getFechaFin(), "La fecha de fin debe coincidir.");
    }

    private void search(Ciclo ciclo) throws SQLException {
        ArrayList<Ciclo> ciclos = cicloDAO.search(ciclo.getNombreCiclo());
        assertFalse(ciclos.isEmpty(), "La búsqueda debería devolver al menos un ciclo.");
        boolean encontrado = ciclos.stream()
                .allMatch(c -> c.getNombreCiclo().contains(ciclo.getNombreCiclo()));
        assertTrue(encontrado, "El nombre buscado no fue encontrado: " + ciclo.getNombreCiclo());
    }

    private void delete(Ciclo ciclo) throws SQLException {
        boolean res = cicloDAO.delete(ciclo);
        assertTrue(res, "La eliminación del ciclo debería ser exitosa.");
        Ciclo res2 = cicloDAO.getById(ciclo.getIdCiclo());
        assertNull(res2, "El ciclo debería haber sido eliminado y no encontrado por ID.");
    }

    @Test
    void testCicloDAO() throws SQLException {
        Random random = new Random();
        int num = random.nextInt(1000) + 1;
        String nombre = "Ciclo Test " + num;

        Ciclo ciclo = new Ciclo(
                0,
                nombre,
                Date.valueOf(LocalDate.of(2025, 1, 10)),
                Date.valueOf(LocalDate.of(2025, 6, 10))
        );

        Ciclo testCiclo = create(ciclo);
        update(testCiclo);
        search(testCiclo);
        delete(testCiclo);
    }

    @Test
    void createCiclo() throws SQLException {
        Ciclo ciclo = new Ciclo(
                0,
                "Ciclo Especial",
                Date.valueOf(LocalDate.of(2025, 7, 1)),
                Date.valueOf(LocalDate.of(2025, 12, 1))
        );
        Ciclo res = cicloDAO.create(ciclo);
        assertNotNull(res);
    }

}