package by.anpoliakov.petshop.controller;

import by.anpoliakov.petshop.entity.Pet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/pet") // по URL получаю допуск: http://localhost:8080/pet
public class PetController {
    private List<Pet> petsList;

    public PetController() {
        petsList = new ArrayList<>();
        petsList.add(new Pet("Tom", "cat", false));
        petsList.add(new Pet("Monti","dog", true));
    }

    //GET DEFAULT ALL
    @GetMapping
    public List<Pet> getAllPets(){
        return petsList;
    }

    //GET
    @GetMapping("{index:\\d+}") //Regex: error 404 if there isn't int value
    public Pet getPetByIndex(@PathVariable final int index){
        return petsList.get(index);
    }

    //POST
    @PostMapping
    public Pet addPet(@RequestBody final Pet pet){
        petsList.add(pet);
        return pet;

        /* Request for the console:
        *
        * fetch('/pet', {method: 'POST',
        *                  headers: { 'Content-Type': 'application/json;charset=utf-8' },
        *                  body: JSON.stringify({ name: 'V1', typePet: 'dog', homeless:true })}).then(result => result.json().then(console.log))
        *
        * */
    }

    //PUT - обновить весь обьект
    @PutMapping("{index}")
    public Pet updateDataPet(@PathVariable final int index, @RequestBody final Pet pet){
        return petsList.set(index, pet);

        /* Предназначен для вставки нового обьекта в определённое место
        *
        * fetch('/pet/0', {method: 'PUT',
        *                  headers: { 'Content-Type': 'application/json;charset=utf-8' },
        *                  body: JSON.stringify({ name: 'Moni', typePet: 'dog', homeless:true })}).then(result => result.json().then(console.log))
        *
        * */
    }

    //DELETE
    @DeleteMapping("{index}")
    public void deletePet(@PathVariable int index){
        petsList.remove(index);

        /* Запрос для консоли браузера:
        *
        * fetch('/pet/0', {method: 'DELETE'}).then(res => console.log(res))
        *
        * */
    }

    //PATCH - обновить часть обьекта
    @PatchMapping(path = "/{index}", consumes = "application/json-patch+json")
    public Pet updatePartDataPet(@PathVariable final int index, @RequestBody final Pet pet){


        /*
        fetch("/pet/0", {
            method: "PATCH",
            headers: {"Content-Type": "application/json-patch+json"},
            body: JSON.stringify({"op":"replace","path":"/name","value":"BOBIK"})
          }).then(result => result.json().then(console.log));

        */
    }
}
