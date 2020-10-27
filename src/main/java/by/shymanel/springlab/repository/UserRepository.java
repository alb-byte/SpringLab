package by.shymanel.springlab.repository;


import by.shymanel.springlab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
    User getUserById(Long id);
}
