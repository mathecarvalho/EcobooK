package com.ecobook.dto;

import com.ecobook.model.Cliente;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String sobrenome,
        String cpf,
        String email
) {

    public static ClienteResponseDTO fromEntity(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getSobrenome(),
                cliente.getCpf(),
                cliente.getEmail()
        );
    }
}
