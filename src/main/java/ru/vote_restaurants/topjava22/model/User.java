package ru.vote_restaurants.topjava22.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends AbstractNamedEntity {

    @Size(min = 4, max = 20)
    @Column(name = "email", nullable = false, unique = true)
    @Email
    protected String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 3, max = 51)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    protected String password;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<Role> roles;

    public User() {
    }

    public User(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getRoles(), user.isEnabled());
    }

    public User(Integer id, String name, String email, String password, Set<Role> roles, boolean enabled) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        setRoles(roles);
    }

    public User(Integer id, String name, String email, String password, Role role) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.roles = Collections.singleton(role);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", roles=" + roles +
                '}';
    }
}