package com.ecobook.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TelefoneDTO {

    @NotBlank(message = "Tipo do telefone é obrigatório")
    private String tipo;

    @NotBlank(message = "DDD é obrigatório")
    @Pattern(regexp = "\\d{2}", message = "DDD deve ter 2 dígitos")
    private String ddd;

    @NotBlank(message = "Número é obrigatório")
    @Pattern(regexp = "\\d{8,9}", message = "Número deve ter 8 ou 9 dígitos")
    private String numero;
}
