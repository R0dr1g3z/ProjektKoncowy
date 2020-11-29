package pl.coderslab.demo.Project.users;

public interface UserService {

    AppUser findByUserName(String name);

    void saveStudent(AppUser appUser);
    void saveTeacher(AppUser appUser);
    void saveDirector(AppUser appUser);
}
