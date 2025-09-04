package com.ecobook.service;

import com.ecobook.dto.CartaoAddDTO;
import com.ecobook.dto.CartaoDeleteDTO;
import com.ecobook.dto.CartaoResponseDTO;
import com.ecobook.dto.CartaoUpdateDTO;
import com.ecobook.exception.ClienteException;
import com.ecobook.model.Cartao;
import com.ecobook.model.Cliente;
import com.ecobook.repository.CartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoRepository cartaoRepository;
    private final ClienteService clienteService;

    public CartaoResponseDTO adicionar(CartaoAddDTO dto) {
        Cliente cliente = clienteService.findCliente(dto.getClienteId());

        Cartao cartao = Cartao.builder()
                .numero(dto.getNumero())
                .nomeImpresso(dto.getNomeImpresso())
                .bandeira(dto.getBandeira())
                .codigoSeguranca(dto.getCodigoSeguranca())
                .validade(dto.getValidade())
                .cliente(cliente)
                .build();

        cartaoRepository.save(cartao);
        return CartaoResponseDTO.fromEntity(cartao);
    }

    public CartaoResponseDTO atualizar(CartaoUpdateDTO dto) {
        Cartao cartao = cartaoRepository.findById(dto.getId()).orElseThrow(() -> new ClienteException("Cartão não encontrado"));

        cartao.setNumero(dto.getNumero());
        cartao.setNomeImpresso(dto.getNomeImpresso());
        cartao.setBandeira(dto.getBandeira());
        cartao.setCodigoSeguranca(dto.getCodigoSeguranca());
        cartao.setValidade(dto.getValidade());

        cartaoRepository.save(cartao);

        return CartaoResponseDTO.fromEntity(cartao);
    }

    public void deletar(CartaoDeleteDTO dto) {
        Cliente cliente = clienteService.findCliente(dto.getClienteId());

        Cartao cartao = cliente.getCartoes().stream()
                .filter(c -> c.getId().equals(dto.getId()))
                .findFirst()
                .orElseThrow(() -> new ClienteException("Cartão não encontrado"));

        cliente.getCartoes().remove(cartao);
        cartaoRepository.delete(cartao);
    }
}
