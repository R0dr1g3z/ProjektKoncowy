package pl.coderslab.demo.Project.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.demo.Project.users.CurrentUser;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/homepage")
    public String homepage(@AuthenticationPrincipal CurrentUser customUser){
        return "student/student";
    }
}
