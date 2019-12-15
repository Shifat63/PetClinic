package PetClinicData.Repositories;

import PetClinicData.Model.Visit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface VisitRepository extends CrudRepository<Visit, Long> {
}
