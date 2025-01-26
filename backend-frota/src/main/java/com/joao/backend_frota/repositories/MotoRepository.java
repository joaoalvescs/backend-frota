package com.joao.backend_frota.repositories;

import com.joao.backend_frota.dto.MotoComVeiculoDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Modifying
    @Transactional
    @Query(value = "UPDATE moto SET cilindrada = :cilindrada WHERE id = :motoId", nativeQuery = true)
    void updateMoto(int cilindrada, Long motoId);

    @Query(value = "SELECT m.id AS motoId, m.cilindrada AS cilindrada, " +
        "v.id AS veiculoId, v.modelo AS modelo, v.fabricante AS fabricante, " +
        "v.ano AS ano, v.preco AS preco " +
        "FROM moto m JOIN veiculo v ON m.veiculo_id = v.id WHERE m.id = :id", nativeQuery = true)
    MotoComVeiculoDto findMotoById(@Param("id") Long id);
}

