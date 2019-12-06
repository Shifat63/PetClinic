package PetClinicData.Service;

import PetClinicData.Model.Owner;

import java.util.Set;

public interface OwnerService extends CrudService<Owner, Long> {
    Set<Owner> findByLastName (String lastName);
}
