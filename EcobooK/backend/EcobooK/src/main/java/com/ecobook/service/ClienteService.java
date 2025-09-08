package com.ecobook.service;

import com.ecobook.dto.*;
import com.ecobook.exception.ClienteException;
import com.ecobook.model.Cartao;
import com.ecobook.model.Cliente;
import com.ecobook.model.Endereco;
import com.ecobook.model.Telefone;
import com.ecobook.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteResponseDTO cadastrar(ClienteCreateDTO dto) {

        Telefone telefone = Telefone.builder()
                .tipo(dto.getTelefone().getTipo())
                .ddd(dto.getTelefone().getDdd())
                .numero(dto.getTelefone().getNumero())
                .build();

        List<Endereco> enderecos = dto.getEnderecos().stream().map(e->
                Endereco.builder()
                        .nome(e.getNome())
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
                .status(true)
                .build();

        // Associar endereços e cartões ao cliente
        enderecos.forEach(e -> e.setCliente(cliente));
        cartoes.forEach(c -> c.setCliente(cliente));

        cliente.setEnderecos(enderecos);
        cliente.setCartoes(cartoes);

        Cliente salvo = clienteRepository.save(cliente);

        return ClienteResponseDTO.fromEntity(salvo);
    }

    public ClienteResponseDTO alterarCliente(long id, ClienteCreateDTO dto) {
        Cliente cliente = findCliente(id);
        BeanUtils.copyProperties(dto, cliente, "id");
        clienteRepository.save(cliente);
        return ClienteResponseDTO.fromEntity(cliente);
    }

    public ClienteResponseDTO atualizarCliente(ClientePatchDTO dto) {
        Cliente cliente = findCliente(dto.getId());

        if (dto.getNome() != null) cliente.setNome(dto.getNome());
        if (dto.getSobrenome() != null) cliente.setSobrenome(dto.getSobrenome());
        if (dto.getDataNascimento() != null) cliente.setDataNascimento(dto.getDataNascimento());
        if (dto.getGenero() != null) cliente.setGenero(dto.getGenero());
        if (dto.getEmail() != null) cliente.setEmail(dto.getEmail());

        clienteRepository.save(cliente);

        return ClienteResponseDTO.fromEntity(cliente);
    }

    public ClienteResponseDTO alterarSenha(ClienteSenhaDTO dto) {
        if(!dto.getNovaSenha().equals(dto.getConfirmaSenha())) throw new ClienteException("Senhas são diferentes");
        Cliente cliente = findCliente(dto.getId());
        cliente.setSenha(dto.getNovaSenha());
        clienteRepository.save(cliente);
        return ClienteResponseDTO.fromEntity(cliente);
    }

    public List<ClienteResponseDTO> listarClientes() {
        return clienteRepository.findAll().stream().map(c->
                new ClienteResponseDTO(
                        c.getId(),
                        c.getNome(),
                        c.getSobrenome(),
                        c.getCpf(),
                        c.getEmail(),
                        c.isStatus()
                )).toList();
    }

    public ClienteResponseDTO buscarPorId(Long id) {
        return clienteRepository.findById(id).map(c->
                new ClienteResponseDTO(
                        c.getId(),
                        c.getNome(),
                        c.getSobrenome(),
                        c.getCpf(),
                        c.getEmail(),
                        c.isStatus()
                )).orElseThrow(() -> new ClienteException("Cliente não encontrado"));
    }

    public Cliente detalhesPorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new ClienteException("Cliente não encontrado"));
    }

    public ClienteResponseDTO alterarStatus(ClienteStatusDTO dto) {
        Cliente cliente = findCliente(dto.getId());
        cliente.setStatus(dto.isStatus());
        clienteRepository.save(cliente);
        return ClienteResponseDTO.fromEntity(cliente);
    }

    public Cliente findCliente(Long clienteId) {
        return clienteRepository.findById(clienteId).orElseThrow(() -> new ClienteException("Cliente não encontrado"));
    }
}
