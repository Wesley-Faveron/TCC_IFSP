package br.com.CidConSP.CidConSP.Service.Impl;

import br.com.CidConSP.CidConSP.Entity.Midia;
import br.com.CidConSP.CidConSP.Repository.MidiaRepository;
import br.com.CidConSP.CidConSP.Service.MidiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MidiaServiceImpl implements MidiaService
{

    @Autowired
    private MidiaRepository midiaRepository;

    @Override
    public Midia salvarMidia(Midia midia)
    {
        return midiaRepository.save(midia);
    }

    @Override
    public Midia buscarPorId(Integer id)
    {
        Optional<Midia> midia = midiaRepository.findById(id);
        
        if (midia.isPresent())
        {
            return midia.get();
        }
        throw new RuntimeException("Mídia não encontrada com o ID: " + id);
    }

    @Override
    public List<Midia> buscarPorOcorrenciaId(Integer ocorrenciaId)
    {
        return midiaRepository.findByOcorrenciaId(ocorrenciaId);
    }

    @Override
    public List<Midia> buscarPorTipo(String tipo)
    {
        return midiaRepository.findByTipo(tipo);
    }

    @Override
    public List<Midia> listarMidias()
    {
        return midiaRepository.findAll();
    }

    @Override
    public void deletarMidia(Integer id)
    {
        if (midiaRepository.existsById(id))
        {
            midiaRepository.deleteById(id);
        }
        else
        {
            throw new RuntimeException("Mídia não encontrada com o ID: " + id);
        }
    }
}
