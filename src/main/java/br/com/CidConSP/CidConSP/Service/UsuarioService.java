package br.com.CidConSP.CidConSP.Service;

import br.com.CidConSP.CidConSP.Entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService
{
    Usuario salvarUsuario(Usuario usuario);
    Optional<Usuario> buscarUsuarioPorId(Integer id);
    Optional<Usuario> buscarUsuarioPorEmail(String email);
    Optional<Usuario> buscarUsuarioPorCpf(String cpf);
    List<Usuario> listarUsuarios();
    void deletarUsuario(Integer id);
}
