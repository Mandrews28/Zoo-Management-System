package BackEnd.Model.Pens;

import BackEnd.Model.Animals.Animal;
import BackEnd.Model.Zoo.Message;
import BackEnd.Model.Zookeeper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Pen {

    private static Map<String, Pen> penListById = new HashMap<>();
    private static ArrayList<Pen> penListWithNoZookeeper = new ArrayList<>();

    private String penId;
    PenType penType;
    private HashMap<String,Integer> listOfSpeciesMap;
    private ArrayList<String> listOfSpeciesNames;
    double length;
    double width;
    private double temperature;
    private Zookeeper zookeeper;
    protected int numOfAnimals;

    public Pen() {}

    public Pen(String penId, double length, double width, double temp) {
        this.penId = penId;
        this.length = length;
        this.width = width;
        this.temperature = temp;
        this.numOfAnimals = 0;
        this.zookeeper = null;
        listOfSpeciesMap = new HashMap<>();
        listOfSpeciesNames = new ArrayList<>();
        penListById.put(penId, this);
        penListWithNoZookeeper.add(this);
    }

    public static Map<String, Pen> getPenListById() {
        return penListById;
    }

    public static void setPenListById(Map<String, Pen> penListById) {
        Pen.penListById = penListById;
    }

    public static ArrayList<Pen> getPenListWithNoZookeeper() {
        return penListWithNoZookeeper;
    }

    public static void setPenListWithNoZookeeper(ArrayList<Pen> penListWithNoZookeeper) {
        Pen.penListWithNoZookeeper = penListWithNoZookeeper;
    }

    public static void clearPenList() {
        penListById = new HashMap<>();
        penListWithNoZookeeper = new ArrayList<>();
    }

    abstract Message isSpaceInPen(Animal animal);

    Message isSpaceMessage(boolean isSpaceInPen) {
        if (isSpaceInPen)
            return Message.COMPATIBLE;
        else
            return Message.ADD_ANIMAL_TO_PEN_NO_SPACE_IN_PEN;
    }

    void addAnimal(Animal animal) {
        ChangePenTraits.addAnimal(animal, this);
    };

    public void removeAnimal(Animal animal) {
        ChangePenTraits.removeAnimal(animal, this);
    }

    public Zookeeper getZookeeper() {
        return this.zookeeper;
    }

    public void setZookeeper(Zookeeper zookeeper) {
        this.zookeeper = zookeeper;
        if (zookeeper != null) {
            penListWithNoZookeeper.remove(this);
        } else {
            penListWithNoZookeeper.add(this);
        }
    }

    public HashMap<String, Integer> getListOfSpeciesMap() {
        return this.listOfSpeciesMap;
    }

    public void setListOfSpeciesMap(HashMap<String, Integer> listOfSpeciesMap) {
        this.listOfSpeciesMap = listOfSpeciesMap;
    }

    public ArrayList<String> getListOfSpeciesNames() {
        return listOfSpeciesNames;
    }

    public void setListOfSpeciesNames(ArrayList<String> listOfSpeciesNames) {
        this.listOfSpeciesNames = listOfSpeciesNames;
    }

    public String getPenId() {
        return penId;
    }

    public int getNumOfAnimals() {
        return numOfAnimals;
    }

    public PenType getPenType() {
        return penType;
    }

    public double getTemperature() {
        return temperature;
    }

    public String toString() {
        return "ID: " + this.penId
                + ", Type: " + this.penType
                + ", Species: " + this.listOfSpeciesMap
                + ", Zookeeper: " + this.zookeeper
                + ", Temp: " + this.temperature + "°C";
    }

}
