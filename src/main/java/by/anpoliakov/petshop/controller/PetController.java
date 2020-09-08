package by.anpoliakov.petshop.controller;

import by.anpoliakov.petshop.model.Pet;
import by.anpoliakov.petshop.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    public PetController() {}

    // GET ALL PETS
    @GetMapping //or @GetMapping("/") - тоже самое (вызов главной странички при переходе на сайт)
    public Iterable<Pet> getAllPets(){
       return petRepository.findAll();
    }

    //GET ONE PET
    @GetMapping("{id}")
    public String getPetByID(Model model, @PathVariable final long id){
        Pet pet = petRepository.findById(id).get();
        model.addAttribute("testPet", pet.getName());
        return "index"; //указываем имя шаблона, который необходимо будет открыть
    }

    //POST
    @PostMapping
    public Pet addPet(@RequestBody Pet pet){
        return petRepository.save(pet);

        /* fetch('/pet',{method: 'POST', headers: { 'Content-Type': 'application/json' },
                        body: JSON.stringify({ name: 'Moni', typePet: 'DOG', isHomeless:true})
                        }).then(result => result.json().then(console.log))
        */
    }

    //POST for UPDATE
    @PostMapping("update/{id}")
    public Pet addPet(@PathVariable final long id, @RequestBody Pet petFromClient){
        try{
            Pet petFromDB = petRepository.findById(id).get();
            petFromDB.setName(petFromClient.getName());
            petFromDB.setTypePet(petFromClient.getTypePet());
            petFromDB.setIsHomeless(petFromClient.getIsHomeless());
            return petRepository.save(petFromDB);
        }catch (NoSuchElementException e){
            System.out.println("EXCEPTION MY");
            return null;
        }

        /* fetch('/pet/update/1',{method: 'POST', headers: { 'Content-Type': 'application/json' },
                        body: JSON.stringify({ name: 'Moni', typePet: 'DOG', isHomeless:true})
                        }).then(result => result.json().then(console.log)) */
    }

    //DELETE
    @DeleteMapping("{id}")
    public void deletePet(@PathVariable final long id){
        petRepository.deleteById(id);
        /* Запрос для консоли браузера: fetch('/pet/0', {method: 'DELETE'}).then(res => console.log(res)) */
    }
}



//QUESTION: PATCH и PUT при работе с JPA не работает ? пишет: "Request method 'PATCH' not supported" -> есть только ВСТАВКА, УДАЛЕНИЕ и ОБНОВЛЕНИЕ ?
