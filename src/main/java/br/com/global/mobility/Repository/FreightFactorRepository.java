package br.com.global.mobility.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.global.mobility.Model.FreightFactor;

@Repository
public interface FreightFactorRepository extends JpaRepository< FreightFactor, Integer> {

    @Query(value="select f from FreightFactor f join fetch f.stateOrigin o join fetch f.stateDestination d where o.initials = ?1 and d.initials = ?2", nativeQuery = false) 
    Optional<FreightFactor> findByRoute(String origin, String destination);
    
}
