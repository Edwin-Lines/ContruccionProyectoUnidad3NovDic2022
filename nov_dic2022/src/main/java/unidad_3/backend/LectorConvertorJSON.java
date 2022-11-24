package unidad_3.backend;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import unidad_3.backend.Exceptions.ArregloInicialException;
import unidad_3.backend.Exceptions.EstructuraException;
import unidad_3.backend.Exceptions.LlaveSinValorException;

public class LectorConvertorJSON {

    public ArrayList<Empleado> lecturaArchivo(String nombreArchivo, String directorio)
            throws EstructuraException, IOException, FileNotFoundException, org.json.simple.parser.ParseException,
            LlaveSinValorException {
        String path = directorio + "/" + nombreArchivo;
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(path);
        Object obj = jsonParser.parse(reader);
        JSONObject employeeList = (JSONObject) obj;
        JSONArray employeeData = (JSONArray) employeeList.get("employees");

        if (employeeData == null) {
            throw new ArregloInicialException("employees");
        }

        ArrayList<Empleado> empleados = new ArrayList<>();

        for (int i = 0; i < employeeData.size(); i++) {
            JSONObject tempEmployee = (JSONObject) employeeData.get(i);
            ValidadorArchivos.validarEstructura(tempEmployee);
            JSONObject employeeObject = (JSONObject) tempEmployee.get("employee");

            if (employeeObject == null) {
                throw new ArregloInicialException("employee");
            }

            Empleado tempEmpleado = this.crearEmpleado(employeeObject);
            empleados.add(tempEmpleado);
        }

        return empleados;

    }

    public Empleado crearEmpleado(JSONObject jsonInfo) throws LlaveSinValorException {

        String id = (String) jsonInfo.get("id");
        String firstName = (String) jsonInfo.get("firstName");
        String lastName = (String) jsonInfo.get("lastName");
        String photo = (String) jsonInfo.get("photo");

        if (id.equals("")) {
            throw new LlaveSinValorException("id");
        }
        if (firstName.equals("")) {
            throw new LlaveSinValorException("firstName");
        }
        if (lastName.equals("")) {
            throw new LlaveSinValorException("lastName");
        }
        if (photo.equals("")) {
            throw new LlaveSinValorException("photo");
        }

        return new Empleado(id, firstName, lastName, photo);
    }

}
