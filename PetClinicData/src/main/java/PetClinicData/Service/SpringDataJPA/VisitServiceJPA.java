package PetClinicData.Service.SpringDataJPA;

import PetClinicData.Model.Visit;
import PetClinicData.Repositories.VisitRepository;
import PetClinicData.Service.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile({"springdatajpa"})
public class VisitServiceJPA implements VisitService {
    private VisitRepository visitRepository;

    public VisitServiceJPA(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Set<Visit> findAll() throws Exception {
        Set<Visit> visits = new HashSet<>();
        visitRepository.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public Visit findById(Long id) throws Exception {
        return visitRepository.findById(id).get();
    }

    @Override
    public Visit save(Visit object) throws Exception {
        return visitRepository.save(object);
    }

    @Override
    public void delete(Visit object) throws Exception {
        visitRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        visitRepository.deleteById(id);
    }
}
