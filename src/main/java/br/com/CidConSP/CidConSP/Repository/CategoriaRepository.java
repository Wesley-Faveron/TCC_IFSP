package br.com.CidConSP.CidConSP.Repository;

import br.com.CidConSP.CidConSP.Entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>
{
    Categoria findByNome(String nome);
}