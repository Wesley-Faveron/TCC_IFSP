package br.com.CidConSP.CidConSP.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Orgao_Publico")
public class OrgaoPublico
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Orgao_Publico")
    private Integer id;

    @Column(name = "Api_Endpoint", nullable = false)
    private String apiEndpoint;

    @Column(name = "Contato")
    private String contato;

    @Column(name = "Nome", nullable = false)
    private String nome;
}