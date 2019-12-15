package PetClinicData.Service.SpringDataJPA;

import PetClinicData.Model.Speciality;
import PetClinicData.Repositories.SpecialityRepository;
import PetClinicData.Service.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile({"springdatajpa"})
public class SpecialityServiceJPA implements SpecialityService {
    private SpecialityRepository specialityRepository;

    public SpecialityServiceJPA(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Set<Speciality> findAll() throws Exception {
        Set<Speciality> specialities = new HashSet<>();
        specialityRepository.findAll().forEach(specialities::add);
        return specialities;
    }

    @Override
    public Speciality findById(Long id) throws Exception {
        return specialityRepository.findById(id).get();
    }

    @Override
    public Speciality save(Speciality object) throws Exception {
        return specialityRepository.save(object);
    }

    @Override
    public void delete(Speciality object) throws Exception {
        specialityRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        specialityRepository.deleteById(id);
    }
}
