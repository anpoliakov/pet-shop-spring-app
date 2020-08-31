package by.anpoliakov.petshop.repository;

import by.anpoliakov.petshop.model.Pet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PetRepository extends CrudRepository<Pet, Long> {
    //новый метод, которого нету в стандартном списке методов
    //spring сам преобразует по имени метода в нужную команду SQL (смотри урок https://www.youtube.com/watch?v=nyFLX3q3poY)
    List<Pet> findByName(String name);
}
