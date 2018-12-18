package BackEnd.Model.Pens;

public class PettingPen extends DryPen {

    private static int numOfPens = 0;

    public PettingPen() {}

    public PettingPen(String penId, double length, double width, double temp) {
        super(penId, length, width, temp);
        this.penType = PenType.PETTING;
        numOfPens++;
    }

    public static int getNumOfPens() {
        return numOfPens;
    }

    public static void setNumOfPens(int numOfPens) {
        PettingPen.numOfPens = numOfPens;
    }
}
