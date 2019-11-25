package test.helloJpa.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import test.helloJpa.entity.Users;
import test.helloJpa.persistence.UsersRepository;

import javax.net.ssl.HttpsURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    @GetMapping(value="/create")
    public String createPage(Model model) {
        model.addAttribute("list", usersRepository.findAll());
        return "users/create";
    }

    @PostMapping(value="/create/action")
    public ResponseEntity<?> createAction(@ModelAttribute Users users) {
        usersRepository.save(users);
        Map<String,Object> map = new HashMap<>();
        map.put("result_code","1");
        map.put("result_type","success");

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping(value="/view/{no}")
    public String readPage(@PathVariable long no, Model model) {
        model.addAttribute("user",usersRepository.findById(no).get());

        return "users/view";
    }

    @GetMapping(value="/update/{no}")
    public String updatePage(@PathVariable long no, Model model) {
        model.addAttribute("user", usersRepository.findById(no).get());
        return "users/update";
    }

    @PostMapping(value="/update/action")
    public ResponseEntity<?> updateAction(@ModelAttribute Users users) {
        usersRepository.save(users);
        Map<String,Object> map = new HashMap<>();
        map.put("result_code","1");
        map.put("result_type","success");

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping(value="delete")
    public ResponseEntity<?> deleteUser(@ModelAttribute Users users) {
        usersRepository.delete(users);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
