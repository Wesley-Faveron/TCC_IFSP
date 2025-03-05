package br.com.CidConSP.CidConSP.Service;

import br.com.CidConSP.CidConSP.Entity.OrgaoPublico;

import java.util.List;

public interface OrgaoPublicoService
{
    OrgaoPublico salvarOrgaoPublico(OrgaoPublico orgaoPublico);
    OrgaoPublico buscarPorId(Integer id);
    OrgaoPublico buscarPorNome(String nome);
    List<OrgaoPublico> buscarPorApiEndpoint(String apiEndpoint);
    List<OrgaoPublico> listarOrgaosPublicos();
    void deletarOrgaoPublico(Integer id);
}
