package com.ecobook.model;

import com.ecobook.enumeration.Bandeira;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cartoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String numero;

    @Column(name = "nome_impresso", nullable = false)
    private String nomeImpresso;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Bandeira bandeira;

    @Column(name = "codigo_seguranca", nullable = false)
    private String codigoSeguranca;

    @Column(name = "validade", nullable = false)
    private String validade;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
}
