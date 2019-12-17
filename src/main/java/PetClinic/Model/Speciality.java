package PetClinic.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "speciality")
public class Speciality extends BaseEntity {
    @NotBlank(message = "Description must not be empty")
    @Size(min = 1, max = 25, message = "Description must be between 1 to 25 characters")
    @Column(name = "description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
