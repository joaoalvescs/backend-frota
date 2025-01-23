package com.joao.backend_frota.dto;

import com.joao.backend_frota.models.Carro;
import com.joao.backend_frota.models.Veiculo;

public class CarroVeiculoDto {
    private Carro carro;
    private Veiculo veiculo;

    public CarroVeiculoDto() {}

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}
