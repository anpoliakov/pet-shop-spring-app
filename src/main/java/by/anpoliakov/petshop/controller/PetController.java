package by.anpoliakov.petshop.controller;

import by.anpoliakov.petshop.model.Pet;
import by.anpoliakov.petshop.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetRepository petRepository;          //репозиторий для работы с БД

    public PetController() {}

    //GET ALL PETS (default method)               //любая функция при использовании thymeleaf возвращает String (имя шаблона)
    @GetMapping                                   //or @GetMapping("/") - тоже самое (вызов главной странички при переходе на сайт)
    public String getAllPets(Model model){
        model.addAttribute("pets", petRepository.findAll());
        return "home";                            //имя страницы html в папке templates (к которой передаётся model)
    }

    //GET ONE PET BY ID
    @GetMapping("{id}")
    public String getPetByID(Model model, @PathVariable final long id){
        Pet pet = petRepository.findById(id).get();
        model.addAttribute("pet", pet);
        return "infoPet";
    }

    //PAGE FOR ENTER DATA ABOUT NEW PET
    @GetMapping("/add")
    public String getPageAdd(Model model){
        return "addPet";
    }

    //CREATE NEW PET (PAGE "addPet.html")
    @PostMapping("/add")
    public String addPet(@RequestParam String name, @RequestParam String typePet, @RequestParam boolean isHomeless, Model model){
        Pet newPet = new Pet(name, typePet, isHomeless);
        petRepository.save(newPet);
        //переадресация пользователя на другой шаблон
        return "redirect:/pet";
    }

    //POST for UPDATE
//    @PostMapping("update/{id}")
//    public Pet addPet(@PathVariable final long id, @RequestBody Pet petFromClient){
//        try{
//            Pet petFromDB = petRepository.findById(id).get();
//            petFromDB.setName(petFromClient.getName());
//            petFromDB.setTypePet(petFromClient.getTypePet());
//            petFromDB.setIsHomeless(petFromClient.getIsHomeless());
//            return petRepository.save(petFromDB);
//        }catch (NoSuchElementException e){
//            System.out.println("EXCEPTION MY");
//            return null;
//        }
//
//        /* fetch('/pet/update/1',{method: 'POST', headers: { 'Content-Type': 'application/json' },
//                        body: JSON.stringify({ name: 'Moni', typePet: 'DOG', isHomeless:true})
//                        }).then(result => result.json().then(console.log)) */
//    }

    //DELETE
    @GetMapping("/del/{id}")
    public String deletePet(@PathVariable final long id){
        petRepository.deleteById(id);
        return "redirect:/pet";
        /* Запрос для консоли браузера: fetch('/pet/0', {method: 'DELETE'}).then(res => console.log(res)) */
    }
}



/* QUESTION:
    #PATCH и PUT при работе с JPA не работает ? пишет: "Request method 'PATCH' not supported" -> есть только ВСТАВКА, УДАЛЕНИЕ и ОБНОВЛЕНИЕ ?

*/

/* Что улучшить:
*  Подгрузка возможных типов из properties файла и создание на неё ENUM класса ?
*  Множественне выделение и удаление
*
* */


