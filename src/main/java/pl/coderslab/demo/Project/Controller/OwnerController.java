package pl.coderslab.demo.Project.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.demo.Project.school.School;
import pl.coderslab.demo.Project.school.SchoolRepository;
import pl.coderslab.demo.Project.users.*;

import java.util.List;

@Controller
@RequestMapping("/owner")
public class OwnerController {
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final SchoolRepository schoolRepository;

    public OwnerController(UserService userService, RoleRepository roleRepository, UserRepository userRepository, SchoolRepository schoolRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.schoolRepository = schoolRepository;
    }

    @RequestMapping("/homepage")
    public String homepage() {
        return "owner/owner";
    }

    @RequestMapping("/directors")
    public String directors(Model model) {
        Role role = roleRepository.findByName("ROLE_DIRECTOR");
        List<AppUser> allByRoles = userRepository.findAllByRoles(role);
        model.addAttribute("directors", allByRoles);
        return "owner/allDirectors";
    }

    @GetMapping("/createDirector")
    public String createDirector(Model model) {
        AppUser appUser = new AppUser();
        model.addAttribute("appUser", appUser);
        model.addAttribute("Role", "Dyrektora");
        return "createUser";
    }

    @PostMapping("/createDirector")
    public String createDirector(AppUser appUser) {
        School school = new School();
        school.setDirector(appUser);
        schoolRepository.save(school);
        userService.saveDirector(appUser);
        return "redirect:/owner/directors";
    }
}
