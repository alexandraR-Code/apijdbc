package com.krakdev.jdbc.videojuegos.services;

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
}
