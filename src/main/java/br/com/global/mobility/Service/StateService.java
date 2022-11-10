package br.com.global.mobility.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.global.mobility.Model.State;
import br.com.global.mobility.Repository.StateRepository;

@Service
public class StateService {

    @Autowired
    StateRepository repository;

    public Page<State> listAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public List<State> listAllStates(){
        return repository.listAllStates().get();
    }

    public Optional<List<String>> listAllInitial(){
        return repository.listAllInitial();
    }

    public Optional<State> findById(Integer id){
        return repository.findById(id);
    }

    public Optional<State> findByInitials(String initials){
        return repository.findByInitials(initials);
    }

    public void save(State state){
        state.setInclusionDate();
        repository.save(state);
    }
 
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
    
}
