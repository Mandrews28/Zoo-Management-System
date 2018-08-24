package APAssignment.Model.Zoo;

import APAssignment.Model.Animals.Animal;
import APAssignment.Model.Animals.Species;
import APAssignment.Model.Pens.*;
import APAssignment.Model.Zookeeper;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.Map;

public class Zoo {
    private Map<String, Zookeeper> zookeeperListByName;
    private Map<String, Species> speciesListByName;
    private Map<String, Pen> penListById;
    private Map<String, Animal> animalListByName;
    private ArrayList<String> compatibleSpecies;

    private int numOfAquariums;
    private int numOfAviaries;
    private int numOfDryPens;
    private int numOfPartWaterPartDryPens;
    private int numOfPettingPens;

    public Zoo() {
        this.zookeeperListByName = Zookeeper.getZookeeperListByName();
        this.speciesListByName = Species.getSpeciesListByName();
        this.penListById = Pen.getPenListById();
        this.animalListByName = Animal.getAnimalListByName();
        this.compatibleSpecies = CompatibleSpecies.getList();

        this.numOfAquariums = Aquarium.getNumOfPens();
        this.numOfAviaries = Aviary.getNumOfPens();
        this.numOfDryPens = DryPen.getNumOfPens();
        this.numOfPartWaterPartDryPens = PartWaterPartDry.getNumOfPens();
        this.numOfPettingPens = PettingPen.getNumOfPens();
    }

    public static void setStaticVariablesWithJsonString(Zoo zoo) {
        Zookeeper.setZookeeperListByName(zoo.getZookeeperListByName());
        Species.setSpeciesListByName(zoo.getSpeciesListByName());
        Pen.setPenListById(zoo.getPenListById());
        Animal.setAnimalListByName(zoo.getAnimalListByName());
        CompatibleSpecies.setList(zoo.getCompatibleSpecies());

        setAnimalListWithNoPen();
        setPenListWithNoZookeeper();

        Aquarium.setNumOfPens(zoo.getNumOfAquariums());
        Aviary.setNumOfPens(zoo.getNumOfAviaries());
        DryPen.setNumOfPens(zoo.getNumOfDryPens());
        PartWaterPartDry.setNumOfPens(zoo.getNumOfPartWaterPartDryPens());
        PettingPen.setNumOfPens(zoo.getNumOfPettingPens());
    }

    public static void setPenListWithNoZookeeper() {
        ArrayList<Pen> penListWithNoZookeeper = new ArrayList<>();
        Map<String, Pen> penListById = Pen.getPenListById();
        for (Pen pen : penListById.values()) {
            if (pen.getZookeeper() == null) {
                penListWithNoZookeeper.add(pen);
            }
        }
        Pen.setPenListWithNoZookeeper(penListWithNoZookeeper);
    }

    public static void setAnimalListWithNoPen() {
        ArrayList<Animal> animalListWithNoPen = new ArrayList<>();
        Map<String, Animal> animalListByName = Animal.getAnimalListByName();
        for (Animal animal : animalListByName.values()) {
            if (animal.getCurrentPen() == null) {
                animalListWithNoPen.add(animal);
            }
        }
        Animal.setAnimalListWithNoPen(animalListWithNoPen);
    }

    public Map<String, Animal> getAnimalListByName() {
        return animalListByName;
    }

    public Map<String, Species> getSpeciesListByName() {
        return speciesListByName;
    }

    public Map<String, Pen> getPenListById() {
        return penListById;
    }

    public Map<String, Zookeeper> getZookeeperListByName() {
        return zookeeperListByName;
    }

    public ArrayList<String> getCompatibleSpecies() {
        return compatibleSpecies;
    }

    public void setAnimalListByName(Map<String, Animal> animalListByName) {
        this.animalListByName = animalListByName;
    }

    public void setSpeciesListByName(Map<String, Species> speciesListByName) {
        this.speciesListByName = speciesListByName;
    }

    public void setPenListById(Map<String, Pen> penListById) {
        this.penListById = penListById;
    }

    public void setZookeeperListByName(Map<String, Zookeeper> zookeeperListByName) {
        this.zookeeperListByName = zookeeperListByName;
    }

    public void setCompatibleSpecies(ArrayList<String> compatibleSpecies) {
        this.compatibleSpecies = compatibleSpecies;
    }

    public int getNumOfAquariums() {
        return numOfAquariums;
    }

    public void setNumOfAquariums(int numOfAquariums) {
        this.numOfAquariums = numOfAquariums;
    }

    public int getNumOfAviaries() {
        return numOfAviaries;
    }

    public void setNumOfAviaries(int numOfAviaries) {
        this.numOfAviaries = numOfAviaries;
    }

    public int getNumOfDryPens() {
        return numOfDryPens;
    }

    public void setNumOfDryPens(int numOfDryPens) {
        this.numOfDryPens = numOfDryPens;
    }

    public int getNumOfPartWaterPartDryPens() {
        return numOfPartWaterPartDryPens;
    }

    public void setNumOfPartWaterPartDryPens(int numOfPartWaterPartDryPens) {
        this.numOfPartWaterPartDryPens = numOfPartWaterPartDryPens;
    }

    public int getNumOfPettingPens() {
        return numOfPettingPens;
    }

    public void setNumOfPettingPens(int numOfPettingPens) {
        this.numOfPettingPens = numOfPettingPens;
    }

    @Override
    public String toString() {
        return "Zoo{" +
                "zookeeperListByName=" + zookeeperListByName +
                ", speciesListByName=" + speciesListByName +
                ", penListById=" + penListById +
                ", animalListByName=" + animalListByName +
                ", compatibleSpecies=" + compatibleSpecies +
                ", numOfAquariums=" + numOfAquariums +
                ", numOfAviaries=" + numOfAviaries +
                ", numOfDryPens=" + numOfDryPens +
                ", numOfPartWaterPartDryPens=" + numOfPartWaterPartDryPens +
                ", numOfPettingPens=" + numOfPettingPens +
                '}';
    }
}
