package PetClinicData.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "owner")
public class Owner extends Person {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets;

    @NotBlank(message = "Address must not be empty")
    @Size(min = 1,max = 255, message = "Address must be between 1 to 255 characters")
    @Column(name = "address")
    private String address;

    @NotBlank(message = "City must not be empty")
    @Size(min = 1, max = 255, message = "City must be between 1 to 255 characters")
    @Column(name = "city")
    private String city;

    @NotBlank(message = "Telephone must not be empty")
    @Size(min = 5, max = 20, message = "Telephone must be between 5 to 20 characters")
    @Pattern(regexp="([0-9]*)", message = "Telephone must contain numbers only.")
    @Column(name = "telephone")
    private String telephone;

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
