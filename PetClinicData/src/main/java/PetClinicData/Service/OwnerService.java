package PetClinicData.Service;

import PetClinicData.Model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName (String lastName);
}
