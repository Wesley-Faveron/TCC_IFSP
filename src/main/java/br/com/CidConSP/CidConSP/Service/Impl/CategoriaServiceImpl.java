package br.com.CidConSP.CidConSP.Service.Impl;

import br.com.CidConSP.CidConSP.Entity.Categoria;
import br.com.CidConSP.CidConSP.Repository.CategoriaRepository;
import br.com.CidConSP.CidConSP.Service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService
{

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Categoria salvarCategoria(Categoria categoria)
    {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria buscarPorId(Integer id)
    {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + id));
    }

    @Override
    public Categoria buscarPorNome(String nome)
    {
        Categoria categoria = categoriaRepository.findByNome(nome);
        
        if (categoria != null)
        {
            return categoria;
        }
        throw new RuntimeException("Categoria não encontrada com o nome: " + nome);
    }

    @Override
    public List<Categoria> listarCategorias()
    {
        return categoriaRepository.findAll();
    }

    @Override
    public void deletarCategoria(Integer id)
    {
        if (categoriaRepository.existsById(id))
        {
            categoriaRepository.deleteById(id);
        }
        else
        {
            throw new RuntimeException("Categoria não encontrada com o ID: " + id);
        }
    }
}