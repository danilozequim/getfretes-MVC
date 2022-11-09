package br.com.global.mobility.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.global.mobility.Enumerator.EN_Status;
import br.com.global.mobility.Model.Status;
import br.com.global.mobility.Repository.StatusRepository;

@Service
public class StatusService {

    @Autowired
    StatusRepository repository;

    public Page<Status> listAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Optional<Status> findById(Integer id){
        return repository.findById(id);
    }

    public Optional<Status> findByStatus(EN_Status status){
        return repository.findByStatus(status);
    }

    public void save(Status status){
        status.setInclusionDate();
        repository.save(status);
    }
 
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
    
}
