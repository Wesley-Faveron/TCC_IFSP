package br.com.CidConSP.CidConSP.Repository;

import br.com.CidConSP.CidConSP.Entity.OrgaoPublico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrgaoPublicoRepository extends JpaRepository<OrgaoPublico, Integer>
{
    OrgaoPublico findByNome(String nome);
    List<OrgaoPublico> findByApiEndpoint(String apiEndpoint);
}
