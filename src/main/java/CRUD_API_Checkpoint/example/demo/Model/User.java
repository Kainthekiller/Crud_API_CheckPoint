package CRUD_API_Checkpoint.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "username")
public class User {





    //Primary Key
    @Id
    //@Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Inc
    Long id;

    String email;


    String password;

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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }
}
