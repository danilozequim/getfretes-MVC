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

import br.com.global.mobility.Enumerator.EN_Status;
import br.com.global.mobility.Model.Request;
import br.com.global.mobility.Model.RequestStatusRel;
import br.com.global.mobility.Model.Status;
import br.com.global.mobility.Service.RequestService;
import br.com.global.mobility.Service.RequestStatusRelService;
import br.com.global.mobility.Service.StatusService;

@RestController
@RequestMapping("/api/requestStatus")
public class RequestStatusRelController {

    @Autowired
    RequestStatusRelService service;

    @Autowired
    RequestService requestService;

    @Autowired
    StatusService statusService;

    @GetMapping
    public Page<RequestStatusRel> index( @PageableDefault(size = 10, sort = "id") Pageable pageable){
        return service.listAll(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<RequestStatusRel> show(@PathVariable Integer id){
        return ResponseEntity.of(service.findById(id));   
    }

    @PostMapping
    public ResponseEntity<RequestStatusRel> create(@RequestBody @Valid RequestStatusRel requestStatusRel){
        service.save(requestStatusRel);
        return ResponseEntity.status(HttpStatus.CREATED).body(requestStatusRel);
    }

    @PostMapping("accept/{id}")
    public ResponseEntity<RequestStatusRel> requestAccept(@PathVariable @Valid Integer id){
        
        var optional = requestService.findById(id);

        if(optional.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }else{

            Status status = statusService.findByStatus(EN_Status.aceito).get();
            Request request = optional.get();

            RequestStatusRel requestStatusRel = new RequestStatusRel();
            requestStatusRel.addRequest(request);
            requestStatusRel.addStatus(status);

            service.save(requestStatusRel);

            return ResponseEntity.status(HttpStatus.CREATED).body(requestStatusRel);

        }

    }

    @PostMapping("finish/{id}")
    public ResponseEntity<RequestStatusRel> requestFinish(@PathVariable @Valid Integer id){
        
        var optional = requestService.findById(id);

        if(optional.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }else{

            Status status = statusService.findByStatus(EN_Status.finalizado).get();
            Request request = optional.get();

            RequestStatusRel requestStatusRel = new RequestStatusRel();
            requestStatusRel.addRequest(request);
            requestStatusRel.addStatus(status);

            service.save(requestStatusRel);

            return ResponseEntity.status(HttpStatus.CREATED).body(requestStatusRel);

        }

    }

    @PutMapping("{id}")
    public ResponseEntity<RequestStatusRel> update(@PathVariable Integer id, @RequestBody @Valid RequestStatusRel newRequestStatusRel){
        Optional<RequestStatusRel> optional = service.findById(id);

        if (optional.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
        }else{

            RequestStatusRel requestStatusRel = optional.get();
            BeanUtils.copyProperties(newRequestStatusRel, requestStatusRel);
            requestStatusRel.setId(id);
    
            service.save(requestStatusRel);
    
            return ResponseEntity.ok(requestStatusRel);

        }
        
    }

    @DeleteMapping("{id}")
    public ResponseEntity<RequestStatusRel> delete(@PathVariable Integer id){
        Optional<RequestStatusRel> optional = service.findById(id);

        if (optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }else{
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }

}
