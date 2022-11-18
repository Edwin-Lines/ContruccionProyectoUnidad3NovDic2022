package unidad_3.backend;

import org.json.simple.JSONObject;
import unidad_3.backend.Exceptions.EstructuraException;
import unidad_3.backend.Exceptions.LlaveFaltanteException;

public class ValidadorArchivos {

    public ValidadorArchivos() {

    }

    public static void validarEstructura(JSONObject employee) throws EstructuraException {
        tieneLlavesvalidas(employee);
    }

    public static void tieneLlavesvalidas(JSONObject employee) throws LlaveFaltanteException {

        if (employee.get("employee") == null) {
            throw new LlaveFaltanteException("employee");
        }

        JSONObject employeeObject = (JSONObject) employee.get("employee");
        String error = "";

        if (employeeObject.get("id") == null) {
            error = "id";
        } else if (employeeObject.get("firstName") == null) {
            error = "firstName";
        } else if (employeeObject.get("lastName") == null) {
            error = "lastName";
        } else if (employeeObject.get("photo") == null) {
            error = "photo";
        }

        if (!error.equals("")) {
            throw new LlaveFaltanteException(error);
        }

    }

}