package br.com.CidConSP.CidConSP.Repository;

import br.com.CidConSP.CidConSP.Entity.Midia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MidiaRepository extends JpaRepository<Midia, Integer>
{
    List<Midia> findByOcorrenciaId(Integer ocorrenciaId);
    List<Midia> findByTipo(String tipo);
}
