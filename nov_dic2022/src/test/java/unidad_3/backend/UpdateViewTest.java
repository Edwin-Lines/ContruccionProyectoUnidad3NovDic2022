package unidad_3.backend;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import unidad_3.backend.Exceptions.LlaveSinValorException;

public class UpdateViewTest {

   @Test
   @DisplayName("Se verifica que ocurra un cambio en un registro dentro del archivo JSON")
   public void validarModificacion() throws LlaveSinValorException, IOException, ParseException {
      String path = "files/pruebasModificacion.json";
      LectorConvertorJSON lector = new LectorConvertorJSON();

      ModificadorArchivosJSON modificador = new ModificadorArchivosJSON(path);
      Empleado antes = new Empleado("2", "Tom", "Cruise",
            "https://static.independent.co.uk/s3fs-public/thumbnails/image/2014/08/11/12/Michael-Jackson-6.jpg?quality=75&width=1200&auto=webp");
      Empleado despues = new Empleado(antes.getId(), antes.getFirstName(), antes.getLastName(), antes.getPhoto());

      despues.setFirstName("Emilio");
      despues.setLastName("Mex");
      despues.setPhoto("URL");

      int existe = modificador.buscarEmpleado(antes);

      if (existe != -1) {
         modificador.modificarRegistro(despues);
      }

      JSONParser jsonParser = new JSONParser();
      FileReader reader = new FileReader(path);
      Object obj = jsonParser.parse(reader);
      JSONObject employeeList = (JSONObject) obj;
      JSONArray employeeData = (JSONArray) employeeList.get("employees");

      JSONObject tempEmployeeObject = (JSONObject) employeeData.get(existe);

      JSONObject employeeObject = (JSONObject) tempEmployeeObject.get("employee");

      Empleado actual = lector.crearEmpleado(employeeObject);

      Assertions.assertEquals("Emilio", actual.getFirstName());

   }

   @Test
   @DisplayName("Se verifica que ocurra el borrado de un registro dentro del archivo JSON")
   public void validarEliminacion() throws LlaveSinValorException, IOException, ParseException {
      String path = "files/pruebasModificacion.json";
      LectorConvertorJSON lector = new LectorConvertorJSON();

      ModificadorArchivosJSON modificador = new ModificadorArchivosJSON(path);
      Empleado aBorrar = new Empleado("2", "Tom", "Cruise",
            "https://static.independent.co.uk/s3fs-public/thumbnails/image/2014/08/11/12/Michael-Jackson-6.jpg?quality=75&width=1200&auto=webp");

      int existe = modificador.buscarEmpleado(aBorrar);

      if (existe != -1) {
         modificador.borrarRegistro(aBorrar);
      }

      Assertions.assertEquals(-1, modificador.buscarEmpleado(aBorrar));

   }

}
