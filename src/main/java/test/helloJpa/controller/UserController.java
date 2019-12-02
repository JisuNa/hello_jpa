package test.helloJpa.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import test.helloJpa.entity.Users;
import test.helloJpa.persistence.UsersRepository;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value="/users")
public class UserController {

    private final UsersRepository usersRepository;

    public UserController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping(value="/list")
    public String index(Model model) {
        model.addAttribute("list", usersRepository.findAll());
        return "users/list";
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

    @PostMapping(value="/delete")
    public ResponseEntity<?> deleteUser(@RequestBody String seq_arr) {
        JsonObject obj = new JsonParser().parse(seq_arr).getAsJsonObject();

        String seqs = String.valueOf(obj.get("seq")).replace("[", "").replace("]","").replace("\"", "");;
        String[] seq = seqs.split(",");

        Users users = null;

        for (int i=0; i<seq.length; i++) {
            users = new Users();
            users = usersRepository.findById(Long.parseLong(seq[i])).get();
            users.setIs_deleted('1');
            usersRepository.save(users);
        }

        return new ResponseEntity<>("", HttpStatus.OK);

    }
}
