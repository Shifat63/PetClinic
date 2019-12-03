package PetClinicData.Repositories;

import PetClinicData.Model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
