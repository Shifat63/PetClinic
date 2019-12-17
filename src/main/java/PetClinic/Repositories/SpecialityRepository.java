package PetClinic.Repositories;

import PetClinic.Model.Speciality;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SpecialityRepository extends CrudRepository<Speciality, Long> {
}
