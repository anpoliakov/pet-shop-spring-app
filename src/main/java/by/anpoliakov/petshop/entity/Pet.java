package by.anpoliakov.petshop.entity;

import by.anpoliakov.petshop.enumClasses.TypePet;

public class Pet {
    private String name;
    private TypePet typePet;
    private boolean ishomeless;

    public Pet() {}

    public Pet(String name, String typePet, boolean ishomeless) {
        this.name = name;
        this.typePet = getTypePetByString(typePet);
        this.ishomeless = ishomeless;
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

    public boolean isIshomeless() {
        return ishomeless;
    }

    public void setIshomeless(boolean ishomeless) {
        this.ishomeless = ishomeless;
    }

    private TypePet getTypePetByString(String type){
        return TypePet.valueOf(type.toUpperCase());
    }

    @Override
    public String toString() {
        return "PET: " + "name= " + name + ", type= " + typePet + ", isHomeLess= " + ishomeless + ".";
    }
}
