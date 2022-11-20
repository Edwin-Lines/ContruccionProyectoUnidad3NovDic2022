package unidad_3.backend;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import unidad_3.backend.Exceptions.ArregloInicialException;
import unidad_3.backend.Exceptions.LlaveFaltanteException;

class ValidadorArchivosTest {
	LectorConvertorJSON lector = new LectorConvertorJSON();

	@Test
	@DisplayName("No debe continuarse la lectura si tiene caracteres,tokens erroneos en el archivo json o cuando está vacio")
	public void deberiaLanzarParseException() {
		Assertions.assertThrows(org.json.simple.parser.ParseException.class, () -> {
			lector.lecturaArchivo("erroresSintacticos.json", "files");
			lector.lecturaArchivo("vacio.json", "files");
		});
	}

	@Test
	@DisplayName("No debe continuarse la lectura si no existe el archivo json")
	public void deberiaLanzarFileNotFoundException() {
		Assertions.assertThrows(java.io.FileNotFoundException.class, () -> {
			lector.lecturaArchivo("inexistente.json", "files");
		});
	}

	@Test
	@DisplayName("No debe continuarse la lectura en caso de interrupciones I/O")
	public void deberiaLanzarIOException() {
		Assertions.assertThrows(IOException.class, () -> {
			lector.lecturaArchivo("inexistente.json", "files");
		});
	}

	@Test
	@DisplayName("No debe continuarse la lectura en caso de que no se tenga la informacion en un arreglo inicial")
	public void deberiaLanzarArregloInicialException() {
		Assertions.assertThrows(ArregloInicialException.class, () -> {
			lector.lecturaArchivo("arregloInicialFaltante.json", "files");
		});
	}

	@Test
	@DisplayName("No debe continuarse la lectura en caso de que falte alguna de las llaves iniciales")
	public void deberiaLanzarLlaveFaltanteException() {
		Assertions.assertThrows(LlaveFaltanteException.class, () -> {
			lector.lecturaArchivo("llaveFaltante.json", "files");
		});
	}

}
