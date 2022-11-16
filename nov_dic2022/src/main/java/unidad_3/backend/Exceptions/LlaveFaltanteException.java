package unidad_3.backend.Exceptions;

public class LlaveFaltanteException extends EstructuraException {

    public LlaveFaltanteException(String llaveFaltante) {
        System.err.println("El archivo no contiene la llave " + llaveFaltante);
    }

}
