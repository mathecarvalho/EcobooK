package com.ecobook.dto;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String sobrenome,
        String cpf,
        String email
) {}
