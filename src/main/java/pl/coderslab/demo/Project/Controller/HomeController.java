package pl.coderslab.demo.Project.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.demo.Project.users.*;

import java.util.Set;

@Controller
@Slf4j
public class HomeController {
    private final UserRepository userRepository;
    private final SpringDataUserDetailsService springDataUserDetailsService;

    public HomeController(UserRepository userRepository, SpringDataUserDetailsService springDataUserDetailsService) {
        this.userRepository = userRepository;
        this.springDataUserDetailsService = springDataUserDetailsService;
    }


    @RequestMapping("/")
    public String home(){
        return "redirect:/login";
    }
    @RequestMapping("/theme")
    public String theme() {

        log.info("asdasd {}", 12);
        return "Arek/index";
    }

    @RequestMapping("/403")
    public String errorWeb() {
        return "Arek/403";
    }

    @RequestMapping("/check")
    public String check(@AuthenticationPrincipal CurrentUser customUser) {
        AppUser entityUser = customUser.getAppUser();
        Set<Role> roles = entityUser.getRoles();
        for (Role role : roles) {
            if (role.getName().equals("ROLE_DIRECTOR")) {
                return "redirect:/director/homepage";
            }
            if (role.getName().equals("ROLE_OWNER")) {
                return "redirect:/owner/homepage";
            }
            if (role.getName().equals("ROLE_STUDENT")) {
                return "redirect:/student/homepage";
            }
            if (role.getName().equals("ROLE_TEACHER")) {
                return "redirect:/teacher/homepage";
            }
        }
        return "redirect:/login";
    }
}
