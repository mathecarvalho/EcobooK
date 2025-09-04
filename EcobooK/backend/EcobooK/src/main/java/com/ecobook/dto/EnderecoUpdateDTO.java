package com.ecobook.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnderecoUpdateDTO {

    @NotNull(message = "Id é obrigatório")
    private Long id;

    private String nome;

    private String tipoResidencia;

    private String tipoLogradouro;

    private String logradouro;

    private String numero;

    private String bairro;

    private String cep;

    private String cidade;

    private String estado;

    private String pais;

    private String observacoes;
}
