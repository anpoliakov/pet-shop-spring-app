package by.anpoliakov.petshop.Entity;

public class Pet {
    private String name;
    private String type;
    private boolean homeless;

    public Pet(String name, String type, boolean homeless) {
        this.name = name;
        this.type = type;
        this.homeless = homeless;
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

    public boolean isHomeless() {
        return homeless;
    }

    public void setHomeless(boolean homeless) {
        this.homeless = homeless;
    }

    @Override
    public String toString() {
        return "PET: " +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", homeless=" + homeless +
                '.';
    }
}
