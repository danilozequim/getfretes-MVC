package br.com.global.mobility;

import java.util.ArrayList;
import java.util.List;

import br.com.global.mobility.Model.Request;

public class Test {

    public static void main(String[] args) {
        

        List<Request> r = new ArrayList<Request>();

        r.get(0).setFreightValue(5f);;

        System.out.println(r.get(0));
        
    }
    
}
