package br.com.global.mobility.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.global.mobility.Model.RequestItem;

@Repository
public interface RequestItemRepository extends JpaRepository< RequestItem, Integer> {

    @Query(value="select * from TblPRODUTO_PEDIDO", nativeQuery = true) 
    public Optional<List<RequestItem>> listAll();
    
    
}
