package com.joao.backend_frota.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.joao.backend_frota.models.Veiculo;
import com.joao.backend_frota.repositories.VeiculoRepository;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {
    @Autowired
    VeiculoRepository veiculoRepository;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Veiculo>> consultarTodosVeiculos() {
        try {
            List<Veiculo> veiculos = veiculoRepository.findAllVeiculos();
            return ResponseEntity.ok(veiculos);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping(value = "/criar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> criarVeiculo(@RequestBody Veiculo veiculo) {
        try {
            veiculoRepository.criarVeiculo(
                veiculo.getModelo(),
                veiculo.getFabricante(),
                veiculo.getAno(),
                veiculo.getPreco()
            );
            return ResponseEntity.ok("Veículo criado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao criar veículo.");
        }
    }

    @DeleteMapping(value = "/apagar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> apagarVeiculo(@PathVariable Long id) {
        try {
            veiculoRepository.apagarVeiculo(id);
            return ResponseEntity.ok("Veículo apagado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao apagar o veículo: " + e.getMessage());
        }
    }

    @PutMapping(value = "/atualizar/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> atualizarVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculo) {
        try {
            int resultado = veiculoRepository.atualizarVeiculo(id, veiculo.getModelo(), veiculo.getFabricante(), veiculo.getAno(), veiculo.getPreco());

            if (resultado == 0) {
                return ResponseEntity.status(404).body("Veículo não encontrado.");
            }
            
            return ResponseEntity.ok("Veículo atualizado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao atualizar o veículo: " + e.getMessage());
        }
    }
}
