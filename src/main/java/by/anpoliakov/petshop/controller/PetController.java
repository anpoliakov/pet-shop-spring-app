package by.anpoliakov.petshop.controller;

import by.anpoliakov.petshop.enumClass.TypePet;
import by.anpoliakov.petshop.model.Pet;
import by.anpoliakov.petshop.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        if(!petRepository.existsById(id)){
            return "redirect:/pet";
        }
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

    //EDIT
    @GetMapping("{id}/edit")
    public String getPageEditPet (@PathVariable final long id, Model model){
        if(!petRepository.existsById(id)){
            return "redirect:/pet";
        }
        Pet pet = petRepository.findById(id).get();
        model.addAttribute("pet", pet);
        return "editPet";
    }

    @PostMapping("{id}/edit")
    public String petUpdate(@PathVariable final long id, @RequestParam final String name, @RequestParam final String typePet, @RequestParam final boolean isHomeless, Model model){
        //находим существующий обьект - и обновляем
        Pet pet = petRepository.findById(id).get();
        pet.setName(name);
        pet.setTypePet(TypePet.valueOf(typePet));
        pet.setIsHomeless(isHomeless);
        petRepository.save(pet);
        return "redirect:/pet";
    }

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


