package com.practicasesfe.persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.practicasesfe.dominio.Ciclo;

public class CicloDAO {

    // Obtener todos los ciclos
    public List<Ciclo> obtenerTodos() {
        List<Ciclo> lista = new ArrayList<>();
        String sql = "SELECT * FROM ciclos";

        try (Connection conn = ConnectionManager.getInstance().connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Ciclo(
                        rs.getInt("id_ciclo"),
                        rs.getString("nombre_ciclo"),
                        rs.getDate("fecha_inicio"),
                        rs.getDate("fecha_fin")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Insertar nuevo ciclo
    public boolean insertar(Ciclo ciclo) {
        String sql = "INSERT INTO ciclos (nombre_ciclo, fecha_inicio, fecha_fin) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionManager.getInstance().connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ciclo.getNombreCiclo());
            ps.setDate(2, ciclo.getFechaInicio());
            ps.setDate(3, ciclo.getFechaFin());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Actualizar ciclo existente
    public boolean actualizar(Ciclo ciclo) {
        String sql = "UPDATE ciclos SET nombre_ciclo = ?, fecha_inicio = ?, fecha_fin = ? WHERE id_ciclo = ?";

        try (Connection conn = ConnectionManager.getInstance().connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ciclo.getNombreCiclo());
            ps.setDate(2, ciclo.getFechaInicio());
            ps.setDate(3, ciclo.getFechaFin());
            ps.setInt(4, ciclo.getIdCiclo());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar ciclo por ID
    public boolean eliminar(int idCiclo) {
        String sql = "DELETE FROM ciclos WHERE id_ciclo = ?";

        try (Connection conn = ConnectionManager.getInstance().connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCiclo);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
