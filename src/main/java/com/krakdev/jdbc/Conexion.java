package com.krakdev.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Conexion {

	private static final Logger log = LogManager.getLogger(Conexion.class);

	private static final String URL = "jdbc:postgresql://localhost:5432/apijdbc";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Maribel1995.";

	public static Connection getConnection() {
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			log.info("conexion exitosa");
			return con;

		} catch (Exception e) {
			log.error("Error en la conexion" + e.getMessage());
			throw new RuntimeException("No se puede conectar", e);
		}
	}

}
