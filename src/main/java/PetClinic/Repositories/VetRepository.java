package PetClinic.Repositories;

import PetClinic.Model.Vet;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface VetRepository extends CrudRepository<Vet, Long>, JpaSpecificationExecutor<Vet> {
}
