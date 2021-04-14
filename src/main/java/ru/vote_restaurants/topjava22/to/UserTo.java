package ru.vote_restaurants.topjava22.to;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class UserTo extends BaseTo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(min = 5, max = 30)
    @Column(name = "name", nullable = false)
    private String name;

    @Size(min = 4, max = 20)
    @Column(name = "email", nullable = false)
    private String email;

    @Size(min = 3, max = 21)
    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;

    public UserTo() {

    }

    public UserTo(Integer id, String name, String email, String password) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
