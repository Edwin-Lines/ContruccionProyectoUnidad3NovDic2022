package unidad_3.backend;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

import unidad_3.backend.Exceptions.LlaveSinValorException;

public class ModificadorArchivosJSON {
    private String documento;

    public ModificadorArchivosJSON(String documento) {
        this.documento = documento;
    }

    private void crearArchivoJSON(JSONObject finalJSON) {
        try (PrintWriter file = new PrintWriter(this.documento)) {
            ObjectMapper mapper = new ObjectMapper();
            String betterFormat = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(mapper.readTree(finalJSON.toJSONString()));
            file.write(betterFormat);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int buscarEmpleado(Empleado empleado) {
        JSONParser jsonParser = new JSONParser();
        try {

            FileReader reader = new FileReader(this.documento);
            Object obj = jsonParser.parse(reader);
            LectorConvertorJSON convertorJSON = new LectorConvertorJSON();

            JSONObject employeeList = (JSONObject) obj;
            JSONArray employeeData = (JSONArray) employeeList.get("employees");

            for (int i = 0; i < employeeData.size(); i++) {
                JSONObject tempEmployeeObject = (JSONObject) employeeData.get(i);
                JSONObject employeeObject = (JSONObject) tempEmployeeObject.get("employee");
                Empleado employee = convertorJSON.crearEmpleado(employeeObject);

                if (employee.getId().equals(empleado.getId())) {
                    return i;
                }

            }
            return -1;

        } catch (IOException | ParseException | LlaveSinValorException e) {
            e.printStackTrace();
            return -1;
        }

    }

    public boolean modificarRegistro(Empleado empleado) {
        int posEmpleado = this.buscarEmpleado(empleado);
        if (posEmpleado != -1) {

            JSONParser jsonParser = new JSONParser();

            try {
                FileReader reader = new FileReader(this.documento);
                Object obj = jsonParser.parse(reader);
                JSONObject employeeList = (JSONObject) obj;
                JSONArray employeeData = (JSONArray) employeeList.get("employees");

                JSONObject tempEmployeeObject = (JSONObject) employeeData.get(posEmpleado);

                JSONObject employeeObject = (JSONObject) tempEmployeeObject.get("employee");

                employeeObject.replace("id", empleado.getId());
                employeeObject.replace("firstName", empleado.getFirstName());
                employeeObject.replace("lastName", empleado.getLastName());
                employeeObject.replace("photo", empleado.getPhoto());
                tempEmployeeObject.replace("employee", employeeObject);

                employeeData.remove(posEmpleado);

                employeeData.add(posEmpleado, tempEmployeeObject);

                JSONObject finalJSON = new JSONObject();
                finalJSON.put("employees", employeeData);
                crearArchivoJSON(finalJSON);
                return true;

            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
