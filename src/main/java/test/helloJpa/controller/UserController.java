package test.helloJpa.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import test.helloJpa.persistence.UsersRepository;

@Controller
@RequestMapping(value="/users")
public class UserController {

    private final UsersRepository usersRepository;

    public UserController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping(value="")
    public String index(Model model) {
        model.addAttribute("list", usersRepository.findAll());
        return "index";
    }
}
