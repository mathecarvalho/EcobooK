package com.ecobook.dto;

import com.ecobook.model.Cartao;

public record CartaoResponseDTO(String numero) {

    public static CartaoResponseDTO fromEntity(Cartao cartao) {
        return new CartaoResponseDTO(cartao.getNumero());
    }
}
