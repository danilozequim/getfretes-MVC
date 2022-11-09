package br.com.global.mobility.Controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.global.mobility.Model.User;
import br.com.global.mobility.Service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping
    public Page<User> index( @PageableDefault(size = 10, sort = "id") Pageable pageable){
        return service.listAll(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> show(@PathVariable Integer id){
        return ResponseEntity.of(service.findById(id));   
    }

    @GetMapping("login")
    public ResponseEntity<User> login(@RequestParam String email,@RequestParam String password){
        password = passwordEncoder.encode(password);
        return ResponseEntity.of(service.login(email,password));
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        service.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody @Valid User newUser){
        Optional<User> optional = service.findById(id);

        if (optional.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
        }else{

            User user = optional.get();
            BeanUtils.copyProperties(newUser, user, new String [] {"id", "password"});
            user.setId(id);
    
            service.save(user);
    
            return ResponseEntity.ok(user);

        }
        
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> delete(@PathVariable Integer id){
        Optional<User> optional = service.findById(id);

        if (optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }else{
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }

}
