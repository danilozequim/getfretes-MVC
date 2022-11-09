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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.global.mobility.Model.FreightFactor;
import br.com.global.mobility.Service.FreightFactorService;

@RestController
@RequestMapping("/api/factor")
public class FreightFactorController {

    @Autowired
    FreightFactorService service;

    @GetMapping
    public Page<FreightFactor> index( @PageableDefault(size = 10, sort = "id") Pageable pageable){
        return service.listAll(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<FreightFactor> show(@PathVariable Integer id){
        return ResponseEntity.of(service.findById(id));   
    }

    @GetMapping("route")
    public ResponseEntity<FreightFactor> findByRoute(@RequestParam String origin,@RequestParam String destination){
        return ResponseEntity.of(service.findByRoute(origin, destination));
    }

    @PostMapping
    public ResponseEntity<FreightFactor> create(@RequestBody @Valid FreightFactor freightFactor){
        service.save(freightFactor);
        return ResponseEntity.status(HttpStatus.CREATED).body(freightFactor);
    }

    @PutMapping("{id}")
    public ResponseEntity<FreightFactor> update(@PathVariable Integer id, @RequestBody @Valid FreightFactor newFreightFactor){
        Optional<FreightFactor> optional = service.findById(id);

        if (optional.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
        }else{

            FreightFactor freightFactor = optional.get();
            BeanUtils.copyProperties(newFreightFactor, freightFactor);
            freightFactor.setId(id);
    
            service.save(freightFactor);
    
            return ResponseEntity.ok(freightFactor);

        }
        
    }

    @DeleteMapping("{id}")
    public ResponseEntity<FreightFactor> delete(@PathVariable Integer id){
        Optional<FreightFactor> optional = service.findById(id);

        if (optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }else{
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }

}
