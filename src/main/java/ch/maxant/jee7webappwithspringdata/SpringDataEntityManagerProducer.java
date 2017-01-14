package ch.maxant.jee7webappwithspringdata;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Dependent //requestscope here doesn't work because jboss fails to find an EM to eagerly setup the UserRepository
public class SpringDataEntityManagerProducer {

    @PersistenceContext(name = "primary")
    EntityManager em;

    /** spring data needs this guy */
    @Produces
    public EntityManager getEm(){
        return em;
    }
}
