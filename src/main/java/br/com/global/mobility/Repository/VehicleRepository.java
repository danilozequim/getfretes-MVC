package br.com.global.mobility.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.global.mobility.Model.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository< Vehicle, Integer> {
    
}
