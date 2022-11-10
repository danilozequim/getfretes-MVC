package br.com.global.mobility.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.global.mobility.Model.GroupMembers;

@RestController
@RequestMapping("/api/group")
public class GroupMembersController {

    @GetMapping
    public ResponseEntity<List<GroupMembers>> index(){

        List<GroupMembers> list = new ArrayList<GroupMembers>();

        GroupMembers m1 = new GroupMembers("Bianca Man Tchuin Chang Lee", "89171");
        GroupMembers m2 = new GroupMembers("Danilo Zequim de Moura", "89045");
        GroupMembers m3 = new GroupMembers("Eric Brianez Giannetti", "87087");
        GroupMembers m4 = new GroupMembers("Matheus Pismel de Jeronymo", "86931");
        GroupMembers m5 = new GroupMembers("Otavio de Magalhães Gomes", "87680");
        GroupMembers m6 = new GroupMembers("Zack Lorenzzo Corrêa", "87149");

        list.add(m1);
        list.add(m2);
        list.add(m3);
        list.add(m4);
        list.add(m5);
        list.add(m6);

        return ResponseEntity.of(Optional.of(list));

    }

    
}
