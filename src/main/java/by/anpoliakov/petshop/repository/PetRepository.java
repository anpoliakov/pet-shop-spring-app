package by.anpoliakov.petshop.repository;

import by.anpoliakov.petshop.model.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends CrudRepository<Pet, Long> {
    //новый метод, которого нету в стандартном списке методов интерфейса "CrudRepository"
    //spring сам преобразует по имени метода интерфейса в нужную команду SQL (смотри урок https://www.youtube.com/watch?v=nyFLX3q3poY) там есть ссылка на список
}
