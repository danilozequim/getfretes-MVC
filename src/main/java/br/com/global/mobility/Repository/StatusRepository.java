package br.com.global.mobility.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.global.mobility.Enumerator.EN_Status;
import br.com.global.mobility.Model.Status;

@Repository
public interface StatusRepository extends JpaRepository< Status, Integer> {

    // @Query(value="select * from Status where status = ?1", nativeQuery = false) 
    Optional<Status> findByStatus(EN_Status status);
    
}
