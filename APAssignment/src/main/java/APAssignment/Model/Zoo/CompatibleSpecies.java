package APAssignment.Model.Zoo;

import APAssignment.Model.Animals.Species;

import java.util.ArrayList;

public abstract class CompatibleSpecies {
    private static ArrayList<String> list;

    public static ArrayList<String> getList() {
        return list;
    }

    public static void setList(ArrayList<String> list) {
        CompatibleSpecies.list = list;
    }

    public static Message canSpeciesBeAddedToCompatibleList(Species species1, Species species2) {
        if (!areSpeciesInCompatibleList(species1, species2)) {
            if (doSpeciesHaveCompatibleTypes(species1, species2)) {
                return Message.COMPATIBLE;
            } else {
                return Message.ADD_COMPATIBLE_SPECIES_INCOMPATIBLE_PEN_TYPES;
            }
        } else {
            return Message.ADD_COMPATIBLE_SPECIES_ALREADY_COMPATIBLE;
        }
    }

    public static boolean areSpeciesInCompatibleList(Species species1, Species species2) {
        return species1.getName().equals(species2.getName()) ||
                list.contains(species1.getName() + "-" + species2.getName()) ||
                list.contains(species2.getName() + "-" + species1.getName());
    }

    private static boolean doSpeciesHaveCompatibleTypes(Species species1, Species species2) {
        return species1.getAnimalType() == species2.getAnimalType();
    }

    public static void add(Species species1, Species species2) {
        String combination = species1.getName() + "-" + species2.getName();
        list.add(combination);
    }

    public static void clearList() {
        list = new ArrayList<>();
    }
}
