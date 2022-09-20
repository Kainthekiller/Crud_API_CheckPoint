package CRUD_API_Checkpoint.example.demo.Controller;

import CRUD_API_Checkpoint.example.demo.Model.Authenticated;
import CRUD_API_Checkpoint.example.demo.Model.User;
import CRUD_API_Checkpoint.example.demo.Repo.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    final UserRepo userRepo;

    public UserController(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @GetMapping("")
    public Iterable<User> getAllUsers(){
        return this.userRepo.findAll();
    }

    @PostMapping("")
    public User postUser(@RequestBody User user){
        return this.userRepo.save(user);
    }


    //Get a Single User
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id){

        //User Exist
        if (this.userRepo.findById(id).isPresent())
        {
            User user = this.userRepo.findById(id).get();
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        //User Dose Not Exit
        else
        {
            return new ResponseEntity<>("User Dose Not Exist", HttpStatus.NOT_FOUND);
        }

    }

    @PatchMapping("/{id}")
    public Object patch(@PathVariable Long id, @RequestBody User body){

        if (this.userRepo.findById(id).isPresent()){
            User oldBody = this.userRepo.findById(id).get();
            oldBody.setEmail(body.getEmail());
            return this.userRepo.save(oldBody);
        } else {
            return String.format("Could not find user with id: %d", id);
        }


    }


    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id)
    {
        //User Exist
        if (this.userRepo.findById(id).isPresent())
        {
            this.userRepo.deleteById(id);
            return new ResponseEntity<>(this.userRepo.count(), HttpStatus.OK);
        }
        //User Dose Not Exist
        else
        {
            return new ResponseEntity<>("User Could Not Be Found", HttpStatus.NO_CONTENT);
        }
    }


    //Authenticate

    @PostMapping("/authenticate")
    public Object authenticateUser(@RequestBody User user)
    {
        //We need to find the user and then check if the password matches so we can authenticate
        User oldUser = this.userRepo.findByEmail(user.getEmail());
        if (oldUser.getPassword().equals(user.getPassword())){
            return new Authenticated(true, oldUser);
        } return new Authenticated(false);

    }


}
