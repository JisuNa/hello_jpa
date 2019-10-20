package test.helloJpa.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import test.helloJpa.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
