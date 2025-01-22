package com.joao.backend_frota.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.joao.backend_frota.models.Veiculo;

import jakarta.transaction.Transactional;

@Repository
public interface VeiculoRepository extends org.springframework.data.repository.CrudRepository<Veiculo, Long> {

    @Query(value = "SELECT * FROM veiculo", nativeQuery = true)
    List<Veiculo> findAllVeiculos();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO veiculo (modelo, fabricante, ano, preco) VALUES (:modelo, :fabricante, :ano, :preco)", nativeQuery = true)
    void criarVeiculo(
        @Param("modelo") String modelo, 
        @Param("fabricante") String fabricante, 
        @Param("ano") String ano, 
        @Param("preco") double preco
    );

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM veiculo WHERE id = :id", nativeQuery = true)
    void apagarVeiculo(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE veiculo SET modelo = :modelo, fabricante = :fabricante, ano = :ano, preco = :preco WHERE id = :id", nativeQuery = true)
    int atualizarVeiculo(
        @Param("id") Long id,
        @Param("modelo") String modelo, 
        @Param("fabricante") String fabricante, 
        @Param("ano") String ano, 
        @Param("preco") double preco
    );
}

