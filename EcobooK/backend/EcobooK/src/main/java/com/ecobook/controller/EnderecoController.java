package com.ecobook.controller;

import com.ecobook.dto.EnderecoAddDTO;
import com.ecobook.dto.EnderecoDeleteDTO;
import com.ecobook.dto.EnderecoResponseDTO;
import com.ecobook.dto.EnderecoUpdateDTO;
import com.ecobook.service.EnderecoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/endereco")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<EnderecoResponseDTO> adicionar(@Valid @RequestBody EnderecoAddDTO dto) {
        return ResponseEntity.ok(enderecoService.adicionar(dto));
    }

    @PatchMapping
    public ResponseEntity<EnderecoResponseDTO> atualizar(@Valid @RequestBody EnderecoUpdateDTO dto) {
        return ResponseEntity.ok(enderecoService.atualizar(dto));
    }

    @DeleteMapping
    public ResponseEntity<EnderecoResponseDTO> deletar(@Valid @RequestBody EnderecoDeleteDTO dto) {
        enderecoService.deletar(dto);
        return ResponseEntity.noContent().build();
    }
}
