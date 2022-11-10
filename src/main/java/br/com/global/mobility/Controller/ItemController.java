package br.com.global.mobility.Controller;

import java.util.List;
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

import br.com.global.mobility.Model.Item;
import br.com.global.mobility.Service.ItemService;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    
    @Autowired
    ItemService service;

    @GetMapping
    public Page<Item> index( @PageableDefault(size = 10, sort = "id") Pageable pageable){
        return service.listAll(pageable);
    }

    @GetMapping("itens")
    public ResponseEntity<List<String>> listAllNames(){
        return ResponseEntity.of(service.listAllNames());   
    }

    @GetMapping("{id}")
    public ResponseEntity<Item> show(@PathVariable Integer id){
        return ResponseEntity.of(service.findById(id));   
    }

    @PostMapping
    public ResponseEntity<Item> create(@RequestBody @Valid Item item){
        service.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Item> delete(@PathVariable Integer id){
        Optional<Item> optional = service.findById(id);

        if (optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }else{
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<Item> update(@PathVariable Integer id, @RequestBody @Valid Item newItem){
        Optional<Item> optional = service.findById(id);

        if (optional.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
        }else{

            Item item = optional.get();
            BeanUtils.copyProperties(newItem, item);
            item.setId(id);
    
            service.save(item);
    
            return ResponseEntity.ok(item);

        }
        
    }

}
