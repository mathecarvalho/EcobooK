package com.ecobook.controller;

import com.ecobook.dto.ClienteRequestDTO;
import com.ecobook.dto.ClienteResponseDTO;
import com.ecobook.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criarCliente(@Valid @RequestBody ClienteRequestDTO dto) {
        ClienteResponseDTO cliente = clienteService.criarCliente(dto);
        return ResponseEntity
                .created(URI.create("/clientes/" + cliente.id()))
                .body(cliente);
    }

    @GetMapping("/teste")
    public String teste() {
        return "teste";
    }
}