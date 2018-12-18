package BackEnd.Model.Zoo;

import BackEnd.Model.Pens.Pen;
import com.google.gson.*;

import java.lang.reflect.Type;

public class ZooAdapter implements JsonSerializer<Pen>, JsonDeserializer<Pen> {

    @Override
    public JsonElement serialize(Pen src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("type", new JsonPrimitive(src.getClass().getSimpleName()));
        result.add("properties", context.serialize(src, src.getClass()));

        return result;
    }

    @Override
    public Pen deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        JsonElement element = jsonObject.get("properties");

        try {
            return context.deserialize(element,
                    Class.forName("BackEnd.Model.Pens." + type));
        } catch (ClassNotFoundException cnfe) {
            throw new JsonParseException("Unknown element type: " + type, cnfe);
        }
    }
}
