package com.joao.backend_frota.repositories;

import com.joao.backend_frota.dto.MotoComVeiculoDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.joao.backend_frota.models.Moto;
import java.util.List;

@Repository
public interface MotoRepository extends org.springframework.data.repository.CrudRepository<Moto, Long> {

    @Query(value = "SELECT m.id AS motoId, m.cilindrada, v.id AS veiculoId, v.modelo, v.fabricante, v.ano, v.preco " +
                   "FROM moto m JOIN veiculo v ON m.veiculo_id = v.id", nativeQuery = true)
    List<MotoComVeiculoDto> findAllMotosWithVeiculo();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO moto (cilindrada, veiculo_id) VALUES (:cilindrada, :veiculoId)", nativeQuery = true)
    void insertMoto(int cilindrada, Long veiculoId);
}

