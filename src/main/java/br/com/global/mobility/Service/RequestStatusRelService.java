package br.com.global.mobility.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.global.mobility.Model.RequestStatusRel;
import br.com.global.mobility.Repository.RequestStatusRelRepository;

@Service
public class RequestStatusRelService {

    @Autowired
    RequestStatusRelRepository repository;

    public Page<RequestStatusRel> listAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Optional<RequestStatusRel> findById(Integer id){
        return repository.findById(id);
    }

    public void save(RequestStatusRel requestStatusRel){
        requestStatusRel.setInclusionDate();
        repository.save(requestStatusRel);
    }
 
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
    
}
