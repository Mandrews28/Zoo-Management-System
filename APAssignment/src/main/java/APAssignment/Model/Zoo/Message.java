package APAssignment.Model.Zoo;

public enum Message {
    ERROR_LOADING_FILE("No saved file available. Please select another option"),

    COMPATIBLE("Compatible"),
    SUCCESS("Success"),

    ADD_ANIMAL_TO_PEN_INCOMPATIBLE_SPECIES("Animal cannot be added because of incompatible species"),
    ADD_ANIMAL_TO_PEN_INCOMPATIBLE_PEN_TYPES("Animal cannot be added because of incompatible pen types"),
    ADD_ANIMAL_TO_PEN_ANIMAL_ALREADY_IN_PEN("Animal is already in this Pen!"),
    ADD_ANIMAL_TO_PEN_NO_SPACE_IN_PEN("Not enough space in pen for this animal"),
    REMOVE_ANIMAL_FROM_PEN_ANIMAL_NOT_IN_PEN("Animal is not in this pen!"),

    ADD_ZOOKEEPER_TO_PEN_INCOMPATIBLE_PEN_TYPES("Zookeeper cannot be assigned due to incompatible pen types"),
    REMOVE_ZOOKEEPER_ANIMALS_STILL_IN_PEN("Cannot remove the zookeeper because animals are currently in the pen"),
    REMOVE_ZOOKEEPER_NO_ZOOKEEPER_IN_PEN("There is no zookeeper in this pen to remove!"),
    NO_ZOOKEEPER_ASSIGNED_TO_PEN("A zookeeper must be assigned to this pen before animals are added"),

    ADD_COMPATIBLE_SPECIES_INCOMPATIBLE_PEN_TYPES("These species cannot be compatible as they have incompatible animal types"),
    ADD_COMPATIBLE_SPECIES_ALREADY_COMPATIBLE("These species are already compatible!"),

    AUTO_ALLOCATE_ANIMALS_NO_ANIMALS_NOT_IN_PEN("All animals are already in a pen!"),
    AUTO_ALLOCATE_ZOOKEEPERS_NO_PENS_UNMANNED("All pens already have a zookeeper!"),
    ;

    public String message;

    Message(String message) {
        this.message = message;
    }

}
