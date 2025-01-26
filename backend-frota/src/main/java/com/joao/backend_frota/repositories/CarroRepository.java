package com.joao.backend_frota.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    @Query(value = "INSERT INTO carro (quantidade_portas, tipo_combustivel, veiculo_id) VALUES (:quantidadePortas, :tipoCombustivel, :veiculoId)", nativeQuery = true)
    void insertCarro(int quantidadePortas, String tipoCombustivel, Long veiculoId, String fabricante, String modelo);

    @Modifying
    @Transactional
    @Query(value = "UPDATE carro SET quantidade_portas = :quantidadePortas, tipo_combustivel = :tipoCombustivel WHERE id = :carroId", nativeQuery = true)
    void updateCarro(Long carroId, int quantidadePortas, String tipoCombustivel);

    @Query(value = "SELECT c.id AS carroId, c.quantidade_portas AS quantidadePortas, " +
        "c.tipo_combustivel AS tipoCombustivel, v.id AS veiculoId, v.modelo AS modelo, " +
        "v.fabricante AS fabricante, v.ano AS ano, v.preco AS preco " +
        "FROM carro c JOIN veiculo v ON c.veiculo_id = v.id WHERE c.id = :id", nativeQuery = true)
    CarroComVeiculoDto findCarroById(@Param("id") Long id);


    @Query(value = "SELECT c.id AS carroId, c.quantidade_portas AS quantidadePortas, " +
        "c.tipo_combustivel AS tipoCombustivel, v.id AS veiculoId, v.modelo AS modelo, " +
        "v.fabricante AS fabricante, v.ano AS ano, v.preco AS preco " +
        "FROM carro c JOIN veiculo v ON c.veiculo_id = v.id " +
        "WHERE (:modelo IS NULL OR v.modelo LIKE %:modelo%) " +
        "AND (:fabricante IS NULL OR v.fabricante LIKE %:fabricante%) " +
        "AND (:ano IS NULL OR v.ano = :ano)", nativeQuery = true)
    List<CarroComVeiculoDto> findCarrosByFiltro(@Param("modelo") String modelo, 
                                                @Param("fabricante") String fabricante, 
                                                @Param("ano") Integer ano);

}
