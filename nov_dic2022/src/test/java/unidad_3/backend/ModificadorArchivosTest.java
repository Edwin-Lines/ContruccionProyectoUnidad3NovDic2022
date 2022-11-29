package unidad_3.backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ModificadorArchivosTest {
	LectorConvertorJSON lector = new LectorConvertorJSON();
	ModificadorArchivosJSON mod = new ModificadorArchivosJSON("files/pruebasModificacion.json");

	@Test
	@DisplayName("No se realiza una modificacion al documento JSON en caso de que el empleado no se encuentre en el archivo JSON")
	public void noDeberiaRealizarModificacion() {
		Empleado modEmp = new Empleado("1", "No", "existe", "https://jsonformatter.org/img/tom-cruise.jpg");
		Assertions.assertFalse(mod.modificarRegistro(modEmp));
	}

}
