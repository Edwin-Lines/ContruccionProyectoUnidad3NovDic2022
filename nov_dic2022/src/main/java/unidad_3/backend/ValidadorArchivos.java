package unidad_3.backend;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import unidad_3.backend.Exceptions.ArregloInicialException;
import unidad_3.backend.Exceptions.EstructuraException;
import unidad_3.backend.Exceptions.LlaveFaltanteException;

public class ValidadorArchivos {

    public ValidadorArchivos() {

    }

    public String lecturaArchivo(String nombreArchivo, String directorio)
            throws EstructuraException, IOException, FileNotFoundException, org.json.simple.parser.ParseException {
        String path = directorio + "/" + nombreArchivo;

        JSONParser jsonParser = new JSONParser();

        FileReader reader = new FileReader(path);
        Object obj = jsonParser.parse(reader);

        JSONObject employeeList = (JSONObject) obj;

        JSONArray employeeData = (JSONArray) employeeList.get("employees");
        if (employeeData == null) {
            throw new ArregloInicialException();
        }

        for (int i = 0; i < employeeData.size(); i++) {
            JSONObject tempEmployee = (JSONObject) employeeData.get(i);
            this.validarEstructura(tempEmployee);

            System.out.println(tempEmployee.toString());
        }
        return "El archivo " + nombreArchivo + " presenta una estructura correcta";

    }

    public void validarEstructura(JSONObject employee) throws EstructuraException {
        this.tieneLlavesvalidas(employee);
    }

    public void tieneLlavesvalidas(JSONObject employee) throws LlaveFaltanteException {

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