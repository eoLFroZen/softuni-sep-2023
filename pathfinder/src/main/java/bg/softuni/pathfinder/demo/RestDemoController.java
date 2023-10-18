package bg.softuni.pathfinder.demo;

import bg.softuni.pathfinder.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class RestDemoController {

    private RestDemoService userService;

    @RequestMapping(path = "/all", method = RequestMethod.GET)
//    @GetMapping("/users/all")
    public String getAll() {
        return this.userService.getAll();
    }

    @Autowired
    public void setRestDemoService(RestDemoService userService) {
        this.userService = userService;
    }
}
