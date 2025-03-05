package br.com.CidConSP.CidConSP.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Status_Ocorrencia")
public class StatusOcorrencia
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Status")
    private Integer id;

    @Column(name = "Descricao", nullable = false, unique = true)
    private String descricao;
}