package com.joao.backend_frota.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.joao.backend_frota.models.Carro;

@Repository
public interface CarroRepository extends org.springframework.data.repository.CrudRepository<Carro, Long> {

    @Query(value = "SELECT * FROM carro", nativeQuery = true)
    List<Carro> findAllCarros();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO carro (quantidade_portas, tipo_combustivel, veiculo_id, fabricante, modelo) VALUES (:quantidadePortas, :tipoCombustivel, :veiculoId, :fabricante, :modelo)", nativeQuery = true)
    void insertCarro(int quantidadePortas, String tipoCombustivel, Long veiculoId, String fabricante, String modelo);
}
