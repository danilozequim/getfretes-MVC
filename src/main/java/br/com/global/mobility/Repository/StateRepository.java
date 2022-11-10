package br.com.global.mobility.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.global.mobility.Model.State;

@Repository
public interface StateRepository extends JpaRepository< State, Integer> {


    @Query(value="select * from TblESTADO", nativeQuery = true) 
    public Optional<List<State>> listAllStates();

    @Query(value="select distinct ds_sigla from TblESTADO", nativeQuery = true) 
    public Optional<List<String>> listAllInitial();
 
    @Query(value="select * from TblESTADO where ds_sigla = ?1", nativeQuery = true) 
    public Optional<State> findByInitials(String initials);
    
}
