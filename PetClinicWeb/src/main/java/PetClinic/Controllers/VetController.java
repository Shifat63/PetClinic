package PetClinic.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VetController {

    @RequestMapping({"/vets","/vest/index","vets/index.html"})
    public String index(){
        return "vets/index";
    }
}
