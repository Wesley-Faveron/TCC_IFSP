package br.com.CidConSP.CidConSP.Repository;

import br.com.CidConSP.CidConSP.Entity.Categoria;
import br.com.CidConSP.CidConSP.Entity.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Integer>
{
    List<Ocorrencia> findByCategoria(Categoria categoria);
    Ocorrencia findByProtocolo(String protocolo);
    List<Ocorrencia> findByUsuarioId(Integer usuarioId);
}
