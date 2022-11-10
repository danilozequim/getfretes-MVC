package br.com.global.mobility.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.global.mobility.Enumerator.EN_Status;
import br.com.global.mobility.Enumerator.EN_User;
import br.com.global.mobility.Model.Request;
import br.com.global.mobility.Model.User;
import br.com.global.mobility.Repository.RequestRepository;

@Service
public class RequestService {

    @Autowired
    RequestRepository repository;

    public Page<Request> listAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Optional<Request> findById(Integer id){

        Optional<Request> optional = repository.findById(id);

        if(optional.isPresent()){
            Request request = optional.get();
            request.setCurrentStatus();
            return Optional.of(request);
        }

        return Optional.empty();
        
    }

    public Optional<List<Request>> listByStatus(EN_Status status){
        return repository.listByStatus(status);
    }

    public Optional<List<Request>> listByUser(User user){

        if(user.getType() == EN_User.CLIENTE){
            return repository.listForClient(user.getId());
        }else if(user.getType() == EN_User.PRESTADOR){
            return repository.listForTransporter(user.getId());
        }else{
            return repository.listForAdmin(user.getId());
        }
        
    }

    public void save(Request request){

        if(request.getFreightValue() == null){
            request.freightCalculation();
        }
        
        request.setInclusionDate();
        repository.save(request);
    
    }
 
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
    
}
