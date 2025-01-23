package com.joao.backend_frota.dto;

import com.joao.backend_frota.models.Moto;
import com.joao.backend_frota.models.Veiculo;

public class MotoVeiculoDto {
    private Moto moto;
    private Veiculo veiculo;

    public MotoVeiculoDto() {}

    public Moto getMoto() {
        return moto;
    }

    public void setMoto(Moto moto) {
        this.moto = moto;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}
