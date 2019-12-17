package PetClinic.Service.SpringDataJPA;

import PetClinic.Model.Vet;
import PetClinic.Repositories.VetRepository;
import PetClinic.Service.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Profile({"springdatajpa"})
public class VetServiceJPA implements VetService {
    private VetRepository vetRepository;

    public VetServiceJPA(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public Set<Vet> findAll() throws Exception {
        Set<Vet> vets = new HashSet<>();
        vetRepository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public Vet findById(Long id) throws Exception {
        return vetRepository.findById(id).get();
    }

    @Override
    public Vet save(Vet object) throws Exception {
        return vetRepository.save(object);
    }

    @Override
    public void delete(Vet object) throws Exception {
        vetRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        vetRepository.deleteById(id);
    }

    @Override
    public Set<Vet> findByCriteria(String name, Long specialityId) throws Exception
    {
        Set<Vet> vets = new HashSet<>();
        vetRepository.findAll(new Specification<Vet>()
        {
            @Override
            public Predicate toPredicate(Root<Vet> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)
            {
                List<Predicate> predicates = new ArrayList<>();
                if(name!=null)
                {
                    predicates.add(criteriaBuilder.or(
                            (criteriaBuilder.like(root.get("firstName"), "%"+name+"%")),
                            (criteriaBuilder.like(root.get("lastName"), "%"+name+"%")))
                    );
                }
                if(specialityId!=null)
                {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.join("specialities").get("id"), specialityId)));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }).forEach(vets::add);
        return vets;
    }
}
