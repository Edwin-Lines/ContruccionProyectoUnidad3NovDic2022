package unidad_3.backend.Exceptions;

public class RegistroExistente extends Exception {
    public RegistroExistente(String id) {
        System.err.println("No se puede crear un Nuevo Registro con un ID ya existente (El ID: " + id
                + " ya es utilizado en el archivo JSON)");
    }

}
