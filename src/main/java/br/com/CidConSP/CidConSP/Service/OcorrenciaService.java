package br.com.CidConSP.CidConSP.Service;

import br.com.CidConSP.CidConSP.Entity.Categoria;
import br.com.CidConSP.CidConSP.Entity.Ocorrencia;

import java.util.List;
import java.util.Optional;

public interface OcorrenciaService {
    Ocorrencia salvarOcorrencia(Ocorrencia ocorrencia);
    Optional<Ocorrencia> buscarPorId(Integer id);
    Ocorrencia buscarPorProtocolo(String protocolo);
    List<Ocorrencia> buscarPorCategoria(Categoria categoria);
    List<Ocorrencia> buscarPorUsuarioId(Integer usuarioId);
    List<Ocorrencia> listarOcorrencias();
    void deletarOcorrencia(Integer id);
}