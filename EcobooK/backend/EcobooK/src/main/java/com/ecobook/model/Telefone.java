package com.ecobook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Telefone {

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false, length = 2)
    private String ddd;

    @Column(nullable = false)
    private String numero;
}
