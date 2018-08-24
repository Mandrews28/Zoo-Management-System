package APAssignment.Model;

import APAssignment.Model.Pens.PenType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Zookeeper {
    private static Map<String, Zookeeper> zookeeperListByName = new HashMap<>();
    private static Map<String, Zookeeper> aquariumZookeepersByName = new HashMap<>();
    private static Map<String, Zookeeper> aviaryZookeepersByName = new HashMap<>();
    private static Map<String, Zookeeper> dryZookeepersByName = new HashMap<>();
    private static Map<String, Zookeeper> partWaterPartDryZookeepersByName = new HashMap<>();
    private static Map<String, Zookeeper> pettingZookeepersByName = new HashMap<>();

    private String name;
    private ArrayList<PenType> penTypes;
    private int numPensAttending;

    public Zookeeper() {}

    public Zookeeper(String name, boolean canAquarium, boolean canAviary, boolean canDryPen,
                     boolean canPartWaterPartDryPen, boolean canPettingPen) {
        this.name = name;
        this.numPensAttending = 0;
        this.penTypes = new ArrayList<>();

        if (canAquarium) {
            penTypes.add(PenType.AQUARIUM);
            aquariumZookeepersByName.put(name, this);
        }
        if (canAviary) {
            penTypes.add(PenType.AVIARY);
            aviaryZookeepersByName.put(name, this);
        }
        if (canDryPen) {
            penTypes.add(PenType.DRY);
            dryZookeepersByName.put(name, this);
        }
        if (canPartWaterPartDryPen) {
            penTypes.add(PenType.PART_WATER_PART_DRY);
            partWaterPartDryZookeepersByName.put(name, this);
        }
        if (canPettingPen) {
            penTypes.add(PenType.PETTING);
            pettingZookeepersByName.put(name, this);
        }

        zookeeperListByName.put(name, this);
    }

    public static Map<String, Zookeeper> getZookeeperListByName() {
        return zookeeperListByName;
    }

    public static void clearZookeeperList() {
        zookeeperListByName = new HashMap<>();
        aquariumZookeepersByName = new HashMap<>();
        aviaryZookeepersByName = new HashMap<>();
        dryZookeepersByName = new HashMap<>();
        partWaterPartDryZookeepersByName = new HashMap<>();
        pettingZookeepersByName = new HashMap<>();
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
        numPensAttending++;
    }

    public void decrementNumPensAttending() {
        numPensAttending--;
    }

    public static Map<String, Zookeeper> getAquariumZookeepersByName() {
        return aquariumZookeepersByName;
    }

    public static Map<String, Zookeeper> getAviaryZookeepersByName() {
        return aviaryZookeepersByName;
    }

    public static Map<String, Zookeeper> getDryZookeepersByName() {
        return dryZookeepersByName;
    }

    public static Map<String, Zookeeper> getPartWaterPartDryZookeepersByName() {
        return partWaterPartDryZookeepersByName;
    }

    public static Map<String, Zookeeper> getPettingZookeepersByName() {
        return pettingZookeepersByName;
    }

    public static void setZookeeperListByName(Map<String, Zookeeper> zookeeperListByName) {
        Zookeeper.zookeeperListByName = zookeeperListByName;
    }

    public static void setAquariumZookeepersByName(Map<String, Zookeeper> aquariumZookeepersByName) {
        Zookeeper.aquariumZookeepersByName = aquariumZookeepersByName;
    }

    public static void setAviaryZookeepersByName(Map<String, Zookeeper> aviaryZookeepersByName) {
        Zookeeper.aviaryZookeepersByName = aviaryZookeepersByName;
    }

    public static void setDryZookeepersByName(Map<String, Zookeeper> dryZookeepersByName) {
        Zookeeper.dryZookeepersByName = dryZookeepersByName;
    }

    public static void setPartWaterPartDryZookeepersByName(Map<String, Zookeeper> partWaterPartDryZookeepersByName) {
        Zookeeper.partWaterPartDryZookeepersByName = partWaterPartDryZookeepersByName;
    }

    public static void setPettingZookeepersByName(Map<String, Zookeeper> pettingZookeepersByName) {
        Zookeeper.pettingZookeepersByName = pettingZookeepersByName;
    }

    public String toString() {
        return "Name: " + this.name
                + ", Pen Types: " + this.penTypes
                + ", Number of Pens Attending: " + this.numPensAttending + "\n";
    }
}
