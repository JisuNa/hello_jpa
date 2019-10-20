package test.helloJpa.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import test.helloJpa.entity.Users;
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

    @GetMapping(value="/create/page")
    public String createPage(Model model) {
        model.addAttribute("list", usersRepository.findAll());
        return "users/create";
    }

    @PostMapping(value="/create/regist")
    public String createRegist(@ModelAttribute Users users) {
        usersRepository.save(users);
        return "index";
    }
}
