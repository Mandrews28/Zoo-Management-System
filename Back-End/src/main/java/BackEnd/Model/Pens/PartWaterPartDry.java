package BackEnd.Model.Pens;

import BackEnd.Model.Animals.Animal;
import BackEnd.Model.Zoo.Message;

public class PartWaterPartDry extends Pen {

    private static int numOfPens = 0;

    private double totalArea;
    private double remainingArea;
    private double totalVolume;
    private double remainingVolume;

    public PartWaterPartDry() {}

    public PartWaterPartDry(String penId, double length, double width, double waterVolume, double temp) {
        super(penId, length, width, temp);
        this.penType = PenType.PART_WATER_PART_DRY;
        this.totalArea = this.length * this.width;
        this.remainingArea = this.totalArea;
        this.totalVolume = waterVolume;
        this.remainingVolume = this.totalVolume;
        numOfPens++;
    }

    public static int getNumOfPens() {
        return numOfPens;
    }

    public static void setNumOfPens(int numOfPens) {
        PartWaterPartDry.numOfPens = numOfPens;
    }

    public double getTotalArea() {
        return totalArea;
    }

    public double getRemainingArea() {
        return remainingArea;
    }

    public double getTotalVolume() {
        return totalVolume;
    }

    public double getRemainingVolume() {
        return remainingVolume;
    }

    Message isSpaceInPen(Animal animal) {
        boolean isSpace = this.remainingArea > animal.getSpecies().getLandRequired() &&
                this.remainingVolume > animal.getSpecies().getWaterRequired();
        return isSpaceMessage(isSpace);
    }

    void addAnimal(Animal animal) {
        super.addAnimal(animal);
        this.remainingArea -= animal.getSpecies().getLandRequired();
        this.remainingVolume -= animal.getSpecies().getWaterRequired();
    }

    public void removeAnimal(Animal animal) {
        super.removeAnimal(animal);
        this.remainingArea += animal.getSpecies().getLandRequired();
        this.remainingVolume += animal.getSpecies().getWaterRequired();
    }

    @Override
    public String toString() {
        return super.toString()
                + ", totalArea: " + totalArea + "m^2"
                + ", remainingArea: " + remainingArea + "m^2"
                + ", totalVolume: " + totalVolume + "m^3"
                + ", remainingVolume: " + remainingVolume + "m^3\n";
    }
}
