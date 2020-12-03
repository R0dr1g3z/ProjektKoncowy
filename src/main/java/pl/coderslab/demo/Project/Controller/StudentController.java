package pl.coderslab.demo.Project.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.demo.Project.school.*;
import pl.coderslab.demo.Project.users.AppUser;
import pl.coderslab.demo.Project.users.CurrentUser;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final TestRepository testRepository;
    private final ShortTestRepository shortTestRepository;
    private final HomeworkRepository homeworkRepository;
    private final OtherRepository otherRepository;

    public StudentController(TestRepository testRepository, ShortTestRepository shortTestRepository, HomeworkRepository homeworkRepository, OtherRepository otherRepository) {
        this.testRepository = testRepository;
        this.shortTestRepository = shortTestRepository;
        this.homeworkRepository = homeworkRepository;
        this.otherRepository = otherRepository;
    }

    @RequestMapping("/homepage")
    public String homepage(@AuthenticationPrincipal CurrentUser customUser, Model model){
        AppUser appUser = customUser.getAppUser();
        String fullName = appUser.getFullName();
        model.addAttribute("FullName",fullName);
        return "student/student";
    }
    public String Ratings(@AuthenticationPrincipal CurrentUser currentUser,Model model,String schoolSubject,String schoolSubject2){
        AppUser appUser = currentUser.getAppUser();
        List<Test> tests = testRepository.findAllByAppUserAndSchoolSubject(appUser, schoolSubject);
        List<ShortTest> shortTests = shortTestRepository.findAllByAppUserAndSchoolSubject(appUser, schoolSubject);
        List<Homework> homeworks = homeworkRepository.findAllByAppUserAndSchoolSubject(appUser, schoolSubject);
        List<Other> others = otherRepository.findAllByAppUserAndSchoolSubject(appUser, schoolSubject);
        model.addAttribute("tests",tests);
        model.addAttribute("student",appUser);
        model.addAttribute("shortTests",shortTests);
        model.addAttribute("homeworks",homeworks);
        model.addAttribute("others",others);
        model.addAttribute("schoolSubject",schoolSubject2);
        return "student/studentRatings";
    }
    @RequestMapping("/polishRatings")
    public String polishRatings(@AuthenticationPrincipal CurrentUser currentUser,Model model){
        return Ratings(currentUser,model,"Język Polski","języka polskiego");
    }
    @RequestMapping("/englishRatings")
    public String englishRatings(@AuthenticationPrincipal CurrentUser currentUser,Model model){
       return Ratings(currentUser,model,"Język Angielski","języka angielskiego");
    }
    @RequestMapping("/biologyRatings")
    public String biologyRatings(@AuthenticationPrincipal CurrentUser currentUser,Model model){
        return Ratings(currentUser,model,"Biologia","biologi");
    }
    @RequestMapping("/chemistryRatings")
    public String chemistryRatings(@AuthenticationPrincipal CurrentUser currentUser,Model model){
        return Ratings(currentUser,model,"Chemia","chemii");
    }
    @RequestMapping("/physicsRatings")
    public String physicsRatings(@AuthenticationPrincipal CurrentUser currentUser,Model model){
        return Ratings(currentUser,model,"Fizyka","fizyki");
    }
    @RequestMapping("/geographyRatings")
    public String geographyRatings(@AuthenticationPrincipal CurrentUser currentUser,Model model){
        return Ratings(currentUser,model,"Geografia","geografi");
    }
    @RequestMapping("/historyRatings")
    public String historyRatings(@AuthenticationPrincipal CurrentUser currentUser,Model model){
        return Ratings(currentUser,model,"Historia","histori");
    }
    @RequestMapping("/informaticRatings")
    public String informaticRatings(@AuthenticationPrincipal CurrentUser currentUser,Model model){
        return Ratings(currentUser,model,"Informatyka","informatyki");
    }
    @RequestMapping("/mathematicsRatings")
    public String mathematicsRatings(@AuthenticationPrincipal CurrentUser currentUser,Model model){
        return Ratings(currentUser,model,"Matematyka","matematyki");
    }
    @RequestMapping("/musicRatings")
    public String musicRatings(@AuthenticationPrincipal CurrentUser currentUser,Model model){
        return Ratings(currentUser,model,"Muzyka","muzyki");
    }
    @RequestMapping("/artRatings")
    public String artRatings(@AuthenticationPrincipal CurrentUser currentUser,Model model){
        return Ratings(currentUser,model,"Plastyka","plastyki");
    }
    @RequestMapping("/religionRatings")
    public String religionRatings(@AuthenticationPrincipal CurrentUser currentUser,Model model){
        return Ratings(currentUser,model,"Religia","religi");
    }
    @RequestMapping("/physicalRatings")
    public String physicalRatings(@AuthenticationPrincipal CurrentUser currentUser,Model model){
        return Ratings(currentUser,model,"WF","WFu");
    }

}
