package PetClinic.Service;

import PetClinic.Model.Vet;

import java.util.Set;

public interface VetService extends CrudService<Vet, Long> {
    public Set<Vet> findByCriteria(String name, Long specialityId) throws Exception;
}
