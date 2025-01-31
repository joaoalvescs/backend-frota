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

    @Query(value = "SELECT * FROM veiculo v " +
            "WHERE (:termo IS NULL OR (" +
            "LOWER(v.modelo) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
            "LOWER(v.fabricante) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
            "CAST(v.ano AS CHAR) LIKE CONCAT('%', :termo, '%')" +
            "))", 
            nativeQuery = true)
    List<Veiculo> pesquisarVeiculos(@Param("termo") String termo);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO veiculo (modelo, fabricante, ano, preco) VALUES (:modelo, :fabricante, :ano, :preco)", nativeQuery = true)
    void insertVeiculo(String modelo, String fabricante, int ano, double preco);

    @Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
    Long getLastInsertedId();

    @Modifying
    @Transactional
    @Query(value = "UPDATE veiculo SET modelo = :modelo, fabricante = :fabricante, ano = :ano, preco = :preco WHERE id = :veiculoId", nativeQuery = true)
    void updateVeiculo(String modelo, String fabricante, int ano, double preco, Long veiculoId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE veiculo SET modelo = :modelo, fabricante = :fabricante, ano = :ano, preco = :preco WHERE id = :veiculoId", nativeQuery = true)
    void updateVeiculo(Long veiculoId, String modelo, String fabricante, int ano, double preco);

    @Query(value = "SELECT * FROM veiculo WHERE id = :id", nativeQuery = true)
    Veiculo findVeiculoById(@Param("id") Long id);
}