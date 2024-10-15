package ru.baranova.NauJava.repository;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import ru.baranova.NauJava.entity.Role;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="roles")
public interface RoleRepo extends CrudRepository<Role, Long> {
    List<Role> findByroleName(String roleName);
    
}

