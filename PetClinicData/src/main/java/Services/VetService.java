package Services;

import Model.Vet;

import java.util.Set;

public interface VetService {
    Vet findById(long Id);
    Vet save(Vet vet);
    Set<Vet> findAll();
}
