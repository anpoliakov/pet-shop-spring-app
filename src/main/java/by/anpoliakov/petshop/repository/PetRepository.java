package by.anpoliakov.petshop.repository;

import by.anpoliakov.petshop.model.PetTable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PetRepository extends CrudRepository<PetTable, Long> {
    List<PetTable> findByName(String name);
}
