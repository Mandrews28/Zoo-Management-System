package BackEnd.Model.Pens;

import BackEnd.Model.Animals.Animal;
import BackEnd.Model.Zoo.Message;

public class Aviary extends Pen {

    private static int numOfPens = 0;

    private double totalVolume;
    private double remainingVolume;

    public Aviary() {}

    public Aviary(String penId, double length, double width, double height, double temp) {
        super(penId, length, width, temp);
        this.penType = PenType.AVIARY;
        this.totalVolume = this.length * this.width * height;
        this.remainingVolume = this.totalVolume;
        numOfPens++;
    }

    public double getTotalVolume() {
        return totalVolume;
    }

    public double getRemainingVolume() {
        return remainingVolume;
    }

    public static int getNumOfPens() {
        return numOfPens;
    }

    public static void setNumOfPens(int numOfPens) {
        Aviary.numOfPens = numOfPens;
    }

    Message isSpaceInPen(Animal animal) {
        boolean isSpace = this.remainingVolume > animal.getSpecies().getAirRequired();
        return isSpaceMessage(isSpace);
    }

    void addAnimal(Animal animal) {
        ChangePenTraits.addAnimal(animal, this);
        this.remainingVolume -= animal.getSpecies().getAirRequired();
    }

    public void removeAnimal(Animal animal) {
        super.removeAnimal(animal);
        this.remainingVolume += animal.getSpecies().getAirRequired();
    }

    public String toString() {
        return super.toString()
                + ", totalVolume: " + totalVolume + "m^3"
                + ", remainingVolume: " + remainingVolume + "m^3\n";
    }

}
