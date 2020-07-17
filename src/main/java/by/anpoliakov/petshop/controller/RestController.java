package by.anpoliakov.petshop.controller;

import by.anpoliakov.petshop.Entity.Pet;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/pet")
public class RestController {
    private List<Pet> petsList;

    @PostConstruct
    private void myInitMethod(){
        petsList = new ArrayList<>();
        petsList.add(new Pet("Tom","cat", false));
        petsList.add(new Pet("Monti","elephant", true));
    }

    @GetMapping
    public List<Pet> getAllPets(){
        return petsList;
    }

    @GetMapping("{idPet:\\d+}") //404 если ввели не int
    public Pet getPet(@PathVariable int idPet){
        return petsList.get(idPet); //а что если не нашли нужного id ?
    }

    @PostMapping
    public List<Pet> addPet(@RequestBody ){

    }
}
