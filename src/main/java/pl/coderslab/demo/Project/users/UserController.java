package pl.coderslab.demo.Project.users;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/createStudent")
    public String createStudent(Model model) {
        AppUser appUser = new AppUser();
        model.addAttribute("AppUser", appUser);
        model.addAttribute("Role", "Studenta");
        return "createUser";
    }

    @PostMapping("/createStudent")
    @ResponseBody
    public String createStudent(AppUser appUser) {
        userService.saveStudent(appUser);
        return "Utworzono studenta";
    }

    @GetMapping("/createTeacher")
    public String createTeacher(Model model) {
        AppUser appUser = new AppUser();
        model.addAttribute("AppUser", appUser);
        model.addAttribute("Role", "Nauczyciela");
        return "createUser";
    }

    @PostMapping("/createTeacher")
    @ResponseBody
    public String createTeacher(AppUser appUser) {
        userService.saveTeacher(appUser);
        return "Utworzono nauczyciela";
    }

    @GetMapping("/createDirector")
    public String createDirector(Model model) {
        AppUser appUser = new AppUser();
        model.addAttribute("AppUser", appUser);
        model.addAttribute("Role", "Nauczyciela");
        return "createUser";
    }

    @PostMapping("/createDirector")
    @ResponseBody
    public String createDirector(AppUser appUser) {
        userService.saveDirector(appUser);
        return "Utworzono dyrektora";
    }
}
