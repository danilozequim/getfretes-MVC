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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.global.mobility.Model.Status;
import br.com.global.mobility.Service.StatusService;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    @Autowired
    StatusService service;

    @GetMapping
    public Page<Status> index( @PageableDefault(size = 10, sort = "id") Pageable pageable){
        return service.listAll(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<Status> show(@PathVariable Integer id){
        return ResponseEntity.of(service.findById(id));   
    }

    @PostMapping
    public ResponseEntity<Status> create(@RequestBody @Valid Status status){
        service.save(status);
        return ResponseEntity.status(HttpStatus.CREATED).body(status);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Status> delete(@PathVariable Integer id){
        Optional<Status> optional = service.findById(id);

        if (optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }else{
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<Status> update(@PathVariable Integer id, @RequestBody @Valid Status newStatus){
        Optional<Status> optional = service.findById(id);

        if (optional.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
        }else{

            Status status = optional.get();
            BeanUtils.copyProperties(newStatus, status);
            status.setId(id);
    
            service.save(status);
    
            return ResponseEntity.ok(status);

        }
        
    }

}
