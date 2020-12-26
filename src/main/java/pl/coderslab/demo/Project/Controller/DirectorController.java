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
    private final SchoolRepository schoolRepository;

    public DirectorController(UserRepository userRepository, RoleRepository roleRepository, UserService userService, SchoolClassRepository schoolClassRepository, TestRepository testRepository, ShortTestRepository shortTestRepository, HomeworkRepository homeworkRepository, OtherRepository otherRepository, SchoolRepository schoolRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.schoolClassRepository = schoolClassRepository;
        this.testRepository = testRepository;
        this.shortTestRepository = shortTestRepository;
        this.homeworkRepository = homeworkRepository;
        this.otherRepository = otherRepository;
        this.schoolRepository = schoolRepository;
    }

    @RequestMapping("/homepage")
    public String homepage(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        AppUser appUser = currentUser.getAppUser();
        School school = schoolRepository.findByDirector(appUser);
        model.addAttribute("school",school);
        return "director/director";
    }

    @RequestMapping("/teachers")
    public String teachers(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        AppUser appUser = currentUser.getAppUser();
        School school = schoolRepository.findByDirector(appUser);
        List<AppUser> teachers = school.getTeachers();
        model.addAttribute("teachers", teachers);
        return "director/allTeachers";
    }

    @RequestMapping("/students")
    public String students(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        AppUser appUser = currentUser.getAppUser();
        School school = schoolRepository.findByDirector(appUser);
        List<AppUser> students = school.getStudents();
        model.addAttribute("students", students);
        return "director/allStudents";
    }

    @RequestMapping("/schoolClass")
    public String schoolClass(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        AppUser appUser = currentUser.getAppUser();
        School school = schoolRepository.findByDirector(appUser);
        List<SchoolClass> schoolClasses = school.getSchoolClasses();
        model.addAttribute("schoolClasses", schoolClasses);
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
    public String createTeacher(@Valid AppUser appUser, BindingResult result,@AuthenticationPrincipal CurrentUser currentUser,Model model) {
        AppUser appUser1 = currentUser.getAppUser();
        String username = appUser.getUsername();
        List<AppUser> users = userRepository.findAll();
        for (AppUser user:users){
            String username1 = user.getUsername();
            if (username.equals(username1)){
                model.addAttribute("Role","Nauczyciela");
                return "createUserErrorDuplicate";
            }
        }
        if (result.hasErrors()) {
            return "createUser";
        }
        School school = schoolRepository.findByDirector(appUser1);
        List<AppUser> teachers = school.getTeachers();
        teachers.add(appUser);
        school.setTeachers(teachers);
        userService.saveTeacher(appUser);
        schoolRepository.save(school);
        return "redirect:/director/teachers";
    }

    @RequestMapping("/removeTeacher/{id}")
    public String removeTeacher(@PathVariable Long id) {
        userRepository.deleteTeacherSchool(id);
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
        model.addAttribute("appUser", appUser);
        model.addAttribute("Role", "Ucznia");
        return "createUser";
    }

    @PostMapping("/createStudent")
    public String createStudent(@AuthenticationPrincipal CurrentUser currentUser, AppUser appUser) {
        AppUser appUser1 = currentUser.getAppUser();
        School school = schoolRepository.findByDirector(appUser1);
        List<AppUser> students = school.getStudents();
        students.add(appUser);
        school.setStudents(students);
        userService.saveStudent(appUser);
        schoolRepository.save(school);
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
        userRepository.deleteStudentSchool(id);
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
    public String createSchoolClass(@AuthenticationPrincipal CurrentUser currentUser, SchoolClass schoolClass) {
        AppUser appUser = currentUser.getAppUser();
        School school = schoolRepository.findByDirector(appUser);
        List<SchoolClass> schoolClasses = school.getSchoolClasses();
        schoolClasses.add(schoolClass);
        schoolClassRepository.save(schoolClass);
        schoolRepository.save(school);
        return "redirect:/director/schoolClass";
    }

    @GetMapping("/editSchoolClass/{id}")
    public String editSchoolClass(@PathVariable Long id, Model model,@AuthenticationPrincipal CurrentUser currentUser) {
        AppUser appUser = currentUser.getAppUser();
        School school = schoolRepository.findByDirector(appUser);
        List<AppUser> teachers = school.getTeachers();
        SchoolClass schoolClass = schoolClassRepository.getOne(id);
        model.addAttribute("SchoolClass", schoolClass);
        model.addAttribute("teachers", teachers);
        return "director/editSchoolClass";
    }

    @PostMapping("/editSchoolClass/{id}")
    public String editSchoolClass(SchoolClass schoolClass) {
        schoolClassRepository.save(schoolClass);
        return "redirect:/director/schoolClass";
    }

    @RequestMapping("/removeSchoolClass/{id}")
    public String removeSchoolClass(@PathVariable Long id) {
        schoolClassRepository.deleteSchoolClassSchool(id);
        schoolClassRepository.deleteAllStudentFromSchoolClass(id);
        schoolClassRepository.deleteById(id);
        return "redirect:/director/schoolClass";
    }

    @RequestMapping("/schoolClassDetails/{id}")
    public String schoolClassDetails(@PathVariable Long id, Model model) {
        SchoolClass schoolClass = schoolClassRepository.getOne(id);
        Role role_student = roleRepository.findByName("ROLE_STUDENT");
        Role role_teacher = roleRepository.findByName("ROLE_TEACHER");
        List<AppUser> students = userRepository.findAllBySchoolClassesAndRoles(schoolClass, role_student);
        List<AppUser> teachers = userRepository.findAllBySchoolClassesAndRoles(schoolClass, role_teacher);
        model.addAttribute("Students", students);
        model.addAttribute("Teachers", teachers);
        model.addAttribute("schoolClass", schoolClass);
        return "director/schoolClassDetails";
    }

    @RequestMapping("addStudentToClass/{id}")
    public String addStudentToClass(@PathVariable Long id, Model model) {
        SchoolClass schoolClass = schoolClassRepository.getOne(id);
        Role role = roleRepository.findByName("ROLE_STUDENT");
        List<AppUser> allByRoles = userRepository.findAllByRoles(role);
        model.addAttribute("students", allByRoles);
        model.addAttribute("schoolClass", schoolClass);
        return "director/addStudentToClass";
    }

    @RequestMapping("addTeacherToClass/{id}")
    public String addTeacherToClass(@PathVariable Long id, Model model) {
        SchoolClass schoolClass = schoolClassRepository.getOne(id);
        Role role = roleRepository.findByName("ROLE_TEACHER");
        List<AppUser> allByRoles = userRepository.findAllByRoles(role);
        model.addAttribute("teachers", allByRoles);
        model.addAttribute("schoolClass", schoolClass);
        return "director/addTeacherToClass";
    }

    @RequestMapping("addedStudentToClass/{username}/{id}")
    public String addedStudentToClass(@PathVariable String username, @PathVariable Long id) {
        List<SchoolClass> schoolClass = schoolClassRepository.findAllById(id);
        SchoolClass one = schoolClassRepository.getOne(id);
        AppUser student = userRepository.findByUsername(username);
        student.setSchoolClasses(schoolClass);
        userRepository.save(student);
        return "redirect:/director/addStudentToClass/" + one.getName();
    }

    @RequestMapping("addedTeacherToClass/{username}/{id}")
    public String addedTeacherToClass(@PathVariable String username, @PathVariable Long id) {
        List<SchoolClass> schoolClass = schoolClassRepository.findAllById(id);
        SchoolClass one = schoolClassRepository.getOne(id);
        AppUser teacher = userRepository.findByUsername(username);
        List<SchoolClass> schoolClasses = teacher.getSchoolClasses();
        for (SchoolClass schoolClass1 : schoolClass) {
            schoolClasses.add(schoolClass1);
        }
        teacher.setSchoolClasses(schoolClasses);
        userRepository.save(teacher);
        return "redirect:/director/addTeacherToClass/" + one.getName();
    }

    @RequestMapping("removeStudentFromClass/{idStudent}/{idClass}")
    public String removeStudentFromClass(@PathVariable Long idStudent, @PathVariable Long idClass) {
        userRepository.deleteUserSchoolClass(idStudent);
        SchoolClass one = schoolClassRepository.getOne(idClass);
        return "redirect:/director/schoolClassDetails/" + one.getName();
    }

    @RequestMapping("removeTeacherFromClass/{idStudent}/{idClass}")
    public String removeTeacherFromClass(@PathVariable Long idStudent, @PathVariable Long idClass) {
        userRepository.deleteUserSchoolClass(idStudent);
        SchoolClass one = schoolClassRepository.getOne(idClass);
        return "redirect:/director/schoolClassDetails/" + one.getName();
    }
}
