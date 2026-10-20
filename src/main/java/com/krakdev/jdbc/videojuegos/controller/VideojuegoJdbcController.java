package com.krakdev.jdbc.videojuegos.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping
	public ResponseEntity<List<Videojuego>> listar() {
		List<Videojuego> videojuegos = servicioVideojuegosJdbc.listar();

		if (videojuegos.size() > 0) {
			return new ResponseEntity<List<Videojuego>>(videojuegos, HttpStatus.OK);
		}
		return new ResponseEntity<List<Videojuego>>(videojuegos, HttpStatus.NOT_FOUND);

	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Videojuego> buscarPorCodigo(@PathVariable String codigo) {
		Videojuego encontrado = servicioVideojuegosJdbc.buscarPorCodigo(codigo);

		if (encontrado != null) {
			return new ResponseEntity<Videojuego>(encontrado, HttpStatus.OK);
		}
		return new ResponseEntity<Videojuego>(encontrado, HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Videojuego> actualizar(@PathVariable String codigo, @RequestBody Videojuego videojuego) {
		Videojuego actualizado = servicioVideojuegosJdbc.actualizar(codigo, videojuego);

		HttpStatusCode code = HttpStatus.OK;

		if (actualizado != null) {
			code = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<Videojuego>(actualizado, code);
	}

	@DeleteMapping("/{codigo}")
	public ResponseEntity<String> eliminar(@PathVariable String codigo) {
		boolean eliminado = servicioVideojuegosJdbc.eliminar(codigo);

		HttpStatusCode code = HttpStatus.NO_CONTENT;

		if (eliminado) {
			code = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<String>("", code);

	}

}
