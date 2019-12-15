package PetClinicData.Model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "visit")
public class Visit extends BaseEntity {
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @FutureOrPresent(message = "Date must me present or future date")
    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    @NotNull(message = "No visit possible without a pet")
    private Pet pet;

    @NotBlank(message = "Description must not be empty")
    @Size(min = 1, max = 255, message = "Description must be between 1 to 255 characters")
    @Column(name = "desciption")
    private String description;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
