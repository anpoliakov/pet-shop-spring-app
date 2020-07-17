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
    public Pet addPet(@RequestBody Pet pet){
        petsList.add(pet);
        return pet; //исправить ?

        /*
        * Запрос в консоли для теста:
        *
        * fetch('/pet',{
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ name: 'Moni', type: 'dog', homeless:true })
              }).then(result => result.json().then(console.log))
        * */
    }

    @PutMapping("{id}")
    public Pet updateDataPet(@PathVariable int id, @RequestBody Pet pet){
        return petsList.set(id, pet);
    }

    @DeleteMapping("{id}")
    public void deletePet(@PathVariable int id){
        petsList.remove(id);

        /*
        * Пример запроса для теста
        * fetch('/pet/0', {method: 'DELETE'}).then(res => console.log(res))
        *
        * */
    }
}
