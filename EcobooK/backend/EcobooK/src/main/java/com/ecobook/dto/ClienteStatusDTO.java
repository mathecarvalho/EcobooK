package com.ecobook.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClienteStatusDTO {

    @NotNull(message = "ID é obrigatório")
    private Long id;

    @NotNull(message = "Status é obrigatório")
    private boolean status;
}
