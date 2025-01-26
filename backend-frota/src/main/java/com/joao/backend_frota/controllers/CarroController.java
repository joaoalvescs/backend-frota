package com.joao.backend_frota.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joao.backend_frota.dto.CarroComVeiculoDto;
import com.joao.backend_frota.dto.CarroVeiculoDto;
import com.joao.backend_frota.repositories.CarroRepository;
import com.joao.backend_frota.repositories.VeiculoRepository;

@RestController
@RequestMapping("/carro")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

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
}
