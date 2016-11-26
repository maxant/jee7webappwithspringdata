package ch.maxant.jee7webappwithspringdata;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class SpringDataEntityManagerProducer {

    @PersistenceContext(name = "primary")
    EntityManager em;

    /** spring data needs this guy */
    @Produces
    public EntityManager getEm(){
        return em;
    }
}
