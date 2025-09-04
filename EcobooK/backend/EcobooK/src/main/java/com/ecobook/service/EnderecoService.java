package com.ecobook.service;

import com.ecobook.dto.EnderecoAddDTO;
import com.ecobook.dto.EnderecoDeleteDTO;
import com.ecobook.dto.EnderecoResponseDTO;
import com.ecobook.dto.EnderecoUpdateDTO;
import com.ecobook.exception.ClienteException;
import com.ecobook.model.Cliente;
import com.ecobook.model.Endereco;
import com.ecobook.repository.EnderecoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ClienteService clienteService;

    public EnderecoResponseDTO adicionar(EnderecoAddDTO dto) {
        Cliente cliente = clienteService.findCliente(dto.getClienteId());
        Endereco endereco = Endereco.builder()
                .nome(dto.getNome())
                .tipoResidencia(dto.getTipoResidencia())
                .tipoLogradouro(dto.getTipoLogradouro())
                .logradouro(dto.getLogradouro())
                .numero(dto.getNumero())
                .bairro(dto.getBairro())
                .cep(dto.getCep())
                .cidade(dto.getCidade())
                .estado(dto.getEstado())
                .pais(dto.getPais())
                .observacoes(dto.getObservacoes())
                .cliente(cliente)
                .build();

        enderecoRepository.save(endereco);
        return EnderecoResponseDTO.fromEntity(endereco);
    }

    public EnderecoResponseDTO atualizar(EnderecoUpdateDTO dto) {
        Endereco endereco = enderecoRepository.findById(dto.getId()).orElseThrow(() -> new ClienteException("Endereço não encontrado"));

        if (dto.getNome() != null) endereco.setNome(dto.getNome());
        if (dto.getTipoResidencia() != null) endereco.setTipoResidencia(dto.getTipoResidencia());
        if (dto.getTipoLogradouro() != null) endereco.setTipoLogradouro(dto.getTipoLogradouro());
        if (dto.getLogradouro() != null) endereco.setLogradouro(dto.getLogradouro());
        if (dto.getNumero() != null) endereco.setNumero(dto.getNumero());
        if (dto.getBairro() != null) endereco.setBairro(dto.getBairro());
        if (dto.getCep() != null) endereco.setCep(dto.getCep());
        if (dto.getCidade() != null) endereco.setCidade(dto.getCidade());
        if (dto.getEstado() != null) endereco.setEstado(dto.getEstado());
        if (dto.getPais() != null) endereco.setPais(dto.getPais());
        if (dto.getObservacoes() != null) endereco.setObservacoes(dto.getObservacoes());

        enderecoRepository.save(endereco);

        return EnderecoResponseDTO.fromEntity(endereco);
    }

    public void deletar(EnderecoDeleteDTO dto) {
        Cliente cliente = clienteService.findCliente(dto.getClienteId());

        Endereco endereco = cliente.getEnderecos().stream()
                .filter(e -> e.getId().equals(dto.getId()))
                .findFirst()
                .orElseThrow(() -> new ClienteException("Endereço não encontrado"));
        cliente.getEnderecos().remove(endereco);
        enderecoRepository.delete(endereco);
    }
}
