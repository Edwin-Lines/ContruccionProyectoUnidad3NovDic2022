package unidad_3.backend;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import unidad_3.backend.Exceptions.ArregloInicialException;
import unidad_3.backend.Exceptions.EstructuraException;
import unidad_3.backend.Exceptions.LlaveFaltanteException;

class ValidadorArchivosTest {
	ValidadorArchivos validador = new ValidadorArchivos();

	@Test
	@DisplayName("No debe continuarse la lectura si tiene caracteres,tokens erroneos en el archivo json o cuando está vacio")
	public void deberiaLanzarParseException() {
		Assertions.assertThrows(org.json.simple.parser.ParseException.class, () -> {
			validador.lecturaArchivo("vacio.json", "files");
		});
	}

	@Test
	@DisplayName("No debe continuarse la lectura si no existe el archivo json")
	public void deberiaLanzarFileNotFoundException() {
		Assertions.assertThrows(java.io.FileNotFoundException.class, () -> {
			validador.lecturaArchivo("inexistente.json", "files");
		});
	}

	@Test
	@DisplayName("No debe continuarse la lectura en caso de interrupciones I/O")
	public void deberiaLanzarIOException() {
		Assertions.assertThrows(IOException.class, () -> {
			validador.lecturaArchivo("inexistente.json", "files");
		});
	}

	@Test
	@DisplayName("No debe continuarse la lectura en caso de que no se tenga la informacion en un arreglo inicial")
	public void deberiaLanzarArregloInicialException() {
		Assertions.assertThrows(ArregloInicialException.class, () -> {
			validador.lecturaArchivo("arregloInicialFaltante.json", "files");
		});
	}

	@Test
	@DisplayName("No debe continuarse la lectura en caso de que falte alguna de las llaves iniciales")
	public void deberiaLanzarLlaveFaltanteException() {
		Assertions.assertThrows(LlaveFaltanteException.class, () -> {
			validador.lecturaArchivo("llaveFaltante.json", "files");
		});
	}

	@Test
	@DisplayName("Debe visualizarse la informacion en caso de que el archivo json tenga la estructura correcta")
	public void mostrarInformacionArchivoJSON()
			throws FileNotFoundException, EstructuraException, IOException,
			ParseException {
		String correcto = "El archivo estructuraCorrecta.json presenta una estructura correcta";

		Assertions.assertEquals(correcto,
				validador.lecturaArchivo("estructuraCorrecta.json", "files"));
	}

}
