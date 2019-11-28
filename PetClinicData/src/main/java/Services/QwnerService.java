package Services;

import Model.Owner;

import java.util.Set;

public interface QwnerService {
    Owner findByLastName (String lastName);
    Owner findById(long Id);
    Owner save(Owner owner);
    Set<Owner> findAll();
}
