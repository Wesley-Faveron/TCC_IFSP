package br.com.CidConSP.CidConSP.Service.Impl;

import br.com.CidConSP.CidConSP.Entity.Ocorrencia;
import br.com.CidConSP.CidConSP.Entity.StatusOcorrencia;
import br.com.CidConSP.CidConSP.Repository.OcorrenciaRepository;
import br.com.CidConSP.CidConSP.Repository.StatusOcorrenciaRepository;
import br.com.CidConSP.CidConSP.Service.StatusOcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusOcorrenciaServiceImpl implements StatusOcorrenciaService
{

    @Autowired
    private StatusOcorrenciaRepository statusOcorrenciaRepository;
    
    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Override
    public StatusOcorrencia salvarStatus(StatusOcorrencia statusOcorrencia)
    {
        return statusOcorrenciaRepository.save(statusOcorrencia);
    }

    @Override
    public StatusOcorrencia buscarPorId(Integer id)
    {
        Optional<StatusOcorrencia> status = statusOcorrenciaRepository.findById(id);
        
        if (status.isPresent())
        {
            return status.get();
        }
        throw new RuntimeException("Status de ocorrência não encontrado com o ID: " + id);
    }

    @Override
    public StatusOcorrencia buscarPorDescricao(String descricao)
    {
        StatusOcorrencia status = statusOcorrenciaRepository.findByDescricao(descricao);
        
        if (status != null)
        {
            return status;
        }
        throw new RuntimeException("Status de ocorrência não encontrado com a descrição: " + descricao);
    }

    @Override
    public List<StatusOcorrencia> listarStatus()
    {
        return statusOcorrenciaRepository.findAll();
    }

    @Override
    public void deletarStatus(Integer id)
    {
        if (statusOcorrenciaRepository.existsById(id))
        {
            statusOcorrenciaRepository.deleteById(id);
        }
        else
        {
            throw new RuntimeException("Status de ocorrência não encontrado com o ID: " + id);
        }
    }
    
    @Override
    public List<Ocorrencia> buscarPorUsuarioId(Integer usuarioId)
    {
        return ocorrenciaRepository.findByUsuarioId(usuarioId);
    }
}