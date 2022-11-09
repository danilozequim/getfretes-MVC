package br.com.global.mobility.Controller;

import java.util.List;
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

import br.com.global.mobility.Enumerator.EN_Status;
import br.com.global.mobility.Model.Request;
import br.com.global.mobility.Model.RequestItem;
import br.com.global.mobility.Service.RequestService;
import br.com.global.mobility.Service.UserService;

@RestController
@RequestMapping("/api/request")
public class RequestController {

    @Autowired
    RequestService service;

    @Autowired
    UserService userService;

    @GetMapping
    public Page<Request> index( @PageableDefault(size = 10, sort = "id") Pageable pageable){
        return service.listAll(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<Request> show(@PathVariable Integer id){
        return ResponseEntity.of(service.findById(id));   
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Request>> listByUser(@PathVariable EN_Status status){
        return ResponseEntity.of(service.listByStatus(status));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Request>> listByUser(@PathVariable Integer id){

        var optional = userService.findById(id);

        if(optional.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }else{

            return ResponseEntity.of(service.listByUser(optional.get()));

        }
        
    }

    @PostMapping
    public ResponseEntity<Request> create(@RequestBody @Valid Request request){

        for(RequestItem l : request.getRequestItemList()){
            if(l.getRequest() == null){
                l.setRequest(request);
            }
        }

        service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(request);
    }

    @PutMapping("{id}")
    public ResponseEntity<Request> update(@PathVariable Integer id, @RequestBody @Valid Request newRequest){
        Optional<Request> optional = service.findById(id);

        if (optional.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
        }else{

            Request request = optional.get();
            BeanUtils.copyProperties(newRequest, request);
            request.setId(id);
    
            service.save(request);
    
            return ResponseEntity.ok(request);

        }
        
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Request> delete(@PathVariable Integer id){
        Optional<Request> optional = service.findById(id);

        if (optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }else{
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }

}
