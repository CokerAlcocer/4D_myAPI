package utez.edu.mx.myApi.pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findAll(); // Traer todos
    Pet findById(long id); // Traer uno
}
