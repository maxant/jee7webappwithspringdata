package ch.maxant.jee7webappwithspringdata;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

//or use this annotation: @RepositoryDefinition(domainClass = User.class, idClass = Long.class)
@org.springframework.data.repository.cdi.Eager
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByName(String name);
}
