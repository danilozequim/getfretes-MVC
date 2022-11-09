package br.com.global.mobility.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.global.mobility.Model.Address;
import br.com.global.mobility.Repository.AddressRepository;

@Service
public class AddressService {

    @Autowired
    AddressRepository repository;

    public Page<Address> listAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Optional<Address> findById(Integer id){
        return repository.findById(id);
    }

    public void save(Address address){
        address.setInclusionDate();
        repository.save(address);
    }
 
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
    
}
