/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JGBasePrograms;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author manue
 */
public class connect {

    Connection SQLConexion;

    public connect() {
        String host = "localhost";
        String puerto = "3306";
        String nameDB = "jgbasepkmn";

        String usuario = "root";
        String password = "";

        String driver = "com.mysql.cj.jdbc.Driver";

        String DB_URL = "jdbc:mysql://" + host + ":" + puerto + "/" + nameDB + "?useSSL=false";

        try {
            Class.forName(driver);
            SQLConexion = DriverManager.getConnection(DB_URL, usuario, password);
            System.out.println("Base de datos conectada.");
        } catch (Exception ex) {
            System.out.println("No se pudo conectar a la base de datos.");
        }
    }

    public Connection getConectarDB() {
        return SQLConexion;
    }
    
}