package br.com.CidConSP.CidConSP.Controller;

import br.com.CidConSP.CidConSP.Entity.Usuario;
import br.com.CidConSP.CidConSP.Service.UsuarioService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController
{

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/cadastro")
    public String exibirFormularioCadastro(Model model)
    {
        model.addAttribute("usuario", new Usuario());
    
        return "CadastroUsuario";
    }

    @PostMapping("/cadastro")
    public String cadastrarUsuario(
            @ModelAttribute Usuario usuario,
            RedirectAttributes redirectAttributes)
    {
        try
        {
            usuarioService.salvarUsuario(usuario);
            redirectAttributes.addFlashAttribute("flashMessage", "Usuário cadastrado com sucesso!");
            
            return "redirect:/usuarios/login";
        }
        catch (Exception e)
        {
            redirectAttributes.addFlashAttribute("flashMessage", "Erro ao cadastrar o usuário: " + e.getMessage());
            
            return "redirect:/usuarios/cadastro";
        }
    }

    @GetMapping("/login")
    public String exibirFormularioLogin()
    {
        return "Login";
    }

    @RequestMapping("/login")
    public String autenticarUsuario(
            @RequestParam String email,
            @RequestParam String senha,
            HttpSession session,
            RedirectAttributes redirectAttributes)
    {
        Optional<Usuario> usuario = usuarioService.buscarUsuarioPorEmail(email);

        if (usuario.isPresent() && usuario.get().getSenha().equals(senha))
        {
            session.setAttribute("idUsuario", usuario.get().getId());
            
            redirectAttributes.addFlashAttribute("flashMessage", "Login bem-sucedido!");
            
            return "redirect:/ocorrencias/index";
        }
        else
        {
            redirectAttributes.addFlashAttribute("flashMessage", "Credenciais inválidas!");
         
            return "redirect:/usuarios/login";
        }
    }

    @GetMapping("/sobre")
    public String exibirPaginaSobre()
    {
        return "redirect:/sobre";
    }
}
