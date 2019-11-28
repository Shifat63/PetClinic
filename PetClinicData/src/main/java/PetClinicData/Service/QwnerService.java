package PetClinicData.Service;

import PetClinicData.Model.Owner;

public interface QwnerService extends CrudService<Owner, Long> {
    Owner findByLastName (String lastName);
}
