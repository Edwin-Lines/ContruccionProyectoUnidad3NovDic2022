package unidad_3.backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import unidad_3.backend.Exceptions.LlaveSinValorException;

public class LectorConvertorJSONTest {
    LectorConvertorJSON lector = new LectorConvertorJSON();

    @Test
    @DisplayName("Si un atributo dentro del archivo no tiene valor, se notifica al usuario al crearse un empleado")
    public void atributoSinValor() {
        Assertions.assertThrows(LlaveSinValorException.class, () -> {
            lector.lecturaArchivo("llaveSinValor.json", "files");
        });

    }

}
