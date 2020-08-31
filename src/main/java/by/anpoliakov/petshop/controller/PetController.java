package by.anpoliakov.petshop.controller;

import by.anpoliakov.petshop.model.Pet;
import by.anpoliakov.petshop.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetRepository petRepository;
    private List<Pet> petsList;

    public PetController() {
        petsList = new ArrayList<>();
        petsList.add(new Pet("Tom", "cat", false));
        petsList.add(new Pet("Monti","dog", true));
    }

    @GetMapping("/getAll")
    public Iterable<Pet> gettAll(){
        Iterable<Pet> all = petRepository.findAll();
        return all;
    }

    //поиск питомцев по имени
    @GetMapping("find/{name}")
    public List<Pet> findByNamePet(@PathVariable final String name){
        List<Pet> byName = petRepository.findByName(name);
        return byName;
    }

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
        petRepository.save(pet);
        return pet;

        /* Запрос для консоли браузера:
        *
        * fetch('/pet',{method: 'POST', headers: { 'Content-Type': 'application/json' },
                        body: JSON.stringify({ name: 'Moni', typePet: 'dog', homeless:true })
                        }).then(result => result.json().then(console.log))
        * */
    }

    //PUT
    @PutMapping("{index}")
    public Pet updateDataPet(@PathVariable final int index, @RequestBody final Pet pet){
        return petsList.set(index, pet);
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

    //PATCH
//    @PatchMapping(path = "{index}", consumes = "application/json-patch+json")
//    public Pet updatePartDataPet(@PathVariable final int index, @RequestBody final JsonPatch jsonPatch){
//        Pet updatingPet = petsList.get(index);
//
//        try {
//            Pet newPet = applyPatchToPet(jsonPatch, updatingPet);
//            return newPet;
//        } catch (JsonPatchException | JsonProcessingException e) {
//            return null;
//        }
//
//        /*
//        fetch("/pet/0", {
//            method: "PATCH",
//            headers: {"Content-Type": "application/json-patch+json"},
//            body: JSON.stringify({"op":"replace","path":"/name","value":"BOBIK"})
//          }).then(result => result.json().then(console.log));
//
//
//         let objectPatch = {
//            op: "replace",
//            path: "/name",
//            value: "BOBIK"
//         }
//
//            fetch("/pet/0", {
//            method: "PATCH",
//            headers: {"Content-Type": "application/json-patch+json"},
//            body: JSON.stringify(objectPatch)
//            }).then(result => result.json().then(console.log));
//        */
//    }

//    private Pet applyPatchToPet(JsonPatch patch, Pet targetPet) throws JsonPatchException, JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode patched = patch.apply(mapper.convertValue(targetPet, JsonNode.class));
//        return mapper.treeToValue(patched, Pet.class);
//    }

}
