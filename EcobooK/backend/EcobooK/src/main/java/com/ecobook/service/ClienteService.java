package com.ecobook.service;

import com.ecobook.dto.ClienteRequestDTO;
import com.ecobook.dto.ClienteResponseDTO;
import com.ecobook.model.Cartao;
import com.ecobook.model.Cliente;
import com.ecobook.model.Endereco;
import com.ecobook.model.Telefone;
import com.ecobook.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteResponseDTO criarCliente(ClienteRequestDTO dto) {

        Telefone telefone = Telefone.builder()
                .tipo(dto.getTelefone().getTipo())
                .ddd(dto.getTelefone().getDdd())
                .numero(dto.getTelefone().getNumero())
                .build();

        List<Endereco> enderecos = dto.getEnderecos().stream().map(e->
                Endereco.builder()
                        .tipoResidencia(e.getTipoResidencia())
                        .tipoLogradouro(e.getTipoLogradouro())
                        .logradouro(e.getLogradouro())
                        .numero(e.getNumero())
                        .bairro(e.getBairro())
                        .cep(e.getCep())
                        .cidade(e.getCidade())
                        .estado(e.getEstado())
                        .pais(e.getPais())
                        .observacoes(e.getObservacoes())
                        .build()).toList();

        List<Cartao> cartoes = dto.getCartoes().stream().map(c ->
                Cartao.builder()
                        .numero(c.getNumero())
                        .nomeImpresso(c.getNomeImpresso())
                        .bandeira(c.getBandeira())
                        .codigoSeguranca(c.getCodigoSeguranca())
                        .validade(c.getValidade())
                        .build()
        ).toList();

        Cliente cliente = Cliente.builder()
                .nome(dto.getNome())
                .sobrenome(dto.getSobrenome())
                .cpf(dto.getCpf())
                .dataNascimento(dto.getDataNascimento())
                .genero(dto.getGenero())
                .telefone(telefone)
                .email(dto.getEmail())
                .senha(dto.getSenha()) // 👉 aqui é o ponto certo para criptografar no futuro
                .build();

        // Associar endereços e cartões ao cliente
        enderecos.forEach(e -> e.setCliente(cliente));
        cartoes.forEach(c -> c.setCliente(cliente));

        cliente.setEnderecos(enderecos);
        cliente.setCartoes(cartoes);

        Cliente salvo = clienteRepository.save(cliente);

        return new ClienteResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getSobrenome(),
                salvo.getCpf(),
                salvo.getEmail()
        );
    }
}
