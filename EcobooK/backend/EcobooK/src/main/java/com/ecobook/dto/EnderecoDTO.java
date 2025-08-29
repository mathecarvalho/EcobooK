package com.ecobook.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EnderecoDTO {

    @NotBlank(message = "Tipo de residência é obrigatório")
    private String tipoResidencia;

    @NotBlank(message = "Tipo de Logradouro é obrigatório")
    private String tipoLogradouro;

    @NotBlank(message = "Logradouro é obrigatório")
    private String logradouro;

    @NotBlank(message = "Número é obrigatório")
    private String numero;

    @NotBlank(message = "Bairro é obrigatório")
    private String bairro;

    @NotBlank(message = "CEP é obrigatório")
    private String cep;

    @NotBlank(message = "Cidade é obrigatória")
    private String cidade;

    @NotBlank(message = "Estado é obrigatório")
    private String estado;

    @NotBlank(message = "País é obrigatório")
    private String pais;

    private String observacoes;
}