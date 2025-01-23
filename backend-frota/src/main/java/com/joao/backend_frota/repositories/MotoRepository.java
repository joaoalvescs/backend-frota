package com.joao.backend_frota.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.joao.backend_frota.models.Moto;

@Repository
public interface MotoRepository extends org.springframework.data.repository.CrudRepository<Moto, Long> {

    @Query(value = "SELECT * FROM moto", nativeQuery = true)
    List<Moto> findAllMotos();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO moto (cilindrada, veiculo_id) VALUES (:cilindrada, :veiculoId)", nativeQuery = true)
    void insertMoto(int cilindrada, Long veiculoId);
}
