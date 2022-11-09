package br.com.global.mobility.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.global.mobility.Enumerator.EN_Status;
import br.com.global.mobility.Model.Request;

@Repository
public interface RequestRepository extends JpaRepository< Request, Integer> {

    @Query(value="select r from Request r join fetch r.statusList sl join fetch sl.status s where s.status = ?1", nativeQuery = false) 
    Optional<List<Request>> listByStatus(EN_Status status);
    
    @Query(value="select * from TblPEDIDO where ID_USUARIO_CLIENTE = ?1", nativeQuery = true) 
    Optional<List<Request>> listForClient(Integer id);

    @Query(value="select * from TblPEDIDO where ID_USUARIO_PRESTADOR = ?1", nativeQuery = true) 
    Optional<List<Request>> listForTransporter(Integer id);

    @Query(value="select * from TblPEDIDO where ID_USUARIO_CLIENTE = ?1 or ID_USUARIO_PRESTADOR = ?1", nativeQuery = true) 
    Optional<List<Request>> listForAdmin(Integer id);

}
