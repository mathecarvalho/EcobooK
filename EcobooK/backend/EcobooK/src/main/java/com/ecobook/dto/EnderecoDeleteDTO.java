package com.ecobook.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnderecoDeleteDTO {

    @NotNull(message = "ID é obrigatório")
    private Long id;

    @NotNull(message = "Cliente é obrigatório")
    private Long clienteId;
}
