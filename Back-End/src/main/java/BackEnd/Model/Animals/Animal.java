package BackEnd.Model.Animals;

import BackEnd.Model.Pens.Pen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Animal {
    //Instance of an animal with a species type i.e. Dog, Cat
    private static Map<String, Animal> animalListByName = new HashMap<>();
    private static ArrayList<Animal> animalListWithNoPen = new ArrayList<>();

    private String name;
    private Species species; //Dog, Cat
    private Pen currentPen;

    public Animal() {}

    public Animal(String name, Species species, Pen pen) {
        this.name = name;
        this.species = species;
        if (pen != null)
            this.setCurrentPen(pen);
        else
            this.setCurrentPen();
        animalListByName.put(name, this);
        species.incrementNumOfAnimals();
    }

    public static Map<String, Animal> getAnimalListByName() {
        return animalListByName;
    }

    public static void setAnimalListByName(Map<String, Animal> animalListByName) {
        Animal.animalListByName = animalListByName;
    }

    public static ArrayList<Animal> getAnimalListWithNoPen() {
        return animalListWithNoPen;
    }

    public static void setAnimalListWithNoPen(ArrayList<Animal> animalListWithNoPen) {
        Animal.animalListWithNoPen = animalListWithNoPen;
    }

    public static void clearAnimalList() {
        animalListByName = new HashMap<>();
        animalListWithNoPen = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Pen getCurrentPen() {
        return currentPen;
    }

    public void setCurrentPen(Pen pen) {
        this.currentPen = pen;
        animalListWithNoPen.remove(this);
    }

    public void setCurrentPen() {
        this.currentPen = null;
        animalListWithNoPen.add(this);
    }

    public String toString() {
        return "Name: " + this.name
                + ", Species: " + this.species
                + ", Current Pen: " + this.currentPen + "\n";
    }

}
