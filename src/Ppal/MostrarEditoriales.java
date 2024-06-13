package Ppal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Conexion.ConexionBD;

public class MostrarEditoriales {
	public static void main(String[] args) {
		ConexionBD conexion = new ConexionBD();
		// Paso 1. Obtener la conexion
		Connection con =conexion.getConnection();
		
		// Objetos necesarios para hacer una consulta
		Statement sentencia =null;
		ResultSet resultado = null;
		
		System.out.println("Conectando a la base de datos...");
		
		// Algún procesamiento con la base de datos..
		try {
			// Paso 2. Obtener el Statement
			sentencia = con.createStatement();
			
			// Paso 3. Ejecutar la sentencia
			resultado = sentencia.executeQuery("select * from editoriales"); //bien la consulta
			
			System.out.println("Año \t Codigo Editorial\t Nombre");
			// Paso 4. Recorrer el resultado
			while (resultado.next()) {
				// datos correctos y acordes a tabla
				int anio = resultado.getInt("anio");
				int codEditorial = resultado.getInt("codEditorial");
				String nombre = resultado.getString("nombre");
				
				System.out.println(anio + "\t\t"+ codEditorial + "\t\t"+ nombre);
			}
		} catch (SQLException e) {
			System.out.println("Error al consultar datos" + e.getMessage());
		}
		finally{
			try {
				resultado.close();
				sentencia.close();
			} catch (SQLException e) {
				// Error al liberar recursos
			}
		}
		
		// Liberamos la conexion  
		conexion.desconectar();
		
	}
}
