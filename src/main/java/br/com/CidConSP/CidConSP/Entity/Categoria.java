package br.com.CidConSP.CidConSP.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "Categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Categoria")
    private Integer id;

    @Column(name = "Nome", nullable = false, unique = true)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "Id_Orgao_Publico", nullable = false)
    private OrgaoPublico orgaoPublico;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Ocorrencia> ocorrencias;
}
