package CRUD_API_Checkpoint.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "username")
public class User {

    //
    Boolean authenticated = false; // Mite not stay




    //Primary Key
    @Id
    //@Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Inc
    Long id;

    String email;

    @JsonIgnore
    String password;

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
