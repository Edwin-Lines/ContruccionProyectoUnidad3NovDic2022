package unidad_3.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ModificadorArchivosJSON {

    public static boolean actualizarArchivo(ArrayList<Empleado> empleados, String directorio) {
        boolean wasCreated;
        JSONArray employees = new JSONArray();

        for (Empleado empleado : empleados) {
            JSONObject employee = new JSONObject();

            JSONObject tempJsonEmpleado = new JSONObject();
            tempJsonEmpleado.put("id", empleado.getId());
            tempJsonEmpleado.put("firstName", empleado.getFirstName());
            tempJsonEmpleado.put("lastName", empleado.getLastName());
            tempJsonEmpleado.put("photo", empleado.getPhoto());

            employee.put("employee", tempJsonEmpleado);
            employees.add(employee);
        }

        JSONObject finalJSON = new JSONObject();

        finalJSON.put("employees", employees);

        try (PrintWriter file = new PrintWriter(directorio)) {

            ObjectMapper mapper = new ObjectMapper();
            String betterFormat = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(mapper.readTree(finalJSON.toJSONString()));

            file.write(betterFormat);
            file.flush();
            file.close();

            wasCreated = true;
        } catch (IOException e) {
            e.printStackTrace();
            wasCreated = false;
        }
        return wasCreated;
    }
}
