package ch.maxant.jee7webappwithspringdata;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import java.util.List;

import static java.util.stream.Collectors.toList;

@ApplicationPath("rs")
@Path("/employees")
@Stateless
public class UserResource extends Application {

    @PersistenceContext(name = "primary")
    EntityManager em;

    @Inject
    UserRepository userRepository; //spring is kind enough to be able to inject us one of these as a CDI bean

    //GET at http://localhost:8080/jee7webappwithspringdata/rs/employees/all
    @GET
    @Path("all")
    @Produces("application/json")
    public List<String> get() {
        System.out.println("======================================");

        List<String> allRoles = userRepository
                .findByName("John")
                .stream()
                .flatMap(u -> u.getRoles().stream())
                .map(r -> r.getRole())
                .collect(toList());
        System.out.println("Using Spring Data Repo: " + allRoles);

        System.out.println("======================================");

        allRoles = em
                .createQuery("select u from User u where u.name = :name", User.class)
                .setParameter("name", "Jane")
                .getResultList()
                .stream()
                .flatMap(u -> u.getRoles().stream())
                .map(r -> r.getRole())
                .collect(toList());
        System.out.println("Using JPA: " + allRoles);

        System.out.println("======================================");

        // with spring data you can modify entities once they are loaded, just like in jpa:
        userRepository.findOne(1L).setEmail("john@maxant.ch");

        return allRoles;
    }
}
