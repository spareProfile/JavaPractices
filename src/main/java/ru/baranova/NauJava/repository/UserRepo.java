package ru.baranova.NauJava.repository;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import ru.baranova.NauJava.entity.User;

public interface UserRepo extends CrudRepository<User, Long> {
    
    List<User> findByuserName(String userName);

    List<User> findByemail(String email);
    
}
