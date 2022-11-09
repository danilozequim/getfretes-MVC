package br.com.global.mobility.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.global.mobility.Enumerator.EN_Item;
import br.com.global.mobility.Enumerator.EN_Status;
import br.com.global.mobility.Enumerator.EN_User;
import br.com.global.mobility.Enumerator.EN_Vehicle;

@RestController
@RequestMapping("/api/enum")
public class EnumController {
    
    @GetMapping("{name}")
    public ResponseEntity<List<String>> index(@PathVariable String name){
        
        List<String> list = new ArrayList<String>();
        
        if(name.equals("status")){

            for(EN_Status e : EN_Status.values()){
                list.add(e.toString());
            }

        }else if(name.equals("user")){

            for(EN_User e : EN_User.values()){
                list.add(e.toString());
            }

        }else if(name.equals("vehicle")){

            for(EN_Vehicle e : EN_Vehicle.values()){
                list.add(e.toString());
            }

        }else if(name.equals("item")){

            for(EN_Item e : EN_Item.values()){
                list.add(e.toString());
            }

        }else{

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }

        return ResponseEntity.of(Optional.of(list));
        
    }

}
