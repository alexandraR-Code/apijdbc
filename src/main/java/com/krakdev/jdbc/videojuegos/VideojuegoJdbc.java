package com.krakdev.jdbc.videojuegos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.krakdev.jdbc.Conexion;
import com.krakdev.videojuegos.entidades.Videojuego;

public class VideojuegoJdbc {
	private static final Logger log = LogManager.getLogger(VideojuegoJdbc.class);

	// INSERTAR
	public static Videojuego insertar(Videojuego videoJuego) {
		Videojuego videojuegoCreado = null;

		Connection con = null;
		PreparedStatement ps = null;

		String sql = """
				INSERT INTO videojuegos
				(codigo, nombre, plataforma, precio, disponible, genero)
				VALUES
				(?, ?, ?, ?, ?, ?);
								""";

		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, videoJuego.getCodigo());
			ps.setString(2, videoJuego.getNombre());
			ps.setString(3, videoJuego.getPlataforma());
			ps.setDouble(4, videoJuego.getPrecio());
			ps.setBoolean(5, videoJuego.isDisponible());
			ps.setString(6, videoJuego.getGenero());

			int filas = ps.executeUpdate();

			videojuegoCreado = videoJuego;
			log.info("Filas creadas: " + filas);
		} catch (Exception e) {
			log.error("Ocurrio un error al insertar" + e.getMessage());
		} finally {
			try {
				con.close();
				ps.close();
				log.info("Conexiones creadas");
			} catch (SQLException e) {
				log.error("Ocurrrio un error al cerrar conexiones" + e.getMessage());
			}

		}
		return videojuegoCreado;

	}

	// LISTAR
	public static List<Videojuego> listar() {
		List<Videojuego> videojuegos = new ArrayList<Videojuego>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = """
				SELECT codigo, nombre, plataforma, precio, disponible, genero FROM videojuegos;
				""";

		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Videojuego videojuego = new Videojuego();

				videojuego.setCodigo(rs.getString(1));
				videojuego.setNombre(rs.getString(2));
				videojuego.setPlataforma(rs.getString(3));
				videojuego.setPrecio(rs.getDouble(4));
				videojuego.setDisponible(rs.getBoolean(5));
				videojuego.setGenero(rs.getString(6));

				videojuegos.add(videojuego);

			}
			log.info("Filas encontradas: " + videojuegos.size());

		} catch (Exception e) {
			log.error("Ocurrio un error al listar: " + e.getMessage());

		} finally {
			Conexion.cerrarConexiones(con, ps, rs);
		}
		return videojuegos;

	}

	// buscar

	public static Videojuego buscar(String codigo) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = """
				SELECT codigo, nombre, plataforma, precio, disponible, genero FROM videojuegos WHERE codigo = ?;
				""";

		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, codigo);
			rs = ps.executeQuery();
			if (rs.next()) {
				Videojuego videojuego = new Videojuego();

				videojuego.setCodigo(rs.getString(1));
				videojuego.setNombre(rs.getString(2));
				videojuego.setPlataforma(rs.getString(3));
				videojuego.setPrecio(rs.getDouble(4));
				videojuego.setDisponible(rs.getBoolean(5));
				videojuego.setGenero(rs.getString(6));

				log.info("Video juego encontrado");
				return videojuego;

			}
		} catch (Exception e) {
			log.error("Ocurrio un error al buscar el codigo " + e.getMessage());

		} finally {
			Conexion.cerrarConexiones(con, ps, rs);
		}
		return null;
	}

	// ACTUALIZAR

	public static Videojuego actualizar(Videojuego videojuego) {
		Connection con = null;
		PreparedStatement ps = null;

		String sql = """
				UPDATE videojuegos SET nombre = ?,
				plataforma = ?,
				precio = ?,
				disponible = ?,
				genero = '' WHERE codigo = ?;
				""";

		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, videojuego.getNombre());
			ps.setString(2, videojuego.getPlataforma());
			ps.setDouble(3, videojuego.getPrecio());
			ps.setBoolean(4, videojuego.isDisponible());
			ps.setString(5, videojuego.getGenero());

			// condicion

			ps.setString(6, videojuego.getCodigo());
			int filas = ps.executeUpdate();
			if (filas > 0) {
				log.info("Videojuego actualizado con exito");
				return buscar(videojuego.getCodigo());

			}
		} catch (Exception e) {
			log.error("Ocurrio un error el actualizar videojuego: " + e.getMessage());
		} finally {
			Conexion.cerrarConexiones(con, ps, null);
		}
		return null;
	}

	// ELIMINAR

	public static boolean eliminar(String codigo) {
		Connection con = null;
		PreparedStatement ps = null;

		String sql = """
				DELETE videojuegos
				WHERE codigo = ?;

				""";
		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, codigo);

			int filas = ps.executeUpdate();
			log.info("Filas eliminadas" + filas);
			if (filas > 0) {

				return true;
			}
		} catch (Exception e) {
			log.error("Ocurrio un error al eliminar videojuego" + e.getMessage());
		} finally {
			Conexion.cerrarConexiones(con, ps, null);
		}

		return false;

	}

}
