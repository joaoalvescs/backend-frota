package com.joao.backend_frota.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.joao.backend_frota.dto.CarroComVeiculoDto;
import com.joao.backend_frota.models.Carro;

@Repository
public interface CarroRepository extends org.springframework.data.repository.CrudRepository<Carro, Long> {

    @Query(value = "SELECT c.id AS carroId, c.quantidade_portas AS quantidadePortas, " +
        "c.tipo_combustivel AS tipoCombustivel, v.id AS veiculoId, v.modelo AS modelo, " +
        "v.fabricante AS fabricante, v.ano AS ano, v.preco AS preco " +
        "FROM carro c JOIN veiculo v ON c.veiculo_id = v.id", nativeQuery = true)
    List<CarroComVeiculoDto> findAllCarrosWithVeiculo();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO carro (quantidade_portas, tipo_combustivel, veiculo_id, fabricante, modelo) VALUES (:quantidadePortas, :tipoCombustivel, :veiculoId, :fabricante, :modelo)", nativeQuery = true)
    void insertCarro(int quantidadePortas, String tipoCombustivel, Long veiculoId, String fabricante, String modelo);
}
