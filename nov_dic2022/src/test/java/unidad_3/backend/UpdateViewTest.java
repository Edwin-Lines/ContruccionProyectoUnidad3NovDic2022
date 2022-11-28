package unidad_3.backend;



import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UpdateViewTest {
   ArrayList<Empleado> listEmpleado= new ArrayList();

   @Test
	@DisplayName("No debe continuarse el set con id que no existe")
   public void name() {
      Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            
            Empleado empleado= new Empleado("0","Rafael","Perea","onlyfans...");
            listEmpleado.add(empleado);
            Empleado empleado2= new Empleado("2","michael","jackson","www.google.com");
            listEmpleado.set(5, empleado2);
        });
   }
}
