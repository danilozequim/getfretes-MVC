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

import br.com.global.mobility.Model.Vehicle;
import br.com.global.mobility.Service.VehicleService;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    @Autowired
    VehicleService service;

    @GetMapping
    public Page<Vehicle> index( @PageableDefault(size = 10, sort = "id") Pageable pageable){
        return service.listAll(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<Vehicle> show(@PathVariable Integer id){
        return ResponseEntity.of(service.findById(id));   
    }

    @PostMapping
    public ResponseEntity<Vehicle> create(@RequestBody @Valid Vehicle vehicle){
        service.save(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicle);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Vehicle> delete(@PathVariable Integer id){
        Optional<Vehicle> optional = service.findById(id);

        if (optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }else{
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<Vehicle> update(@PathVariable Integer id, @RequestBody @Valid Vehicle newVehicle){
        Optional<Vehicle> optional = service.findById(id);

        if (optional.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
        }else{

            Vehicle vehicle = optional.get();
            BeanUtils.copyProperties(newVehicle, vehicle);
            vehicle.setId(id);
    
            service.save(vehicle);
    
            return ResponseEntity.ok(vehicle);

        }
        
    }

}
