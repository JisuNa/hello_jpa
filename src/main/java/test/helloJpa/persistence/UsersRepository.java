package test.helloJpa.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import test.helloJpa.entity.Users;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {

    List<Users> findByIdContaining(String keyword);

}
