package br.com.global.mobility.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.global.mobility.Model.RequestItem;
import br.com.global.mobility.Repository.RequestItemRepository;

@Service
public class RequestItemService {

    @Autowired
    RequestItemRepository repository;

    public Page<RequestItem> listAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Optional<RequestItem> findById(Integer id){
        return repository.findById(id);
    }

    public void save(RequestItem requestItem){
        requestItem.setInclusionDate();
        repository.save(requestItem);
    }
 
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
    
}
