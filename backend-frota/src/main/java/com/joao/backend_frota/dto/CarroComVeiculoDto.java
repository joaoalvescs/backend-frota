package com.joao.backend_frota.dto;

public interface CarroComVeiculoDto {
    Long getCarroId();
    int getQuantidadePortas();
    String getTipoCombustivel();
    Long getVeiculoId();
    String getModelo();
    String getFabricante();
    int getAno();
    double getPreco();
}
