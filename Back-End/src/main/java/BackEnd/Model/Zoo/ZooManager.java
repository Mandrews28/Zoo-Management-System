package BackEnd.Model.Zoo;

import BackEnd.Model.Animals.Animal;
import BackEnd.Model.Animals.AnimalType;
import BackEnd.Model.Animals.Species;
import BackEnd.Model.Pens.*;
import BackEnd.Model.Zookeeper;

import java.util.ArrayList;

public abstract class ZooManager {

    public static void clearZoo() {
        Animal.clearAnimalList();
        Species.clearSpeciesList();
        Pen.clearPenList();
        Zookeeper.clearZookeeperList();
        CompatibleSpecies.clearList();
        Aquarium.setNumOfPens(0);
        Aviary.setNumOfPens(0);
        DryPen.setNumOfPens(0);
        PartWaterPartDry.setNumOfPens(0);
        PettingPen.setNumOfPens(0);
    }

    public static void createNewPen(String penId, String penType_un, String temp_un, String length_un, String width_un, String height_un, String waterVolume_un) {
        PenType penType = ConvertInput.penType(penType_un);
        double length = Double.parseDouble(length_un);
        double width = Double.parseDouble(width_un);
        double temp = Double.parseDouble(temp_un);
        double height = Double.parseDouble(height_un);
        double waterVolume = Double.parseDouble(waterVolume_un);

        switch (penType) {
            case AQUARIUM:
                new Aquarium(penId, length, width, height, temp);
                break;
            case AVIARY:
                new Aviary(penId, length, width, height, temp);
                break;
            case DRY:
                new DryPen(penId, length, width, temp);
                break;
            case PART_WATER_PART_DRY:
                new PartWaterPartDry(penId, length, width, waterVolume, temp);
                break;
            case PETTING:
                new PettingPen(penId, length, width, temp);
                break;
        }
    }

    public static void createNewSpecies(String name, String animalType_un, String airRequirement_un, String landRequirement_un, String waterRequirement_un, boolean isPettable) {
        AnimalType animalType = ConvertInput.animalType(animalType_un);

        double airRequirement = Double.parseDouble(airRequirement_un);
        double landRequirement = Double.parseDouble(landRequirement_un);
        double waterRequirement = Double.parseDouble(waterRequirement_un);

        new Species(name, animalType, airRequirement, landRequirement, waterRequirement, isPettable);
    }

    public static void createNewAnimal(String name, String speciesName) {
        Species species = ConvertInput.species(speciesName);
        new Animal(name, species, null);
    }

    public static Message addCompatibleSpecies(String species1Name, String species2Name) {
        Species species1 = ConvertInput.species(species1Name);
        Species species2 = ConvertInput.species(species2Name);
        Message canSpeciesBeAdded = CompatibleSpecies.canSpeciesBeAddedToCompatibleList(species1, species2);
        if (canSpeciesBeAdded.equals(Message.COMPATIBLE)) {
            CompatibleSpecies.add(species1, species2);
            return Message.SUCCESS;
        } else {
            return canSpeciesBeAdded;
        }
    }

    public static Message canAutoAllocateAnimalsToPens() {
        return (Animal.getAnimalListWithNoPen().size() == 0) ?
                Message.AUTO_ALLOCATE_ANIMALS_NO_ANIMALS_NOT_IN_PEN :
                Message.COMPATIBLE;
    }

    public static void autoAllocateAnimalsToPens() {
        ArrayList<Animal> originalAnimalListWithNoPen = new ArrayList<>(Animal.getAnimalListWithNoPen());
        for (Animal animal : originalAnimalListWithNoPen) {

            for (Pen pen : Pen.getPenListById().values()) {
                Message result = PenManager.addAnimal(animal, pen);
                if (result.equals(Message.SUCCESS)) break;
            }

        }
    }

    public static Message canAutoAllocateZookeepersToPens() {
        return (Pen.getPenListWithNoZookeeper().size() == 0) ?
                Message.AUTO_ALLOCATE_ZOOKEEPERS_NO_PENS_UNMANNED :
                Message.COMPATIBLE;
    }

    public static void autoAllocateZookeepersToPens() {
        ArrayList<Pen> originalPenListWithNoZookeeper = new ArrayList<>(Pen.getPenListWithNoZookeeper());
        for (Pen pen : originalPenListWithNoZookeeper) {
            Zookeeper chosenZookeeper = getChosenZookeeperFromPenType(pen.getPenType());
            if (chosenZookeeper != null) {
                PenManager.addZookeeper(chosenZookeeper, pen);
            }
        }
    }

    private static Zookeeper getChosenZookeeperFromPenType(PenType penType) {
        ArrayList<Zookeeper> suitableZookeepers = new ArrayList<>();

        for (Zookeeper zookeeper : Zookeeper.getZookeeperListByName().values()) {
            for (PenType type : zookeeper.getPenTypes()) {
                if (type.equals(penType)) {
                    suitableZookeepers.add(zookeeper);
                }
            }
        }

        return getChosenZookeeperFromList(suitableZookeepers);
    }

    private static Zookeeper getChosenZookeeperFromList(ArrayList<Zookeeper> zookeepers) {
        Zookeeper chosenZookeeper = null;
        int minimumNumPensAttendingByAZookeeper = Pen.getPenListById().size();

        for (Zookeeper zookeeper : zookeepers) {

            if (zookeeper.getNumPensAttending() < minimumNumPensAttendingByAZookeeper) {
                chosenZookeeper = zookeeper;
                minimumNumPensAttendingByAZookeeper = zookeeper.getNumPensAttending();
            }

        }
        return chosenZookeeper;
    }

}
