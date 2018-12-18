package BackEnd.Model.Pens;

import BackEnd.Model.Animals.Animal;
import BackEnd.Model.Zoo.Message;

public class Aquarium extends Pen {

    private static int numOfPens = 0;

    private double totalVolume;
    private double remainingVolume;

    public Aquarium() {}

    public Aquarium(String penId, double length, double width, double height, double temp) {
        super(penId, length, width, temp);
        this.penType = PenType.AQUARIUM;
        this.totalVolume = this.length * this.width * height;
        this.remainingVolume = this.totalVolume;
        numOfPens++;
    }


    public static int getNumOfPens() {
        return numOfPens;
    }

    public static void setNumOfPens(int numOfPens) {
        Aquarium.numOfPens = numOfPens;
    }

    public double getTotalVolume() {
        return totalVolume;
    }

    public double getRemainingVolume() {
        return remainingVolume;
    }

    Message isSpaceInPen(Animal animal) {
        boolean isSpace = this.remainingVolume > animal.getSpecies().getWaterRequired();
        return isSpaceMessage(isSpace);
    }

    void addAnimal(Animal animal) {
        super.addAnimal(animal);
        this.remainingVolume -= animal.getSpecies().getWaterRequired();
    }

    public void removeAnimal(Animal animal) {
        super.removeAnimal(animal);
        this.remainingVolume += animal.getSpecies().getWaterRequired();
    }

    public String toString() {
        return super.toString()
                + ", totalVolume: " + totalVolume + "m^3"
                + ", remainingVolume: " + remainingVolume + "m^3\n";
    }
}
