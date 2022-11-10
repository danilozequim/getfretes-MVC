package br.com.global.mobility.Utilities;

import java.util.ArrayList;
import java.util.List;

import br.com.global.mobility.Enumerator.EN_Item;
import br.com.global.mobility.Enumerator.EN_Status;
import br.com.global.mobility.Enumerator.EN_User;
import br.com.global.mobility.Enumerator.EN_Vehicle;

public class EnumListFactory {

    public static List<String> listEnumValues(String name){

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

        }

        return list;

    }
    
}
