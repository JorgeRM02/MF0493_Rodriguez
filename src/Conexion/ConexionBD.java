package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Clase que permite la conexion con la base de datos. 
// Registrando un driver de tipo MariaDB

public class ConexionBD {
	
	// Propiedades de la conexion
	// Cambiar el valor entre comillas para conectar a otra base de datos (tiene que estar ya creada)
	private static String database = "biblioteca";
	private static String usuario = "root";
	private static String contrasena = "";
	private static String url = "jdbc:mariadb://localhost/"+ database;
	
	// Objeto Conection que debemosnusar en JDBC
	private Connection conexion=null;
	
	public Connection getConnection() {
		if (this.conexion!=null) {
			// Si ya hay conexion, la devuelvo
			return this.conexion;
		} 
			// Si no esta creada, hacemos la conexion a la base de datos
			
			try {
			// Registrar el driver. Previamente habrá que haber añadido el driver al proyecto (Build Path)
				Class.forName("org.mariadb.jdbc.Driver");
				
				// Obtenemos el objeto Connection de la clase Driver Manager. Lanza exception si no se puede conectar
				this.conexion = DriverManager.getConnection(url,usuario,contrasena);
				
				System.out.println("Conexion a base de datos correcta");
			} catch (ClassNotFoundException e) {
				System.out.println("Error al registrar el driver");
				
				
			} catch (SQLException e) {
				System.out.println("No se puede conectar con la base de datos."+e.getLocalizedMessage());
			}
			return this.conexion;
		
	}
	
	//Metodo de la clase que libera los recursos asociados a la conexion
	public void desconectar() {
		if (this.conexion!=null) {
			try {
				this.conexion.close();
			} catch (SQLException e) {
				System.out.println("Error, no se puede liberar la conexion");
			}
		}
	}
}
