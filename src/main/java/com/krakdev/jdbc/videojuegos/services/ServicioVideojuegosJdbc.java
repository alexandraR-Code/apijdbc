package com.krakdev.jdbc.videojuegos.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.krakdev.jdbc.videojuegos.VideojuegoJdbc;
import com.krakdev.videojuegos.entidades.Videojuego;

@Service
public class ServicioVideojuegosJdbc {

	private static final Logger log = LogManager.getLogger(ServicioVideojuegosJdbc.class);

	public Videojuego crear(Videojuego videojuego) {
		log.info("Servicio crear");
		return VideojuegoJdbc.insertar(videojuego);

	}

	public List<Videojuego> listar() {
		log.info("Servicio listar");
		return VideojuegoJdbc.listar();
	}

	public Videojuego buscarPorCodigo(String codigo) {
		log.info("SErvicio buscar por juego");
		return VideojuegoJdbc.buscar(codigo);
	}

	public Videojuego actualizar(String codigo, Videojuego videojuego) {
		log.info("SErvicio actualizar");
		return VideojuegoJdbc.actualizar(codigo, videojuego);
	}

	public Boolean eliminar(String codigo) {
		log.info("Servicio eliminado");
		return VideojuegoJdbc.eliminar(codigo);
	}
}
