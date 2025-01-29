package com.joao.backend_frota.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.joao.backend_frota.dto.VeiculoDto;
import com.joao.backend_frota.models.Veiculo;
import com.joao.backend_frota.repositories.VeiculoRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/veiculo")
@Tag(name = "Veículo", description = "Gerenciamento de Veículos")
public class VeiculoController {
    @Autowired
    VeiculoRepository veiculoRepository;

    @Operation(summary = "Consultar todos os veículos.", description = "Consulta todos os veículos cadastrados.")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Veiculo>> consultarTodosVeiculos() {
        try {
            List<Veiculo> veiculos = veiculoRepository.findAllVeiculos();
            return ResponseEntity.ok(veiculos);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @Operation(summary = "Criar um veículo.", description = "Cria um veículo específico com os atributos: modelo, fabricante, ano e preço.")
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

    @Operation(summary = "Apagar um veículo pelo ID.", description = "Apaga um veículo específico pelo ID fornecido.")
    @DeleteMapping(value = "/apagar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> apagarVeiculo(@PathVariable Long id) {
        try {
            veiculoRepository.apagarVeiculo(id);
            return ResponseEntity.ok("Veículo apagado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao apagar o veículo: " + e.getMessage());
        }
    }

    @Operation(summary = "Atualizar um veículo específico.", description = "Atualiza um veículo pelo ID e seus atributos.")
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

    @Operation(summary = "Pesquisa veículos usando vários atributos.", 
            description = "Pesquisa veículos por modelo, fabricante e ano em uma única string.")
    @PostMapping(value = "/pesquisar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Veiculo>> pesquisarVeiculos(@RequestBody Map<String, String> body) {
        String termo = body.get("termo");
        if (termo == null || termo.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        try {
            String[] termos = termo.split(" ");

            List<Veiculo> resultados = new ArrayList<>();
            for (String t : termos) {
                resultados.addAll(veiculoRepository.pesquisarVeiculos(t));
            }

            List<Veiculo> distinctResultados = resultados.stream().distinct().toList();

            return ResponseEntity.ok(distinctResultados);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @Operation(summary = "Consultar um carro pelo ID.", description = "Consulta um veículo pelo ID e retorna seus atributos.")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VeiculoDto> consultarVeiculoPorId(@PathVariable Long id) {
        try {
            Veiculo veiculo = veiculoRepository.findVeiculoById(id);
            if (veiculo == null) {
                return ResponseEntity.status(404).body(null);
            }

            VeiculoDto veiculoDto = new VeiculoDto();
            veiculoDto.setVeiculoId(veiculo.getId());
            veiculoDto.setModelo(veiculo.getModelo());
            veiculoDto.setFabricante(veiculo.getFabricante());
            veiculoDto.setAno(veiculo.getAno());
            veiculoDto.setPreco(veiculo.getPreco());

            return ResponseEntity.ok(veiculoDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
