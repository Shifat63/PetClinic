package PetClinic.Controllers;

import PetClinicData.Service.SpecialityService;
import PetClinicData.Service.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/vets"})
public class VetController {
    private final VetService vetService;
    private final SpecialityService specialityService;

    public VetController(VetService vetService, SpecialityService specialityService) {
        this.vetService = vetService;
        this.specialityService = specialityService;
    }

    @RequestMapping({"","/","/index","/index.html"})
    public String index(Model model) throws Exception{
        model.addAttribute("vets", vetService.findAll());
        model.addAttribute("specialities", specialityService.findAll());
        return "vets/index";
    }

    @RequestMapping(value = {"/searchVets"}, method = RequestMethod.POST)
    public String searchVets(@RequestParam("name") String name, @RequestParam("speciality") Long specialityId, Model model) throws Exception{
        model.addAttribute("vets", vetService.findByCriteria(name, specialityId));
        model.addAttribute("specialities", specialityService.findAll());
        return "vets/index";
    }
}
