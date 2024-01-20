package taekwondo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    
    private static final String URL = "jdbc:mysql://localhost:3306/escuela_taekwondo";
    private static final String USER = "root";
    private static final String PASSWORD = "4223";
    
    private static Connection conexion;

    public static Connection obtenerConexion() {
        try {
            // Verifica si la conexión existente es válida antes de devolverla
            if (conexion != null && !conexion.isClosed() && conexion.isValid(1)) {
                return conexion;
            }
            
            // Si hay una conexión existente, pero está cerrada o no es válida, ciérrala
            if (conexion != null) {
                conexion.close();
                conexion = null;
            }

            // Crea una nueva conexión
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            return conexion;
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción de alguna manera adecuada
            return null;
        }
    }



}
