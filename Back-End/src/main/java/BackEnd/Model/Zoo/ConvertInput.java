package BackEnd.Model.Zoo;

import BackEnd.Model.Animals.Animal;
import BackEnd.Model.Animals.AnimalType;
import BackEnd.Model.Animals.Species;
import BackEnd.Model.Pens.Pen;
import BackEnd.Model.Pens.PenType;
import BackEnd.Model.Zookeeper;

public abstract class ConvertInput {
    protected static AnimalType animalType(String animalType_un) {
        AnimalType animalType;
        switch (animalType_un) {
            case "Air":
                animalType = AnimalType.AIR;
                break;
            case "Amphibian":
                animalType = AnimalType.AMPHIBIAN;
                break;
            case "Land":
                animalType = AnimalType.LAND;
                break;
            case "Water":
                animalType = AnimalType.WATER;
                break;
            default:
                System.out.println("Error: Incorrect animal type");
                animalType = null;
        }
        return animalType;
    }

    protected static PenType penType(String penType_un) {
        PenType penType;
        switch (penType_un) {
            case "Aquarium":
                penType = PenType.AQUARIUM;
                break;
            case "Aviary":
                penType = PenType.AVIARY;
                break;
            case "Dry":
                penType = PenType.DRY;
                break;
            case "Part Water Part Dry":
                penType = PenType.PART_WATER_PART_DRY;
                break;
            case "Petting":
                penType = PenType.PETTING;
                break;
            default:
                System.out.println("Error: Incorrect pen type");
                penType = null;
        }
        return penType;
    }

    public static Pen pen(String penId) {
        return Pen.getPenListById().get(penId);
    }

    public static Species species(String speciesName) {
        return Species.getSpeciesListByName().get(speciesName);
    }

    public static Animal animal(String animalName) {
        return Animal.getAnimalListByName().get(animalName);
    }

    public static Zookeeper zookeeper(String zookeeperName) {
        return Zookeeper.getZookeeperListByName().get(zookeeperName);
    }

}