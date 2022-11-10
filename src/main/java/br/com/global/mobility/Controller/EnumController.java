package br.com.global.mobility.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.global.mobility.Utilities.EnumListFactory;

@RestController
@RequestMapping("/api/enum")
public class EnumController {
    
    @GetMapping("{name}")
    public ResponseEntity<List<String>> index(@PathVariable String name){
        
        List<String> list = EnumListFactory.listEnumValues(name);

        if(list != null){
            return ResponseEntity.of(Optional.of(list));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        
    }

}
