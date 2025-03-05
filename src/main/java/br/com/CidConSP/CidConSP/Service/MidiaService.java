package br.com.CidConSP.CidConSP.Service;

import br.com.CidConSP.CidConSP.Entity.Midia;

import java.util.List;

public interface MidiaService
{
    Midia salvarMidia(Midia midia);
    Midia buscarPorId(Integer id);
    List<Midia> buscarPorOcorrenciaId(Integer ocorrenciaId);
    List<Midia> buscarPorTipo(String tipo);
    List<Midia> listarMidias();
    void deletarMidia(Integer id);
}
