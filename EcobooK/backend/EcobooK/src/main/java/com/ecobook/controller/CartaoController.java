package com.ecobook.controller;

import com.ecobook.dto.CartaoAddDTO;
import com.ecobook.dto.CartaoDeleteDTO;
import com.ecobook.dto.CartaoResponseDTO;
import com.ecobook.dto.CartaoUpdateDTO;
import com.ecobook.service.CartaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartao")
@RequiredArgsConstructor
public class CartaoController {

    private final CartaoService cartaoService;

    @PostMapping
    public ResponseEntity<CartaoResponseDTO> adicionar(@Valid @RequestBody CartaoAddDTO dto) {
        return ResponseEntity.ok(cartaoService.adicionar(dto));
    }

    @PutMapping
    public ResponseEntity<CartaoResponseDTO> atualizar(@Valid @RequestBody CartaoUpdateDTO dto) {
        return ResponseEntity.ok(cartaoService.atualizar(dto));
    }

    @DeleteMapping
    public ResponseEntity<CartaoResponseDTO> deletar(@Valid @RequestBody CartaoDeleteDTO dto) {
        cartaoService.deletar(dto);
        return ResponseEntity.noContent().build();
    }
}
