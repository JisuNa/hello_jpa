package test.helloJpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import test.helloJpa.persistence.UsersRepository;

@RestController
public class UserController {

    final UsersRepository usersRepository;

    public UserController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping(value = "/index")
    public ResponseEntity<?> index() {
        return new ResponseEntity<>("Nothing", HttpStatus.OK);
    }

    @GetMapping(value = "/jpa")
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(usersRepository.findAll(), HttpStatus.OK);
    }
}
