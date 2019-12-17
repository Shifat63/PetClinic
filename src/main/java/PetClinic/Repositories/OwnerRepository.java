package PetClinic.Repositories;

import PetClinic.Model.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
@Transactional
public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Set<Owner> findByLastName(String lastName);
}
