package PetClinicData.Repositories;

import PetClinicData.Model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
