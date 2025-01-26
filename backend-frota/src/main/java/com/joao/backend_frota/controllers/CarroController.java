package com.joao.backend_frota.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joao.backend_frota.dto.CarroComVeiculoDto;
import com.joao.backend_frota.dto.CarroVeiculoDto;
import com.joao.backend_frota.repositories.CarroRepository;
import com.joao.backend_frota.repositories.VeiculoRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/carro")
@Tag(name = "Carro", description = "Gerenciamento de Carros")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Operation(summary = "Consultar todos os carros.", description = "Consulta todos os carros cadastrados.")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CarroComVeiculoDto>> consultarTodosCarrosComVeiculo() {
        try {
            List<CarroComVeiculoDto> carrosComVeiculo = carroRepository.findAllCarrosWithVeiculo();
            return ResponseEntity.ok(carrosComVeiculo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @Operation(summary = "Criar um carro.", description = "Cria um carro específico com quantidade de portas e tipo de combustível.")
    @PostMapping(value = "/criar", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> criarCarroComVeiculo(@RequestBody CarroVeiculoDto request) {
        try {
            if (request.getCarro() == null || request.getVeiculo() == null) {
                return ResponseEntity.badRequest().body("Carro ou Veículo não fornecidos.");
            }

            int ano = Integer.parseInt(request.getVeiculo().getAno());

            veiculoRepository.insertVeiculo(
                    request.getVeiculo().getModelo(),
                    request.getVeiculo().getFabricante(),
                    ano,  
                    request.getVeiculo().getPreco()
            );

            Long veiculoId = veiculoRepository.getLastInsertedId();

            carroRepository.insertCarro(
                    request.getCarro().getQuantidadePortas(),
                    request.getCarro().getTipoCombustivel(),
                    veiculoId,
                    request.getVeiculo().getFabricante(), 
                    request.getVeiculo().getModelo()
            );

            return ResponseEntity.ok("Carro e veículo criados com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro ao criar carro e veículo: " + e.getMessage());
        }
    }

    @Operation(summary = "Atualizar um carro específico.", description = "Atualiza um carro pelo ID junto com os atributos de carro e veículo.")
    @PutMapping(value = "/atualizar", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> atualizarCarroComVeiculo(@RequestBody CarroVeiculoDto request) {
        try {
            if (request.getCarro() == null || request.getVeiculo() == null) {
                return ResponseEntity.badRequest().body("Carro ou Veículo não fornecidos.");
            }

            Long veiculoId = request.getVeiculo().getId();
            Long carroId = request.getCarro().getId();

            if (veiculoId == null || carroId == null) {
                return ResponseEntity.badRequest().body("IDs do Carro ou Veículo não fornecidos.");
            }

            int ano = Integer.parseInt(request.getVeiculo().getAno());

            veiculoRepository.updateVeiculo(
                    veiculoId,
                    request.getVeiculo().getModelo(),
                    request.getVeiculo().getFabricante(),
                    ano,
                    request.getVeiculo().getPreco()
            );

            carroRepository.updateCarro(
                    carroId,
                    request.getCarro().getQuantidadePortas(),
                    request.getCarro().getTipoCombustivel()
            );

            return ResponseEntity.ok("Carro e veículo atualizados com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro ao atualizar carro e veículo: " + e.getMessage());
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Consultar um carro pelo ID.", description = "Consulta um carro pelo ID e retorna os atributos de carro e veículo.")
    public ResponseEntity<CarroComVeiculoDto> consultarCarroPorId(@PathVariable Long id) {
        try {
            CarroComVeiculoDto carroComVeiculo = carroRepository.findCarroById(id);
            if (carroComVeiculo != null) {
                return ResponseEntity.ok(carroComVeiculo);
            } else {
                return ResponseEntity.status(404).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @Operation(summary = "Filtrar carros por modelo, fabricante e ano", description = "Filtra os carros com base nos parâmetros fornecidos: modelo, fabricante e ano.")
    @GetMapping(value = "/filtrar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CarroComVeiculoDto>> filtrarCarrosPorModeloFabricanteAno(
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) String fabricante,
            @RequestParam(required = false) Integer ano) {
        try {
            List<CarroComVeiculoDto> carrosFiltrados = carroRepository.findCarrosByFiltro(modelo, fabricante, ano);
            return ResponseEntity.ok(carrosFiltrados);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
