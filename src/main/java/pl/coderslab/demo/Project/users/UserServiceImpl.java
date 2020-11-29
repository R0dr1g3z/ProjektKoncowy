package pl.coderslab.demo.Project.users;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.demo.Project.school.SchoolClass;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    @Override
    public AppUser findByUserName(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public void saveStudent(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_STUDENT");
        appUser.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        String firstName = appUser.getFirstName();
        String lastName = appUser.getLastName();
        appUser.setFullName(firstName + " " + lastName);
        userRepository.save(appUser);
    }

    @Override
    public void saveTeacher(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_TEACHER");
        appUser.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        String firstName = appUser.getFirstName();
        String lastName = appUser.getLastName();
        appUser.setFullName(firstName + " " + lastName);
        userRepository.save(appUser);
    }

    @Override
    public void saveDirector(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_DIRECTOR");
        appUser.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        String firstName = appUser.getFirstName();
        String lastName = appUser.getLastName();
        appUser.setFullName(firstName + " " + lastName);
        userRepository.save(appUser);
    }
}
