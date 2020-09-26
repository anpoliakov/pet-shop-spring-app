package by.anpoliakov.petshop.model;

import by.anpoliakov.petshop.enumClass.TypePet;

import javax.persistence.*;

@Entity //класс является JPA сущностью
public class Pet {

    @Id //поле является ID
    @GeneratedValue(strategy = GenerationType.AUTO) //автоматическое увеличение ID
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING) //говорю что поле в БД будет являться ENUM
    @Column(name = "typePet", nullable = false)
    private TypePet typePet;

    @Column(name = "isHomeless", nullable = false)
    private boolean isHomeless;

    // У Entity всегда должен быть конструктор по умолчанию
    public Pet() {}

    public Pet(String name, String typePet, boolean isHomeless) {
        this.name = name;
        this.typePet = getTypePetByString(typePet);
        this.isHomeless = isHomeless;
    }

    //Особой роли конструкторы с параметрами, для GJSON не играют (используется default конструктор + внедрение значений через методы get and set)

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setTypePet(TypePet typePet) {
        this.typePet = typePet;
    }

    public boolean getIsHomeless() {
        return isHomeless;
    }

    public void setIsHomeless(boolean isHomeless) {
        this.isHomeless = isHomeless;
    }

    //убрать ? так как enum в mySQL используется напрямую ?
    private TypePet getTypePetByString(String type){
        return TypePet.valueOf(type.toUpperCase());
    }

    @Override
    public String toString() {
        return "PET: ID(Data Base)= " + id + ", name= " + name + ", type= " + typePet + ", isHomeless= " + isHomeless + ".";
    }
}
