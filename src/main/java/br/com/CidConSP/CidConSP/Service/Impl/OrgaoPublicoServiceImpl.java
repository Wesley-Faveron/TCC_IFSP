package br.com.CidConSP.CidConSP.Service.Impl;

import br.com.CidConSP.CidConSP.Entity.OrgaoPublico;
import br.com.CidConSP.CidConSP.Repository.OrgaoPublicoRepository;
import br.com.CidConSP.CidConSP.Service.OrgaoPublicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrgaoPublicoServiceImpl implements OrgaoPublicoService
{

    @Autowired
    private OrgaoPublicoRepository orgaoPublicoRepository;

    @Override
    public OrgaoPublico salvarOrgaoPublico(OrgaoPublico orgaoPublico)
    {
        return orgaoPublicoRepository.save(orgaoPublico);
    }

    @Override
    public OrgaoPublico buscarPorId(Integer id)
    {
        Optional<OrgaoPublico> orgaoPublico = orgaoPublicoRepository.findById(id);
        
        if (orgaoPublico.isPresent())
        {
            return orgaoPublico.get();
        }
        throw new RuntimeException("Órgão público não encontrado com o ID: " + id);
    }

    @Override
    public OrgaoPublico buscarPorNome(String nome)
    {
        OrgaoPublico orgaoPublico = orgaoPublicoRepository.findByNome(nome);
        
        if (orgaoPublico != null)
        {
            return orgaoPublico;
        }
        throw new RuntimeException("Órgão público não encontrado com o nome: " + nome);
    }

    @Override
    public List<OrgaoPublico> buscarPorApiEndpoint(String apiEndpoint)
    {
        return orgaoPublicoRepository.findByApiEndpoint(apiEndpoint);
    }

    @Override
    public List<OrgaoPublico> listarOrgaosPublicos()
    {
        return orgaoPublicoRepository.findAll();
    }

    @Override
    public void deletarOrgaoPublico(Integer id)
    {
        if (orgaoPublicoRepository.existsById(id))
        {
            orgaoPublicoRepository.deleteById(id);
        }
        else
        {
            throw new RuntimeException("Órgão público não encontrado com o ID: " + id);
        }
    }
}
