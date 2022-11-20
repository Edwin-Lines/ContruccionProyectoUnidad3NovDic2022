package unidad_3.backend.Exceptions;

public class ArregloInicialException extends EstructuraException {
    public ArregloInicialException(String arreglo) {
        System.err.println("El archivo no contiene el arreglo inicial (llave " + arreglo + ")");
    }

}
