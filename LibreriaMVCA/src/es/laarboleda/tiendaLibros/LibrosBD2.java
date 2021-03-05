package es.laarboleda.tiendaLibros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class LibrosBD2 {
	
	public static ArrayList<Libro> libros=new ArrayList();

	public static void cargarDatos() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		// 1. Conectarse a la base de datos
		String url = "jdbc:mysql://localhost:3306/tiendalibros?serverTimezone=UTC";
		Properties props = new Properties();
		props.put("user", "root");
		props.put("password", "");
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, props);
			// 2. preparar la sentencia de SQL
			Statement st = con.createStatement();
			String consulta = "select * from libros";
			ResultSet res = st.executeQuery(consulta);
			// 3. Procesar las filas resultado de la consulta
			
			while (res.next()) {
				libros.add(new Libro(res.getString("titulo"),res.getString("autor"),res.getFloat("precio")));
				
			}
			// 4. Cerrar los objetos Statement y ResultSet
			res.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	} // fin del método

	/** Devuelve el número de libros */
	public static int tamanyo() {
		return libros.size();
	}

	/** Devuelve el título del libro idLibro */
	public static String getTitulo(int idLibro) {
		return libros.get(idLibro).getTitulo();
	}

	/** Devuelve el autor del libro idLibro */
	public static String getAutor(int idLibro) {
		return libros.get(idLibro).getAutor();
	}

	/** Devuelve el precio del libro idLibro */
	public static float getPrecio(int idLibro) {
		return libros.get(idLibro).getPrecio();
	}
}


