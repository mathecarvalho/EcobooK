package com.ecobook.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClienteSenhaDTO {

    @NotNull(message = "ID é obrigatório")
    private Long id;

    @NotNull(message = "Senha é obrigatório")
    private String senha;

    @NotNull(message = "Nova senha é obrigatório")
    private String novaSenha;

    @NotNull(message = "Confirma senha é obrigatório")
    private String confirmaSenha;
}
