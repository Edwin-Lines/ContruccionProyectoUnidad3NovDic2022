package unidad_3.backend.Exceptions;

public class LlaveSinValorException extends Exception {
    public LlaveSinValorException(String llave) {
        System.err.println("La llave " + llave + " no tiene valor dentro del archivo JSON (\"\")");
    }
}
