package PetClinic.Service;

import PetClinic.Model.Owner;

import java.util.Set;

public interface OwnerService extends CrudService<Owner, Long> {
    Set<Owner> findByLastName (String lastName) throws Exception;
}
