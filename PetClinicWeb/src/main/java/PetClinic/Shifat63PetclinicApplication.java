package PetClinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"PetClinicData.Service", "PetClinic"})
public class Shifat63PetclinicApplication {

    public static void main(String[] args) {
        SpringApplication.run(Shifat63PetclinicApplication.class, args);
    }

}
