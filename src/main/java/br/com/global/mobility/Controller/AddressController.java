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

import br.com.global.mobility.Model.Address;
import br.com.global.mobility.Service.AddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService service;

    @GetMapping
    public Page<Address> index( @PageableDefault(size = 10, sort = "id") Pageable pageable){
        return service.listAll(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<Address> show(@PathVariable Integer id){
        return ResponseEntity.of(service.findById(id));   
    }

    @PostMapping
    public ResponseEntity<Address> create(@RequestBody @Valid Address address){
        service.save(address);
        return ResponseEntity.status(HttpStatus.CREATED).body(address);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Address> delete(@PathVariable Integer id){
        Optional<Address> optional = service.findById(id);

        if (optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }else{
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<Address> update(@PathVariable Integer id, @RequestBody @Valid Address newAddress){
        Optional<Address> optional = service.findById(id);

        if (optional.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
        }else{

            Address address = optional.get();
            BeanUtils.copyProperties(newAddress, address);
            address.setId(id);
    
            service.save(address);
    
            return ResponseEntity.ok(address);

        }
        
    }

}
