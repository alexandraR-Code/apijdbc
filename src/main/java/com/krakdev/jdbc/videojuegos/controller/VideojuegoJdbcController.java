package com.krakdev.jdbc.videojuegos.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakdev.jdbc.videojuegos.services.ServicioVideojuegosJdbc;
import com.krakdev.videojuegos.entidades.Videojuego;

@RestController
@RequestMapping("/jdbc/videojuegos")
public class VideojuegoJdbcController {
	private static final Logger Log = LogManager.getLogger(VideojuegoJdbcController.class);

	private final ServicioVideojuegosJdbc servicioVideojuegosJdbc;

	public VideojuegoJdbcController(ServicioVideojuegosJdbc servicioVideojuegosJdbc) {
		Log.info("init " + VideojuegoJdbcController.class);

		this.servicioVideojuegosJdbc = servicioVideojuegosJdbc;
	}

	@PostMapping
	public ResponseEntity<Videojuego> crear(@RequestBody Videojuego videojuego) {
		Videojuego creado = servicioVideojuegosJdbc.crear(videojuego);
		if (creado != null) {
			return new ResponseEntity<Videojuego>(creado, HttpStatus.CREATED);
		}
		return new ResponseEntity<Videojuego>(creado, HttpStatus.BAD_REQUEST);
	}

}
