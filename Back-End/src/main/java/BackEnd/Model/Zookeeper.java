package BackEnd.Model;

import BackEnd.Model.Pens.PenType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Zookeeper {
    private static Map<String, Zookeeper> zookeeperListByName = new HashMap<>();

    private String name;
    private ArrayList<PenType> penTypes;
    private int numPensAttending;

    public Zookeeper() {}

    public Zookeeper(String name, boolean canAquarium, boolean canAviary, boolean canDryPen,
                     boolean canPartWaterPartDryPen, boolean canPettingPen) {
        this.name = name;
        this.numPensAttending = 0;
        this.penTypes = new ArrayList<>();

        if (canAquarium) penTypes.add(PenType.AQUARIUM);
        if (canAviary) penTypes.add(PenType.AVIARY);
        if (canDryPen) penTypes.add(PenType.DRY);
        if (canPartWaterPartDryPen) penTypes.add(PenType.PART_WATER_PART_DRY);
        if (canPettingPen) penTypes.add(PenType.PETTING);

        zookeeperListByName.put(name, this);
    }

    public static void clearZookeeperList() {
        zookeeperListByName = new HashMap<>();
    }

    public static Map<String, Zookeeper> getZookeeperListByName() {
        return zookeeperListByName;
    }

    public static void setZookeeperListByName(Map<String, Zookeeper> zookeeperListByName) {
        Zookeeper.zookeeperListByName = zookeeperListByName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<PenType> getPenTypes() {
        return penTypes;
    }

    public void setPenTypes(ArrayList<PenType> penTypes) {
        this.penTypes = penTypes;
    }

    public int getNumPensAttending() {
        return numPensAttending;
    }

    public void setNumPensAttending(int numPensAttending) {
        this.numPensAttending = numPensAttending;
    }

    public void incrementNumPensAttending() {
        this.setNumPensAttending(this.getNumPensAttending() + 1);
    }

    public void decrementNumPensAttending() {
        this.setNumPensAttending(this.getNumPensAttending() - 1);
    }

    public String toString() {
        return "Name: " + this.name
                + ", Pen Types: " + this.penTypes
                + ", Number of Pens Attending: " + this.numPensAttending + "\n";
    }
}
