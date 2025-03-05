package br.com.CidConSP.CidConSP.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Usuario")
public class Usuario
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Usuario")
    private Integer id;

    @Column(name = "CPF", nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "Nome", nullable = false)
    private String nome;

    @Column(name = "Senha", nullable = false)
    private String senha;

    @Column(name = "Data_Criacao", nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Column(name = "Status_Usuario", nullable = false)
    private String statusUsuario = "Ativo";
}