package br.com.global.mobility.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.global.mobility.Model.Item;
import br.com.global.mobility.Repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    ItemRepository repository;

    public Page<Item> listAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Optional<Item> findById(Integer id){
        return repository.findById(id);
    }

    public void save(Item item){
        item.setInclusionDate();
        repository.save(item);
    }
 
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
    
}
