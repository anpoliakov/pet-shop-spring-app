package by.anpoliakov.petshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //Говорим, что это класс является JPA сущностью
public class PetTable {

    @Id //Поле будет являться индификатором
    @GeneratedValue(strategy = GenerationType.AUTO) //Поле будет автоматически увеличиваться
    private Long id;
    private String name, type;
    private boolean isHomeLess;

    public PetTable() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isHomeLess() {
        return isHomeLess;
    }

    public void setHomeLess(boolean homeLess) {
        isHomeLess = homeLess;
    }
}
