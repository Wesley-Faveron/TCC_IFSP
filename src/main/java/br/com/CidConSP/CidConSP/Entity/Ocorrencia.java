package br.com.CidConSP.CidConSP.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Ocorrencia")
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Ocorrencia")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Id_Categoria", nullable = false)
    private Categoria categoria;

    @Column(name = "Descricao", nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "Protocolo", unique = true)
    private String protocolo;
    
    @Column(name = "Data_Criacao", nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "Id_Status", nullable = false)
    private StatusOcorrencia status;

    @ManyToOne
    @JoinColumn(name = "Id_Usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "Id_Orgao_Publico", nullable = false)
    private OrgaoPublico orgaoPublico;
    
    @OneToMany(mappedBy = "ocorrencia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Midia> midia = new ArrayList<>();
}