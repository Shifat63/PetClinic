package PetClinic.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "vet")
public class Vet extends Person {
    @ManyToMany
    @JoinTable(name = "vet_speciality", joinColumns = @JoinColumn(name = "vet_id"), inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    private Set<Speciality> specialities;

    public Set<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
    }
}
