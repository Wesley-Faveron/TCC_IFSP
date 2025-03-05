package br.com.CidConSP.CidConSP.Service;

import br.com.CidConSP.CidConSP.Entity.Categoria;
import java.util.List;

public interface CategoriaService {
    Categoria salvarCategoria(Categoria categoria);
    Categoria buscarPorId(Integer id);
    Categoria buscarPorNome(String nome);
    List<Categoria> listarCategorias();
    void deletarCategoria(Integer id);
}