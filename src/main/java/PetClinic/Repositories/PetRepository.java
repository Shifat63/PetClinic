package PetClinic.Repositories;

import PetClinic.Model.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PetRepository extends CrudRepository<Pet, Long> {
}
