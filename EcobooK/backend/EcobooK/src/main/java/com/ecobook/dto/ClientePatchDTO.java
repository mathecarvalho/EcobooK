package com.ecobook.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientePatchDTO {

    @NotNull(message = "ID é obrigatório")
    private Long id;

    private String nome;

    private String sobrenome;

    private LocalDate dataNascimento;

    private String genero;

    @Email(message = "E-mail inválido")
    private String email;
}
