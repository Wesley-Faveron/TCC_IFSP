package br.com.CidConSP.CidConSP.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Midia")
public class Midia
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Midia")
    private Integer id;

    @Column(name = "Caminho_Arquivo", nullable = false, length = 2000)
    private String caminhoArquivo;

    @Column(name = "Tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "Data_Upload", nullable = false)
    private LocalDateTime dataUpload = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "Id_Ocorrencia", nullable = false)
    private Ocorrencia ocorrencia;
}
