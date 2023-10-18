package bg.softuni.pathfinder.demo;

import bg.softuni.pathfinder.model.User;
import bg.softuni.pathfinder.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestDemoServiceImpl implements RestDemoService {

    private final UserRepository userRepository;

    public RestDemoServiceImpl (UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public String getAll() {

        final List<User> allUsers = userRepository.findAll();
        return "userRepository.findAll() <-- Exposing an entity directly is a bad practice by itself, but in this case returning userRepository.findAll() would expose a json containing all the information of all users persisted in the database which is disastrous security-wise. Please reconsider this demo.";
    }
}
