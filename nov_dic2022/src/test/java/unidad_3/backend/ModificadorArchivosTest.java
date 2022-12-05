package unidad_3.backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import unidad_3.backend.Exceptions.RegistroExistente;

class ModificadorArchivosTest {
	LectorConvertorJSON lector = new LectorConvertorJSON();
	ModificadorArchivosJSON mod = new ModificadorArchivosJSON("files/pruebasModificacion.json");

	@Test
	@DisplayName("No se realiza una modificacion al documento JSON en caso de que el empleado no se encuentre en el archivo JSON")
	public void noDeberiaRealizarModificacion() {
		Empleado modEmp = new Empleado("1", "No", "existe", "https://jsonformatter.org/img/tom-cruise.jpg");
		Assertions.assertFalse(mod.modificarRegistro(modEmp));
	}

	@Test
	@DisplayName("No se realiza una eliminar un registro en el documento JSON en caso de que el empleado no se encuentre en el archivo JSON")
	public void noSedeberiaEliminar() {
		Empleado modEmp = new Empleado("1", "No", "existe", "https://jsonformatter.org/img/tom-cruise.jpg");
		Assertions.assertFalse(mod.borrarRegistro(modEmp));
	}

	@Test
	@DisplayName("No se crea un nuevo registro en el documento JSON en caso de que el empleado ya exista en el archivo JSON")
	public void noSedeberiaCrear() {
		Empleado modEmp = new Empleado("2", "Tom", "Cruise",
				"https: //static.independent.co.uk/s3fs-public/thumbnails/image/2014/08/11/12/Michael-Jackson-6.jpg?quality=75&width=1200&auto=webp");

		Assertions.assertThrows(RegistroExistente.class, () -> {
			mod.agregarRegistro(modEmp);
		});
	}

}
