package CRUD_API_Checkpoint.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Authenticated {
    Boolean authenticated = false;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    User user;


    //If passwords match
    public Authenticated(Boolean authenticated, User user){
        this.authenticated = authenticated;
        this.user = user;
    }

    //If passwords do not match
    public Authenticated(Boolean authenticated){
        this.authenticated = authenticated;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
