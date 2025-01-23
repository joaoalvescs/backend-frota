package com.joao.backend_frota.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "carro")
public class Carro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "A quantidade de portas é obrigatório.")
    @Column(nullable = false)
    private int quantidadePortas;

    @NotEmpty(message = "O tipo de combustível é obrigatório.")
    @Column(nullable = false)
    private String tipoCombustivel;

    public Carro() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidadePortas() {
      return quantidadePortas;
    }

    public void setQuantidadePortas(int quantidadePortas){
      this.quantidadePortas = quantidadePortas;
    }

    public String getTipoCombustivel() {
      return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
      this.tipoCombustivel = tipoCombustivel;
    }
}
