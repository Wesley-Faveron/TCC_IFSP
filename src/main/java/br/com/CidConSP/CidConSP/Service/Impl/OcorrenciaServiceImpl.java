package br.com.CidConSP.CidConSP.Service.Impl;

import br.com.CidConSP.CidConSP.Entity.Categoria;
import br.com.CidConSP.CidConSP.Entity.Ocorrencia;
import br.com.CidConSP.CidConSP.Repository.OcorrenciaRepository;
import br.com.CidConSP.CidConSP.Service.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OcorrenciaServiceImpl implements OcorrenciaService
{

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Override
    public Ocorrencia salvarOcorrencia(Ocorrencia ocorrencia)
    {
        if (ocorrencia.getDataCriacao() == null)
        {
            ocorrencia.setDataCriacao(LocalDateTime.now());
        }
        
        return ocorrenciaRepository.save(ocorrencia);
    }

    @Override
    public Optional<Ocorrencia> buscarPorId(Integer id)
    {
        Optional<Ocorrencia> ocorrencia = ocorrenciaRepository.findById(id);
        
        if (ocorrencia.isPresent())
        {
            return ocorrenciaRepository.findById(id);
        }
        throw new RuntimeException("Ocorrência não encontrada com o ID: " + id);
    }

    @Override
    public Ocorrencia buscarPorProtocolo(String protocolo)
    {
        Ocorrencia ocorrencia = ocorrenciaRepository.findByProtocolo(protocolo);
        
        if (ocorrencia != null)
        {
            return ocorrencia;
        }
        throw new RuntimeException("Ocorrência não encontrada com o protocolo: " + protocolo);
    }

    @Override
    public List<Ocorrencia> buscarPorCategoria(Categoria categoria)
    {
        return ocorrenciaRepository.findByCategoria(categoria);
    }

    @Override
    public List<Ocorrencia> buscarPorUsuarioId(Integer usuarioId)
    {
        return ocorrenciaRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<Ocorrencia> listarOcorrencias()
    {
        return ocorrenciaRepository.findAll();
    }

    @Override
    public void deletarOcorrencia(Integer id)
    {
        if (ocorrenciaRepository.existsById(id))
        {
            ocorrenciaRepository.deleteById(id);
        }
        else
        {
            throw new RuntimeException("Ocorrência não encontrada com o ID: " + id);
        }
    }
}