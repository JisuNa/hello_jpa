package test.helloJpa.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import test.helloJpa.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Page<Users> findByIdContaining(String keyword, Pageable pageable);
}
