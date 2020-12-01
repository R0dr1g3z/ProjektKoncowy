package pl.coderslab.demo.Project.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.demo.Project.school.*;
import pl.coderslab.demo.Project.users.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    private final SchoolClassRepository schoolClassRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final TestRepository testRepository;
    private final ShortTestRepository shortTestRepository;
    private final HomeworkRepository homeworkRepository;
    private final OtherRepository otherRepository;

    public TeacherController(SchoolClassRepository schoolClassRepository, RoleRepository roleRepository, UserRepository userRepository, TestRepository testRepository, ShortTestRepository shortTestRepository, HomeworkRepository homeworkRepository, OtherRepository otherRepository) {
        this.schoolClassRepository = schoolClassRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.testRepository = testRepository;
        this.shortTestRepository = shortTestRepository;
        this.homeworkRepository = homeworkRepository;
        this.otherRepository = otherRepository;
    }

    @RequestMapping("/homepage")
    public String homepage(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        AppUser appUser = currentUser.getAppUser();
        List<SchoolClass> schoolClasses = appUser.getSchoolClasses();
        String fullName = appUser.getFullName();
        model.addAttribute("SchoolClass", schoolClasses);
        model.addAttribute("FullName", fullName);
        return "teacher/teacher";
    }

    @RequestMapping("/schoolClassDetails/{name}")
    public String schoolClassDetails(@PathVariable String name, Model model) {
        SchoolClass schoolClass = schoolClassRepository.findByName(name);
        Role role_student = roleRepository.findByName("ROLE_STUDENT");
        List<AppUser> students = userRepository.findAllBySchoolClassesAndRoles(schoolClass, role_student);
        model.addAttribute("Students", students);
        model.addAttribute("schoolClass", schoolClass);
        return "teacher/schoolClassDetails";
    }

    @RequestMapping("/studentRatings/{id}/{name}")
    public String studentRatings(@PathVariable Long id, @PathVariable String name, Model model) {
        AppUser appUser = userRepository.getOne(id);
        List<Test> tests = testRepository.findAllByAppUser(appUser);
        List<ShortTest> shortTests = shortTestRepository.findAllByAppUser(appUser);
        List<Homework> homeworks = homeworkRepository.findAllByAppUser(appUser);
        List<Other> others = otherRepository.findAllByAppUser(appUser);
        model.addAttribute("Student", appUser);
        model.addAttribute("Tests", tests);
        model.addAttribute("ShortTests", shortTests);
        model.addAttribute("Homeworks", homeworks);
        model.addAttribute("Others", others);
        model.addAttribute("SchoolClass", name);
        return "teacher/studentRatings";
    }

    @RequestMapping("/addRating/{id}/{name}")
    public String addRating(@PathVariable Long id,@PathVariable String name,Model model) {
        model.addAttribute("Id",id);
        model.addAttribute("Name",name);
        return "teacher/addRating";
    }
    @GetMapping("/addTest/{id}/{name}")
    public String addTest(Model model,@PathVariable Long id){
        Test test = new Test();
        AppUser one = userRepository.getOne(id);
        model.addAttribute("Student",one);
        model.addAttribute("Test",test);
        return "teacher/addTest";
    }
    @PostMapping("/addTest/{id}/{name}")
    public String addTest(Test test,@PathVariable Long id,@PathVariable String name){
        AppUser one = userRepository.getOne(id);
        test.setAppUser(one);
        testRepository.save(test);
        return "redirect:/teacher/schoolClassDetails/"+name;
    }
    @GetMapping("/editTest/{idTest}/{idUser}/{name}")
    public String editTest(Model model,@PathVariable Long idUser,@PathVariable Long idTest){
        AppUser student = userRepository.getOne(idUser);
        Test test = testRepository.getOne(idTest);
        model.addAttribute("Student",student);
        model.addAttribute("Test",test);
        return "teacher/editTest";
    }
    @PostMapping("/editTest/{idTest}/{idUser}/{name}")
    public String editTest(Test test,@PathVariable String name){
        testRepository.save(test);
        return "redirect:/teacher/schoolClassDetails/"+name;
    }
    @GetMapping("/addShortTest/{id}/{name}")
    public String addShortTest(Model model,@PathVariable Long id){
        ShortTest shortTest = new ShortTest();
        AppUser one = userRepository.getOne(id);
        model.addAttribute("Student",one);
        model.addAttribute("ShortTest",shortTest);
        return "teacher/addShortTest";
    }
    @PostMapping("/addShortTest/{id}/{name}")
    public String addShortTest(ShortTest shortTest,@PathVariable Long id,@PathVariable String name){
        AppUser one = userRepository.getOne(id);
        shortTest.setAppUser(one);
        shortTestRepository.save(shortTest);
        return "redirect:/teacher/schoolClassDetails/"+name;
    }
    @GetMapping("/editShortTest/{idShortTest}/{idUser}/{name}")
    public String editShortTest(Model model,@PathVariable Long idUser,@PathVariable Long idShortTest){
        AppUser student = userRepository.getOne(idUser);
        ShortTest shortTest = shortTestRepository.getOne(idShortTest);
        model.addAttribute("Student",student);
        model.addAttribute("ShortTest",shortTest);
        return "teacher/editShortTest";
    }
    @PostMapping("/editShortTest/{idShortTest}/{idUser}/{name}")
    public String editShortTest(ShortTest shortTest,@PathVariable String name){
        shortTestRepository.save(shortTest);
        return "redirect:/teacher/schoolClassDetails/"+name;
    }
    @GetMapping("/addHomework/{id}/{name}")
    public String addHomework(Model model,@PathVariable Long id){
        Homework homework = new Homework();
        AppUser one = userRepository.getOne(id);
        model.addAttribute("Student",one);
        model.addAttribute("Homework",homework);
        return "teacher/addHomework";
    }
    @PostMapping("/addHomework/{id}/{name}")
    public String addHomework(Homework homework,@PathVariable Long id,@PathVariable String name){
        AppUser one = userRepository.getOne(id);
        homework.setAppUser(one);
        homeworkRepository.save(homework);
        return "redirect:/teacher/schoolClassDetails/"+name;
    }
    @GetMapping("/editHomework/{idHomework}/{idUser}/{name}")
    public String editHomework(Model model,@PathVariable Long idUser,@PathVariable Long idHomework){
        AppUser student = userRepository.getOne(idUser);
        Homework homework = homeworkRepository.getOne(idHomework);
        model.addAttribute("Student",student);
        model.addAttribute("Homework",homework);
        return "teacher/editHomework";
    }
    @PostMapping("/editHomework/{idTest}/{idUser}/{name}")
    public String editHomework(Homework homework,@PathVariable String name){
        homeworkRepository.save(homework);
        return "redirect:/teacher/schoolClassDetails/"+name;
    }
    @GetMapping("/addOther/{id}/{name}")
    public String addOther(Model model,@PathVariable Long id){
        Other other = new Other();
        AppUser one = userRepository.getOne(id);
        model.addAttribute("Student",one);
        model.addAttribute("Other",other);
        return "teacher/addOther";
    }
    @PostMapping("/addOther/{id}/{name}")
    public String addOther(Other other,@PathVariable Long id,@PathVariable String name){
        AppUser one = userRepository.getOne(id);
        other.setAppUser(one);
        otherRepository.save(other);
        return "redirect:/teacher/schoolClassDetails/"+name;
    }
    @GetMapping("/editOther/{idOther}/{idUser}/{name}")
    public String editOther(Model model,@PathVariable Long idUser,@PathVariable Long idOther){
        AppUser student = userRepository.getOne(idUser);
        Other other = otherRepository.getOne(idOther);
        model.addAttribute("Student",student);
        model.addAttribute("Other",other);
        return "teacher/editOther";
    }
    @PostMapping("/editOther/{idOther}/{idUser}/{name}")
    public String editOther(Other other,@PathVariable String name){
        otherRepository.save(other);
        return "redirect:/teacher/schoolClassDetails/"+name;
    }
}
