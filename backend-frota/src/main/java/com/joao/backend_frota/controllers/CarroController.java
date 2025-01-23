package com.joao.backend_frota.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joao.backend_frota.dto.CarroVeiculoDto;
import com.joao.backend_frota.models.Carro;
import com.joao.backend_frota.repositories.CarroRepository;
import com.joao.backend_frota.repositories.VeiculoRepository; // Certifique-se de importar o VeiculoRepository

@RestController
@RequestMapping("/carro")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;  // Injetando o VeiculoRepository

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Carro>> consultarTodosCarros() {
        try {
            List<Carro> carros = carroRepository.findAllCarros();
            return ResponseEntity.ok(carros);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping(value = "/criar", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> criarCarroComVeiculo(@RequestBody CarroVeiculoDto request) {
        try {
            if (request.getCarro() == null || request.getVeiculo() == null) {
                return ResponseEntity.badRequest().body("Carro ou Veículo não fornecidos.");
            }

            // Converte o ano de String para int
            int ano = Integer.parseInt(request.getVeiculo().getAno());

            // Inserir Veículo
            veiculoRepository.insertVeiculo(
                    request.getVeiculo().getModelo(),
                    request.getVeiculo().getFabricante(),
                    ano,  
                    request.getVeiculo().getPreco()
            );

            // Recuperar o ID do Veículo recém-criado
            Long veiculoId = veiculoRepository.getLastInsertedId();

            // Inserir Carro com modelo
            carroRepository.insertCarro(
                    request.getCarro().getQuantidadePortas(),
                    request.getCarro().getTipoCombustivel(),
                    veiculoId,
                    request.getVeiculo().getFabricante(), // Passando o fabricante
                    request.getVeiculo().getModelo() // Passando o modelo
            );

            return ResponseEntity.ok("Carro e veículo criados com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro ao criar carro e veículo: " + e.getMessage());
        }
    }
}
