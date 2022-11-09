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

import br.com.global.mobility.Model.RequestItem;
import br.com.global.mobility.Service.RequestItemService;

@RestController
@RequestMapping("/api/requestItem")
public class RequestItemController {

    @Autowired
    RequestItemService service;

    @GetMapping
    public Page<RequestItem> index( @PageableDefault(size = 10, sort = "id") Pageable pageable){
        return service.listAll(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<RequestItem> show(@PathVariable Integer id){
        return ResponseEntity.of(service.findById(id));   
    }

    @PostMapping
    public ResponseEntity<RequestItem> create(@RequestBody @Valid RequestItem requestItem){
        service.save(requestItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(requestItem);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<RequestItem> delete(@PathVariable Integer id){
        Optional<RequestItem> optional = service.findById(id);

        if (optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }else{
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<RequestItem> update(@PathVariable Integer id, @RequestBody @Valid RequestItem newRequestItem){
        Optional<RequestItem> optional = service.findById(id);

        if (optional.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
        }else{

            RequestItem requestItem = optional.get();
            BeanUtils.copyProperties(newRequestItem, requestItem);
            requestItem.setId(id);
    
            service.save(requestItem);
    
            return ResponseEntity.ok(requestItem);

        }
        
    }

}
