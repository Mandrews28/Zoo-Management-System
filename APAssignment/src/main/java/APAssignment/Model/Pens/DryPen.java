package APAssignment.Model.Pens;

import APAssignment.Model.Animals.Animal;
import APAssignment.Model.Zoo.Message;

public class DryPen extends Pen {

    private static int numOfPens = 0;

    private double totalArea;
    private double remainingArea;

    public DryPen() {}

    public DryPen(String penId, double length, double width, double temp) {
        super(penId, length, width, temp);
        this.penType = PenType.DRY;
        this.totalArea = this.length * this.width;
        this.remainingArea = this.totalArea;
        if (this.getClass() == DryPen.class)
            numOfPens++;
    }

    public static int getNumOfPens() {
        return numOfPens;
    }

    public static void setNumOfPens(int numOfPens) {
        DryPen.numOfPens = numOfPens;
    }

    public double getTotalArea() {
        return totalArea;
    }

    public double getRemainingArea() {
        return remainingArea;
    }

    Message isSpaceInPen(Animal animal) {
        boolean isSpace = this.remainingArea > animal.getSpecies().getLandRequired();
        return isSpaceMessage(isSpace);
    }

    void addAnimal(Animal animal) {
        ChangePenTraits.addAnimal(animal, this);
        this.remainingArea -= animal.getSpecies().getLandRequired();
    }

    public void removeAnimal(Animal animal) {
        super.removeAnimal(animal);
        this.remainingArea += animal.getSpecies().getLandRequired();
    }

    @Override
    public String toString() {
        return super.toString()
                + ", totalArea: " + totalArea + "m^2"
                + ", remainingArea: " + remainingArea + "m^2\n";
    }
}
