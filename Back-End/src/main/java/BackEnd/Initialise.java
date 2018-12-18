package BackEnd;

import BackEnd.Model.Animals.AnimalType;
import BackEnd.Model.Animals.Species;
import BackEnd.Model.Pens.*;
import BackEnd.Model.Zoo.ZooManager;
import BackEnd.Model.Zookeeper;

abstract class Initialise {

    protected static void zooTemplate() {
        ZooManager.clearZoo();
        zookeepers();
    }

    protected static void basicZoo() {
        ZooManager.clearZoo();
        zookeepers();
        species();
        pens();
        animals();
        assigning();
    }

    private static void zookeepers() {
        new Zookeeper("Hardip", false,true, true, false, false);
        new Zookeeper("Alex", true, false, false, true, false);
        new Zookeeper("Farhad", true, true, false, false, false);
        new Zookeeper("Alan", false, false, true, false, true);
    }

    private static void species() {
        new Species("Sloth", AnimalType.LAND, 0, 3, 0, false);
        new Species("Penguin", AnimalType.AMPHIBIAN, 0, 2, 4, false);
        new Species("Goat", AnimalType.LAND, 0, 3, 0, true);
        new Species("Dog", AnimalType.LAND, 0, 3.5, 0, true);
        new Species("Owl", AnimalType.AIR, 20, 0, 0, false);
        new Species("Dolphin", AnimalType.WATER, 0, 0, 40, false);
        new Species("Hippo", AnimalType.AMPHIBIAN, 0, 10, 20, false);
        new Species("Cat", AnimalType.LAND, 0, 4, 0, true);
    }

    private static void pens() {
        new Aquarium("Aqua1", 10, 10, 10, 20);
        new Aviary("Avi1", 10, 10, 5, 18);
        new DryPen("Dry1", 8, 12, 15);
        new PartWaterPartDry("Part1", 10, 5, 100, 18);
        new PettingPen("Pet1", 10, 10, 15);
        new DryPen("Dry2", 20,20,20);
    }

    private static void animals() {
        ZooManager.createNewAnimal("Bone", "Dog");
        ZooManager.createNewAnimal("Aria", "Hippo");
        ZooManager.createNewAnimal("Milly", "Cat");
    }

    private static void assigning() {
        PenManager.addZookeeper("Alex", "Part1");
        PenManager.addAnimal("Aria", "Part1");
    }
}
