package com.ecobook.controller;

import com.ecobook.dto.*;
import com.ecobook.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteResponseDTO> cadastrar(@Valid @RequestBody ClienteCreateDTO dto) {
        ClienteResponseDTO cliente = clienteService.cadastrar(dto);
        return ResponseEntity
                .created(URI.create("/cliente/" + cliente.id()))
                .body(cliente);
    }

    @PatchMapping("/atualizar")
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(@Valid @RequestBody ClientePatchDTO dto) {
        return ResponseEntity.ok(clienteService.atualizarCliente(dto));
    }

    @PatchMapping("/senha")
    public ResponseEntity<ClienteResponseDTO> alterarSenha(@Valid @RequestBody ClienteSenhaDTO dto) {
        return ResponseEntity.ok(clienteService.alterarSenha(dto));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ClienteResponseDTO>> listarClientes() {
        return ResponseEntity.ok(clienteService.listarClientes());
    }

//    @GetMapping("/buscar/{id}")
//    public ResponseEntity<Cliente> buscarPorId(@PathVariable long id) {
//        return ResponseEntity.ok(clienteService.buscarPorId(id));
//    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @PatchMapping("/status")
    public ResponseEntity<ClienteResponseDTO> alterarStatus(@Valid @RequestBody ClienteStatusDTO dto) {
        return ResponseEntity.ok(clienteService.alterarStatus(dto));
    }
}