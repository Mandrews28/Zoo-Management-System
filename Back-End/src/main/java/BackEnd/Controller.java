package BackEnd;

import BackEnd.Model.Pens.PenManager;
import BackEnd.Model.Zoo.Message;
import BackEnd.Model.Zoo.Zoo;
import BackEnd.Model.Zoo.ZooManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class Controller {

//    @Autowired
//    private Response service;

    private static String zooFile = "zoo.json";

    @RequestMapping(value = "/zoo", method = RequestMethod.POST)
    public ResponseEntity<Object> zoo(@RequestBody Map<String, Object> payload) {
        String zooVersion = (String) payload.get("selectedZooVersion");
        switch (zooVersion) {
            case "Get zoo from saved file":
                return Response.getFromFile(zooFile);
            case "Get basic zoo with some animals/pens created (overwrite saved file)":
                Initialise.basicZoo();
                return Response.initialiseZoo(zooFile);
            case "Get zoo template (overwrite saved file)":
                Initialise.zooTemplate();
                return Response.initialiseZoo(zooFile);
            default:
                return null;
        }
    }

    @RequestMapping("/weather")
    public String weather() {
        return Utility.getWeather();
    }

    @RequestMapping(value = "/create-animal", method = RequestMethod.POST)
    public ResponseEntity<Object> createAnimal(@RequestBody Map<String, Object> payload) {
        String name = (String) payload.get("name");
        String species = (String) payload.get("species");
        ZooManager.createNewAnimal(name, species);
        return Response.getCurrentZoo(zooFile);
    }

    @RequestMapping(value = "/create-species", method = RequestMethod.POST)
    public ResponseEntity<Object> createSpecies(@RequestBody Map<String, Object> payload) {
        String name = (String) payload.get("name");
        String animalType = (String) payload.get("animalType");
        String airRequirement = String.valueOf(payload.get("airRequirement"));
        String landRequirement = String.valueOf(payload.get("landRequirement"));
        String waterRequirement = String.valueOf(payload.get("waterRequirement"));
        boolean isPettable = (boolean) payload.get("isPettable");
        ZooManager.createNewSpecies(name, animalType, airRequirement,landRequirement, waterRequirement, isPettable);
        return Response.getCurrentZoo(zooFile);
    }

    @RequestMapping(value = "/create-pen", method = RequestMethod.POST)
    public ResponseEntity<Object> createPen(@RequestBody Map<String, Object> payload) {
        String penId = (String) payload.get("penId");
        String penType = (String) payload.get("penType");
        String temperature = String.valueOf(payload.get("temperature"));
        String length = String.valueOf(payload.get("length"));
        String width = String.valueOf(payload.get("width"));
        String height = String.valueOf(payload.get("height"));
        String waterVolume = String.valueOf(payload.get("waterVolume"));
        ZooManager.createNewPen(penId, penType, temperature, length, width, height, waterVolume);
        return Response.getCurrentZoo(zooFile);
    }

    @RequestMapping(value = "/add-animal-to-pen", method = RequestMethod.POST)
    public ResponseEntity<Object> addAnimalToPen(@RequestBody Map<String, Object> payload) {
        String animalName = (String) payload.get("animalName");
        String penId = (String) payload.get("penId");
        Message result =  PenManager.addAnimal(animalName, penId);
        return Response.get(result, zooFile);
    }

    @RequestMapping(value = "/add-zookeeper-to-pen", method = RequestMethod.POST)
    public ResponseEntity<Object> addZookeeperToPen(@RequestBody Map<String, Object> payload) {
        String zookeeperName = (String) payload.get("zookeeperName");
        String penId = (String) payload.get("penId");
        Message result =  PenManager.addZookeeper(zookeeperName, penId);
        return Response.get(result, zooFile);
    }

    @RequestMapping(value = "/add-compatible-species", method = RequestMethod.POST)
    public ResponseEntity<Object> addCompatibleSpecies(@RequestBody Map<String, Object> payload) {
        String species1 = (String) payload.get("species1");
        String species2 = (String) payload.get("species2");
        Message result =  ZooManager.addCompatibleSpecies(species1, species2);
        return Response.get(result, zooFile);
    }

    @RequestMapping(value = "/auto-allocate-animals")
    public ResponseEntity<Object> autoAllocateAnimals() {
        Message result =  ZooManager.canAutoAllocateAnimalsToPens();
        if (result.equals(Message.COMPATIBLE)) {
            ZooManager.autoAllocateAnimalsToPens();
            return Response.getCurrentZoo(zooFile);
        } else {
            return Response.get(result, zooFile);
        }
    }

    @RequestMapping(value = "/auto-allocate-zookeepers")
    public ResponseEntity<Object> autoAllocateZookeepers() {
        Message result =  ZooManager.canAutoAllocateZookeepersToPens();
        if (result.equals(Message.COMPATIBLE)) {
            ZooManager.autoAllocateZookeepersToPens();
            return Response.getCurrentZoo(zooFile);
        } else {
            return Response.get(result, zooFile);
        }
    }

}

@Component
class Response {
    public static ResponseEntity<Object> getFromFile(String zooFile) {
        ZooManager.clearZoo();
        Object response = JsonConversion.getZooFromJsonFile(zooFile);

        if (response.getClass().equals(Message.class)) {
            Message message = (Message) response;
            Map<String, String> map = new HashMap<String, String>(1){{put("result", message.message);}};
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            Zoo.setStaticVariablesWithJsonString( (Zoo) response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    public static ResponseEntity<Object> initialiseZoo(String zooFile) {
        Zoo newZoo = new Zoo();
        JsonConversion.createJsonFile(newZoo, zooFile);
        return new ResponseEntity<>(newZoo, HttpStatus.OK);
    }

    public static ResponseEntity<Object> getCurrentZoo(String zooFile) {
        Zoo currentZoo = new Zoo();
        JsonConversion.createJsonFile(currentZoo, zooFile);
        return new ResponseEntity<>(currentZoo, HttpStatus.OK);
    }

    public static ResponseEntity<Object> get(Message message, String zooFile) {
        if (message.equals(Message.SUCCESS)) {
            return Response.getCurrentZoo(zooFile);
        } else {
            Map<String, String> map = new HashMap<String, String>(1){{put("result", message.message);}};
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }
}
