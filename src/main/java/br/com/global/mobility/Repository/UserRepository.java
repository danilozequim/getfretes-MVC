package br.com.global.mobility.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.global.mobility.Model.User;

@Repository
public interface UserRepository extends JpaRepository< User, Integer> {

    @Query(value="select * from TblUSUARIO where ds_email = ?1 and ds_senha = ?2", nativeQuery = true) 
    Optional<User> login(String email, String senha);
    
}
