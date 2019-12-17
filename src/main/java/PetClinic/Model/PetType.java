package PetClinic.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "type")
public class PetType extends BaseEntity {
    @NotBlank(message = "Name must not be empty")
    @Size(min = 1, max = 20, message = "Name must be between 1 to 20 characters")
    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
