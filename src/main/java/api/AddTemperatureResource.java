package api;

import dynamodb.TemperatureDAO;
import dynamodb.TemperatureItem;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.io.IOException;

/**
 * Created by jamescross91 on 05/12/2015.
 */

@Slf4j
public class AddTemperatureResource extends ServerResource {
    @Get
    public String represent() {
        return "Add new temp";
    }

    @Post
    public Representation acceptItem(Representation entity) throws IOException {
        JSONObject json = new JsonRepresentation(entity).getJsonObject();

        String validation = validateJson(json);
        if (!validation.equals("")) {
            return response(false, validation);
        }

        saveData(json);

        return response(true, "Data saved");
    }

    private void saveData(JSONObject jsonObject) throws JSONException {
        int temp = jsonObject.getInt("temp");
        String sensorAddress = jsonObject.getString("sensorAddress");
        TemperatureItem temperatureItem = new TemperatureItem(temp, sensorAddress);

        new TemperatureDAO().save(temperatureItem);
    }

    private String validateJson(JSONObject jsonObject) {
        if (!jsonObject.has("temp")) {
            return "Missing field: temp";
        }

        if (!jsonObject.has("sensorAddress")) {
            return "Missing field: sensorAddress";
        }

        return "";
    }

    private JsonRepresentation response(boolean success, String message) {
        JSONObject object = new JSONObject();
        try {
            object.put("success", success);
            object.put("message", message);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        log.warn("sql field missing from body");
        return new JsonRepresentation(object);
    }
}
