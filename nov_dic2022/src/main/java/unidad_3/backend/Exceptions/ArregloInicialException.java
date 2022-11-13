package unidad_3.backend.Exceptions;

public class ArregloInicialException extends EstructuraException {
    public ArregloInicialException() {
        System.err.println("El archivo no contiene el arreglo inicial de empleados (llave \"employees\")");
    }

}
