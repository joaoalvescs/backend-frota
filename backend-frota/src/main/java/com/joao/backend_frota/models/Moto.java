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
@Table(name = "moto")
public class Moto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "A cilindrada é obrigatório.")
    @Column(nullable = false)
    private int cilindrada;

    public Moto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCilindrada() {
      return cilindrada;
    }

    public void setCilindrada(int cilindrada){
      this.cilindrada = cilindrada;
    }

    public void setVeiculoId(Long id2) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'setVeiculoId'");
    }
}
