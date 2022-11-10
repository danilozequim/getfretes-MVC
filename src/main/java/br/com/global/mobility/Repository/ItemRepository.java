package br.com.global.mobility.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.global.mobility.Model.Item;

@Repository
public interface ItemRepository extends JpaRepository< Item, Integer> {

    @Query(value="select distinct nm_produto from TblProduto", nativeQuery = true) 
    public Optional<List<String>> listAllNames();

    @Query(value="select * from TblProduto", nativeQuery = true) 
    public Optional<List<Item>> listAll();

    @Query(value="select * from TblProduto where nm_produto = ?1", nativeQuery = true) 
    public Optional<Item> findByName(String name);
    
}
