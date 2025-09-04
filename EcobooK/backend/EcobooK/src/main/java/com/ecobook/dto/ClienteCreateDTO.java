package com.ecobook.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class ClienteCreateDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Sobrenome é obrigatório")
    private String sobrenome;

    @CPF(message = "CPF inválido")
    @NotBlank(message = "CPF é obrigatório")
    private String cpf;

    @NotNull(message = "Data de nascimento é obrigatório")
    private LocalDate dataNascimento;

    @NotBlank(message = "Gênero é obrigatório")
    private String genero;

    @Valid
    @NotNull(message = "Telefone é obrigatório")
    private TelefoneDTO telefone;

    @Email(message = "E-mail inválido")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @NotBlank(message = "Senha é obrigatório")
    private String senha;

    @Valid
    @NotEmpty(message = "Endereço é obrigatório")
    private List<EnderecoCreateDTO> enderecos;

    @Valid
    @NotEmpty(message = "Cartão é obrigatório")
    private List<CartaoCreateDTO> cartoes;
}
