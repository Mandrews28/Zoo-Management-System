package APAssignment.Model.Pens;

import APAssignment.Model.Animals.Animal;
import APAssignment.Model.Zoo.CompatibleSpecies;
import APAssignment.Model.Animals.Species;
import APAssignment.Model.Zoo.ConvertInput;
import APAssignment.Model.Zoo.Message;
import APAssignment.Model.Zookeeper;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class ChangePenTraits {

    protected static Message canZookeeperBeRemovedFromPen(Pen pen) {
        if (pen.getZookeeper() == null)
            return Message.REMOVE_ZOOKEEPER_NO_ZOOKEEPER_IN_PEN;

        if (pen.getListOfSpeciesMap().size() == 0) {
            return Message.COMPATIBLE;
        } else {
            return Message.REMOVE_ZOOKEEPER_ANIMALS_STILL_IN_PEN;
        }
    }

    protected static Message isZookeeperCompatibleWithPen(Zookeeper zookeeper, Pen pen) {
        if (zookeeper.getPenTypes().contains(pen.penType)) {
            return Message.COMPATIBLE;
        } else {
            return Message.ADD_ZOOKEEPER_TO_PEN_INCOMPATIBLE_PEN_TYPES;
        }
    }

    protected static Message canAnimalBeRemovedFromPen(Animal animal, Pen pen) {
        if (animal.getCurrentPen() == pen) {
            return Message.COMPATIBLE;
        } else {
            return Message.REMOVE_ANIMAL_FROM_PEN_ANIMAL_NOT_IN_PEN;
        }
    }


    protected static void removeAnimal(Animal animal, Pen pen) {
        animal.setCurrentPen();
        pen.numOfAnimals--;
        updateSpeciesForRemoveAnimal(animal, pen);
    }

    private static void updateSpeciesForRemoveAnimal(Animal animal, Pen thisPen) {
        HashMap<String,Integer> speciesInThisPen = thisPen.getListOfSpeciesMap();
        ArrayList<String> speciesNames = thisPen.getListOfSpeciesNames();
        Species animalSpecies = animal.getSpecies();

        String animalSpeciesName = animalSpecies.getName();
        if (speciesInThisPen.get(animalSpeciesName) == 1) {
            speciesInThisPen.remove(animalSpeciesName);
            speciesNames.remove(animalSpeciesName);
        } else {
            speciesInThisPen.put(animalSpeciesName, speciesInThisPen.get(animalSpeciesName) - 1);
        }
        thisPen.setListOfSpeciesMap(speciesInThisPen);
        thisPen.setListOfSpeciesNames(speciesNames);
    }

    protected static Message isAnimalCompatibleWithPen(Animal animal, Pen pen) {
        Message result;
        Message isSpeciesCompatible = ChangePenTraits.isSpeciesCompatibleWithPen(animal.getSpecies(), pen);

        if (isSpeciesCompatible.equals(Message.COMPATIBLE)) {
            if (animal.getCurrentPen() != pen)
                result = Message.COMPATIBLE;
            else
                result = Message.ADD_ANIMAL_TO_PEN_ANIMAL_ALREADY_IN_PEN;
        } else {
            result = isSpeciesCompatible;
        }
        return result;
    }

    private static Message isSpeciesCompatibleWithPen(Species species, Pen thisPen) {
        Message result;

        if (checkPenType(species, thisPen)) {
            boolean isCurrentSpecies = thisPen.getListOfSpeciesMap().containsKey(species);

            if (isCurrentSpecies || isCompatibleSpecies(thisPen, species))
                result = Message.COMPATIBLE;
            else
                result = Message.ADD_ANIMAL_TO_PEN_INCOMPATIBLE_SPECIES;
        } else {
            result = Message.ADD_ANIMAL_TO_PEN_INCOMPATIBLE_PEN_TYPES;
        }
        return result;
    }

    protected static void addAnimal(Animal animal, Pen thisPen) {
        Pen animalPen = animal.getCurrentPen();
        Species animalSpecies = animal.getSpecies();
        boolean isCurrentSpecies = thisPen.getListOfSpeciesMap().containsKey(animalSpecies.getName());

        if (animalPen != null)
            removeAnimal(animal, animalPen);

        animal.setCurrentPen(thisPen);
        thisPen.numOfAnimals++;
        updateSpeciesForAddAnimal(isCurrentSpecies, thisPen, animalSpecies);
    }

    private static boolean checkPenType(Species species, Pen thisPen) {
        return species.getSuitablePenTypes().contains(thisPen.penType);
    }

    private static boolean isCompatibleSpecies(Pen thisPen, Species newAnimalSpecies) {
        for (String currentSpeciesName : thisPen.getListOfSpeciesMap().keySet()) {
            Species currentSpecies = ConvertInput.species(currentSpeciesName);
            if (! CompatibleSpecies.areSpeciesInCompatibleList(currentSpecies, newAnimalSpecies)) {
                return false;
            }
        }
        return true;
    }

    private static void updateSpeciesForAddAnimal(boolean isCurrentSpecies, Pen thisPen, Species newAnimalSpecies) {
        HashMap<String,Integer> speciesInThisPen = thisPen.getListOfSpeciesMap();
        ArrayList<String> speciesNames = thisPen.getListOfSpeciesNames();

        String newAnimalSpeciesName = newAnimalSpecies.getName();
        if (!isCurrentSpecies) {
            speciesInThisPen.put(newAnimalSpeciesName, 1);
            speciesNames.add(newAnimalSpeciesName);
        } else {
            speciesInThisPen.put(newAnimalSpeciesName, speciesInThisPen.get(newAnimalSpeciesName) + 1);
        }
        thisPen.setListOfSpeciesMap(speciesInThisPen);
        thisPen.setListOfSpeciesNames(speciesNames);
    }

}
