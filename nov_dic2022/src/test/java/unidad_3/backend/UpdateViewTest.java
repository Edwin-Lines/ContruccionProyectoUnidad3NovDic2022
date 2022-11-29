package unidad_3.backend;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import unidad_3.backend.Exceptions.EstructuraException;
import unidad_3.backend.Exceptions.LlaveSinValorException;

public class UpdateViewTest {


   @Test
	@DisplayName("No debe continuarse si el ID no existe")
   public void validarModificacion() {
      ModificadorArchivosJSON modificador= new ModificadorArchivosJSON("nov_dic2022/files/estructuraCorrecta.json");
      Empleado empleado= new Empleado("5","Maikol","Jackson","https://static.independent.co.uk/s3fs-public/thumbnails/image/2014/08/11/12/Michael-Jackson-6.jpg?quality=75&width=1200&auto=webp");
      boolean verificador=modificador.modificarRegistro(empleado); 
      Assertions.assertFalse(verificador);

   }

}
