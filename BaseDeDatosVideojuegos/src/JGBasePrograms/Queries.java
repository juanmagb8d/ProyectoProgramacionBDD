/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JGBasePrograms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author manue
 */
public class Queries {

    connect con = new connect();
    Connection conexion = con.getConectarDB();

    public boolean verificarCredenciales(String nombreUsuario, String contrasena) {
        String sql = "SELECT * FROM usuarios WHERE nombre = ? AND contraseña = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, nombreUsuario);
            statement.setString(2, contrasena);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Devuelve true si hay coincidencia de credenciales, false de lo contrario
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // En caso de error, se considera acceso inválido
    }

    public void MostrarResultadosTabla(DefaultTableModel model, String condLegendario, String condGen, String condTipo1, String condTipo2, String condNombre) {
        if (condLegendario == null) {
            condLegendario = " WHERE legendario IS NOT NULL";
        }
        if (condGen == null) {
            condGen = " AND gen IS NOT NULL";
        }
        if (condTipo1 == null) {
            condTipo1 = " AND Tipo1 IS NOT NULL";
        }
        if (condTipo2 == null) {
            condTipo2 = "";
        }
        if (condNombre == null) {
            condNombre = "";
        }
        String sql = "SELECT * FROM pokemon" + condLegendario + condGen + condTipo1 + condTipo2 + condNombre + "";
        Statement st;
        String[] Datos = new String[6];
        try {
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Datos[0] = rs.getString(1);
                Datos[1] = rs.getString(2);
                Datos[2] = rs.getString(3);
                Datos[3] = rs.getString(4);
                if (rs.getString(5).equals("1")) {
                    Datos[4] = "Sí";
                } else {
                    Datos[4] = "No";
                }
                Datos[5] = rs.getString(6);

                model.addRow(Datos);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la consulta: " + sql + " // " + e.toString());
        }
    }

    public void AgregarPKMN(int ID, String Nombre, String Tipo1, String Tipo2, boolean Legendario, int Gen) {
        PreparedStatement ps;
        String sql = "INSERT INTO pokemon (ID, Nombre, Tipo1, Tipo2, Legendario, Gen) VALUES (?, ?, ?, ?, ?, ?);";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, ID); // ID de la Pokédex
            ps.setString(2, Nombre); // Tipo 1
            ps.setString(3, Tipo1); // Tipo 2
            ps.setString(4, Tipo2);
            ps.setBoolean(5, Legendario);
            ps.setInt(6, Gen);// Generación
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Has agregado exitosamente al Pokémon.");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la consulta: " + sql + " // " + e.toString());
        }
    }
    public int getValorID(){
        String sql = "SELECT max(id) FROM pokemon";
        Statement st;
        int datosID=0;
        try {
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
             while (rs.next()) {
                datosID = rs.getInt(1);               
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la consulta: " + sql + " // " + e.toString());
        }
        return datosID+1;
    }
    public void Registrarse(String usuario, String contrasena){
        PreparedStatement ps;
        String sql = "INSERT INTO usuarios (nombre, contraseña) VALUES (?, ?);";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, usuario); // ID de la Pokédex
            ps.setString(2, contrasena); // Tipo 1
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Te has registrado exitosamente.");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la consulta: " + sql + " // " + e.toString());
        }
    }

}
