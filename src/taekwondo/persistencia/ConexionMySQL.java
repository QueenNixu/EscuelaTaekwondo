package taekwondo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
	
	private static final String URL = "jdbc:mysql://localhost:3306/escuela_taekwondo";
    private static final String USER = "root";
    private static final String PASSWORD = "4223";
    
    private static Connection conexion;

    static {
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción de alguna manera adecuada
        }
    }

    public static Connection obtenerConexion() {
        return conexion;
    }


}
