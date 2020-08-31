package by.anpoliakov.petshop.model;

import by.anpoliakov.petshop.enumClass.TypePet;

import javax.persistence.*;

@Entity //класс является JPA сущностью
public class Pet {

    @Id //поле является ID
    @GeneratedValue(strategy = GenerationType.AUTO) //автоматическое увеличение ID
    private int id;

    private String name;

    @Enumerated(EnumType.STRING)
    private TypePet typePet;

    private boolean isHomeLess;

    // У Entity всегда должен быть конструктор по умолчанию!
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
