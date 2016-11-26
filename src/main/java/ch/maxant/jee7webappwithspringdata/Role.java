package ch.maxant.jee7webappwithspringdata;

import javax.persistence.*;

@Entity
@Table(name = "ROLE")
public class Role {

    @Id
    @Column(name = "ID")
    Long id;

    @Column(name = "EMAIL")
    String email;

    @Column(name = "ROLE")
    String role;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
