package br.com.CidConSP.CidConSP.Service;

import br.com.CidConSP.CidConSP.Entity.Ocorrencia;
import br.com.CidConSP.CidConSP.Entity.StatusOcorrencia;

import java.util.List;

public interface StatusOcorrenciaService
{
    StatusOcorrencia salvarStatus(StatusOcorrencia statusOcorrencia);
    StatusOcorrencia buscarPorId(Integer id);
    StatusOcorrencia buscarPorDescricao(String descricao);
    List<StatusOcorrencia> listarStatus();
    void deletarStatus(Integer id);
    List<Ocorrencia> buscarPorUsuarioId( Integer usuarioId );
}