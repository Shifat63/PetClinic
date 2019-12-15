package PetClinicData.Repositories;

import PetClinicData.Model.PetType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
