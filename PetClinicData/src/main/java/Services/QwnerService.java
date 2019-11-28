package Services;

import Model.Owner;

public interface QwnerService extends CrudService<Owner, Long> {
    Owner findByLastName (String lastName);
}
