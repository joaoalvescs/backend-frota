package net.javaguides.backend_frota.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/veiculo")
public class VeiculoController {
  @GetMapping
  public String getVeiculo() {
      return "Peugeot 207 XS Passion";
  }
}
