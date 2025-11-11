package UTN.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    // Método que establece y devuelve la conexión con la base de datos
    public static Connection getConnection() {
        Connection conexion = null;

        // Parámetros de conexión a la base de datos
        var baseDatos = "Practica"; // Nombre de la base de datos
        var url = "jdbc:mysql://127.0.0.1:3306/" + baseDatos; // URL del servidor y base de datos
        var usuario = "root"; // Usuario de MySQL (modificar según tu configuración)
        var password = ""; // Contraseña del usuario (dejar vacío si no tiene)

        // Intentamos cargar el driver de MySQL y establecer la conexión
        try {
            // Cargar el controlador JDBC de MySQL en memoria
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión con los parámetros definidos
            conexion = DriverManager.getConnection(url, usuario, password);

        } catch (ClassNotFoundException | SQLException e) {
            // Captura de errores si el driver no se encuentra o falla la conexión
            System.out.println("Ocurrió un error al conectar con la base de datos: " + e.getMessage());
        }

        // Devuelve el objeto Connection (puede ser null si no se logró conectar)
        return conexion;
    } // Fin del método getConnection
}
