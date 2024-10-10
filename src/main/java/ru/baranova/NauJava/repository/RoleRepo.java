package ru.baranova.NauJava.repository;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import ru.baranova.NauJava.entity.Role;

public interface RoleRepo extends CrudRepository<Role, Long> {
    List<Role> findByroleName(String roleName);
    
}

