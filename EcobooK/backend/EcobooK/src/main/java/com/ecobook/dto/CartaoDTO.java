package com.ecobook.dto;

import com.ecobook.enumeration.Bandeira;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CartaoDTO {

    @NotBlank(message = "Número do cartão é obrigatório")
    @Pattern(regexp = "\\d{13,16}", message = "Número do cartão deve ter entre 13 e 16 dígitos")
    private String numero;

    @NotBlank(message = "Nome impresso é obrigatório")
    private String nomeImpresso;

    @NotNull(message = "Bandeira é obrigatória")
    private Bandeira bandeira;

    @NotBlank(message = "CVV é obrigatório")
    @Pattern(regexp = "\\d{3,4}", message = "CVV deve ter 3 ou 4 dígitos")
    private String codigoSeguranca;

    @NotBlank(message = "Validade é obrigatória")
    @Pattern(regexp = "(0[1-9]|1[0-2])/\\d{2}", message = "Validade deve estar no formato MM/AA")
    private String validade;
}