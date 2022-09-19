package CRUD_API_Checkpoint.example.demo.Repo;

import CRUD_API_Checkpoint.example.demo.Model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {

    List<User> findByEmailAndPassword(String emailAddress, String password);

}
