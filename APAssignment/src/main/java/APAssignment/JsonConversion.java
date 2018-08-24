package APAssignment;

import APAssignment.Model.Pens.Pen;
import APAssignment.Model.Zoo.Message;
import APAssignment.Model.Zoo.Zoo;
import APAssignment.Model.Zoo.ZooAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonConversion {
    public static void createJsonFile(Zoo zoo, String filePath) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Pen.class, new ZooAdapter());
        Gson gson = gsonBuilder.create();
        try {
            String jsonString = gson.toJson(zoo);
            File file = new File(filePath);
            PrintWriter fileWriter = new PrintWriter(file);
            fileWriter.print(jsonString);
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public static Object getZooFromDefaultJsonFile() {
        return getZooFromJsonFile("zoo.json");
    }

    public static Object getZooFromJsonFile(String filePath) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Pen.class, new ZooAdapter());
        Gson gson = gsonBuilder.create();
        try {
            Path path = Paths.get(filePath);
            String jsonString = new String(Files.readAllBytes(path));
            return gson.fromJson(jsonString, Zoo.class);
        } catch (Exception e) {
            e.printStackTrace();
            return Message.ERROR_LOADING_FILE;
        }
    }

}
