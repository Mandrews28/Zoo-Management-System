package BackEnd.Model.Animals;

import BackEnd.Model.Pens.PenType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Species {
    //Species is a type of animal. Such as Dog, Dolphin, Owl
    private static Map<String, Species> speciesListByName = new HashMap<>();

    private String name;             //Dog
    private AnimalType animalType;   //Land
    private boolean isPettable;
    private double airRequired;      //volume
    private double landRequired;     //area
    private double waterRequired;    //volume
    private ArrayList<PenType> suitablePenTypes; //DryPen, PettingPen

    private int currentNumOfAnimals;
    private int totalNumOfAnimalsCreated;

    public Species() {}

    public Species(String name, AnimalType animalType, double airRequired,
                   double landRequired, double waterRequired, boolean isPettable) {
        this.name = name;
        this.animalType = animalType;
        this.airRequired = airRequired;
        this.landRequired = landRequired;
        this.waterRequired = waterRequired;
        this.isPettable = isPettable;
        speciesListByName.put(name, this);

        suitablePenTypes = new ArrayList<>();
        this.addPenTypes(animalType, isPettable);

        currentNumOfAnimals = totalNumOfAnimalsCreated = 0;
    }

    public static Map<String, Species> getSpeciesListByName() {
        return speciesListByName;
    }

    public static void setSpeciesListByName(Map<String, Species> speciesListByName) {
        Species.speciesListByName = speciesListByName;
    }

    public static void clearSpeciesList() {
        speciesListByName = new HashMap<>();
    }

    private void addPenTypes(AnimalType animalType, boolean isPettable) {
        switch (animalType) {
            case AIR:
                suitablePenTypes.add(PenType.AVIARY);
                break;
            case AMPHIBIAN:
                suitablePenTypes.add(PenType.PART_WATER_PART_DRY);
                break;
            case LAND:
                suitablePenTypes.add(PenType.DRY);
                if (isPettable) suitablePenTypes.add(PenType.PETTING);
                break;
            case WATER:
                suitablePenTypes.add(PenType.AQUARIUM);
        }
    }

    public String toString() {
        return "{Name: " + this.name
                + ", Animal Type: " + this.animalType
                + ", Is it Pettable?: " + this.isPettable
                + ", Air Required: " + this.airRequired + "m^3"
                + ", Land Required: " + this.landRequired + "m^2"
                + ", Water Required: " + this.waterRequired + "m^3"
                + ", Suitable Pen Types: " + this.suitablePenTypes + "}";
    }

    protected void incrementNumOfAnimals() {
        totalNumOfAnimalsCreated++;
        currentNumOfAnimals++;
    }

    protected void decrementNumOfAnimals() {
        currentNumOfAnimals--;
    }

    public String getName() {
        return name;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public boolean getIsPettable() {
        return isPettable;
    }

    public double getAirRequired() {
        return airRequired;
    }

    public double getLandRequired() {
        return landRequired;
    }

    public double getWaterRequired() {
        return waterRequired;
    }

    public ArrayList<PenType> getSuitablePenTypes() {
        return suitablePenTypes;
    }

    public int getCurrentNumOfAnimals() {
        return currentNumOfAnimals;
    }

    public int getTotalNumOfAnimalsCreated() {
        return totalNumOfAnimalsCreated;
    }

}
