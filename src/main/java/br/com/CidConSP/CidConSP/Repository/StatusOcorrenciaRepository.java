package br.com.CidConSP.CidConSP.Repository;

import br.com.CidConSP.CidConSP.Entity.StatusOcorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusOcorrenciaRepository extends JpaRepository<StatusOcorrencia, Integer>
{
    StatusOcorrencia findByDescricao(String descricao);
}
