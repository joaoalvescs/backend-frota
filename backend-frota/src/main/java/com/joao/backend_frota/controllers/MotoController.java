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

import com.joao.backend_frota.dto.MotoComVeiculoDto;
import com.joao.backend_frota.dto.MotoVeiculoDto;
import com.joao.backend_frota.repositories.MotoRepository;
import com.joao.backend_frota.repositories.VeiculoRepository;

@RestController
@RequestMapping("/moto")
public class MotoController {

    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MotoComVeiculoDto>> consultarTodosMotosComVeiculo() {
        try {
            List<MotoComVeiculoDto> motosComVeiculo = motoRepository.findAllMotosWithVeiculo();
            return ResponseEntity.ok(motosComVeiculo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping(value = "/criar", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> criarMotoComVeiculo(@RequestBody MotoVeiculoDto request) {
        try {
            if (request.getMoto() == null || request.getVeiculo() == null) {
                return ResponseEntity.badRequest().body("Moto ou Veículo não fornecidos.");
            }

            int ano = Integer.parseInt(request.getVeiculo().getAno());

            veiculoRepository.insertVeiculo(
                    request.getVeiculo().getModelo(),
                    request.getVeiculo().getFabricante(),
                    ano,
                    request.getVeiculo().getPreco()
            );

            Long veiculoId = veiculoRepository.getLastInsertedId();

            motoRepository.insertMoto(
                    request.getMoto().getCilindrada(),
                    veiculoId
            );

            return ResponseEntity.ok("Moto e veículo criados com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro ao criar moto e veículo: " + e.getMessage());
        }
    }

    @PutMapping(value = "/atualizar", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> atualizarMotoComVeiculo(@RequestBody MotoVeiculoDto request) {
        try {
            if (request.getMoto() == null || request.getVeiculo() == null || 
                request.getMoto().getId() == null || request.getVeiculo().getId() == null) {
                return ResponseEntity.badRequest().body("IDs da Moto ou do Veículo não fornecidos.");
            }

            int ano = Integer.parseInt(request.getVeiculo().getAno());

            veiculoRepository.updateVeiculo(
                request.getVeiculo().getModelo(),
                request.getVeiculo().getFabricante(),
                ano,
                request.getVeiculo().getPreco(),
                request.getVeiculo().getId()
            );

            motoRepository.updateMoto(
                request.getMoto().getCilindrada(),
                request.getMoto().getId()
            );

            return ResponseEntity.ok("Moto e veículo atualizados com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro ao atualizar moto e veículo: " + e.getMessage());
        }
    }
}
