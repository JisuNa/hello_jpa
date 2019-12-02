package test.helloJpa.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import test.helloJpa.entity.Users;
import test.helloJpa.persistence.UsersRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/users")
public class UserController {

    private final UsersRepository usersRepository;

    public UserController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping(value="/list")
    public String index(Model model
            , @RequestParam(defaultValue = "") String keyword
            , @PageableDefault(size=10, sort = "seq", direction = Sort.Direction.DESC) Pageable pageable) {

        if(keyword.isEmpty()) {
            Page<Users> pageInfo = usersRepository.findAll(pageable);
            model.addAttribute("list", pageInfo.getContent());
            model.addAttribute("totalPage", pageInfo.getTotalPages());
            
        } else {
            model.addAttribute("keyword", keyword);
            model.addAttribute("list", usersRepository.findByIdContaining(keyword));
        }

        model.addAttribute("pageInfo", pageable);

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
        map.put("code","1");
        map.put("type","success");

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
        map.put("code","1");
        map.put("type","success");

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping(value="/delete")
    public ResponseEntity<?> deleteUser(@RequestBody String seq_arr) {
        JsonObject obj = new JsonParser().parse(seq_arr).getAsJsonObject();

        String seqs = String.valueOf(obj.get("seq")).replace("[", "").replace("]","").replace("\"", "");;
        String[] seq = seqs.split(",");

        Users users = null;

        for(int i=0; i<seq.length; i++) {
            users = new Users();
            users = usersRepository.findById(Long.parseLong(seq[i])).get();
            users.setIs_deleted('1');
            usersRepository.save(users);
        }

        Map<String,Object> map = new HashMap<>();
        map.put("code","1");
        map.put("type","success");

        return new ResponseEntity<>(map, HttpStatus.OK);

    }
}
