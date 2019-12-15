package PetClinicData.Model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pet")
public class Pet extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "owner_id")
    @NotNull(message = "There must be no pet without owner")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "type_id")
    @NotNull(message = "Every pet must have a pet type")
    private PetType petType;

    @PastOrPresent(message = "Date of birth must be present date or any past date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @NotBlank(message = "Name must not be empty")
    @Size(min = 1, max = 20, message = "Name must be between 1 to 20 characters")
    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }
}
