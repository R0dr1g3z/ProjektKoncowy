package pl.coderslab.demo.Project.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.demo.Project.school.*;
import pl.coderslab.demo.Project.users.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/director")
public class DirectorController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserService userService;
    private final SchoolClassRepository schoolClassRepository;
    private final TestRepository testRepository;
    private final ShortTestRepository shortTestRepository;
    private final HomeworkRepository homeworkRepository;
    private final OtherRepository otherRepository;

    public DirectorController(UserRepository userRepository, RoleRepository roleRepository, UserService userService, SchoolClassRepository schoolClassRepository, TestRepository testRepository, ShortTestRepository shortTestRepository, HomeworkRepository homeworkRepository, OtherRepository otherRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.schoolClassRepository = schoolClassRepository;
        this.testRepository = testRepository;
        this.shortTestRepository = shortTestRepository;
        this.homeworkRepository = homeworkRepository;
        this.otherRepository = otherRepository;
    }

    @RequestMapping("/homepage")
    public String homepage() {
        return "director/director";
    }

    @RequestMapping("/teachers")
    public String teachers(Model model) {
        Role role = roleRepository.findByName("ROLE_TEACHER");
        List<AppUser> role_teacher = userRepository.findAllByRoles(role);
        model.addAttribute("teachers", role_teacher);
        return "director/allTeachers";
    }

    @RequestMapping("/students")
    public String students(Model model) {
        Role role = roleRepository.findByName("ROLE_STUDENT");
        List<AppUser> allByRoles = userRepository.findAllByRoles(role);
        model.addAttribute("students", allByRoles);
        return "director/allStudents";
    }

    @RequestMapping("/schoolClass")
    public String schoolClass(Model model) {
        List<SchoolClass> all = schoolClassRepository.findAll();
        model.addAttribute("SchoolClass", all);
        return "director/allSchoolClass";
    }

    @GetMapping("/createTeacher")
    public String createTeacher(Model model) {
        AppUser appUser = new AppUser();
        model.addAttribute("appUser", appUser);
        model.addAttribute("Role", "Nauczyciela");
        return "createUser";
    }

    @PostMapping("/createTeacher")
    public String createTeacher(@Valid AppUser appUser, BindingResult result) {
        if (result.hasErrors()) {
            return "createUser";
        }
        userService.saveTeacher(appUser);
        return "redirect:/director/teachers";
    }

    @RequestMapping("/removeTeacher/{id}")
    public String removeTeacher(@PathVariable Long id) {
        userRepository.deleteUserRole(id);
        userRepository.deleteById(id);
        return "redirect:/director/teachers";
    }

    @GetMapping("/editTeacher/{username}")
    public String editTeacher(@PathVariable String username, Model model) {
        AppUser byUsername = userRepository.findByUsername(username);
        model.addAttribute("AppUser", byUsername);
        return "director/editUser";
    }

    @PostMapping("/editTeacher/{username}")
    public String editTeacher(AppUser appUser) {
        userService.saveTeacher(appUser);
        return "redirect:/director/teachers";
    }

    @GetMapping("/createStudent")
    public String createStudent(Model model) {
        AppUser appUser = new AppUser();
        model.addAttribute("AppUser", appUser);
        model.addAttribute("Role", "Ucznia");
        return "createUser";
    }

    @PostMapping("/createStudent")
    public String createStudent(AppUser appUser) {
        userService.saveStudent(appUser);
        return "redirect:/director/students";
    }

    @GetMapping("/editStudent/{username}")
    public String editStudent(@PathVariable String username, Model model) {
        AppUser byUsername = userRepository.findByUsername(username);
        model.addAttribute("AppUser", byUsername);
        return "director/editUser";
    }

    @PostMapping("/editStudent/{username}")
    public String editStudent(AppUser appUser) {
        userService.saveStudent(appUser);
        return "redirect:/director/students";
    }

    @RequestMapping("/removeStudent/{id}")
    public String removeStudent(@PathVariable Long id) {
        userRepository.deleteUserRole(id);
        userRepository.deleteById(id);
        return "redirect:/director/students";
    }

    @RequestMapping("/studentRatings/{id}")
    public String studentRatings(@PathVariable Long id, Model model) {
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
        return "director/allStudentRatings";
    }

    @GetMapping("/createSchoolClass")
    public String createSchoolClass(Model model) {
        SchoolClass schoolClass = new SchoolClass();
        model.addAttribute("SchoolClass", schoolClass);
        return "director/createSchoolClass";
    }

    @PostMapping("/createSchoolClass")
    public String createSchoolClass(SchoolClass schoolClass) {
        schoolClassRepository.save(schoolClass);
        return "redirect:/director/schoolClass";
    }

    @GetMapping("/editSchoolClass/{name}")
    public String editSchoolClass(@PathVariable String name, Model model) {
        SchoolClass byName = schoolClassRepository.findByName(name);
        Role role = roleRepository.findByName("ROLE_TEACHER");
        List<AppUser> allByRoles = userRepository.findAllByRoles(role);
        model.addAttribute("SchoolClass", byName);
        model.addAttribute("teachers", allByRoles);
        return "director/editSchoolClass";
    }

    @PostMapping("/editSchoolClass/{name}")
    public String editSchoolClass(SchoolClass schoolClass) {
        schoolClassRepository.save(schoolClass);
        return "redirect:/director/schoolClass";
    }

    @RequestMapping("/removeSchoolClass/{id}")
    public String removeSchoolClass(@PathVariable Long id) {
        schoolClassRepository.deleteAllStudentFromSchoolClass(id);
        schoolClassRepository.deleteById(id);
        return "redirect:/director/schoolClass";
    }

    @RequestMapping("/schoolClassDetails/{name}")
    public String schoolClassDetails(@PathVariable String name, Model model) {
        SchoolClass schoolClass = schoolClassRepository.findByName(name);
        Role role_student = roleRepository.findByName("ROLE_STUDENT");
        Role role_teacher = roleRepository.findByName("ROLE_TEACHER");
        List<AppUser> students = userRepository.findAllBySchoolClassesAndRoles(schoolClass, role_student);
        List<AppUser> teachers = userRepository.findAllBySchoolClassesAndRoles(schoolClass, role_teacher);
        model.addAttribute("Students", students);
        model.addAttribute("Teachers", teachers);
        model.addAttribute("schoolClass", schoolClass);
        return "director/schoolClassDetails";
    }

    @RequestMapping("addStudentToClass/{name}")
    public String addStudentToClass(@PathVariable String name, Model model) {
        SchoolClass schoolClass = schoolClassRepository.findByName(name);
        Role role = roleRepository.findByName("ROLE_STUDENT");
        List<AppUser> allByRoles = userRepository.findAllByRoles(role);
        model.addAttribute("students", allByRoles);
        model.addAttribute("schoolClass", schoolClass);
        return "director/addStudentToClass";
    }

    @RequestMapping("addTeacherToClass/{name}")
    public String addTeacherToClass(@PathVariable String name, Model model) {
        SchoolClass schoolClass = schoolClassRepository.findByName(name);
        Role role = roleRepository.findByName("ROLE_TEACHER");
        List<AppUser> allByRoles = userRepository.findAllByRoles(role);
        model.addAttribute("teachers", allByRoles);
        model.addAttribute("schoolClass", schoolClass);
        return "director/addTeacherToClass";
    }

    @RequestMapping("addedStudentToClass/{username}/{name}")
    public String addedStudentToClass(@PathVariable String username, @PathVariable String name) {
        List<SchoolClass> schoolClass = schoolClassRepository.findAllByName(name);
        AppUser student = userRepository.findByUsername(username);
        student.setSchoolClasses(schoolClass);
        userRepository.save(student);
        return "redirect:/director/addStudentToClass/" + name;
    }

    @RequestMapping("addedTeacherToClass/{username}/{name}")
    public String addedTeacherToClass(@PathVariable String username, @PathVariable String name) {
        List<SchoolClass> schoolClass = schoolClassRepository.findAllByName(name);
        AppUser teacher = userRepository.findByUsername(username);
        List<SchoolClass> schoolClasses = teacher.getSchoolClasses();
        for (SchoolClass schoolClass1 : schoolClass) {
            schoolClasses.add(schoolClass1);
        }
        teacher.setSchoolClasses(schoolClasses);
        userRepository.save(teacher);
        return "redirect:/director/addTeacherToClass/" + name;
    }

    @RequestMapping("removeStudentFromClass/{id}/{name}")
    public String removeStudentFromClass(@PathVariable Long id, @PathVariable String name) {
        userRepository.deleteUserSchoolClass(id);
        return "redirect:/director/schoolClassDetails/" + name;
    }

    @RequestMapping("removeTeacherFromClass/{id}/{name}")
    public String removeTeacherFromClass(@PathVariable Long id, @PathVariable String name) {
        userRepository.deleteUserSchoolClass(id);
        return "redirect:/director/schoolClassDetails/" + name;
    }
}
