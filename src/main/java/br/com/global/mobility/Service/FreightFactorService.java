package br.com.global.mobility.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.global.mobility.Model.FreightFactor;
import br.com.global.mobility.Repository.FreightFactorRepository;

@Service
public class FreightFactorService {

    @Autowired
    FreightFactorRepository repository;

    public Page<FreightFactor> listAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Optional<FreightFactor> findById(Integer id){
        return repository.findById(id);
    }

    public Optional<FreightFactor> findByRoute(String origin, String destination){
        return repository.findByRoute(origin, destination);
    }

    public void save(FreightFactor freightFactor){
        freightFactor.setInclusionDate();
        repository.save(freightFactor);
    }
 
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
    
}
