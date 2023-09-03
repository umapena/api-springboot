package br.edu.senai.sc.web.springboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long quarto;
    private LocalDateTime inicio;
    private LocalDateTime termino;
    @ManyToOne
    @JoinColumn(name = "cliente")
    private Cliente cliente;
    private LocalDateTime saida;
}
