package br.com.global.mobility.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.global.mobility.Model.Vehicle;
import br.com.global.mobility.Repository.VehicleRepository;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository repository;

    public Page<Vehicle> listAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Optional<Vehicle> findById(Integer id){
        return repository.findById(id);
    }

    public void save(Vehicle vehicle){
        vehicle.setInclusionDate();
        repository.save(vehicle);
    }
 
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
    
}
