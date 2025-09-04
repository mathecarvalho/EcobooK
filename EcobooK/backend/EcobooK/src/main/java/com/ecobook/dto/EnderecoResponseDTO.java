package com.ecobook.dto;

import com.ecobook.model.Endereco;

public record EnderecoResponseDTO(String nome) {

    public static EnderecoResponseDTO fromEntity(Endereco endereco) {
        return new EnderecoResponseDTO(endereco.getNome());
    }
}
