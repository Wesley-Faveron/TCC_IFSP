package br.com.CidConSP.CidConSP.Service.Impl;

import br.com.CidConSP.CidConSP.Entity.Usuario;
import br.com.CidConSP.CidConSP.Repository.UsuarioRepository;
import br.com.CidConSP.CidConSP.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService
{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario salvarUsuario(Usuario usuario)
    {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorId(Integer id)
    {
        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorEmail(String email)
    {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorCpf(String cpf)
    {
        return usuarioRepository.findByCpf(cpf);
    }

    @Override
    public List<Usuario> listarUsuarios()
    {
        return usuarioRepository.findAll();
    }

    @Override
    public void deletarUsuario(Integer id)
    {
        if (usuarioRepository.existsById(id))
        {
            usuarioRepository.deleteById(id);
        }
        else
        {
            throw new RuntimeException("Usuário com ID " + id + " não encontrado.");
        }
    }
}
