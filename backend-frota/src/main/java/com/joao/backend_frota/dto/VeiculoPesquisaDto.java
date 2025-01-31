package com.joao.backend_frota.dto;

import com.joao.backend_frota.models.Veiculo;

public class VeiculoPesquisaDto {
    private Veiculo veiculo;

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}
