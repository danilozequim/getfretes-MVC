package br.com.global.mobility.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.global.mobility.Model.User;
import br.com.global.mobility.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public Page<User> listAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Optional<User> findById(Integer id){
        return repository.findById(id);
    }

    public Optional<User> login(String email, String password){
        return repository.login(email, password);
    }

    public void save(User user){
        user.setInclusionDate();
        repository.save(user);
    }
 
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
    
}
