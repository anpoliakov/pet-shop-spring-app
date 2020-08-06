package by.anpoliakov.petshop.entity;

import by.anpoliakov.petshop.enumClass.TypePet;

public class Pet {
    private String name;
    private TypePet typePet;
    private boolean isHomeLess;

    public Pet() {}

    public Pet(String name, String typePet, boolean isHomeLess) {
        this.name = name;
        this.typePet = getTypePetByString(typePet);
        this.isHomeLess = isHomeLess;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypePet getTypePet() {
        return typePet;
    }

    public void setTypePet(String typePet) {
        this.typePet = getTypePetByString(typePet);
    }

    public boolean isHomeLess() {
        return isHomeLess;
    }

    public void setHomeLess(boolean homeLess) {
        this.isHomeLess = homeLess;
    }

    private TypePet getTypePetByString(String type){
        return TypePet.valueOf(type.toUpperCase());
    }

    @Override
    public String toString() {
        return "PET: " + "name= " + name + ", type= " + typePet + ", isHomeLess= " + isHomeLess + ".";
    }
}
