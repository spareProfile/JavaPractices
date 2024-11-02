package ru.baranova.NauJava.repository;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import ru.baranova.NauJava.entity.User;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="users")
public interface UserRepo extends CrudRepository<User, Long> {
    
    List<User> findByuserName(String userName);

    List<User> findByemail(String email);
    
}
