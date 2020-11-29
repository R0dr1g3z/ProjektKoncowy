package pl.coderslab.demo.Project.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @RequestMapping("/homepage")
    public String homepage(){
        return "teacher/teacher";
    }
}
