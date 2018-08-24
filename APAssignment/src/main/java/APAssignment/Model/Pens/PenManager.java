package APAssignment.Model.Pens;

import APAssignment.Model.Animals.Animal;
import APAssignment.Model.Zoo.ConvertInput;
import APAssignment.Model.Zoo.Message;
import APAssignment.Model.Zookeeper;

public abstract class PenManager {

    public static Message addAnimal(String animalName, String penId) {
        Animal animal = ConvertInput.animal(animalName);
        Pen pen = ConvertInput.pen(penId);
        return addAnimal(animal, pen);
    }

    public static Message addAnimal(Animal animal, Pen pen) {
        if (pen.getZookeeper() == null)
            return Message.NO_ZOOKEEPER_ASSIGNED_TO_PEN;

        Message isCompatible = ChangePenTraits.isAnimalCompatibleWithPen(animal, pen);
        if (isCompatible.equals(Message.COMPATIBLE)) {
            Message isSpace = pen.isSpaceInPen(animal);

            if (isSpace.equals(Message.COMPATIBLE)) {
                pen.addAnimal(animal);
                return Message.SUCCESS;
            }
            else {
                return isSpace;
            }

        } else {
            return isCompatible;
        }
    }

    public static Message removeAnimal(String animalName, String penId) {
        Animal animal = ConvertInput.animal(animalName);
        Pen pen = ConvertInput.pen(penId);
        Message canAnimalBeRemoved = ChangePenTraits.canAnimalBeRemovedFromPen(animal, pen);
        if (canAnimalBeRemoved.equals(Message.COMPATIBLE)) {
            pen.removeAnimal(animal);
            return Message.SUCCESS;
        } else {
            return canAnimalBeRemoved;
        }
    }

    public static Message addZookeeper(String zookeeperName, String penId) {
        Zookeeper zookeeper = ConvertInput.zookeeper(zookeeperName);
        Pen pen = ConvertInput.pen(penId);
        return addZookeeper(zookeeper, pen);
    }

    public static Message addZookeeper(Zookeeper zookeeper, Pen pen) {
        Message isCompatible = ChangePenTraits.isZookeeperCompatibleWithPen(zookeeper, pen);
        if (isCompatible.equals(Message.COMPATIBLE)) {
            removeZookeeper(pen);
            pen.setZookeeper(zookeeper);
            zookeeper.incrementNumPensAttending();
            return Message.SUCCESS;
        } else {
            return isCompatible;
        }
    }

    public static void removeZookeeper(Pen pen) {
        Zookeeper zookeeper = pen.getZookeeper();
        if (zookeeper != null) {
            zookeeper.decrementNumPensAttending();
            pen.setZookeeper(null);
        }
    }

    public static Message canZookeeperBeRemovedFromPen(String penId) {
        Pen pen = ConvertInput.pen(penId);
        return ChangePenTraits.canZookeeperBeRemovedFromPen(pen);
    }
}
