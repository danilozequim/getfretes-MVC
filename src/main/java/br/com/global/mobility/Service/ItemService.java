package br.com.global.mobility.Service;

import java.util.List;
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

    public Optional<List<Item>> listAll(){
        return repository.listAll();
    }

    public Optional<List<String>> listAllNames(){
        return repository.listAllNames();
    }

    public Optional<Item> findById(Integer id){
        return repository.findById(id);
    }

    public Optional<Item> findByName(String name){
        return repository.findByName(name);
    }

    public void save(Item item){
        item.setInclusionDate();
        repository.save(item);
    }
 
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
    
}
